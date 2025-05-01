package org.example.reeksamen.Service;

import lombok.RequiredArgsConstructor;
import org.example.reeksamen.entity.Album;
import org.example.reeksamen.entity.Customer;
import org.example.reeksamen.entity.Store;
import org.example.reeksamen.entity.Subscription;
import org.example.reeksamen.repository.AlbumRepository;
import org.example.reeksamen.repository.CustomerRepository;
import org.example.reeksamen.repository.StoreRepository;
import org.example.reeksamen.repository.SubscriptionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final StoreRepository storeRepo;
    private final AlbumRepository albumRepo;
    private final CustomerRepository customerRepo;
    private final SubscriptionRepository subscriptionRepo;

    @Override
    public void run(String... args) {
        Store s1 = storeRepo.save(new Store(
                null,
                "City Vinyl",
                "Main St 1",
                "Springfield",
                "12345",
                new ArrayList<>()
        ));

        Album a1 = albumRepo.save(new Album(
                null,
                "Purple Rain",
                "Prince",
                "Pop",
                true,
                s1,
                new ArrayList<>()
        ));

        Album a2 = albumRepo.save(new Album(
                null,
                "Dark Side of the Moon",
                "Pink Floyd",
                "Rock",
                false,
                s1,
                new ArrayList<>()
        ));
    }
}