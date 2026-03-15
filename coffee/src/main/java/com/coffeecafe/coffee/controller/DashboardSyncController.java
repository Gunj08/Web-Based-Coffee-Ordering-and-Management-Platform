package com.coffeecafe.coffee.controller;

import com.coffeecafe.coffee.entity.User;
import com.coffeecafe.coffee.entity.Cafe;
import com.coffeecafe.coffee.entity.StaffProfile;
import com.coffeecafe.coffee.repository.UserRepository;
import com.coffeecafe.coffee.repository.CafeRepository;
import com.coffeecafe.coffee.repository.StaffProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardSyncController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CafeRepository cafeRepository;
 
    @Autowired
    private StaffProfileRepository staffProfileRepository;

    @GetMapping("/sync-profile")
    public ResponseEntity<?> syncProfile(@RequestParam("email") String email) {
        System.out.println("DEBUG: syncProfile called for email: [" + email + "]");
        try {
            Optional<User> userOpt = userRepository.findByEmail(email);
            if (!userOpt.isPresent()) {
                // Fallback lookup via findAll (safe for duplicates)
                userOpt = userRepository.findAll().stream()
                    .filter(u -> email.equalsIgnoreCase(u.getEmail()))
                    .findFirst();
            }
            
            if (userOpt.isPresent()) {
                return createProfileMap(userOpt.get());
            }
            return ResponseEntity.status(404).body("User not found");
        } catch (Exception e) {
            System.err.println("DEBUG: Error in syncProfile lookup: " + e.getMessage());
            return ResponseEntity.status(500).body("Error syncing profile: " + e.getMessage());
        }
    }

    private ResponseEntity<Object> createProfileMap(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("email", user.getEmail());
        map.put("firstName", user.getFirstName());
        map.put("lastName", user.getLastName());
        map.put("role", user.getRole());
        map.put("status", user.getStatus());
        
        // Link Cafe
        Long cafeId = null;
        String cafeName = null;
        
        if (user.getCafe() != null) {
            cafeId = user.getCafe().getId();
            cafeName = user.getCafe().getCafeName();
            System.out.println("DEBUG: Cafe found directly on User entity: " + cafeName + " (ID: " + cafeId + ")");
        } else if ("CafeOwner".equalsIgnoreCase(user.getRole())) {
            // Fallback for owners: lookup cafe by their email
            String ownerEmail = user.getEmail();
            Optional<Cafe> ownerCafe = cafeRepository.findAll().stream()
                .filter(c -> ownerEmail.equalsIgnoreCase(c.getEmail()))
                .findFirst();
            if (ownerCafe.isPresent()) {
                cafeId = ownerCafe.get().getId();
                cafeName = ownerCafe.get().getCafeName();
                System.out.println("DEBUG: Cafe found via Owner email fallback: " + cafeName);
            }
        } else if ("CHEF".equalsIgnoreCase(user.getRole()) || "WAITER".equalsIgnoreCase(user.getRole()) || "Chef".equalsIgnoreCase(user.getRole()) || "Waiter".equalsIgnoreCase(user.getRole())) {
            // Fallback for staff: lookup StaffProfile by user or email
            System.out.println("DEBUG: Staff role detected (" + user.getRole() + "). Searching StaffProfile...");
            List<StaffProfile> profiles = staffProfileRepository.findAll();
            Optional<StaffProfile> staffProfile = profiles.stream()
                .filter(p -> (p.getUser() != null && p.getUser().getId().equals(user.getId())) || 
                             (user.getEmail() != null && user.getEmail().equalsIgnoreCase(p.getEmail())))
                .findFirst();
            if (staffProfile.isPresent()) {
                System.out.println("DEBUG: StaffProfile found for user ID: " + user.getId());
                if (staffProfile.get().getCafe() != null) {
                    cafeId = staffProfile.get().getCafe().getId();
                    cafeName = staffProfile.get().getCafe().getCafeName();
                    System.out.println("DEBUG: Cafe found via StaffProfile: " + cafeName);
                } else {
                    System.out.println("DEBUG: StaffProfile found but cafe is NULL");
                }
            } else {
                System.out.println("DEBUG: No StaffProfile found for user ID: " + user.getId() + " or email: " + user.getEmail());
            }
        }
        
        map.put("cafeId", cafeId);
        map.put("cafeName", cafeName);
        System.out.println("DEBUG: Returning profile map for " + user.getEmail() + " with cafeId: " + cafeId);
        return ResponseEntity.ok(map);
    }
}
