package com.paikesystem.controller;

import com.paikesystem.entity.Notification;
import com.paikesystem.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    
    @Autowired
    private NotificationService notificationService;
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getNotificationById(@PathVariable Long id) {
        Notification notification = notificationService.findById(id);
        if (notification == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notification);
    }
    
    @GetMapping("/recipient/{recipientId}")
    public ResponseEntity<?> getNotificationsByRecipient(@PathVariable Long recipientId) {
        List<Notification> notifications = notificationService.findByRecipientId(recipientId);
        return ResponseEntity.ok(notifications);
    }
    
    @GetMapping("/recipient/{recipientId}/unread")
    public ResponseEntity<?> getUnreadNotifications(@PathVariable Long recipientId) {
        List<Notification> notifications = notificationService.findUnreadByRecipientId(recipientId);
        return ResponseEntity.ok(notifications);
    }
    
    @GetMapping("/type/{type}")
    public ResponseEntity<?> getNotificationsByType(@PathVariable String type) {
        List<Notification> notifications = notificationService.findByType(type);
        return ResponseEntity.ok(notifications);
    }
    
    @GetMapping("/recipient/{recipientId}/type/{type}")
    public ResponseEntity<?> getNotificationsByRecipientAndType(@PathVariable Long recipientId, @PathVariable String type) {
        List<Notification> notifications = notificationService.findByRecipientIdAndType(recipientId, type);
        return ResponseEntity.ok(notifications);
    }
    
    @GetMapping("/recipient/{recipientId}/count/unread")
    public ResponseEntity<?> getUnreadCount(@PathVariable Long recipientId) {
        int count = notificationService.countUnreadByRecipientId(recipientId);
        return ResponseEntity.ok(Map.of("count", count));
    }
    
    @PostMapping
    public ResponseEntity<?> createNotification(@RequestBody Notification notification) {
        try {
            Notification newNotification = notificationService.createNotification(notification);
            return ResponseEntity.ok(newNotification);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateNotification(@PathVariable Long id, @RequestBody Notification notification) {
        try {
            notification.setId(id);
            Notification updatedNotification = notificationService.updateNotification(notification);
            return ResponseEntity.ok(updatedNotification);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/{id}/read")
    public ResponseEntity<?> markAsRead(@PathVariable Long id) {
        try {
            notificationService.markAsRead(id);
            return ResponseEntity.ok(Map.of("message", "通知已标记为已读"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/recipient/{recipientId}/read-all")
    public ResponseEntity<?> markAllAsRead(@PathVariable Long recipientId) {
        try {
            notificationService.markAllAsRead(recipientId);
            return ResponseEntity.ok(Map.of("message", "所有通知已标记为已读"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long id) {
        try {
            notificationService.deleteNotification(id);
            return ResponseEntity.ok(Map.of("message", "通知删除成功"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}