package com.paikesystem.service;

import com.paikesystem.entity.Teacher;
import com.paikesystem.mapper.TeacherMapper;
import com.paikesystem.mapper.TeacherSubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TeacherService {
    
    @Autowired
    private TeacherMapper teacherMapper;
    
    @Autowired
    private TeacherSubjectMapper teacherSubjectMapper;
    
    public Teacher findById(Long id) {
        return teacherMapper.findById(id);
    }
    
    public Teacher findByUserId(Long userId) {
        return teacherMapper.findByUserId(userId);
    }
    
    public Teacher findByEmployeeId(String employeeId) {
        return teacherMapper.findByEmployeeId(employeeId);
    }
    
    public List<Teacher> findAll() {
        return teacherMapper.findAll();
    }
    
    public List<Teacher> findBySubjectId(Long subjectId) {
        return teacherMapper.findBySubjectId(subjectId);
    }
    
    @Transactional
    public Teacher createTeacher(Teacher teacher) {
        teacher.setCreatedAt(LocalDateTime.now());
        teacher.setUpdatedAt(LocalDateTime.now());
        teacherMapper.insert(teacher);
        return teacher;
    }
    
    @Transactional
    public Teacher updateTeacher(Teacher teacher) {
        teacher.setUpdatedAt(LocalDateTime.now());
        teacherMapper.update(teacher);
        return teacher;
    }
    
    @Transactional
    public void assignSubject(Long teacherId, Long subjectId) {
        teacherSubjectMapper.insert(teacherId, subjectId);
    }
    
    @Transactional
    public void unassignSubject(Long teacherId, Long subjectId) {
        teacherSubjectMapper.delete(teacherId, subjectId);
    }
    
    @Transactional
    public void deleteTeacher(Long id) {
        // Delete teacher-subject relationships
        teacherSubjectMapper.deleteByTeacherId(id);
        
        // Delete teacher
        teacherMapper.deleteById(id);
    }
}