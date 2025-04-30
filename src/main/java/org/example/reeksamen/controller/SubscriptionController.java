package org.example.reeksamen.controller;


import lombok.RequiredArgsConstructor;
import org.example.reeksamen.Service.SubscriptionService;
import org.example.reeksamen.entity.Subscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<List<Subscription>> getAll() {
        return ResponseEntity.ok(subscriptionService.findAllSubscriptions());
    }

    @PostMapping
    public ResponseEntity<Subscription> subscribe(@RequestBody Subscription sub) {
        return ResponseEntity.status(201).body(subscriptionService.subscribe(sub));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> unsubscribe(@PathVariable Long id) {
        if (!subscriptionService.unsubscribe(id)) {
            return ResponseEntity.status(404).body("Subscription not found");
        }
        return ResponseEntity.noContent().build();
    }
}
