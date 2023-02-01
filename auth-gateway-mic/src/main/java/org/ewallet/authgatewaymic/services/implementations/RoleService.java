package org.ewallet.authgatewaymic.services.implementations;

import lombok.RequiredArgsConstructor;
import org.ewallet.authgatewaymic.entities.Role;
import org.ewallet.authgatewaymic.enums.RoleEnum;
import org.ewallet.authgatewaymic.repositories.RoleRepository;
import org.ewallet.authgatewaymic.services.interfaces.RoleServiceInterface;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class RoleService implements RoleServiceInterface {
    private final RoleRepository roleRepository;

    @Override
    public Role findRoleByName(RoleEnum roleName) {
        return roleRepository.findRoleByName(roleName);
    }
}