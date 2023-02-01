package org.ewallet.authgatewaymic.services.implementations;

import lombok.RequiredArgsConstructor;
import org.ewallet.authgatewaymic.entities.AppUser;
import org.ewallet.authgatewaymic.enums.AvailabilityStateEnum;
import org.ewallet.authgatewaymic.services.dtos.auth.AuthenticationRequestDto;
import org.ewallet.authgatewaymic.services.dtos.auth.AuthenticationResponse;
import org.ewallet.authgatewaymic.services.dtos.auth.RegisterRequestDto;
import org.ewallet.authgatewaymic.services.interfaces.AuthenticationServiceInterface;
import org.ewallet.authgatewaymic.services.interfaces.RoleServiceInterface;
import org.ewallet.authgatewaymic.services.interfaces.UserServiceInterface;
import org.ewallet.authgatewaymic.services.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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
