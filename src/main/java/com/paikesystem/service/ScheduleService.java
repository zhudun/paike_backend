package com.paikesystem.service;

import com.paikesystem.entity.Schedule;
import com.paikesystem.mapper.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleService {
    
    @Autowired
    private ScheduleMapper scheduleMapper;
    
    public Schedule findById(Long id) {
        return scheduleMapper.findById(id);
    }
    
    public List<Schedule> findAll() {
        return scheduleMapper.findAll();
    }
    
    public List<Schedule> findBySemesterId(Long semesterId) {
        return scheduleMapper.findBySemesterId(semesterId);
    }
    
    public List<Schedule> findByClassId(Long classId) {
        return scheduleMapper.findByClassId(classId);
    }
    
    public List<Schedule> findByTeacherId(Long teacherId) {
        return scheduleMapper.findByTeacherId(teacherId);
    }
    
    public List<Schedule> findByClassroomId(Long classroomId) {
        return scheduleMapper.findByClassroomId(classroomId);
    }
    
    public List<Schedule> findByCourseId(Long courseId) {
        return scheduleMapper.findByCourseId(courseId);
    }
    
    public List<Schedule> findByDayOfWeek(Integer dayOfWeek) {
        return scheduleMapper.findByDayOfWeek(dayOfWeek);
    }
    
    public List<Schedule> findBySemesterAndClass(Long semesterId, Long classId) {
        return scheduleMapper.findBySemesterAndClass(semesterId, classId);
    }
    
    public List<Schedule> findBySemesterAndTeacher(Long semesterId, Long teacherId) {
        return scheduleMapper.findBySemesterAndTeacher(semesterId, teacherId);
    }
    
    public List<Schedule> findConflictingSchedules(Long semesterId, Long classroomId, 
                                                   Integer dayOfWeek, String startTime, String endTime) {
        return scheduleMapper.findConflictingSchedules(semesterId, classroomId, dayOfWeek, startTime, endTime);
    }
    
    @Transactional
    public Schedule createSchedule(Schedule schedule) {
        // Check for conflicts
        List<Schedule> conflicts = findConflictingSchedules(
            schedule.getSemesterId(),
            schedule.getClassroomId(),
            schedule.getDayOfWeek(),
            schedule.getStartTime(),
            schedule.getEndTime()
        );
        
        if (!conflicts.isEmpty()) {
            throw new RuntimeException("教室时间冲突，该时间段已有课程安排");
        }
        
        schedule.setCreatedAt(LocalDateTime.now());
        schedule.setUpdatedAt(LocalDateTime.now());
        scheduleMapper.insert(schedule);
        return schedule;
    }
    
    @Transactional
    public Schedule updateSchedule(Schedule schedule) {
        // Check for conflicts (excluding current schedule)
        List<Schedule> conflicts = findConflictingSchedules(
            schedule.getSemesterId(),
            schedule.getClassroomId(),
            schedule.getDayOfWeek(),
            schedule.getStartTime(),
            schedule.getEndTime()
        );
        
        conflicts.removeIf(s -> s.getId().equals(schedule.getId()));
        
        if (!conflicts.isEmpty()) {
            throw new RuntimeException("教室时间冲突，该时间段已有课程安排");
        }
        
        schedule.setUpdatedAt(LocalDateTime.now());
        scheduleMapper.update(schedule);
        return schedule;
    }
    
    @Transactional
    public void deleteSchedule(Long id) {
        scheduleMapper.deleteById(id);
    }
    
    @Transactional
    public void deleteBySemesterId(Long semesterId) {
        scheduleMapper.deleteBySemesterId(semesterId);
    }
    
    @Transactional
    public void deleteByClassId(Long classId) {
        scheduleMapper.deleteByClassId(classId);
    }
}