package com.nixon.BePrepared.Service;


import com.nixon.BePrepared.DTO.Request.AuthenticationRequestForCitizen;
import com.nixon.BePrepared.DTO.Request.AuthenticationRequestForUser;
import com.nixon.BePrepared.DTO.Response.TokenResponse;

public interface AuthenticationService {

    TokenResponse authenticate(AuthenticationRequestForUser authenticationRequest);

    TokenResponse authenticateCitizen(AuthenticationRequestForCitizen authenticationRequest);
}
