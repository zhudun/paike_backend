package com.paikesystem.service;

import com.paikesystem.entity.Class;
import com.paikesystem.mapper.ClassMapper;
import com.paikesystem.mapper.ClassStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClassService {
    
    @Autowired
    private ClassMapper classMapper;
    
    @Autowired
    private ClassStudentMapper classStudentMapper;
    
    public Class findById(Long id) {
        return classMapper.findById(id);
    }
    
    public Class findByName(String name) {
        return classMapper.findByName(name);
    }
    
    public List<Class> findAll() {
        return classMapper.findAll();
    }
    
    public List<Class> findByStudentId(Long studentId) {
        return classMapper.findByStudentId(studentId);
    }
    
    public List<Class> findByTeacherId(Long teacherId) {
        return classMapper.findByTeacherId(teacherId);
    }
    
    @Transactional
    public Class createClass(Class clazz) {
        clazz.setCreatedAt(LocalDateTime.now());
        clazz.setUpdatedAt(LocalDateTime.now());
        classMapper.insert(clazz);
        return clazz;
    }
    
    @Transactional
    public Class updateClass(Class clazz) {
        clazz.setUpdatedAt(LocalDateTime.now());
        classMapper.update(clazz);
        return clazz;
    }
    
    @Transactional
    public void assignStudent(Long classId, Long studentId) {
        classStudentMapper.insert(classId, studentId);
    }
    
    @Transactional
    public void unassignStudent(Long classId, Long studentId) {
        classStudentMapper.delete(classId, studentId);
    }
    
    @Transactional
    public void deleteClass(Long id) {
        // Delete class-student relationships
        classStudentMapper.deleteByClassId(id);
        
        // Delete class
        classMapper.deleteById(id);
    }
}