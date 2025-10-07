package com.cinescopefinal.CineScope.service.impl;

import com.cinescopefinal.CineScope.dto.*;
import com.cinescopefinal.CineScope.entities.enums.Role;
import com.cinescopefinal.CineScope.entities.Users;
import com.cinescopefinal.CineScope.entities.enums.Subscription;
import com.cinescopefinal.CineScope.exception.DuplicateResourceException;
import com.cinescopefinal.CineScope.repository.UserRepository;
import com.cinescopefinal.CineScope.service.AuthenticationService;
import com.cinescopefinal.CineScope.service.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
        // Check for existing email
        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already exists");
        }

        Users users = new Users();

        users.setEmail(signUpRequest.getEmail());
        users.setName(signUpRequest.getName());
        users.setRole(Role.USER);
        users.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        users.setSubscription(
                Subscription.valueOf(signUpRequest.getSubscription().name())
        );

        // Convert List<Integer> -> CSV string for DB
        String movieTypeStr = signUpRequest.getMovieTypes().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        users.setGenres(movieTypeStr);

        return userRepository.save(users);
    }


    @Override
    public SigninResult signin(SigninRequest signinRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword())
        );

        var user = userRepository.findByEmail(signinRequest.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password") {
                });

        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        Token token = new Token(accessToken, refreshToken);

        return new SigninResult(token, user);
    }

    @Override
    public SigninResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        Users users = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), users)) {
//            var jwt = jwtService.generateToken(users);
            String newAccessToken = jwtService.generateToken(users);
            String refreshToken = refreshTokenRequest.getToken();

            // Parse movieTypes
            List<Integer> movieTypeIds = Arrays.stream(users.getGenres().split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            // Build response just like signin
            SigninResponse response = new SigninResponse();
            response.setAccessToken(newAccessToken);
            response.setRefreshToken(refreshToken);
            response.setStatus(users.getStatus().name());
            response.setMovieTypes(movieTypeIds);
            response.setRole(users.getRole());

            return response;
        }
        throw new RuntimeException("Invalid refresh token");
    }
}


/*    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        Users users = userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), users)) {
            var jwt = jwtService.generateToken(users);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setAccessToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;
        }
        return null;
    }*/

/*public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));

        var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() -> new AuthenticationException(("Invalid email or password")) {
        });

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setAccessToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }*/

/*    public SigninResponse signin(SigninRequest signinRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));

        var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() -> new AuthenticationException(("Invalid email or password")) {
        });

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        List<Integer> movieTypeIds = Arrays.stream(user.getMovieTypes().split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        SigninResponse signinResponse = new SigninResponse();
        signinResponse.setAccessToken(jwt);
        signinResponse.setStatus(user.getStatus());
        signinResponse.setMovieTypes(movieTypeIds);
        signinResponse.setRole(user.getRole());
//        signinResponse.setRefreshToken(refreshToken);

        return signinResponse;
    }*/