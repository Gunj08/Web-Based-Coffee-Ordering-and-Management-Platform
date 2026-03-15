package com.coffeecafe.coffee.controller;

import com.coffeecafe.coffee.dto.ApiResponse;
import com.coffeecafe.coffee.entity.User;
import com.coffeecafe.coffee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @Autowired
    private UserService userService;

    // 1. Get all users waiting for approval
    // URL: http://localhost:8080/users/status/PENDING
    @GetMapping("/status/{status}")
<<<<<<< HEAD
    public List<User> getPendingUsers(@PathVariable("status") String status) {
=======
    public List<User> getPendingUsers(@PathVariable String status) {
>>>>>>> 2b8e9abdb83ddba996deae458df54f7e2258da81
        return userService.getUsersByStatus(status);
    }

    // 2. Approve a user
    // URL: http://localhost:8080/users/approve/{userId}
    @PostMapping("/approve/{userId}")
<<<<<<< HEAD
    public ResponseEntity<?> approveUser(@PathVariable("userId") Long userId) {
=======
    public ResponseEntity<?> approveUser(@PathVariable Long userId) {
>>>>>>> 2b8e9abdb83ddba996deae458df54f7e2258da81
        try {
            userService.approveUser(userId);
            return ResponseEntity.ok(new ApiResponse("User approved successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("Error: " + e.getMessage()));
        }
    }

    // 3. Deny a user
    // URL: http://localhost:8080/users/deny/{userId}
    @PostMapping("/deny/{userId}")
<<<<<<< HEAD
    public ResponseEntity<?> denyUser(@PathVariable("userId") Long userId) {
=======
    public ResponseEntity<?> denyUser(@PathVariable Long userId) {
>>>>>>> 2b8e9abdb83ddba996deae458df54f7e2258da81
        try {
            userService.denyUser(userId);
            return ResponseEntity.ok(new ApiResponse("User denied successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("Error: " + e.getMessage()));
        }
    }
}