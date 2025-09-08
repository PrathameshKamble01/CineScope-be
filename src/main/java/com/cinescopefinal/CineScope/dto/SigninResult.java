package com.cinescopefinal.CineScope.dto;

import com.cinescopefinal.CineScope.entities.Users;
import org.springframework.security.core.userdetails.User;

public class SigninResult {
    private Token token;
    private Users user;

    public SigninResult() {}

    public SigninResult(Token token, Users user) {
        this.token = token;
        this.user = user;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
