package com.paikesystem.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Class extends BaseEntity {
    
    private Long id;
    
    @NotBlank(message = "班级名称不能为空")
    @Size(max = 100, message = "班级名称长度不能超过100位")
    private String name;
    
    @NotBlank(message = "年级不能为空")
    @Size(max = 20, message = "年级长度不能超过20位")
    private String grade;
    
    @NotBlank(message = "专业不能为空")
    @Size(max = 100, message = "专业长度不能超过100位")
    private String major;
    
    @NotBlank(message = "所属院系不能为空")
    @Size(max = 100, message = "所属院系长度不能超过100位")
    private String department;
    
    @NotNull(message = "学生人数不能为空")
    private Integer studentCount = 0;
    
    private Long classTeacherId; // 班主任ID
    
    // 关联对象（查询时填充）
    private Teacher classTeacher;
    
    // Constructors
    public Class() {}
    
    public Class(String name, String grade, String major, String department) {
        this.name = name;
        this.grade = grade;
        this.major = major;
        this.department = department;
        this.studentCount = 0;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getGrade() {
        return grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    public String getMajor() {
        return major;
    }
    
    public void setMajor(String major) {
        this.major = major;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public Integer getStudentCount() {
        return studentCount;
    }
    
    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }
    
    public Long getClassTeacherId() {
        return classTeacherId;
    }
    
    public void setClassTeacherId(Long classTeacherId) {
        this.classTeacherId = classTeacherId;
    }
    
    public Teacher getClassTeacher() {
        return classTeacher;
    }
    
    public void setClassTeacher(Teacher classTeacher) {
        this.classTeacher = classTeacher;
    }
    
    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", major='" + major + '\'' +
                ", department='" + department + '\'' +
                ", studentCount=" + studentCount +
                ", classTeacherId=" + classTeacherId +
                '}';
    }
}