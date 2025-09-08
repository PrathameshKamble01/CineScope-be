package com.cinescopefinal.CineScope.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class RefreshToken {
    @Id
    @GeneratedValue
    private Long id;

    private String token;
    private Long userId;
    private Instant expiryDate;
    private boolean revoked;
}
