package com.cinescopefinal.CineScope.service.impl;

import com.cinescopefinal.CineScope.dto.JwtAuthenticationResponse;
import com.cinescopefinal.CineScope.dto.RefreshTokenRequest;
import com.cinescopefinal.CineScope.dto.SignUpRequest;
import com.cinescopefinal.CineScope.dto.SigninRequest;
import com.cinescopefinal.CineScope.entities.Role;
import com.cinescopefinal.CineScope.entities.Users;
import com.cinescopefinal.CineScope.repository.UserRepository;
import com.cinescopefinal.CineScope.service.AuthenticationService;
import com.cinescopefinal.CineScope.service.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserRepository userRepository, JWTService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public Users signup(SignUpRequest signUpRequest) {
        Users users = new Users();

        users.setEmail(signUpRequest.getEmail());
        users.setName(signUpRequest.getName());
        users.setRole(Role.USER);
        users.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        users.setSubscription(signUpRequest.getSubscription());
        users.setMovieTypes(signUpRequest.getMovie_type());

        return userRepository.save(users);
    }

    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));

        var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() -> new AuthenticationException(("Invalid email or password")) {
        });

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        Users users = userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), users)) {
            var jwt = jwtService.generateToken(users);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;
        }
        return null;
    }
}