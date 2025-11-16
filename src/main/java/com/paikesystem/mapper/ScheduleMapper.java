package com.paikesystem.mapper;

import com.paikesystem.entity.Schedule;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ScheduleMapper {
    
    @Select("SELECT * FROM schedules WHERE id = #{id}")
    Schedule findById(Long id);
    
    @Select("SELECT * FROM schedules ORDER BY day_of_week, start_time")
    List<Schedule> findAll();
    
    @Select("SELECT * FROM schedules WHERE semester_id = #{semesterId} ORDER BY day_of_week, start_time")
    List<Schedule> findBySemesterId(Long semesterId);
    
    @Select("SELECT * FROM schedules WHERE class_id = #{classId} ORDER BY day_of_week, start_time")
    List<Schedule> findByClassId(Long classId);
    
    @Select("SELECT * FROM schedules WHERE teacher_id = #{teacherId} ORDER BY day_of_week, start_time")
    List<Schedule> findByTeacherId(Long teacherId);
    
    @Select("SELECT * FROM schedules WHERE classroom_id = #{classroomId} ORDER BY day_of_week, start_time")
    List<Schedule> findByClassroomId(Long classroomId);
    
    @Select("SELECT * FROM schedules WHERE course_id = #{courseId} ORDER BY day_of_week, start_time")
    List<Schedule> findByCourseId(Long courseId);
    
    @Select("SELECT * FROM schedules WHERE day_of_week = #{dayOfWeek} ORDER BY start_time")
    List<Schedule> findByDayOfWeek(Integer dayOfWeek);
    
    @Select("SELECT * FROM schedules WHERE semester_id = #{semesterId} AND class_id = #{classId} " +
            "ORDER BY day_of_week, start_time")
    List<Schedule> findBySemesterAndClass(@Param("semesterId") Long semesterId, @Param("classId") Long classId);
    
    @Select("SELECT * FROM schedules WHERE semester_id = #{semesterId} AND teacher_id = #{teacherId} " +
            "ORDER BY day_of_week, start_time")
    List<Schedule> findBySemesterAndTeacher(@Param("semesterId") Long semesterId, @Param("teacherId") Long teacherId);
    
    @Select("SELECT * FROM schedules WHERE semester_id = #{semesterId} AND classroom_id = #{classroomId} " +
            "AND day_of_week = #{dayOfWeek} AND ((start_time <= #{startTime} AND end_time > #{startTime}) " +
            "OR (start_time < #{endTime} AND end_time >= #{endTime}) " +
            "OR (start_time >= #{startTime} AND end_time <= #{endTime}))")
    List<Schedule> findConflictingSchedules(@Param("semesterId") Long semesterId, @Param("classroomId") Long classroomId, 
                                           @Param("dayOfWeek") Integer dayOfWeek, @Param("startTime") String startTime, 
                                           @Param("endTime") String endTime);
    
    @Insert("INSERT INTO schedules (semester_id, course_id, class_id, teacher_id, classroom_id, " +
            "day_of_week, start_time, end_time, week_type, created_at, updated_at) " +
            "VALUES (#{semesterId}, #{courseId}, #{classId}, #{teacherId}, #{classroomId}, " +
            "#{dayOfWeek}, #{startTime}, #{endTime}, #{weekType}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Schedule schedule);
    
    @Update("UPDATE schedules SET semester_id = #{semesterId}, course_id = #{courseId}, class_id = #{classId}, " +
            "teacher_id = #{teacherId}, classroom_id = #{classroomId}, day_of_week = #{dayOfWeek}, " +
            "start_time = #{startTime}, end_time = #{endTime}, week_type = #{weekType}, " +
            "updated_at = #{updatedAt} WHERE id = #{id}")
    int update(Schedule schedule);
    
    @Delete("DELETE FROM schedules WHERE id = #{id}")
    int deleteById(Long id);
    
    @Delete("DELETE FROM schedules WHERE semester_id = #{semesterId}")
    int deleteBySemesterId(Long semesterId);
    
    @Delete("DELETE FROM schedules WHERE class_id = #{classId}")
    int deleteByClassId(Long classId);
}