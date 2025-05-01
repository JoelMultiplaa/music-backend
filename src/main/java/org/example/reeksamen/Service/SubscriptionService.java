package org.example.reeksamen.Service;

import lombok.RequiredArgsConstructor;
import org.example.reeksamen.entity.Album;
import org.example.reeksamen.entity.Customer;
import org.example.reeksamen.entity.Subscription;
import org.example.reeksamen.repository.AlbumRepository;
import org.example.reeksamen.repository.CustomerRepository;
import org.example.reeksamen.repository.SubscriptionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subRepo;
    private final CustomerRepository   customerRepo;
    private final AlbumRepository      albumRepo;

    public List<Subscription> findAllSubscriptions() {
        return subRepo.findAll();
    }

    public Subscription subscribe(Subscription sub) {
        // 1) Håndter customer
        Customer incoming = sub.getCustomer();
        Customer customer;
        if (incoming.getId() != null) {
            customer = customerRepo.findById(incoming.getId())
                    .orElseThrow(() ->
                            new ResponseStatusException(
                                    HttpStatus.NOT_FOUND,
                                    "Customer with id=" + incoming.getId() + " not found"
                            ));
        } else {
            // gem ny kunde
            customer = customerRepo.save(
                    new Customer(null,
                            incoming.getName(),
                            incoming.getEmail(),
                            incoming.getPhoneNumber())
            );
        }

        // 2) Hent albummet – det skal altid eksistere
        Long albumId = sub.getAlbum().getId();
        Album album = albumRepo.findById(albumId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Album with id=" + albumId + " not found"
                        ));

        // 3) Fuldfør subscription
        sub.setCustomer(customer);
        sub.setAlbum(album);
        sub.setSubscriptionDate(LocalDateTime.now());
        sub.setNotified(false);

        return subRepo.save(sub);
    }

    public boolean unsubscribe(Long id) {
        if (!subRepo.existsById(id)) {
            return false;
        }
        subRepo.deleteById(id);
        return true;
    }
}
