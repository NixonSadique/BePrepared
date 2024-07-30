package com.nixon.BePrepared.DTO.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CitizenResponseDTO {

    private Long id;
    private String phone;
    private String deviceId;
    private String city;
    private String province;
    private boolean verified;

}
