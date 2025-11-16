package com.paikesystem.controller;

import com.paikesystem.entity.Schedule;
import com.paikesystem.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    
    @Autowired
    private ScheduleService scheduleService;
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getScheduleById(@PathVariable Long id) {
        Schedule schedule = scheduleService.findById(id);
        if (schedule == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(schedule);
    }
    
    @GetMapping
    public ResponseEntity<?> getAllSchedules() {
        List<Schedule> schedules = scheduleService.findAll();
        return ResponseEntity.ok(schedules);
    }
    
    @GetMapping("/semester/{semesterId}")
    public ResponseEntity<?> getSchedulesBySemester(@PathVariable Long semesterId) {
        List<Schedule> schedules = scheduleService.findBySemesterId(semesterId);
        return ResponseEntity.ok(schedules);
    }
    
    @GetMapping("/class/{classId}")
    public ResponseEntity<?> getSchedulesByClass(@PathVariable Long classId) {
        List<Schedule> schedules = scheduleService.findByClassId(classId);
        return ResponseEntity.ok(schedules);
    }
    
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<?> getSchedulesByTeacher(@PathVariable Long teacherId) {
        List<Schedule> schedules = scheduleService.findByTeacherId(teacherId);
        return ResponseEntity.ok(schedules);
    }
    
    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<?> getSchedulesByClassroom(@PathVariable Long classroomId) {
        List<Schedule> schedules = scheduleService.findByClassroomId(classroomId);
        return ResponseEntity.ok(schedules);
    }
    
    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getSchedulesByCourse(@PathVariable Long courseId) {
        List<Schedule> schedules = scheduleService.findByCourseId(courseId);
        return ResponseEntity.ok(schedules);
    }
    
    @GetMapping("/day/{dayOfWeek}")
    public ResponseEntity<?> getSchedulesByDay(@PathVariable Integer dayOfWeek) {
        List<Schedule> schedules = scheduleService.findByDayOfWeek(dayOfWeek);
        return ResponseEntity.ok(schedules);
    }
    
    @PostMapping
    public ResponseEntity<?> createSchedule(@RequestBody Schedule schedule) {
        try {
            Schedule newSchedule = scheduleService.createSchedule(schedule);
            return ResponseEntity.ok(newSchedule);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(java.util.Collections.singletonMap("error", e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSchedule(@PathVariable Long id, @RequestBody Schedule schedule) {
        try {
            schedule.setId(id);
            Schedule updatedSchedule = scheduleService.updateSchedule(schedule);
            return ResponseEntity.ok(updatedSchedule);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(java.util.Collections.singletonMap("error", e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long id) {
        try {
            scheduleService.deleteSchedule(id);
            return ResponseEntity.ok(java.util.Collections.singletonMap("message", "课程安排删除成功"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(java.util.Collections.singletonMap("error", e.getMessage()));
        }
    }
}