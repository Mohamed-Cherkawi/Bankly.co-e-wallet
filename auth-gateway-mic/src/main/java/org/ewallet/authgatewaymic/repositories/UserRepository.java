package org.ewallet.authgatewaymic.repositories;

import org.ewallet.authgatewaymic.entities.AppUser;
import org.ewallet.authgatewaymic.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser , Long> {
    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByRole(Role role);
}