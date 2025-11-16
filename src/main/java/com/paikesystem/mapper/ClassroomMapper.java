package com.paikesystem.mapper;

import com.paikesystem.entity.Classroom;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ClassroomMapper {
    
    @Select("SELECT * FROM classrooms WHERE id = #{id}")
    Classroom findById(Long id);
    
    @Select("SELECT * FROM classrooms WHERE name = #{name}")
    Classroom findByName(String name);
    
    @Select("SELECT * FROM classrooms WHERE building = #{building} AND room_number = #{roomNumber}")
    Classroom findByBuildingAndRoomNumber(@Param("building") String building, @Param("roomNumber") String roomNumber);
    
    @Select("SELECT * FROM classrooms ORDER BY building, room_number")
    List<Classroom> findAll();
    
    @Select("SELECT * FROM classrooms WHERE building = #{building} ORDER BY room_number")
    List<Classroom> findByBuilding(String building);
    
    @Select("SELECT * FROM classrooms WHERE capacity >= #{minCapacity} ORDER BY capacity")
    List<Classroom> findByMinCapacity(Integer minCapacity);
    
    @Select("SELECT DISTINCT building FROM classrooms ORDER BY building")
    List<String> findAllBuildings();
    
    @Insert("INSERT INTO classrooms (name, building, room_number, capacity, type, equipment, " +
            "created_at, updated_at) " +
            "VALUES (#{name}, #{building}, #{roomNumber}, #{capacity}, #{type}, #{equipment}, " +
            "#{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Classroom classroom);
    
    @Update("UPDATE classrooms SET name = #{name}, building = #{building}, room_number = #{roomNumber}, " +
            "capacity = #{capacity}, type = #{type}, equipment = #{equipment}, " +
            "updated_at = #{updatedAt} WHERE id = #{id}")
    int update(Classroom classroom);
    
    @Delete("DELETE FROM classrooms WHERE id = #{id}")
    int deleteById(Long id);
}