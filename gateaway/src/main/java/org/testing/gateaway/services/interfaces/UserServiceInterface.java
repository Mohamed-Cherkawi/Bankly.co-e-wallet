package org.testing.gateaway.services.interfaces;




import org.testing.gateaway.entities.AppUser;
import org.testing.gateaway.enums.RoleEnum;

import java.util.List;

public interface UserServiceInterface {
    List<AppUser> getAllUsers();
    AppUser saveUser(AppUser user);
    AppUser findUserByUsername(String username);
    AppUser findUserByRole(RoleEnum role);
    boolean isUsernameAlreadyExists(String username);
}