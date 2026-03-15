package com.coffeecafe.coffee.service;

import com.coffeecafe.coffee.entity.MenuItem;
import com.coffeecafe.coffee.entity.Cafe;
import com.coffeecafe.coffee.repository.MenuItemRepository;
import com.coffeecafe.coffee.repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private CafeRepository cafeRepository;

    public MenuItem saveMenuItem(MenuItem item, Long cafeId) {

        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(() -> new RuntimeException("Cafe not found"));

        item.setCafe(cafe);
        return menuItemRepository.save(item);
    }
<<<<<<< HEAD

    public void updateAvailability(Long itemId, boolean available) {
        MenuItem item = menuItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Menu item not found with id: " + itemId));
        item.setAvailable(available);
        menuItemRepository.save(item);
    }
=======
>>>>>>> 2b8e9abdb83ddba996deae458df54f7e2258da81
}