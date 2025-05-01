package org.example.reeksamen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime subscriptionDate;
    private boolean notified;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    @JsonIgnoreProperties("subscriptions")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    @JsonIgnoreProperties("subscriptions")
    private Album album;

}
