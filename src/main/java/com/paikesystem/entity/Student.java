package com.paikesystem.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class Student extends BaseEntity {
    
    private Long id;
    
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    
    @NotBlank(message = "学号不能为空")
    @Size(max = 50, message = "学号长度不能超过50位")
    private String studentId;
    
    @NotBlank(message = "姓名不能为空")
    @Size(max = 100, message = "姓名长度不能超过100位")
    private String name;
    
    private String gender; // male, female
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    
    @NotNull(message = "班级ID不能为空")
    private Long classId;
    
    @Email(message = "邮箱格式不正确")
    @Size(max = 100, message = "邮箱长度不能超过100位")
    private String email;
    
    @Size(max = 20, message = "电话长度不能超过20位")
    private String phone;
    
    @Size(max = 100, message = "家长姓名长度不能超过100位")
    private String parentName;
    
    @Size(max = 20, message = "家长电话长度不能超过20位")
    private String parentPhone;
    
    @Size(max = 500, message = "地址长度不能超过500位")
    private String address;
    
    // 关联对象（查询时填充）
    private User user;
    private Class clazz;
    
    // Constructors
    public Student() {}
    
    public Student(Long userId, String studentId, String name, Long classId) {
        this.userId = userId;
        this.studentId = studentId;
        this.name = name;
        this.classId = classId;
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
    
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
    public Long getClassId() {
        return classId;
    }
    
    public void setClassId(Long classId) {
        this.classId = classId;
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
    
    public String getParentName() {
        return parentName;
    }
    
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
    
    public String getParentPhone() {
        return parentPhone;
    }
    
    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Class getClazz() {
        return clazz;
    }
    
    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", userId=" + userId +
                ", studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", classId=" + classId +
                '}';
    }
}