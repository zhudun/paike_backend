package com.paikesystem.mapper;

import com.paikesystem.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    
    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
        @Result(column = "created_at", property = "createdAt"),
        @Result(column = "updated_at", property = "updatedAt")
    })
    User findById(Long id);
    
    @Select("SELECT * FROM users WHERE username = #{username}")
    @Results({
        @Result(column = "created_at", property = "createdAt"),
        @Result(column = "updated_at", property = "updatedAt")
    })
    User findByUsername(String username);
    
    @Select("SELECT * FROM users WHERE email = #{email}")
    @Results({
        @Result(column = "created_at", property = "createdAt"),
        @Result(column = "updated_at", property = "updatedAt")
    })
    User findByEmail(String email);
    
    @Select("SELECT * FROM users WHERE role = #{role}")
    @Results({
        @Result(column = "created_at", property = "createdAt"),
        @Result(column = "updated_at", property = "updatedAt")
    })
    List<User> findByRole(String role);
    
    @Select("SELECT * FROM users")
    @Results({
        @Result(column = "created_at", property = "createdAt"),
        @Result(column = "updated_at", property = "updatedAt")
    })
    List<User> findAll();
    
    @Insert("INSERT INTO users (username, password, name, email, role, department, avatar, status) " +
            "VALUES (#{username}, #{password}, #{name}, #{email}, #{role}, #{department}, #{avatar}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);
    
    @Update("UPDATE users SET username = #{username}, name = #{name}, email = #{email}, " +
            "role = #{role}, department = #{department}, avatar = #{avatar}, status = #{status}, " +
            "updated_at = CURRENT_TIMESTAMP WHERE id = #{id}")
    int update(User user);
    
    @Update("UPDATE users SET password = #{password}, updated_at = CURRENT_TIMESTAMP WHERE id = #{id}")
    int updatePassword(@Param("id") Long id, @Param("password") String password);
    
    @Delete("DELETE FROM users WHERE id = #{id}")
    int deleteById(Long id);
    
    @Select("SELECT COUNT(*) FROM users WHERE role = #{role}")
    int countByRole(String role);
    
    @Select("SELECT COUNT(*) FROM users")
    int countAll();
}