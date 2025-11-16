package com.paikesystem.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Classroom extends BaseEntity {
    
    private Long id;
    
    @NotBlank(message = "教室名称不能为空")
    @Size(max = 100, message = "教室名称长度不能超过100位")
    private String name;
    
    @NotBlank(message = "教学楼不能为空")
    @Size(max = 100, message = "教学楼长度不能超过100位")
    private String building;
    
    @NotNull(message = "容量不能为空")
    private Integer capacity;
    
    @NotBlank(message = "教室类型不能为空")
    private String roomType; // classroom, laboratory, lecture_hall, meeting_room
    
    @NotNull(message = "楼层不能为空")
    private Integer floor;
    
    private String equipment; // JSON格式存储设备信息
    
    @Size(max = 500, message = "描述长度不能超过500位")
    private String description;
    
    private String availableTimeSlots; // JSON格式存储可用时间段
    
    private String status; // available, maintenance, closed
    
    // Constructors
    public Classroom() {}
    
    public Classroom(String name, String building, Integer capacity, String roomType, Integer floor) {
        this.name = name;
        this.building = building;
        this.capacity = capacity;
        this.roomType = roomType;
        this.floor = floor;
        this.status = "available";
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
    
    public String getBuilding() {
        return building;
    }
    
    public void setBuilding(String building) {
        this.building = building;
    }
    
    public Integer getCapacity() {
        return capacity;
    }
    
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    
    public String getRoomType() {
        return roomType;
    }
    
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    
    public Integer getFloor() {
        return floor;
    }
    
    public void setFloor(Integer floor) {
        this.floor = floor;
    }
    
    public String getEquipment() {
        return equipment;
    }
    
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getAvailableTimeSlots() {
        return availableTimeSlots;
    }
    
    public void setAvailableTimeSlots(String availableTimeSlots) {
        this.availableTimeSlots = availableTimeSlots;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", building='" + building + '\'' +
                ", capacity=" + capacity +
                ", roomType='" + roomType + '\'' +
                ", floor=" + floor +
                ", status='" + status + '\'' +
                '}';
    }
}