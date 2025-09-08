package com.cinescopefinal.CineScope.dto;

import java.util.List;

public class SignUpRequest {

    private String name;
    private String email;
    private String password;
    private String subscription;
    private List<Integer> movie_type;

    public SignUpRequest(String name, String email, String password, String subscription, List<Integer> movieTypes) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.subscription = subscription;
        this.movie_type = movie_type;
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

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public List<Integer> getMovie_type() {
        return movie_type;
    }

    public void setMovie_type(List<Integer> movie_type) {
        this.movie_type = movie_type;
    }

    @Override
    public String toString() {
        return "SignUpRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", subscription='" + subscription + '\'' +
                ", movie_type='" + movie_type + '\'' +
                '}';
    }
}
