package com.paikesystem.mapper;

import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface TeacherSubjectMapper {
    
    @Select("SELECT subject_id FROM teacher_subjects WHERE teacher_id = #{teacherId}")
    List<Long> findSubjectIdsByTeacherId(Long teacherId);
    
    @Select("SELECT teacher_id FROM teacher_subjects WHERE subject_id = #{subjectId}")
    List<Long> findTeacherIdsBySubjectId(Long subjectId);
    
    @Insert("INSERT INTO teacher_subjects (teacher_id, subject_id) VALUES (#{teacherId}, #{subjectId})")
    int insert(@Param("teacherId") Long teacherId, @Param("subjectId") Long subjectId);
    
    @Delete("DELETE FROM teacher_subjects WHERE teacher_id = #{teacherId} AND subject_id = #{subjectId}")
    int delete(@Param("teacherId") Long teacherId, @Param("subjectId") Long subjectId);
    
    @Delete("DELETE FROM teacher_subjects WHERE teacher_id = #{teacherId}")
    int deleteByTeacherId(Long teacherId);
    
    @Delete("DELETE FROM teacher_subjects WHERE subject_id = #{subjectId}")
    int deleteBySubjectId(Long subjectId);
}