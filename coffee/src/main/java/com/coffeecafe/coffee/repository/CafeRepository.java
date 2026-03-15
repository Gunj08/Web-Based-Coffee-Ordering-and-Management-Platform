package com.coffeecafe.coffee.repository;

import com.coffeecafe.coffee.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD
import java.util.Optional;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {
    Optional<Cafe> findByCafeName(String cafeName);
=======

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {
>>>>>>> 2b8e9abdb83ddba996deae458df54f7e2258da81
}