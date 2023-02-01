package org.ewallet.authgatewaymic.services.interfaces;



import org.ewallet.authgatewaymic.entities.AppUser;
import org.ewallet.authgatewaymic.enums.RoleEnum;

import java.util.List;

public interface UserServiceInterface {
    List<AppUser> getAllUsers();
    AppUser saveUser(AppUser user);
    AppUser findUserByUsername(String username);
    AppUser findUserByRole(RoleEnum role);
    boolean isUsernameAlreadyExists(String username);
}