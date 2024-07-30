package com.nixon.BePrepared.Service.impl;

import com.nixon.BePrepared.DTO.Response.StatsResponse;
import com.nixon.BePrepared.Exception.BadRequestException;
import com.nixon.BePrepared.Exception.EntityNotFoundException;
import com.nixon.BePrepared.Model.User;
import com.nixon.BePrepared.Model.enums.Role;
import com.nixon.BePrepared.Repository.AlertRepository;
import com.nixon.BePrepared.Repository.CitizenRepository;
import com.nixon.BePrepared.Repository.UserRepository;
import com.nixon.BePrepared.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AlertRepository alertRepository;
    private final CitizenRepository citizenRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public String createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new BadRequestException("Esse email pertence a outro usuario!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        return "Ususario Criado com Sucesso!";
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return this.userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Esse usuario nao foi encontrado!")
        );
    }

    @Override
    public StatsResponse getAllStats() {
        return StatsResponse.builder()
                .citizens(userRepository.count())
                .totalAlerts(alertRepository.count())
                .ActiveAlerts(alertRepository.countByActive(true))
                .build();
    }
}
