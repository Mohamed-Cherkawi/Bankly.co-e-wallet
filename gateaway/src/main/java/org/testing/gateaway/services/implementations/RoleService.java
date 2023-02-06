package org.testing.gateaway.services.implementations;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.testing.gateaway.entities.Role;
import org.testing.gateaway.enums.RoleEnum;
import org.testing.gateaway.repositories.RoleRepository;
import org.testing.gateaway.services.interfaces.RoleServiceInterface;

@Service @RequiredArgsConstructor
public class RoleService implements RoleServiceInterface {
    private final RoleRepository roleRepository;

    @Override
    public Role findRoleByName(RoleEnum roleName) {
        return roleRepository.findRoleByName(roleName);
    }
}