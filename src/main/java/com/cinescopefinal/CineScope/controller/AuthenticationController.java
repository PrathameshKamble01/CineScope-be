package com.cinescopefinal.CineScope.controller;

import com.cinescopefinal.CineScope.dto.*;
import com.cinescopefinal.CineScope.entities.Status;
import com.cinescopefinal.CineScope.entities.Users;
import com.cinescopefinal.CineScope.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignUpRequest signUpRequest) {
//        return ResponseEntity.ok(authenticationService.signup(signUpRequest));

        Users savedUser = authenticationService.signup(signUpRequest);

        List<Integer> movieTypeIds = Arrays.stream(savedUser.getMovieTypes().split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new SignupResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getSubscription(),
                savedUser.getStatus().name(),
                movieTypeIds
        ));

    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest, HttpServletResponse response) {

        SigninResult result = authenticationService.signin(signinRequest);
        Users user = result.getUser();

        if(user.getStatus() == Status.INACTIVE){
            return ResponseEntity.status(403).body("Your account is disabled. Please contact support.");

        }

        Token token = result.getToken();

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", token.getRefreshToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(7 * 24 * 60 * 60) // 7 days in seconds
                .sameSite("Strict")
                .build();

        // Set the cookie in the response header
        response.addHeader("Set-Cookie", refreshCookie.toString());

        // Convert movieTypes string into List<Integer>
        List<Integer> movieTypeIds = Arrays.stream(user.getMovieTypes().split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // Build proper response
        SigninResponse signinResponse = new SigninResponse(
                token.getAccessToken(),
                token.getRefreshToken(),
                user.getStatus().name(),
                movieTypeIds,
                user.getRole()
        );

        return ResponseEntity.ok(signinResponse);

    }

    @PostMapping("/refresh")
    public ResponseEntity<SigninResponse> refresh(@CookieValue("refreshToken") String refreshToken) {
        RefreshTokenRequest request = new RefreshTokenRequest(refreshToken);
        return ResponseEntity.ok(authenticationService.refreshToken(request));
    }

}
