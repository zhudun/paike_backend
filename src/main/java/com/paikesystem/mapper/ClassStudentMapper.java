package com.paikesystem.mapper;

import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ClassStudentMapper {
    
    @Select("SELECT student_id FROM class_students WHERE class_id = #{classId}")
    List<Long> findStudentIdsByClassId(Long classId);
    
    @Select("SELECT class_id FROM class_students WHERE student_id = #{studentId}")
    List<Long> findClassIdsByStudentId(Long studentId);
    
    @Insert("INSERT INTO class_students (class_id, student_id) VALUES (#{classId}, #{studentId})")
    int insert(@Param("classId") Long classId, @Param("studentId") Long studentId);
    
    @Delete("DELETE FROM class_students WHERE class_id = #{classId} AND student_id = #{studentId}")
    int delete(@Param("classId") Long classId, @Param("studentId") Long studentId);
    
    @Delete("DELETE FROM class_students WHERE class_id = #{classId}")
    int deleteByClassId(Long classId);
    
    @Delete("DELETE FROM class_students WHERE student_id = #{studentId}")
    int deleteByStudentId(Long studentId);
}