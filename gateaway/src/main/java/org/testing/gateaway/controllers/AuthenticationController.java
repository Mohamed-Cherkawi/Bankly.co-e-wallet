package org.testing.gateaway.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testing.gateaway.services.dtos.auth.AuthenticationRequestDto;
import org.testing.gateaway.services.dtos.auth.AuthenticationResponse;
import org.testing.gateaway.services.dtos.auth.RegisterRequestDto;
import org.testing.gateaway.services.implementations.AuthenticationService;

@RestController @RequiredArgsConstructor
@RequestMapping("/api/authenticate")
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequestDto request) {
        AuthenticationResponse authResponse = service.register(request);
        return (authResponse == null)
            ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This username is already taken try another one")
            : ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
    }
    @PostMapping("/login")
     public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequestDto request) {
        Object authResponse = service.authenticate(request);
        if( authResponse.equals("403"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You can't reach the application for now because the admin has banned you");
        else if (authResponse.equals("400"))
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Credentials or the account is not even exists");

        return ResponseEntity.ok(authResponse);
    }
}