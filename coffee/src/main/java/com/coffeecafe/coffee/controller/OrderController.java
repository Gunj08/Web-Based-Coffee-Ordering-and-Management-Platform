package com.coffeecafe.coffee.controller;

import com.coffeecafe.coffee.entity.Order;
import com.coffeecafe.coffee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
<<<<<<< HEAD
@CrossOrigin(origins = "http://localhost:5173")
=======
@CrossOrigin(origins = "http://localhost:3000")
>>>>>>> 2b8e9abdb83ddba996deae458df54f7e2258da81
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place")
    public Order placeOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @GetMapping("/customer/{customerId}")
    public Page<Order> getOrderHistory(
<<<<<<< HEAD
            @PathVariable("customerId") Long customerId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        return orderService.getHistory(customerId, page, size);
    }

    @GetMapping("/cafe/id/{cafeId}/status")
    public List<Order> getOrdersByCafeId(@PathVariable("cafeId") Long cafeId, @RequestParam("status") List<String> status) {
        System.out.println("DEBUG: getOrdersByCafeId called. ID: " + cafeId + " | Statuses: " + status);
        List<Order> found = orderService.getOrdersByCafeAndStatuses(cafeId, null, status);
        System.out.println("DEBUG: Found " + found.size() + " orders");
        return found;
    }

    @GetMapping("/cafe/{cafeName}/status")
    public List<Order> getOrdersByCafeName(@PathVariable("cafeName") String cafeName, @RequestParam("status") List<String> status) {
        return orderService.getOrdersByCafeAndStatuses(null, cafeName, status);
    }

    @PostMapping("/{orderId}/status")
    public Order updateStatus(@PathVariable("orderId") Long orderId, @RequestParam("status") String status) {
        return orderService.updateOrderStatus(orderId, status);
    }
=======
            @PathVariable Long customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return orderService.getHistory(customerId, page, size);
    }
>>>>>>> 2b8e9abdb83ddba996deae458df54f7e2258da81
}