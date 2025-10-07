package com.cinescopefinal.CineScope.dto;

import com.cinescopefinal.CineScope.entities.enums.Subscription;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class SignUpRequest {

    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private Subscription subscription;
    private List<Integer> movieTypes;

    public SignUpRequest(String name, String email, String password, Subscription subscription, List<Integer> movieTypes) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.subscription = subscription;
        this.movieTypes = movieTypes;
    }

    public SignUpRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getMovieTypes() {
        return movieTypes;
    }

    public void setMovieTypes(List<Integer> movieTypes) {
        this.movieTypes = movieTypes;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
