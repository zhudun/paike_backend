package com.paikesystem.mapper;

import com.paikesystem.entity.Teacher;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface TeacherMapper {
    
    @Select("SELECT * FROM teachers WHERE id = #{id}")
    Teacher findById(Long id);
    
    @Select("SELECT * FROM teachers WHERE user_id = #{userId}")
    Teacher findByUserId(Long userId);
    
    @Select("SELECT * FROM teachers WHERE employee_id = #{employeeId}")
    Teacher findByEmployeeId(String employeeId);
    
    @Select("SELECT * FROM teachers ORDER BY name")
    List<Teacher> findAll();
    
    @Select("SELECT t.* FROM teachers t " +
            "JOIN teacher_subjects ts ON t.id = ts.teacher_id " +
            "WHERE ts.subject_id = #{subjectId}")
    List<Teacher> findBySubjectId(Long subjectId);
    
    @Insert("INSERT INTO teachers (user_id, employee_id, name, title, department, phone, email, " +
            "max_courses_per_day, created_at, updated_at) " +
            "VALUES (#{userId}, #{employeeId}, #{name}, #{title}, #{department}, #{phone}, #{email}, " +
            "#{maxCoursesPerDay}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Teacher teacher);
    
    @Update("UPDATE teachers SET name = #{name}, title = #{title}, department = #{department}, " +
            "phone = #{phone}, email = #{email}, max_courses_per_day = #{maxCoursesPerDay}, " +
            "updated_at = #{updatedAt} WHERE id = #{id}")
    int update(Teacher teacher);
    
    @Delete("DELETE FROM teachers WHERE id = #{id}")
    int deleteById(Long id);
}