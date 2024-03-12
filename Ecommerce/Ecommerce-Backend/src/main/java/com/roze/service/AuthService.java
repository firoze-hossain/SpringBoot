package com.roze.service;

import com.roze.dto.AuthenticationRequest;
import com.roze.dto.SignupRequest;
import com.roze.dto.UserDto;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;

import java.io.IOException;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);

     void authenticate(AuthenticationRequest authenticationRequest,
                             HttpServletResponse httpServletResponse) throws IOException, JSONException;

    Boolean hasUSerWithEmail(String email);
}
