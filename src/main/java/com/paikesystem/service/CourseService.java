package com.paikesystem.service;

import com.paikesystem.entity.Course;
import com.paikesystem.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseService {
    
    @Autowired
    private CourseMapper courseMapper;
    
    public Course findById(Long id) {
        return courseMapper.findById(id);
    }
    
    public Course findByName(String name) {
        return courseMapper.findByName(name);
    }
    
    public List<Course> findAll() {
        return courseMapper.findAll();
    }
    
    public List<Course> findByTeacherId(Long teacherId) {
        return courseMapper.findByTeacherId(teacherId);
    }
    
    public List<Course> findByClassId(Long classId) {
        return courseMapper.findByClassId(classId);
    }
    
    public List<Course> findBySubjectId(Long subjectId) {
        return courseMapper.findBySubjectId(subjectId);
    }
    
    public Course createCourse(Course course) {
        course.setCreatedAt(LocalDateTime.now());
        course.setUpdatedAt(LocalDateTime.now());
        courseMapper.insert(course);
        return course;
    }
    
    public Course updateCourse(Course course) {
        course.setUpdatedAt(LocalDateTime.now());
        courseMapper.update(course);
        return course;
    }
    
    public void deleteCourse(Long id) {
        courseMapper.deleteById(id);
    }
}