package com.nixon.BePrepared.Config;

import com.nixon.BePrepared.Repository.CitizenRepository;
import com.nixon.BePrepared.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class UserDetailsConfig implements UserDetailsService {

    private final CitizenRepository citizenRepository;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.contains("@")){
            return userRepository.findByEmail(username)
                    .orElseThrow(
                            () -> new UsernameNotFoundException("Usuario nao encontrado!!!")
                    );
        }
        return citizenRepository.findByPhone(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Cidadao nao encontrado!!!")
                );
    }
}
