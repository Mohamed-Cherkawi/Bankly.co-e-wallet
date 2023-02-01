package org.ewallet.authgatewaymic.services.interfaces;


import org.ewallet.authgatewaymic.entities.Role;
import org.ewallet.authgatewaymic.enums.RoleEnum;

public interface RoleServiceInterface {
    Role findRoleByName(RoleEnum roleName);
}