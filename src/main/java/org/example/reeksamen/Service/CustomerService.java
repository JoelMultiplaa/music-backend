package org.example.reeksamen.Service;

import lombok.RequiredArgsConstructor;
import org.example.reeksamen.entity.Customer;
import org.example.reeksamen.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepo;

    public List<Customer> findAllCustomers() {
        return customerRepo.findAll();
    }

    public Optional<Customer> findCustomerById(Long id) {
        return customerRepo.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public Customer updateCustomer(Long id, Customer updated) {
        Optional<Customer> existingOpt = customerRepo.findById(id);
        if (existingOpt.isEmpty()) {
            return null;
        }
        Customer existing = existingOpt.get();
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setPhoneNumber(updated.getPhoneNumber());
        return customerRepo.save(existing);
    }

    public boolean deleteCustomer(Long id) {
        if (!customerRepo.existsById(id)) return false;
        customerRepo.deleteById(id);
        return true;
    }
}
