package com.paikesystem.mapper;

import com.paikesystem.entity.Class;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ClassMapper {
    
    @Select("SELECT * FROM classes WHERE id = #{id}")
    Class findById(Long id);
    
    @Select("SELECT * FROM classes WHERE name = #{name}")
    Class findByName(String name);
    
    @Select("SELECT * FROM classes ORDER BY name")
    List<Class> findAll();
    
    @Select("SELECT c.* FROM classes c " +
            "JOIN class_students cs ON c.id = cs.class_id " +
            "WHERE cs.student_id = #{studentId}")
    List<Class> findByStudentId(Long studentId);
    
    @Select("SELECT c.* FROM classes c " +
            "JOIN teacher_subjects ts ON c.teacher_subject_id = ts.id " +
            "WHERE ts.teacher_id = #{teacherId}")
    List<Class> findByTeacherId(Long teacherId);
    
    @Insert("INSERT INTO classes (name, grade, student_count, head_teacher_id, " +
            "classroom_id, created_at, updated_at) " +
            "VALUES (#{name}, #{grade}, #{studentCount}, #{headTeacherId}, " +
            "#{classroomId}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Class clazz);
    
    @Update("UPDATE classes SET name = #{name}, grade = #{grade}, student_count = #{studentCount}, " +
            "head_teacher_id = #{headTeacherId}, classroom_id = #{classroomId}, " +
            "updated_at = #{updatedAt} WHERE id = #{id}")
    int update(Class clazz);
    
    @Delete("DELETE FROM classes WHERE id = #{id}")
    int deleteById(Long id);
}