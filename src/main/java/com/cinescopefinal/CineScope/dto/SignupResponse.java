package com.cinescopefinal.CineScope.dto;

import java.util.List;

public class SignupResponse {
    private Long id;
    private String name;
    private String email;
    private String subscription;
    private String status;
    private List<Integer> movieTypes;

    public SignupResponse(Long id, String name, String email, String subscription, String status, List<Integer> movieTypes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subscription = subscription;
        this.status = status;
        this.movieTypes = movieTypes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Integer> getMovieTypes() {
        return movieTypes;
    }

    public void setMovieTypes(List<Integer> movieTypes) {
        this.movieTypes = movieTypes;
    }
}
