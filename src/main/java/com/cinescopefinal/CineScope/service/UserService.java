package com.cinescopefinal.CineScope.service;

import com.cinescopefinal.CineScope.dto.SignUpRequest;
import com.cinescopefinal.CineScope.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();

    List<Users> getAllUsers();

    // Add signup method
    Users signup(SignUpRequest signUpRequest);
}
