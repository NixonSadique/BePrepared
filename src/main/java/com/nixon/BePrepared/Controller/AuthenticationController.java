package com.nixon.BePrepared.Controller;

import com.nixon.BePrepared.DTO.Request.AuthenticationRequestForCitizen;
import com.nixon.BePrepared.DTO.Request.AuthenticationRequestForUser;
import com.nixon.BePrepared.DTO.Response.TokenResponse;
import com.nixon.BePrepared.Service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "1.Authentication Controller")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    @Operation(summary = "Entre aqui para fazer a autenticacao do ADMIN")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthenticationRequestForUser authenticationRequest){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/login")
    @Operation(summary = "Entre aqui para fazer a autenticacao do Cidadao")
    public ResponseEntity<TokenResponse> login(@RequestBody AuthenticationRequestForCitizen authenticationRequest){
        return ResponseEntity.ok(authenticationService.authenticateCitizen(authenticationRequest));
    }
}
