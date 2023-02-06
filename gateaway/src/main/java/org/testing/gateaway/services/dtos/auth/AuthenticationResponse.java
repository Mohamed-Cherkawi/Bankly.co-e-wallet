package org.testing.gateaway.services.dtos.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder @Getter @Setter @ToString
public class AuthenticationResponse {
    private String token;
}