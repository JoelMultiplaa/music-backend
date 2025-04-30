package org.example.reeksamen.Service;

import lombok.RequiredArgsConstructor;
import org.example.reeksamen.entity.Subscription;
import org.example.reeksamen.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subRepo;

    public List<Subscription> findAllSubscriptions() {
        return subRepo.findAll();
    }

    public Optional<Subscription> findById(Long id) {
        return subRepo.findById(id);
    }

    public Subscription subscribe(Subscription sub) {
        sub.setSubscriptionDate(LocalDateTime.now());
        sub.setNotified(false);
        return subRepo.save(sub);
    }

    public boolean unsubscribe(Long id) {
        if (!subRepo.existsById(id)) return false;
        subRepo.deleteById(id);
        return true;
    }
}
