package com.nixon.BePrepared.DTO.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatsResponse {

    Long citizens;
    Long totalAlerts;
    Long ActiveAlerts;

}
