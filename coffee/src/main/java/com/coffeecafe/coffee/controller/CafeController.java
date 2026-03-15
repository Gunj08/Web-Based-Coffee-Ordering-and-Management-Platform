package com.coffeecafe.coffee.controller;

import com.coffeecafe.coffee.entity.Cafe;
import com.coffeecafe.coffee.entity.MenuItem;
import com.coffeecafe.coffee.entity.TableEntity;
import com.coffeecafe.coffee.repository.CafeRepository;
import com.coffeecafe.coffee.repository.MenuItemRepository;
import com.coffeecafe.coffee.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cafes")
@CrossOrigin(origins = "http://localhost:5173")
public class CafeController {

    @Autowired
    private CafeRepository cafeRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerCafe(@RequestBody Cafe cafe) {
        try {
            cafe.setId(null);
            Cafe savedCafe = cafeRepository.save(cafe);
            return ResponseEntity.ok(savedCafe);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

<<<<<<< HEAD
    // GET cafe by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCafeById(@PathVariable("id") Long id) {
        System.out.println("DEBUG: getCafeById called for ID: " + id);
        return cafeRepository.findById(id)
                .map(cafe -> {
                    System.out.println("DEBUG: Cafe found: " + cafe.getCafeName());
                    return ResponseEntity.ok(cafe);
                })
                .orElseGet(() -> {
                    System.out.println("DEBUG: Cafe NOT found for ID: " + id);
                    return ResponseEntity.notFound().build();
                });
    }

=======
>>>>>>> 2b8e9abdb83ddba996deae458df54f7e2258da81
    // GET all cafes
    @GetMapping("/all")
    public List<Cafe> getAllCafes() {
        return cafeRepository.findAll();
    }

    // UPDATE cafe
    @PutMapping("/update/{id}")
<<<<<<< HEAD
    public ResponseEntity<?> updateCafe(@PathVariable("id") Long id, @RequestBody Cafe cafeDetails) {
=======
    public ResponseEntity<?> updateCafe(@PathVariable Long id, @RequestBody Cafe cafeDetails) {
>>>>>>> 2b8e9abdb83ddba996deae458df54f7e2258da81
        return cafeRepository.findById(id).map(cafe -> {
            cafe.setCafeName(cafeDetails.getCafeName());
            cafe.setOwnerName(cafeDetails.getOwnerName());
            cafe.setCafeImages(cafeDetails.getCafeImages());
            return ResponseEntity.ok(cafeRepository.save(cafe));
        }).orElse(ResponseEntity.notFound().build());
    }



    @Autowired
<<<<<<< HEAD
    private TableRepository tableRepository;

    @GetMapping("/tables/{cafeId}")
    public List<TableEntity> getTablesByCafe(@PathVariable("cafeId") Long cafeId) {
        return tableRepository.findByCafeId(cafeId);
    }

=======
    private MenuItemRepository menuItemRepository;

    @PostMapping("/menu/add")
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem item) {
        return ResponseEntity.ok(menuItemRepository.save(item));
    }

    @GetMapping("/menu/all")
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    @GetMapping("/menu/{cafeId}")
    public List<MenuItem> getMenuByCafe(@PathVariable Long cafeId) {
        return menuItemRepository.findByCafeId(cafeId);
    }

    @GetMapping("/tables/{cafeId}")
    public List<TableEntity> getTablesByCafe(@PathVariable Long cafeId) {
        return tableRepository.findByCafeId(cafeId);
    }

    @Autowired
    private TableRepository tableRepository;

    @PostMapping("/tables/add")
    public ResponseEntity<TableEntity> addTable(@RequestBody TableEntity table) {
        return ResponseEntity.ok(tableRepository.save(table));
    }

>>>>>>> 2b8e9abdb83ddba996deae458df54f7e2258da81
    @GetMapping("/tables/all")
    public List<TableEntity> getAllTables() {
        return tableRepository.findAll();
    }

    @DeleteMapping("/tables/delete/{id}")
<<<<<<< HEAD
    public ResponseEntity<?> deleteTable(@PathVariable("id") Long id) {
=======
    public ResponseEntity<?> deleteTable(@PathVariable Long id) {
>>>>>>> 2b8e9abdb83ddba996deae458df54f7e2258da81
        tableRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
<<<<<<< HEAD
    public ResponseEntity<?> deleteCafe(@PathVariable("id") Long id) {
=======
    public ResponseEntity<?> deleteCafe(@PathVariable Long id) {
>>>>>>> 2b8e9abdb83ddba996deae458df54f7e2258da81
        try {
            cafeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting cafe");
        }
    }


}