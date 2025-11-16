package com.paikesystem.entity;

import javax.validation.constraints.NotNull;

public class Schedule extends BaseEntity {
    
    private Long id;
    
    @NotNull(message = "学期ID不能为空")
    private Long semesterId;
    
    @NotNull(message = "课程ID不能为空")
    private Long courseId;
    
    @NotNull(message = "教师ID不能为空")
    private Long teacherId;
    
    @NotNull(message = "班级ID不能为空")
    private Long classId;
    
    @NotNull(message = "教室ID不能为空")
    private Long classroomId;
    
    @NotNull(message = "星期几不能为空")
    private Integer weekDay; // 1-7
    
    @NotNull(message = "时间段不能为空")
    private Integer timeSlot; // 1-12
    
    @NotNull(message = "周次范围不能为空")
    private String weeks; // 如: 1-16
    
    private String status; // scheduled, adjusted, cancelled
    
    // 关联对象（查询时填充）
    private Semester semester;
    private Course course;
    private Teacher teacher;
    private Class clazz;
    private Classroom classroom;
    
    // Constructors
    public Schedule() {}
    
    public Schedule(Long semesterId, Long courseId, Long teacherId, Long classId, 
                   Long classroomId, Integer weekDay, Integer timeSlot, String weeks) {
        this.semesterId = semesterId;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.classId = classId;
        this.classroomId = classroomId;
        this.weekDay = weekDay;
        this.timeSlot = timeSlot;
        this.weeks = weeks;
        this.status = "scheduled";
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getSemesterId() {
        return semesterId;
    }
    
    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }
    
    public Long getCourseId() {
        return courseId;
    }
    
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    
    public Long getTeacherId() {
        return teacherId;
    }
    
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
    
    public Long getClassId() {
        return classId;
    }
    
    public void setClassId(Long classId) {
        this.classId = classId;
    }
    
    public Long getClassroomId() {
        return classroomId;
    }
    
    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }
    
    public Integer getWeekDay() {
        return weekDay;
    }
    
    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }
    
    public Integer getTimeSlot() {
        return timeSlot;
    }
    
    public void setTimeSlot(Integer timeSlot) {
        this.timeSlot = timeSlot;
    }
    
    public String getWeeks() {
        return weeks;
    }
    
    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Semester getSemester() {
        return semester;
    }
    
    public void setSemester(Semester semester) {
        this.semester = semester;
    }
    
    public Course getCourse() {
        return course;
    }
    
    public void setCourse(Course course) {
        this.course = course;
    }
    
    public Teacher getTeacher() {
        return teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    
    public Class getClazz() {
        return clazz;
    }
    
    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
    
    public Classroom getClassroom() {
        return classroom;
    }
    
    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
    
    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", semesterId=" + semesterId +
                ", courseId=" + courseId +
                ", teacherId=" + teacherId +
                ", classId=" + classId +
                ", classroomId=" + classroomId +
                ", weekDay=" + weekDay +
                ", timeSlot=" + timeSlot +
                ", weeks='" + weeks + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}