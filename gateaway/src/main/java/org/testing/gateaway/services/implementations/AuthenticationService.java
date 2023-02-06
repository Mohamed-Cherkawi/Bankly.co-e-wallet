package org.testing.gateaway.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.testing.gateaway.entities.AppUser;
import org.testing.gateaway.enums.AvailabilityStateEnum;
import org.testing.gateaway.services.dtos.auth.AuthenticationRequestDto;
import org.testing.gateaway.services.dtos.auth.AuthenticationResponse;
import org.testing.gateaway.services.dtos.auth.RegisterRequestDto;
import org.testing.gateaway.services.interfaces.AuthenticationServiceInterface;
import org.testing.gateaway.services.interfaces.RoleServiceInterface;
import org.testing.gateaway.services.interfaces.UserServiceInterface;
import org.testing.gateaway.services.security.JwtService;


@Service @RequiredArgsConstructor
public class AuthenticationService implements AuthenticationServiceInterface {
    private final UserServiceInterface userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleServiceInterface roleService;

    @Override
    public AuthenticationResponse register(RegisterRequestDto request) {
        boolean isUsernameExists = userService.isUsernameAlreadyExists(request.getUsername());

        if(isUsernameExists)
            return null;

        AppUser user = AppUser.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .phone(request.getPhone())
                .status(AvailabilityStateEnum.ONLINE)
                .role(roleService.findRoleByName(request.getRole()))
                .build();

        userService.saveUser(user);

        String jwtToken = jwtService.generateToken(user);

        return getAuthResponse(jwtToken);
    }

    @Override
    public Object authenticate(AuthenticationRequestDto request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch(AuthenticationException e){
            return "400";
        }

        AppUser user = userService.findUserByUsername(request.getUsername());
        if(user.getStatus().equals(AvailabilityStateEnum.BANNED))
            return "403";

        String jwtToken = jwtService.generateToken(user);

        return getAuthResponse(jwtToken);
    }

    private AuthenticationResponse getAuthResponse(String token) {
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
