package org.testing.gateaway.configuration;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.testing.gateaway.entities.AppUser;
import org.testing.gateaway.entities.Role;
import org.testing.gateaway.enums.AvailabilityStateEnum;
import org.testing.gateaway.enums.RoleEnum;
import org.testing.gateaway.repositories.RoleRepository;
import org.testing.gateaway.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

@Configuration @Transactional @RequiredArgsConstructor
public class Data {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    @Transactional
    public CommandLineRunner insertSomeData() {

        return args -> {
            Role clientRole = new Role(RoleEnum.CLIENT);
            roleRepository.saveAll(List.of(new Role(RoleEnum.ADMIN), clientRole));

            AppUser user = new AppUser(null , UUID.randomUUID().toString(),"Simox", passwordEncoder.encode("pass123"),"Mohamed","0618387383","EE03228", AvailabilityStateEnum.ONLINE ,clientRole);

            userRepository.save(user);
        };
    }
}