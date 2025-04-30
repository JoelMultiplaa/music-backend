package org.example.reeksamen.Service;

import lombok.RequiredArgsConstructor;
import org.example.reeksamen.entity.Store;
import org.example.reeksamen.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepo;

    public List<Store> findAllStores() {
        return storeRepo.findAll();
    }

    public Optional<Store> findStoreById(Long id) {
        return storeRepo.findById(id);
    }

    public Store createStore(Store store) {
        return storeRepo.save(store);
    }
}
