package com.FlightSearchApi.controllers;

import com.FlightSearchApi.dto.requests.CreateUserRequest;
import com.FlightSearchApi.dto.requests.LoginUserRequest;
import com.FlightSearchApi.dto.responses.UserResponse;
import com.FlightSearchApi.services.abstracts.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/save")
    public ResponseEntity<UserResponse> save(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(authenticationService.save(createUserRequest));
    }

    @PostMapping("/auth")
    public ResponseEntity<UserResponse> auth(@RequestBody LoginUserRequest loginUserRequest) {
        return ResponseEntity.ok(authenticationService.auth(loginUserRequest));
    }
}
