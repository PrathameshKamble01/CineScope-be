package com.cinescopefinal.CineScope.dto;

import com.cinescopefinal.CineScope.entities.enums.Role;

import java.util.List;

public class SigninResponse {

    private String accessToken;
    private String refreshToken;
    private String status;
    private List<Integer> movieTypes;
    private Role role;

    public SigninResponse() {
    }

    public SigninResponse(String accessToken,String refreshToken, String status, List<Integer> movieTypes, Role role) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.status = status;
        this.movieTypes = movieTypes;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
