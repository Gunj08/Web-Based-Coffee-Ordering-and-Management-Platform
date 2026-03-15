package com.coffeecafe.coffee.controller;

import com.coffeecafe.coffee.entity.Order;
import com.coffeecafe.coffee.repository.OrderRepository;
import com.coffeecafe.coffee.repository.CafeRepository;
import com.coffeecafe.coffee.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/sync")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DataSyncController {

    private final OrderRepository orderRepository;
    private final CafeRepository cafeRepository;
    private final UserRepository userRepository;



    @PostMapping("/orders/fix-cafe-ids")
    public ResponseEntity<String> fixCafeIds() {
        List<Order> orders = orderRepository.findAll();
        AtomicInteger count = new AtomicInteger(0);

        orders.forEach(order -> {
            boolean updated = false;
            if (order.getCafeId() == null && order.getCafeName() != null) {
                cafeRepository.findByCafeName(order.getCafeName()).ifPresent(cafe -> {
                    order.setCafeId(cafe.getId());
                    count.getAndIncrement();
                });
                updated = true;
            }
            // Also ensure cafeName is set if missing but cafeId is present
            if (order.getCafeName() == null && order.getCafeId() != null) {
                cafeRepository.findById(order.getCafeId()).ifPresent(cafe -> {
                    order.setCafeName(cafe.getCafeName());
                });
                updated = true;
            }

            if (updated) {
                orderRepository.save(order);
            }
        });

        return ResponseEntity.ok("Successfully scanned " + orders.size() + " orders. Updated " + count.get() + " missing cafeIds.");
    }

    @PostMapping("/users/fix-staff-cafes")
    public ResponseEntity<String> fixStaffCafes() {
        List<com.coffeecafe.coffee.entity.User> users = userRepository.findAll();
        AtomicInteger count = new AtomicInteger(0);

        users.forEach(user -> {
            String role = user.getRole() != null ? user.getRole().toUpperCase() : "";
            if ((role.equals("CHEF") || role.equals("WAITER")) && user.getCafe() == null) {
                // Try to find cafe by companyName (often used as fallback for cafe name in profile)
                String cafeNameFallback = user.getCompanyName();
                if (cafeNameFallback != null && !cafeNameFallback.isEmpty()) {
                    cafeRepository.findByCafeName(cafeNameFallback).ifPresent(cafe -> {
                        user.setCafe(cafe);
                        userRepository.save(user);
                        count.getAndIncrement();
                    });
                }
            }
        });

        return ResponseEntity.ok("Successfully scanned " + users.size() + " users. Fixed " + count.get() + " staff-cafe associations.");
    }
}






