package com.paikesystem.controller;

import com.paikesystem.entity.Semester;
import com.paikesystem.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/semesters")
public class SemesterController {
    
    @Autowired
    private SemesterService semesterService;
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getSemesterById(@PathVariable Long id) {
        Semester semester = semesterService.findById(id);
        if (semester == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(semester);
    }
    
    @GetMapping("/active")
    public ResponseEntity<?> getActiveSemester() {
        Semester semester = semesterService.findActiveSemester();
        if (semester == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(semester);
    }
    
    @GetMapping
    public ResponseEntity<?> getAllSemesters() {
        List<Semester> semesters = semesterService.findAll();
        return ResponseEntity.ok(semesters);
    }
    
    @PostMapping
    public ResponseEntity<?> createSemester(@RequestBody Semester semester) {
        try {
            Semester newSemester = semesterService.createSemester(semester);
            return ResponseEntity.ok(newSemester);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(java.util.Collections.singletonMap("error", e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSemester(@PathVariable Long id, @RequestBody Semester semester) {
        try {
            semester.setId(id);
            Semester updatedSemester = semesterService.updateSemester(semester);
            return ResponseEntity.ok(updatedSemester);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(java.util.Collections.singletonMap("error", e.getMessage()));
        }
    }
    
    @PostMapping("/{id}/activate")
    public ResponseEntity<?> activateSemester(@PathVariable Long id) {
        try {
            semesterService.setActiveSemester(id);
            return ResponseEntity.ok(java.util.Collections.singletonMap("message", "学期激活成功"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(java.util.Collections.singletonMap("error", e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSemester(@PathVariable Long id) {
        try {
            semesterService.deleteSemester(id);
            return ResponseEntity.ok(java.util.Collections.singletonMap("message", "学期删除成功"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(java.util.Collections.singletonMap("error", e.getMessage()));
        }
    }
}