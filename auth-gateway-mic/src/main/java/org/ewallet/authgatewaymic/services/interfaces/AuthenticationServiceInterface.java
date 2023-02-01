package org.ewallet.authgatewaymic.services.interfaces;


import org.ewallet.authgatewaymic.services.dtos.auth.AuthenticationRequestDto;
import org.ewallet.authgatewaymic.services.dtos.auth.AuthenticationResponse;
import org.ewallet.authgatewaymic.services.dtos.auth.RegisterRequestDto;

public interface AuthenticationServiceInterface {
    AuthenticationResponse register(RegisterRequestDto request);
    Object authenticate(AuthenticationRequestDto request);
}