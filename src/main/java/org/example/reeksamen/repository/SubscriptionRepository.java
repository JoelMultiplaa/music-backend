package org.example.reeksamen.repository;

import org.example.reeksamen.entity.Customer;
import org.example.reeksamen.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {}