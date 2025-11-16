package com.paikesystem.mapper;

import com.paikesystem.entity.Semester;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface SemesterMapper {
    
    @Select("SELECT * FROM semesters WHERE id = #{id}")
    Semester findById(Long id);
    
    @Select("SELECT * FROM semesters WHERE is_active = 1")
    Semester findActiveSemester();
    
    @Select("SELECT * FROM semesters ORDER BY start_date DESC")
    List<Semester> findAll();
    
    @Insert("INSERT INTO semesters (name, start_date, end_date, is_active, created_at, updated_at) " +
            "VALUES (#{name}, #{startDate}, #{endDate}, #{isActive}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Semester semester);
    
    @Update("UPDATE semesters SET name = #{name}, start_date = #{startDate}, end_date = #{endDate}, " +
            "is_active = #{isActive}, updated_at = #{updatedAt} WHERE id = #{id}")
    int update(Semester semester);
    
    @Update("UPDATE semesters SET is_active = 0 WHERE is_active = 1")
    int deactivateAll();
    
    @Update("UPDATE semesters SET is_active = 1 WHERE id = #{id}")
    int activateById(Long id);
    
    @Delete("DELETE FROM semesters WHERE id = #{id}")
    int deleteById(Long id);
}