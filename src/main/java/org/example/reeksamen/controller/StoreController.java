package org.example.reeksamen.controller;

import lombok.RequiredArgsConstructor;
import org.example.reeksamen.Service.StoreService;
import org.example.reeksamen.entity.Store;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping
    public ResponseEntity<List<Store>> getAll() {
        return ResponseEntity.ok(storeService.findAllStores());
    }

    @PostMapping
    public ResponseEntity<Store> create(@RequestBody Store store) {
        return ResponseEntity.status(201).body(storeService.createStore(store));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        System.out.println("==> ENTER getById for id=" + id);
        return storeService.findStoreById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("Store not found"));
    }
}