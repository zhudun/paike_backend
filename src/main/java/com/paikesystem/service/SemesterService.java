package com.paikesystem.service;

import com.paikesystem.entity.Semester;
import com.paikesystem.mapper.SemesterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SemesterService {
    
    @Autowired
    private SemesterMapper semesterMapper;
    
    public Semester findById(Long id) {
        return semesterMapper.findById(id);
    }
    
    public Semester findActiveSemester() {
        return semesterMapper.findActiveSemester();
    }
    
    public List<Semester> findAll() {
        return semesterMapper.findAll();
    }
    
    public Semester createSemester(Semester semester) {
        semester.setCreatedAt(LocalDateTime.now());
        semester.setUpdatedAt(LocalDateTime.now());
        semesterMapper.insert(semester);
        return semester;
    }
    
    public Semester updateSemester(Semester semester) {
        semester.setUpdatedAt(LocalDateTime.now());
        semesterMapper.update(semester);
        return semester;
    }
    
    public void setActiveSemester(Long semesterId) {
        // Deactivate all semesters
        semesterMapper.deactivateAll();
        
        // Activate the specified semester
        semesterMapper.activateById(semesterId);
    }
    
    public void deleteSemester(Long id) {
        semesterMapper.deleteById(id);
    }
}