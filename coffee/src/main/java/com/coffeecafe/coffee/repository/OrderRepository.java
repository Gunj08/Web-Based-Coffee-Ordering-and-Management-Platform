package com.coffeecafe.coffee.repository;

import com.coffeecafe.coffee.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByCustomerIdOrderByOrderDateDesc(Long customerId, Pageable pageable);
<<<<<<< HEAD
    List<Order> findByCafeIdAndStatusIn(Long cafeId, List<String> status);
    List<Order> findByCafeNameAndStatusIn(String cafeName, List<String> status);
=======
>>>>>>> 2b8e9abdb83ddba996deae458df54f7e2258da81
}