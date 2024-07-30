package com.nixon.BePrepared.DTO.Response;

import com.nixon.BePrepared.Model.City;
import com.nixon.BePrepared.Model.Province;
import com.nixon.BePrepared.Model.enums.Severity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlertResponseDTO {

    private Long id;

    private String title;

    private String message;

    private Boolean isActive;

    private Severity severity;

    private String city;

    private String province;
}
