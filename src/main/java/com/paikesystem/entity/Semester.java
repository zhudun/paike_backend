package com.paikesystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class Semester extends BaseEntity {
    
    private Long id;
    
    @NotBlank(message = "学期名称不能为空")
    @Size(max = 100, message = "学期名称长度不能超过100位")
    private String name;
    
    @NotBlank(message = "学期代码不能为空")
    @Size(max = 50, message = "学期代码长度不能超过50位")
    private String code;
    
    @NotNull(message = "开始日期不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    
    @NotNull(message = "结束日期不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    
    @NotNull(message = "总周数不能为空")
    private Integer totalWeeks;
    
    private Integer currentWeek;
    
    private String status; // draft, active, inactive
    
    @Size(max = 500, message = "描述长度不能超过500位")
    private String description;
    
    // Constructors
    public Semester() {}
    
    public Semester(String name, String code, LocalDate startDate, LocalDate endDate, Integer totalWeeks) {
        this.name = name;
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalWeeks = totalWeeks;
        this.currentWeek = 1;
        this.status = "draft";
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
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public Integer getTotalWeeks() {
        return totalWeeks;
    }
    
    public void setTotalWeeks(Integer totalWeeks) {
        this.totalWeeks = totalWeeks;
    }
    
    public Integer getCurrentWeek() {
        return currentWeek;
    }
    
    public void setCurrentWeek(Integer currentWeek) {
        this.currentWeek = currentWeek;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "Semester{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalWeeks=" + totalWeeks +
                ", currentWeek=" + currentWeek +
                ", status='" + status + '\'' +
                '}';
    }
}