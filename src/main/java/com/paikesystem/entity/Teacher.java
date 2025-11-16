package com.paikesystem.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Teacher extends BaseEntity {
    
    private Long id;
    
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    
    @NotBlank(message = "教职工编号不能为空")
    @Size(max = 50, message = "教职工编号长度不能超过50位")
    private String employeeId;
    
    @NotBlank(message = "姓名不能为空")
    @Size(max = 100, message = "姓名长度不能超过100位")
    private String name;
    
    @NotBlank(message = "所属院系不能为空")
    @Size(max = 100, message = "所属院系长度不能超过100位")
    private String department;
    
    @Size(max = 50, message = "职称长度不能超过50位")
    private String title;
    
    @Email(message = "邮箱格式不正确")
    @Size(max = 100, message = "邮箱长度不能超过100位")
    private String email;
    
    @Size(max = 20, message = "电话长度不能超过20位")
    private String phone;
    
    @NotNull(message = "最大周课时不能为空")
    private Integer maxWeeklyHours = 20;
    
    private String skills; // JSON格式存储技能标签
    
    private String availableTimeSlots; // JSON格式存储可用时间段
    
    // 关联对象（查询时填充）
    private User user;
    
    // Constructors
    public Teacher() {}
    
    public Teacher(Long userId, String employeeId, String name, String department) {
        this.userId = userId;
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.maxWeeklyHours = 20;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public Integer getMaxWeeklyHours() {
        return maxWeeklyHours;
    }
    
    public void setMaxWeeklyHours(Integer maxWeeklyHours) {
        this.maxWeeklyHours = maxWeeklyHours;
    }
    
    public String getSkills() {
        return skills;
    }
    
    public void setSkills(String skills) {
        this.skills = skills;
    }
    
    public String getAvailableTimeSlots() {
        return availableTimeSlots;
    }
    
    public void setAvailableTimeSlots(String availableTimeSlots) {
        this.availableTimeSlots = availableTimeSlots;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", userId=" + userId +
                ", employeeId='" + employeeId + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", maxWeeklyHours=" + maxWeeklyHours +
                '}';
    }
}