package com.cinescopefinal.CineScope.controller;

import com.cinescopefinal.CineScope.dto.JwtAuthenticationResponse;
import com.cinescopefinal.CineScope.dto.RefreshTokenRequest;
import com.cinescopefinal.CineScope.dto.SignUpRequest;
import com.cinescopefinal.CineScope.dto.SigninRequest;
import com.cinescopefinal.CineScope.entities.Users;
import com.cinescopefinal.CineScope.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Users> signup(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest signinRequest){
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
