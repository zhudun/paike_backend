package com.paikesystem.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class Course extends BaseEntity {
    
    private Long id;
    
    @NotNull(message = "学期ID不能为空")
    private Long semesterId;
    
    @NotNull(message = "教师ID不能为空")
    private Long teacherId;
    
    @NotBlank(message = "课程名称不能为空")
    @Size(max = 100, message = "课程名称长度不能超过100位")
    private String name;
    
    @NotBlank(message = "课程代码不能为空")
    @Size(max = 50, message = "课程代码长度不能超过50位")
    private String code;
    
    @NotNull(message = "学分不能为空")
    private Integer credit;
    
    @NotBlank(message = "课程类型不能为空")
    private String courseType; // required, elective, optional
    
    @NotNull(message = "周课时不能为空")
    private Integer weeklyHours;
    
    @NotNull(message = "总课时不能为空")
    private Integer totalHours;
    
    @Size(max = 500, message = "课程描述长度不能超过500位")
    private String description;
    
    private String prerequisites; // JSON格式存储先修课程
    
    // 关联对象（查询时填充）
    private Semester semester;
    private Teacher teacher;
    
    // Constructors
    public Course() {}
    
    public Course(Long semesterId, Long teacherId, String name, String code, 
                  Integer credit, String courseType, Integer weeklyHours, Integer totalHours) {
        this.semesterId = semesterId;
        this.teacherId = teacherId;
        this.name = name;
        this.code = code;
        this.credit = credit;
        this.courseType = courseType;
        this.weeklyHours = weeklyHours;
        this.totalHours = totalHours;
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
    
    public Long getTeacherId() {
        return teacherId;
    }
    
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public Integer getCredit() {
        return credit;
    }
    
    public void setCredit(Integer credit) {
        this.credit = credit;
    }
    
    public String getCourseType() {
        return courseType;
    }
    
    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
    
    public Integer getWeeklyHours() {
        return weeklyHours;
    }
    
    public void setWeeklyHours(Integer weeklyHours) {
        this.weeklyHours = weeklyHours;
    }
    
    public Integer getTotalHours() {
        return totalHours;
    }
    
    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getPrerequisites() {
        return prerequisites;
    }
    
    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }
    
    public Semester getSemester() {
        return semester;
    }
    
    public void setSemester(Semester semester) {
        this.semester = semester;
    }
    
    public Teacher getTeacher() {
        return teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", semesterId=" + semesterId +
                ", teacherId=" + teacherId +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", credit=" + credit +
                ", courseType='" + courseType + '\'' +
                ", weeklyHours=" + weeklyHours +
                ", totalHours=" + totalHours +
                '}';
    }
}