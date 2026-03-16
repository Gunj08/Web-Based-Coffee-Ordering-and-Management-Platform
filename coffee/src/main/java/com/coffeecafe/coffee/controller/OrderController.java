package com.coffeecafe.coffee.controller;

import com.coffeecafe.coffee.entity.Order;
import com.coffeecafe.coffee.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public Order placeOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @GetMapping("/customer/{customerId}")
    public Page<Order> getOrderHistory(
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

}