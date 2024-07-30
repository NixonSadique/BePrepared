package com.nixon.BePrepared.Service.impl;

import com.nixon.BePrepared.DTO.Request.AuthenticationRequestForCitizen;
import com.nixon.BePrepared.DTO.Request.AuthenticationRequestForUser;
import com.nixon.BePrepared.DTO.Response.TokenResponse;
import com.nixon.BePrepared.Model.Citizen;
import com.nixon.BePrepared.Model.Token;
import com.nixon.BePrepared.Model.User;
import com.nixon.BePrepared.Repository.CitizenRepository;
import com.nixon.BePrepared.Repository.TokenRepository;
import com.nixon.BePrepared.Repository.UserRepository;
import com.nixon.BePrepared.Security.JWTService;
import com.nixon.BePrepared.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final CitizenRepository citizenRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    @Override
    @Transactional
    public TokenResponse authenticate(AuthenticationRequestForUser authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );

        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        var token = jwtService.generateToken(user);
        saveUserToken(user,token);

        return TokenResponse.builder()
                .accessToken(token)
                .build();
    }

    @Override
    public TokenResponse authenticateCitizen(AuthenticationRequestForCitizen authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getPhone(),
                        authenticationRequest.getOtp()
                )
        );

        var citizen = citizenRepository.findByPhone(authenticationRequest.getPhone()).orElseThrow();
        var token = jwtService.generateToken(citizen);
        citizen.setOtp(null);
        saveCitizenToken(citizen, token);

        return TokenResponse.builder()
                .accessToken(token)
                .build();
    }

    private void saveUserToken(User user, String token){
        var jwtToken = Token.builder()
                .user(user)
                .token(token)
                .expired(false)
                .revoked(false)
                .createdAt(LocalDateTime.now())
                .build();
        tokenRepository.save(jwtToken);
    }

    private void saveCitizenToken(Citizen citizen, String token){
        var jwtToken = Token.builder()
                .citizen(citizen)
                .token(token)
                .expired(false)
                .revoked(false)
                .createdAt(LocalDateTime.now())
                .build();
        tokenRepository.save(jwtToken);
    }
}