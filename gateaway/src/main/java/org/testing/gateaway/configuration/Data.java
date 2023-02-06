package org.testing.gateaway.configuration;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testing.gateaway.entities.Role;
import org.testing.gateaway.enums.RoleEnum;
import org.testing.gateaway.repositories.RoleRepository;

import java.util.List;

@Configuration @Transactional @RequiredArgsConstructor
public class Data {
    private final RoleRepository roleRepository;
    @Bean
    @Transactional
    public void insertSomeData() {
        roleRepository.saveAll(List.of(new Role(RoleEnum.ADMIN), new Role(RoleEnum.CLIENT)));
    }
}