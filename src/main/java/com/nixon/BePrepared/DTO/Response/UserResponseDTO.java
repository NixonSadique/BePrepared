package com.nixon.BePrepared.DTO.Response;

import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
}
