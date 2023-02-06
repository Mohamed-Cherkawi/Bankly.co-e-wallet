package org.testing.gateaway.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.testing.gateaway.entities.Role;
import org.testing.gateaway.enums.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByName(RoleEnum role);
}