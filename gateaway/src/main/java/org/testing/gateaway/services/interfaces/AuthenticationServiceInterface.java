package org.testing.gateaway.services.interfaces;


import org.testing.gateaway.services.dtos.auth.AuthenticationRequestDto;
import org.testing.gateaway.services.dtos.auth.AuthenticationResponse;
import org.testing.gateaway.services.dtos.auth.RegisterRequestDto;

public interface AuthenticationServiceInterface {
    AuthenticationResponse register(RegisterRequestDto request);
    Object authenticate(AuthenticationRequestDto request);
}