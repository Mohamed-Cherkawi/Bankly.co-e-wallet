package org.ewallet.authgatewaymic.configuration;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ewallet.authgatewaymic.entities.Role;
import org.ewallet.authgatewaymic.enums.RoleEnum;
import org.ewallet.authgatewaymic.repositories.RoleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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