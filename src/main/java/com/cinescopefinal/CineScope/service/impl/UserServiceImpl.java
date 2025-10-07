package com.cinescopefinal.CineScope.service.impl;

import com.cinescopefinal.CineScope.dto.SignUpRequest;
import com.cinescopefinal.CineScope.entities.enums.Role;
import com.cinescopefinal.CineScope.entities.enums.Status;
import com.cinescopefinal.CineScope.entities.Users;
import com.cinescopefinal.CineScope.entities.enums.Subscription;
import com.cinescopefinal.CineScope.exception.DuplicateResourceException;
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

    @Override
    public Users signup(SignUpRequest signUpRequest) {
        // Check for existing email
        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already exists");
        }


        // Optional: check for duplicate password (not usually recommended)
        if (userRepository.existsByPassword(signUpRequest.getPassword())) {
            throw new RuntimeException("Password already in use. Please choose a different password.");
        }

        Users newUser = new Users();
        newUser.setName(signUpRequest.getName());
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setPassword(signUpRequest.getPassword());
        newUser.setSubscription(
                Subscription.valueOf(signUpRequest.getSubscription().name())
        );

        // Convert List<Integer> to comma-separated String
        String movieTypesString = signUpRequest.getMovieTypes().stream()
                .map(String::valueOf)
                .reduce((a, b) -> a + "," + b)
                .orElse("");
        newUser.setGenres(movieTypesString);

        newUser.setRole(Role.USER); // default role
        newUser.setStatus(Status.ACTIVE); // default status

        return userRepository.save(newUser);
    }
}
