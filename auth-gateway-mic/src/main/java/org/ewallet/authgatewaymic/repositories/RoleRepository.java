package org.ewallet.authgatewaymic.repositories;

import org.ewallet.authgatewaymic.entities.Role;
import org.ewallet.authgatewaymic.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role , Integer> {
    Role findRoleByName(RoleEnum role);
}