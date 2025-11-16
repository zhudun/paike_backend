package com.paikesystem.service;

import com.paikesystem.entity.User;
import com.paikesystem.mapper.UserMapper;
import com.paikesystem.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    public User findById(Long id) {
        return userMapper.findById(id);
    }
    
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
    
    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }
    
    public List<User> findAll() {
        return userMapper.findAll();
    }
    
    public User register(User user) {
        // Check if username already exists
        if (userMapper.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("用户名已存在");
        }
        
        // Check if email already exists
        if (userMapper.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("邮箱已存在");
        }
        
        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Set default values
        user.setWechatOpenId("");
        user.setAvatar("");
        user.setStatus("active");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        
        userMapper.insert(user);
        return user;
    }
    
    public String login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("密码错误");
        }
        
        if (!"active".equals(user.getStatus())) {
            throw new RuntimeException("账户已被禁用");
        }
        
        return jwtUtil.generateToken(user.getId(), user.getUsername(), user.getRole());
    }
    
    public User updateProfile(Long userId, User user) {
        User existingUser = userMapper.findById(userId);
        if (existingUser == null) {
            throw new RuntimeException("用户不存在");
        }
        
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setAvatar(user.getAvatar());
        existingUser.setUpdatedAt(LocalDateTime.now());
        
        userMapper.update(existingUser);
        return existingUser;
    }
    
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        User user = userMapper.findById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("原密码错误");
        }
        
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdatedAt(LocalDateTime.now());
        userMapper.update(user);
    }
    
    public void updateWechatOpenId(Long userId, String wechatOpenId) {
        User user = userMapper.findById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        user.setWechatOpenId(wechatOpenId);
        user.setUpdatedAt(LocalDateTime.now());
        userMapper.update(user);
    }
}