package com.coffeecafe.coffee.service;

import com.coffeecafe.coffee.entity.Order;
import com.coffeecafe.coffee.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Page<Order> getHistory(Long customerId, int page, int size) {
        return orderRepository.findByCustomerIdOrderByOrderDateDesc(customerId, PageRequest.of(page, size));
    }

    public List<Order> getOrdersByCafeAndStatuses(Long cafeId, String cafeName, List<String> statuses) {
        if (cafeId != null) {
            return orderRepository.findByCafeIdAndStatusIn(cafeId, statuses);
        }
        return orderRepository.findByCafeNameAndStatusIn(cafeName, statuses);
    }


    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        order.setStatus(status);
        return orderRepository.save(order);
    }

}