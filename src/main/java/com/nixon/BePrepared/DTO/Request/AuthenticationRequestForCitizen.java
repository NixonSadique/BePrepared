package com.nixon.BePrepared.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequestForCitizen {
    @NotBlank
    private String phone;
    @NotBlank
    @Size(min = 6, max = 6)
    private String otp;

}
