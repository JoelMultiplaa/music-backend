package org.example.reeksamen.controller;

import lombok.RequiredArgsConstructor;
import org.example.reeksamen.Service.CustomerService;
import org.example.reeksamen.entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        return ResponseEntity.status(201).body(customerService.createCustomer(customer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Customer> opt = customerService.findCustomerById(id);
        if (opt.isEmpty()) return ResponseEntity.status(404).body("Customer not found");
        return ResponseEntity.ok(opt.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updated = customerService.updateCustomer(id, customer);
        if (updated == null) return ResponseEntity.status(404).body("Customer not found");
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!customerService.deleteCustomer(id)) {
            return ResponseEntity.status(404).body("Customer not found");
        }
        return ResponseEntity.noContent().build();
    }
}
