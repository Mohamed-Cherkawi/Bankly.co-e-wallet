package org.ewallet.authentication.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.ewallet.authentication.repositories.RoleRepository;
import org.ewallet.authentication.repositories.UserRepository;

@Configuration @RequiredArgsConstructor
public class Data {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
//
//    @Bean
//    @Transactional
//    public CommandLineRunner insertSomeData() {
//
//        return args -> {
//            Role clientRole = new Role(RoleEnum.CLIENT);
//            roleRepository.saveAll(List.of(new Role(RoleEnum.ADMIN), clientRole));
//
//            userRepository.save(new AppUser(null , UUID.randomUUID().toString(),"Simox", passwordEncoder.encode("pass123"),"Mohamed","0618387383","EE03228", AvailabilityStateEnum.ONLINE ,clientRole));
//        };
//    }
}