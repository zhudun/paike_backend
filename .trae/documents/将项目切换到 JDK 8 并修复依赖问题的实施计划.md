## 总体目标
- 将构建与运行环境从 JDK 17 切换到 JDK 8。
- 将 Spring Boot 与 MyBatis 相关依赖降级到与 JDK 8 兼容的版本。
- 修复由于 Jakarta/Javax 迁移导致的“依赖找不到”问题。
- 移除 Java 9+ API 用法（如 `Map.of`），保证源码在 JDK 8 下可编译。

## 兼容性评估
- 当前 `pom.xml` 设定为 Java 17 与 Spring Boot 3.x（`spring-boot.version=3.2.0`，`maven.compiler.source/target=17`）（pom.xml:15–24,129–135）。
- 代码使用 `javax.servlet.*`（如 `JwtAuthenticationFilter.java:10–13`），而 Spring Boot 3 依赖 `jakarta.servlet.*`，这会造成类找不到。
- 多处使用 `Map.of(...)`（Java 9+ API），在 JDK 8 下不可用：
  - `config/GlobalExceptionHandler.java:14,22`
  - `controller/NotificationController.java:54,63,74,82,84,92,94,102,104`
  - `controller/ScheduleController.java:75,86,94,96`
  - `controller/SemesterController.java:48,59,67,69,77,79`
  - `controller/UserController.java:31`
  - `controller/AuthController.java:34,53,65,78,80`
- 安全配置使用 Spring Security 6 的 DSL（`authorizeHttpRequests`/`requestMatchers`，`SecurityConfig.java:33–39`），降级到 Spring Boot 2.7（Spring Security 5）后需改为 `authorizeRequests`/`antMatchers`。

## 修改清单
1. 调整 `pom.xml` 到 JDK 8 与 Boot 2.7：
   - 将 `<maven.compiler.source>` 与 `<maven.compiler.target>` 改为 `1.8`（pom.xml:15–18）。
   - 将 `maven-compiler-plugin` 的 `<source>/<target>` 改为 `1.8`（pom.xml:131–134）。
   - 将 `spring-boot.version` 改为 `2.7.18`（pom.xml:19）。
   - 将 `mybatis-spring-boot.version` 改为 `2.3.2`（与 Spring Boot 2.7/Java 8 兼容）。
   - `spring-boot-maven-plugin` 版本随属性同步为 `2.7.18`（pom.xml:121–125）。
2. 替换 Java 9+ API 用法以兼容 JDK 8：
   - 所有 `Map.of(...)`：
     - 单键值场景替换为 `Collections.singletonMap(key, value)`。
     - 多键值场景使用 `new HashMap<>()` 并逐项 `put`，或构造一个简单的 DTO 返回。
   - 具体位置：见“兼容性评估”中的文件/行号列表。
3. Spring Security DSL 回退（`src/main/java/com/paikesystem/config/SecurityConfig.java`）：
   - 将 `authorizeHttpRequests(authz -> authz.requestMatchers(...).permitAll().anyRequest().authenticated())` 改为：
     - `http.authorizeRequests().antMatchers("/api/auth/**", "/api/users/register", "/error").permitAll().anyRequest().authenticated();`
   - 其余配置保持不变（`cors`/`csrf`/`sessionManagement`/`addFilterBefore` 等）。
4. 依赖缺失问题修复说明：
   - 降级到 Spring Boot 2.7 后，`spring-boot-starter-web` 将恢复对 `javax.servlet.*` 的依赖，`JwtAuthenticationFilter.java:10–13` 的导入不再报错。
   - `spring-boot-starter-validation` 在 Boot 2.7 下为 `javax.validation`，当前代码未直接使用 Jakarta 注解，无需改动。

## 验证步骤
- 环境检查：
  - 安装并设置 `JAVA_HOME` 指向 JDK 1.8，`mvn -version` 确认 Maven 使用 JDK 1.8。
- 构建验证：
  - 执行 `mvn clean package -DskipTests`，确认编译通过且不再出现 `javax.servlet` 类找不到或 `Map.of` 的编译错误。
- 运行与冒烟测试：
  - 启动应用后验证核心接口：
    - `POST /api/auth/register`、`POST /api/auth/login`
    - 通知相关 `GET/POST/DELETE /api/notifications...`
    - 检查 CORS 与鉴权过滤链正常工作（带 `Authorization: Bearer <token>`）。

## 风险与回退
- 风险：Spring Security DSL 差异导致鉴权规则异常；`Map.of` 替换不当影响返回体格式。
- 回退方案：如出现不可接受的问题，将 `pom.xml` 中版本改回原值（Boot 3/Java 17），并恢复 `SecurityConfig` 旧 DSL；逐处对比并修正。

## 交付内容
- 更新后的 `pom.xml`。
- 针对上述文件的最小代码改动（Map.of 替换、SecurityConfig DSL 调整）。
- 验证记录与运行说明。