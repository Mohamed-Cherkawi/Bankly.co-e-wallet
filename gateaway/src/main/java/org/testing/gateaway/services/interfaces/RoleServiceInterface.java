package org.testing.gateaway.services.interfaces;


import org.testing.gateaway.entities.Role;
import org.testing.gateaway.enums.RoleEnum;

public interface RoleServiceInterface {
    Role findRoleByName(RoleEnum roleName);
}