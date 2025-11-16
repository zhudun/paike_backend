-- 智能排课系统数据库初始化脚本
-- 数据库名称: course_scheduling

-- 创建数据库
CREATE DATABASE IF NOT EXISTS course_scheduling DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE course_scheduling;

-- 用户表
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码（加密）',
    name VARCHAR(100) NOT NULL COMMENT '姓名',
    email VARCHAR(100) UNIQUE COMMENT '邮箱',
    role VARCHAR(20) NOT NULL DEFAULT 'user' COMMENT '角色: admin, teacher, student',
    department VARCHAR(100) COMMENT '部门/院系',
    avatar VARCHAR(255) COMMENT '头像URL',
    status VARCHAR(20) DEFAULT 'active' COMMENT '状态: active, inactive, locked',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_username (username),
    INDEX idx_email (email),
    INDEX idx_role (role),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 学期表
CREATE TABLE semesters (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL COMMENT '学期名称',
    code VARCHAR(50) NOT NULL UNIQUE COMMENT '学期代码',
    start_date DATE NOT NULL COMMENT '开始日期',
    end_date DATE NOT NULL COMMENT '结束日期',
    total_weeks INT NOT NULL COMMENT '总周数',
    current_week INT DEFAULT 1 COMMENT '当前周',
    status VARCHAR(20) DEFAULT 'draft' COMMENT '状态: draft, active, inactive',
    description TEXT COMMENT '描述',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_code (code),
    INDEX idx_status (status),
    INDEX idx_date_range (start_date, end_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学期表';

-- 教师表
CREATE TABLE teachers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL COMMENT '关联用户ID',
    employee_id VARCHAR(50) NOT NULL UNIQUE COMMENT '教职工编号',
    name VARCHAR(100) NOT NULL COMMENT '姓名',
    department VARCHAR(100) NOT NULL COMMENT '所属院系',
    title VARCHAR(50) COMMENT '职称',
    email VARCHAR(100) COMMENT '邮箱',
    phone VARCHAR(20) COMMENT '电话',
    max_weekly_hours INT DEFAULT 20 COMMENT '最大周课时',
    skills JSON COMMENT '技能标签JSON数组',
    available_time_slots JSON COMMENT '可用时间段JSON',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    INDEX idx_user_id (user_id),
    INDEX idx_employee_id (employee_id),
    INDEX idx_department (department)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教师表';

-- 班级表
CREATE TABLE classes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL COMMENT '班级名称',
    grade VARCHAR(20) NOT NULL COMMENT '年级',
    major VARCHAR(100) NOT NULL COMMENT '专业',
    department VARCHAR(100) NOT NULL COMMENT '所属院系',
    student_count INT DEFAULT 0 COMMENT '学生人数',
    class_teacher_id BIGINT COMMENT '班主任ID（教师）',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (class_teacher_id) REFERENCES teachers(id) ON DELETE SET NULL,
    INDEX idx_grade (grade),
    INDEX idx_major (major),
    INDEX idx_department (department)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='班级表';

-- 学生表
CREATE TABLE students (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL COMMENT '关联用户ID',
    student_id VARCHAR(50) NOT NULL UNIQUE COMMENT '学号',
    name VARCHAR(100) NOT NULL COMMENT '姓名',
    gender VARCHAR(10) COMMENT '性别: male, female',
    birth_date DATE COMMENT '出生日期',
    class_id BIGINT NOT NULL COMMENT '班级ID',
    email VARCHAR(100) COMMENT '邮箱',
    phone VARCHAR(20) COMMENT '电话',
    parent_name VARCHAR(100) COMMENT '家长姓名',
    parent_phone VARCHAR(20) COMMENT '家长电话',
    address TEXT COMMENT '地址',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES classes(id) ON DELETE CASCADE,
    INDEX idx_user_id (user_id),
    INDEX idx_student_id (student_id),
    INDEX idx_class_id (class_id),
    INDEX idx_gender (gender)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表';

-- 课程表
CREATE TABLE courses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    semester_id BIGINT NOT NULL COMMENT '学期ID',
    teacher_id BIGINT NOT NULL COMMENT '主讲教师ID',
    name VARCHAR(100) NOT NULL COMMENT '课程名称',
    code VARCHAR(50) NOT NULL COMMENT '课程代码',
    credit INT NOT NULL COMMENT '学分',
    course_type VARCHAR(20) NOT NULL COMMENT '课程类型: required, elective, optional',
    weekly_hours INT NOT NULL COMMENT '周课时',
    total_hours INT NOT NULL COMMENT '总课时',
    description TEXT COMMENT '课程描述',
    prerequisites JSON COMMENT '先修课程JSON数组',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (semester_id) REFERENCES semesters(id) ON DELETE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES teachers(id) ON DELETE CASCADE,
    INDEX idx_semester_id (semester_id),
    INDEX idx_teacher_id (teacher_id),
    INDEX idx_code (code),
    INDEX idx_course_type (course_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表';

-- 教室表
CREATE TABLE classrooms (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL COMMENT '教室名称',
    building VARCHAR(100) NOT NULL COMMENT '教学楼',
    capacity INT NOT NULL COMMENT '容量',
    room_type VARCHAR(50) NOT NULL COMMENT '教室类型: classroom, laboratory, lecture_hall, meeting_room',
    floor INT NOT NULL COMMENT '楼层',
    equipment JSON COMMENT '设备JSON',
    description TEXT COMMENT '描述',
    available_time_slots JSON COMMENT '可用时间段JSON',
    status VARCHAR(20) DEFAULT 'available' COMMENT '状态: available, maintenance, closed',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_building (building),
    INDEX idx_room_type (room_type),
    INDEX idx_status (status),
    INDEX idx_capacity (capacity)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教室表';

-- 排课表（核心表）
CREATE TABLE schedules (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    semester_id BIGINT NOT NULL COMMENT '学期ID',
    course_id BIGINT NOT NULL COMMENT '课程ID',
    teacher_id BIGINT NOT NULL COMMENT '教师ID',
    class_id BIGINT NOT NULL COMMENT '班级ID',
    classroom_id BIGINT NOT NULL COMMENT '教室ID',
    week_day INT NOT NULL COMMENT '星期几: 1-7',
    time_slot INT NOT NULL COMMENT '时间段: 1-12',
    weeks VARCHAR(100) NOT NULL COMMENT '周次范围，如: 1-16',
    status VARCHAR(20) DEFAULT 'scheduled' COMMENT '状态: scheduled, adjusted, cancelled',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (semester_id) REFERENCES semesters(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES teachers(id) ON DELETE CASCADE,
    FOREIGN KEY (class_id) REFERENCES classes(id) ON DELETE CASCADE,
    FOREIGN KEY (classroom_id) REFERENCES classrooms(id) ON DELETE CASCADE,
    INDEX idx_semester_id (semester_id),
    INDEX idx_course_id (course_id),
    INDEX idx_teacher_id (teacher_id),
    INDEX idx_class_id (class_id),
    INDEX idx_classroom_id (classroom_id),
    INDEX idx_week_day (week_day),
    INDEX idx_time_slot (time_slot),
    INDEX idx_status (status),
    UNIQUE KEY uk_schedule_unique (semester_id, teacher_id, class_id, week_day, time_slot, weeks)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='排课表';

-- 教师不可用时间表
CREATE TABLE teacher_unavailable (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    teacher_id BIGINT NOT NULL COMMENT '教师ID',
    semester_id BIGINT NOT NULL COMMENT '学期ID',
    week_day INT NOT NULL COMMENT '星期几',
    time_slot INT NOT NULL COMMENT '时间段',
    weeks VARCHAR(100) NOT NULL COMMENT '周次范围',
    reason VARCHAR(255) COMMENT '原因',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (teacher_id) REFERENCES teachers(id) ON DELETE CASCADE,
    FOREIGN KEY (semester_id) REFERENCES semesters(id) ON DELETE CASCADE,
    INDEX idx_teacher_id (teacher_id),
    INDEX idx_semester_id (semester_id),
    INDEX idx_week_day (week_day),
    INDEX idx_time_slot (time_slot)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教师不可用时间表';

-- 教室不可用时间表
CREATE TABLE classroom_unavailable (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    classroom_id BIGINT NOT NULL COMMENT '教室ID',
    semester_id BIGINT NOT NULL COMMENT '学期ID',
    week_day INT NOT NULL COMMENT '星期几',
    time_slot INT NOT NULL COMMENT '时间段',
    weeks VARCHAR(100) NOT NULL COMMENT '周次范围',
    reason VARCHAR(255) COMMENT '原因',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (classroom_id) REFERENCES classrooms(id) ON DELETE CASCADE,
    FOREIGN KEY (semester_id) REFERENCES semesters(id) ON DELETE CASCADE,
    INDEX idx_classroom_id (classroom_id),
    INDEX idx_semester_id (semester_id),
    INDEX idx_week_day (week_day),
    INDEX idx_time_slot (time_slot)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教室不可用时间表';

-- 调课申请表
CREATE TABLE adjustments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    schedule_id BIGINT NOT NULL COMMENT '原排课ID',
    teacher_id BIGINT NOT NULL COMMENT '申请教师ID',
    reason TEXT NOT NULL COMMENT '调课原因',
    target_week_day INT COMMENT '目标星期几',
    target_time_slot INT COMMENT '目标时间段',
    target_classroom_id BIGINT COMMENT '目标教室ID',
    target_weeks VARCHAR(100) COMMENT '目标周次',
    status VARCHAR(20) DEFAULT 'pending' COMMENT '状态: pending, approved, rejected',
    approver_id BIGINT COMMENT '审批人ID',
    approve_reason TEXT COMMENT '审批意见',
    approve_time TIMESTAMP COMMENT '审批时间',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (schedule_id) REFERENCES schedules(id) ON DELETE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES teachers(id) ON DELETE CASCADE,
    FOREIGN KEY (target_classroom_id) REFERENCES classrooms(id) ON DELETE SET NULL,
    FOREIGN KEY (approver_id) REFERENCES teachers(id) ON DELETE SET NULL,
    INDEX idx_schedule_id (schedule_id),
    INDEX idx_teacher_id (teacher_id),
    INDEX idx_status (status),
    INDEX idx_approver_id (approver_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='调课申请表';

-- 通知表
CREATE TABLE notifications (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    sender_id BIGINT NOT NULL COMMENT '发送者ID（用户）',
    recipient_id BIGINT NOT NULL COMMENT '接收者ID（用户）',
    title VARCHAR(200) NOT NULL COMMENT '标题',
    content TEXT NOT NULL COMMENT '内容',
    type VARCHAR(50) NOT NULL COMMENT '类型: system, schedule_notification, course_reminder, grade_notification, emergency',
    priority VARCHAR(20) DEFAULT 'normal' COMMENT '优先级: low, normal, high, urgent',
    status VARCHAR(20) DEFAULT 'unread' COMMENT '状态: unread, read, deleted',
    wechat_sent BOOLEAN DEFAULT FALSE COMMENT '是否已发送微信通知',
    metadata JSON COMMENT '扩展数据JSON',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (recipient_id) REFERENCES users(id) ON DELETE CASCADE,
    INDEX idx_sender_id (sender_id),
    INDEX idx_recipient_id (recipient_id),
    INDEX idx_type (type),
    INDEX idx_priority (priority),
    INDEX idx_status (status),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知表';

-- 插入基础数据
INSERT INTO users (username, password, name, email, role, department) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8i at the end 5d8fYH2n7lF0', '系统管理员', 'admin@example.com', 'admin', '教务处'),
('teacher1', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8i at the end 5d8fYH2n7lF0', '张教授', 'teacher1@example.com', 'teacher', '数学系'),
('teacher2', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8i at the end 5d8fYH2n7lF0', '李教授', 'teacher2@example.com', 'teacher', '物理系');

INSERT INTO semesters (name, code, start_date, end_date, total_weeks, current_week, status) VALUES
('2024年春季学期', '2024-SPRING', '2024-02-26', '2024-07-15', 20, 8, 'active');

INSERT INTO teachers (user_id, employee_id, name, department, title, email, phone, max_weekly_hours) VALUES
(2, 'T001', '张教授', '数学系', '教授', 'teacher1@example.com', '13800138000', 20),
(3, 'T002', '李教授', '物理系', '副教授', 'teacher2@example.com', '13800138001', 18);

INSERT INTO classes (name, grade, major, department, student_count, class_teacher_id) VALUES
('计算机科学与技术1班', '2023级', '计算机科学与技术', '计算机学院', 45, 1),
('软件工程2班', '2023级', '软件工程', '软件学院', 42, 2);

INSERT INTO classrooms (name, building, capacity, room_type, floor, equipment, description) VALUES
('A101', '教学楼A', 60, 'classroom', 1, '{"projector": true, "computer": false, "airConditioner": true}', '多媒体教室'),
('B205', '教学楼B', 80, 'lecture_hall', 2, '{"projector": true, "computer": true, "airConditioner": true}', '大型阶梯教室');

INSERT INTO courses (semester_id, teacher_id, name, code, credit, course_type, weekly_hours, total_hours, description) VALUES
(1, 1, '高等数学', 'MATH101', 4, 'required', 4, 80, '高等数学基础课程'),
(1, 2, '大学物理', 'PHYS101', 3, 'required', 3, 60, '大学物理基础课程');

INSERT INTO schedules (semester_id, course_id, teacher_id, class_id, classroom_id, week_day, time_slot, weeks, status) VALUES
(1, 1, 1, 1, 1, 1, 1, '1-16', 'scheduled'),
(1, 2, 2, 2, 2, 2, 2, '1-16', 'scheduled');

INSERT INTO notifications (sender_id, recipient_id, title, content, type, priority, status) VALUES
(1, 2, '系统维护通知', '系统将于今晚22:00-23:00进行例行维护', 'system', 'normal', 'unread'),
(1, 3, '排课调整提醒', '您的大学物理课程已调整到B205教室', 'schedule_notification', 'high', 'unread');

-- 创建视图（可选）
CREATE VIEW schedule_view AS
SELECT 
    s.id,
    s.semester_id,
    sem.name AS semester_name,
    s.course_id,
    c.name AS course_name,
    c.code AS course_code,
    s.teacher_id,
    t.name AS teacher_name,
    t.department AS teacher_department,
    s.class_id,
    cl.name AS class_name,
    s.classroom_id,
    cr.name AS classroom_name,
    cr.building AS classroom_building,
    s.week_day,
    s.time_slot,
    s.weeks,
    s.status,
    s.created_at,
    s.updated_at
FROM schedules s
JOIN semesters sem ON s.semester_id = sem.id
JOIN courses c ON s.course_id = c.id
JOIN teachers t ON s.teacher_id = t.id
JOIN classes cl ON s.class_id = cl.id
JOIN classrooms cr ON s.classroom_id = cr.id;

-- 添加注释
ALTER TABLE schedule_view COMMENT = '排课视图，包含完整的关联信息';