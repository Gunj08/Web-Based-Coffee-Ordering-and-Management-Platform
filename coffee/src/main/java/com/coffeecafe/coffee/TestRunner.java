package com.coffeecafe.coffee;

import com.coffeecafe.coffee.entity.Order;
import com.coffeecafe.coffee.entity.User;
import com.coffeecafe.coffee.repository.OrderRepository;
import com.coffeecafe.coffee.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public TestRunner(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- USERS ---");
        for (User user : userRepository.findAll()) {
            if ("CHEF".equalsIgnoreCase(user.getRole()) || "WAITER".equalsIgnoreCase(user.getRole())) {
                System.out.println("User: " + user.getId() + " | Email: " + user.getEmail() + " | Role: " + user.getRole() + " | CafeID: " + (user.getCafe() != null ? user.getCafe().getId() : "null") + " | CafeName: " + (user.getCafe() != null ? user.getCafe().getCafeName() : "null"));
            }
        }
        
        System.out.println("--- ORDERS ---");
        for (Order order : orderRepository.findAll()) {
            System.out.println("Order: " + order.getId() + " | Customer: " + order.getCustomerName() + " | Status: " + order.getStatus() + " | CafeID: " + order.getCafeId() + " | CafeName: " + order.getCafeName());
        }
    }
}
