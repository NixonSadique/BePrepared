package com.nixon.BePrepared.DTO.Request;

import com.nixon.BePrepared.Model.enums.Severity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlertRequestDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String message;

    @NotNull
    private Severity severity;

    @NotNull
    private Long provinceId;

    @NotNull
    private Long cityId;

}
