package com.paikesystem.mapper;

import com.paikesystem.entity.Course;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CourseMapper {
    
    @Select("SELECT * FROM courses WHERE id = #{id}")
    Course findById(Long id);
    
    @Select("SELECT * FROM courses WHERE name = #{name}")
    Course findByName(String name);
    
    @Select("SELECT * FROM courses ORDER BY name")
    List<Course> findAll();
    
    @Select("SELECT c.* FROM courses c " +
            "JOIN teacher_subjects ts ON c.teacher_subject_id = ts.id " +
            "WHERE ts.teacher_id = #{teacherId}")
    List<Course> findByTeacherId(Long teacherId);
    
    @Select("SELECT c.* FROM courses c " +
            "JOIN schedules s ON c.id = s.course_id " +
            "WHERE s.class_id = #{classId}")
    List<Course> findByClassId(Long classId);
    
    @Select("SELECT c.* FROM courses c " +
            "JOIN teacher_subjects ts ON c.teacher_subject_id = ts.id " +
            "WHERE ts.subject_id = #{subjectId}")
    List<Course> findBySubjectId(Long subjectId);
    
    @Insert("INSERT INTO courses (name, code, description, credits, hours_per_week, " +
            "teacher_subject_id, semester_id, created_at, updated_at) " +
            "VALUES (#{name}, #{code}, #{description}, #{credits}, #{hoursPerWeek}, " +
            "#{teacherSubjectId}, #{semesterId}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Course course);
    
    @Update("UPDATE courses SET name = #{name}, code = #{code}, description = #{description}, " +
            "credits = #{credits}, hours_per_week = #{hoursPerWeek}, " +
            "teacher_subject_id = #{teacherSubjectId}, semester_id = #{semesterId}, " +
            "updated_at = #{updatedAt} WHERE id = #{id}")
    int update(Course course);
    
    @Delete("DELETE FROM courses WHERE id = #{id}")
    int deleteById(Long id);
}