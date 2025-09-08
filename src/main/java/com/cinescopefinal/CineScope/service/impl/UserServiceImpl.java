package com.cinescopefinal.CineScope.service.impl;

import com.cinescopefinal.CineScope.entities.Users;
import com.cinescopefinal.CineScope.repository.UserRepository;
import com.cinescopefinal.CineScope.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                Users user = userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

                return User
                        .withUsername(user.getEmail())
                        .password(user.getPassword())
                        .authorities(user.getRole().name())
                        .build();
            }
        };
    }

    @Override
    public List<Users> getAllUsers() {
        try {
            return userRepository.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
