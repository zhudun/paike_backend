package com.paikesystem.controller;

import com.paikesystem.entity.User;
import com.paikesystem.service.UserService;
import com.paikesystem.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User newUser = userService.register(user);
            String token = jwtUtil.generateToken(newUser.getId(), newUser.getUsername(), newUser.getRole());
            
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", newUser);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        try {
            String username = loginRequest.get("username");
            String password = loginRequest.get("password");
            
            String token = userService.login(username, password);
            User user = userService.findByUsername(username);
            
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", user);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestHeader("Authorization") String token, 
                                         @RequestBody User user) {
        try {
            Long userId = jwtUtil.getUserIdFromToken(token.replace("Bearer ", ""));
            User updatedUser = userService.updateProfile(userId, user);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestHeader("Authorization") String token,
                                          @RequestBody Map<String, String> passwordRequest) {
        try {
            Long userId = jwtUtil.getUserIdFromToken(token.replace("Bearer ", ""));
            String oldPassword = passwordRequest.get("oldPassword");
            String newPassword = passwordRequest.get("newPassword");
            
            userService.changePassword(userId, oldPassword, newPassword);
            return ResponseEntity.ok(Map.of("message", "密码修改成功"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}