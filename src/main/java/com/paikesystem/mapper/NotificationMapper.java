package com.paikesystem.mapper;

import com.paikesystem.entity.Notification;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface NotificationMapper {
    
    @Select("SELECT * FROM notifications WHERE id = #{id}")
    Notification findById(Long id);
    
    @Select("SELECT * FROM notifications WHERE recipient_id = #{recipientId} ORDER BY created_at DESC")
    List<Notification> findByRecipientId(Long recipientId);
    
    @Select("SELECT * FROM notifications WHERE recipient_id = #{recipientId} AND is_read = 0 ORDER BY created_at DESC")
    List<Notification> findUnreadByRecipientId(Long recipientId);
    
    @Select("SELECT * FROM notifications WHERE type = #{type} ORDER BY created_at DESC")
    List<Notification> findByType(String type);
    
    @Select("SELECT * FROM notifications WHERE recipient_id = #{recipientId} AND type = #{type} ORDER BY created_at DESC")
    List<Notification> findByRecipientIdAndType(@Param("recipientId") Long recipientId, @Param("type") String type);
    
    @Select("SELECT COUNT(*) FROM notifications WHERE recipient_id = #{recipientId} AND is_read = 0")
    int countUnreadByRecipientId(Long recipientId);
    
    @Insert("INSERT INTO notifications (recipient_id, title, content, type, priority, is_read, " +
            "related_id, created_at, updated_at) " +
            "VALUES (#{recipientId}, #{title}, #{content}, #{type}, #{priority}, #{isRead}, " +
            "#{relatedId}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Notification notification);
    
    @Update("UPDATE notifications SET title = #{title}, content = #{content}, type = #{type}, " +
            "priority = #{priority}, is_read = #{isRead}, related_id = #{relatedId}, " +
            "updated_at = #{updatedAt} WHERE id = #{id}")
    int update(Notification notification);
    
    @Update("UPDATE notifications SET is_read = 1, updated_at = #{updatedAt} WHERE id = #{id}")
    int markAsRead(@Param("id") Long id, @Param("updatedAt") java.time.LocalDateTime updatedAt);
    
    @Update("UPDATE notifications SET is_read = 1, updated_at = #{updatedAt} WHERE recipient_id = #{recipientId} AND is_read = 0")
    int markAllAsRead(@Param("recipientId") Long recipientId, @Param("updatedAt") java.time.LocalDateTime updatedAt);
    
    @Delete("DELETE FROM notifications WHERE id = #{id}")
    int deleteById(Long id);
    
    @Delete("DELETE FROM notifications WHERE recipient_id = #{recipientId}")
    int deleteByRecipientId(Long recipientId);
    
    @Delete("DELETE FROM notifications WHERE created_at < #{date}")
    int deleteOlderThan(java.time.LocalDateTime date);
}