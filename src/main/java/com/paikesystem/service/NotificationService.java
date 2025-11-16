package com.paikesystem.service;

import com.paikesystem.entity.Notification;
import com.paikesystem.mapper.NotificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {
    
    @Autowired
    private NotificationMapper notificationMapper;
    
    public Notification findById(Long id) {
        return notificationMapper.findById(id);
    }
    
    public List<Notification> findByRecipientId(Long recipientId) {
        return notificationMapper.findByRecipientId(recipientId);
    }
    
    public List<Notification> findUnreadByRecipientId(Long recipientId) {
        return notificationMapper.findUnreadByRecipientId(recipientId);
    }
    
    public List<Notification> findByType(String type) {
        return notificationMapper.findByType(type);
    }
    
    public List<Notification> findByRecipientIdAndType(Long recipientId, String type) {
        return notificationMapper.findByRecipientIdAndType(recipientId, type);
    }
    
    public int countUnreadByRecipientId(Long recipientId) {
        return notificationMapper.countUnreadByRecipientId(recipientId);
    }
    
    public Notification createNotification(Notification notification) {
        notification.setIsRead(false);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setUpdatedAt(LocalDateTime.now());
        notificationMapper.insert(notification);
        return notification;
    }
    
    public Notification updateNotification(Notification notification) {
        notification.setUpdatedAt(LocalDateTime.now());
        notificationMapper.update(notification);
        return notification;
    }
    
    public void markAsRead(Long id) {
        notificationMapper.markAsRead(id, LocalDateTime.now());
    }
    
    public void markAllAsRead(Long recipientId) {
        notificationMapper.markAllAsRead(recipientId, LocalDateTime.now());
    }
    
    public void deleteNotification(Long id) {
        notificationMapper.deleteById(id);
    }
    
    public void deleteByRecipientId(Long recipientId) {
        notificationMapper.deleteByRecipientId(recipientId);
    }
    
    public void cleanOldNotifications(LocalDateTime date) {
        notificationMapper.deleteOlderThan(date);
    }
}