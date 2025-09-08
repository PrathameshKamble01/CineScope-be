package com.cinescopefinal.CineScope.service;

import com.cinescopefinal.CineScope.dto.*;
import com.cinescopefinal.CineScope.entities.Users;

public interface AuthenticationService {
    Users signup(SignUpRequest signUpRequest);

    //    JwtAuthenticationResponse signin(SigninRequest signinRequest);
    SigninResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    //    public SigninResponse signin(SigninRequest signinRequest);
    SigninResult signin(SigninRequest signinRequest);
}
