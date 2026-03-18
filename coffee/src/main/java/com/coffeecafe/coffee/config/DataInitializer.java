package com.coffeecafe.coffee.config;

import com.coffeecafe.coffee.entity.*;
import com.coffeecafe.coffee.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final CafeRepository cafeRepository;
    private final MenuItemRepository menuItemRepository;
    private final TableRepository tableRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    public DataInitializer(CafeRepository cafeRepository, MenuItemRepository menuItemRepository, 
                           TableRepository tableRepository, OrderRepository orderRepository, 
                           UserRepository userRepository, 
                           org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        this.cafeRepository = cafeRepository;
        this.menuItemRepository = menuItemRepository;
        this.tableRepository = tableRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        orderRepository.deleteAll();
        // userRepository.deleteAll(); // Might be dangerous if user has important users, let's just delete test ones
        userRepository.findByEmail("test@chef.com").ifPresent(userRepository::delete);
        userRepository.findByEmail("test@waiter.com").ifPresent(userRepository::delete);
        Cafe cafe = cafeRepository.findAll().stream().findFirst().orElseGet(() -> {
            Cafe newCafe = new Cafe();
            newCafe.setCafeName("Bookafé");
            newCafe.setOwnerName("Admin");
            return cafeRepository.save(newCafe);
        });

        if (menuItemRepository.count() == 0) {
            MenuItem item1 = new MenuItem();
            item1.setItemName("Espresso Classic");
            item1.setCategory("Coffee");
            item1.setPrice(120.0);
            item1.setAvailable(true);
            item1.setCafe(cafe);

            MenuItem item2 = new MenuItem();
            item2.setItemName("Latte Choco Pie");
            item2.setCategory("Coffee");
            item2.setPrice(180.0);
            item2.setAvailable(true);
            item2.setCafe(cafe);

            MenuItem item3 = new MenuItem();
            item3.setItemName("Blueberry Muffin");
            item3.setCategory("Pastry");
            item3.setPrice(90.0);
            item3.setAvailable(true);
            item3.setCafe(cafe);

            menuItemRepository.saveAll(Arrays.asList(item1, item2, item3));
        }

        if (tableRepository.count() == 0) {
            TableEntity table1 = new TableEntity();
            table1.setTableNumber("01");
            table1.setCapacity(2);
            table1.setStatus("Available");
            table1.setCafe(cafe);

            TableEntity table2 = new TableEntity();
            table2.setTableNumber("02");
            table2.setCapacity(4);
            table2.setStatus("Occupied");
            table2.setCafe(cafe);

            tableRepository.saveAll(Arrays.asList(table1, table2));
        }

//        if (orderRepository.count() == 0) {
//            Order order1 = new Order();
//            order1.setCustomerName("Rahul Sharma");
//            order1.setTableNumber("01");
//            order1.setItems(Arrays.asList("Espresso Classic", "Blueberry Muffin"));
//            order1.setStatus("PENDING");
//            order1.setTotalAmount(210.0);
//            order1.setOrderDate(LocalDateTime.now());
//            order1.setCafeId(cafe.getId());
//            order1.setCafeName(cafe.getCafeName());
//
//            Order order2 = new Order();
//            order2.setCustomerName("Sneha Gupta");
//            order2.setTableNumber("02");
//            order2.setItems(Arrays.asList("Latte Choco Pie"));
//            order2.setStatus("PREPARING");
//            order2.setTotalAmount(180.0);
//            order2.setOrderDate(LocalDateTime.now());
//            order2.setCafeId(cafe.getId());
//            order2.setCafeName(cafe.getCafeName());
//
//            Order order3 = new Order();
//            order3.setCustomerName("Amit Kumar");
//            order3.setTableNumber("01");
//            order3.setItems(Arrays.asList("Espresso Classic"));
//            order3.setStatus("READY");
//            order3.setTotalAmount(120.0);
//            order3.setOrderDate(LocalDateTime.now().minusMinutes(30));
//            order3.setCafeId(cafe.getId());
//            order3.setCafeName(cafe.getCafeName());
//
//            orderRepository.saveAll(Arrays.asList(order1, order2, order3));
//        }

        // Initialize Test Users
        if (userRepository.findByEmail("test@chef.com").isEmpty()) {
            User chef = new User();
            chef.setFirstName("Test");
            chef.setLastName("Chef");
            chef.setEmail("test@chef.com");
            chef.setPassword(passwordEncoder.encode("password123"));
            chef.setRole("chef");
            chef.setStatus("ACTIVE");
            chef.setPhoneNumber("1234567890");
            chef.setCafe(cafe);
            userRepository.save(chef);
        }

        if (userRepository.findByEmail("test@waiter.com").isEmpty()) {
            User waiter = new User();
            waiter.setFirstName("Test");
            waiter.setLastName("Waiter");
            waiter.setEmail("test@waiter.com");
            waiter.setPassword(passwordEncoder.encode("password123"));
            waiter.setRole("waiter");
            waiter.setStatus("ACTIVE");
            waiter.setPhoneNumber("0987654321");
            waiter.setCafe(cafe);
            userRepository.save(waiter);
        }
        
        System.out.println(">>> Sample data check complete!");
        System.out.println(">>> Cafes count: " + cafeRepository.count());
        System.out.println(">>> Users count: " + userRepository.count());
        System.out.println(">>> Orders count: " + orderRepository.count());
    }
}
