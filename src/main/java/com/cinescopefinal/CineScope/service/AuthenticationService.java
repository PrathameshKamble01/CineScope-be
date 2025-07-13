package com.cinescopefinal.CineScope.service;

import com.cinescopefinal.CineScope.dto.JwtAuthenticationResponse;
import com.cinescopefinal.CineScope.dto.RefreshTokenRequest;
import com.cinescopefinal.CineScope.dto.SignUpRequest;
import com.cinescopefinal.CineScope.dto.SigninRequest;
import com.cinescopefinal.CineScope.entities.Users;

public interface AuthenticationService{
    Users signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SigninRequest signinRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
