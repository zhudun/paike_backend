package com.paikesystem.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Notification extends BaseEntity {
    
    private Long id;
    
    @NotNull(message = "发送者ID不能为空")
    private Long senderId;
    
    @NotNull(message = "接收者ID不能为空")
    private Long recipientId;
    
    @NotNull(message = "标题不能为空")
    @Size(max = 200, message = "标题长度不能超过200位")
    private String title;
    
    @NotNull(message = "内容不能为空")
    private String content;
    
    @NotNull(message = "类型不能为空")
    private String type; // system, schedule_notification, course_reminder, grade_notification, emergency
    
    private String priority; // low, normal, high, urgent
    
    private String status; // unread, read, deleted
    
    private Boolean wechatSent = false;
    
    private String metadata; // JSON格式存储扩展数据
    
    // 关联对象（查询时填充）
    private User sender;
    private User recipient;

    private Boolean isRead;
    
    // Constructors
    public Notification() {}
    
    public Notification(Long senderId, Long recipientId, String title, String content, String type) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.title = title;
        this.content = content;
        this.type = type;
        this.priority = "normal";
        this.status = "unread";
        this.wechatSent = false;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getSenderId() {
        return senderId;
    }
    
    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }
    
    public Long getRecipientId() {
        return recipientId;
    }
    
    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getPriority() {
        return priority;
    }
    
    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Boolean getWechatSent() {
        return wechatSent;
    }
    
    public void setWechatSent(Boolean wechatSent) {
        this.wechatSent = wechatSent;
    }
    
    public String getMetadata() {
        return metadata;
    }
    
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
    
    public User getSender() {
        return sender;
    }
    
    public void setSender(User sender) {
        this.sender = sender;
    }
    
    public User getRecipient() {
        return recipient;
    }
    
    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
    
    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", recipientId=" + recipientId +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                ", wechatSent=" + wechatSent +
                '}';
    }
}