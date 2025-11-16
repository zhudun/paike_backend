package com.paikesystem.mapper;

import com.paikesystem.entity.Student;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface StudentMapper {
    
    @Select("SELECT * FROM students WHERE id = #{id}")
    Student findById(Long id);
    
    @Select("SELECT * FROM students WHERE user_id = #{userId}")
    Student findByUserId(Long userId);
    
    @Select("SELECT * FROM students WHERE student_id = #{studentId}")
    Student findByStudentId(String studentId);
    
    @Select("SELECT * FROM students WHERE name = #{name}")
    List<Student> findByName(String name);
    
    @Select("SELECT * FROM students ORDER BY name")
    List<Student> findAll();
    
    @Select("SELECT s.* FROM students s " +
            "JOIN class_students cs ON s.id = cs.student_id " +
            "WHERE cs.class_id = #{classId}")
    List<Student> findByClassId(Long classId);
    
    @Insert("INSERT INTO students (user_id, student_id, name, gender, birth_date, " +
            "phone, email, address, emergency_contact, emergency_phone, " +
            "created_at, updated_at) " +
            "VALUES (#{userId}, #{studentId}, #{name}, #{gender}, #{birthDate}, " +
            "#{phone}, #{email}, #{address}, #{emergencyContact}, #{emergencyPhone}, " +
            "#{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Student student);
    
    @Update("UPDATE students SET name = #{name}, gender = #{gender}, birth_date = #{birthDate}, " +
            "phone = #{phone}, email = #{email}, address = #{address}, " +
            "emergency_contact = #{emergencyContact}, emergency_phone = #{emergencyPhone}, " +
            "updated_at = #{updatedAt} WHERE id = #{id}")
    int update(Student student);
    
    @Delete("DELETE FROM students WHERE id = #{id}")
    int deleteById(Long id);
}