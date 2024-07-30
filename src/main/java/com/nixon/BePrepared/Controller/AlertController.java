package com.nixon.BePrepared.Controller;

import com.nixon.BePrepared.DTO.Request.AlertRequestDTO;
import com.nixon.BePrepared.DTO.Response.AlertResponseDTO;
import com.nixon.BePrepared.Mapper.Mapper;
import com.nixon.BePrepared.Model.Alert;
import com.nixon.BePrepared.Service.AlertService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alerts")
@RequiredArgsConstructor
@Tag(name = "2.Alert Controller")
public class AlertController {

    private final Mapper mapper;

    private final AlertService alertService;

    @PostMapping("/")
    public ResponseEntity<String> createAlert(@Valid @RequestBody AlertRequestDTO alertRequestDTO){
        return new ResponseEntity<>(alertService.createAlert(
                mapper.mapAlertRequestToAlertModel(alertRequestDTO),
                alertRequestDTO.getCityId(),
                alertRequestDTO.getProvinceId()),
                HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AlertResponseDTO>> getAllAlerts(){
        return ResponseEntity.ok(
                mapper.mapAlertToResponseList(alertService.getAllAlerts())
        );
    }

    @GetMapping("/city")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AlertResponseDTO>> getAllAlertsByCityId(@RequestParam Long cityId ){
        return ResponseEntity.ok(
                mapper.mapAlertToResponseList(alertService.getAllAlertsByCityId(cityId))
        );
    }

    @GetMapping("/province")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AlertResponseDTO>> getAllAlertsByProvinceId(@PathVariable Long provinceId ){
        return ResponseEntity.ok(
                mapper.mapAlertToResponseList(alertService.getAllAlertsByProvinceId(provinceId))
        );
    }

    @GetMapping("/active")
    public ResponseEntity<List<AlertResponseDTO>> getAllActiveAlerts(){
        return ResponseEntity.ok(
                mapper.mapAlertToResponseList(alertService.getAllActiveAlerts())
        );
    }

    @GetMapping("/{alertId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AlertResponseDTO> getAllAlertById(@PathVariable Long alertId){
        return ResponseEntity.ok(
                mapper.mapAlertToAlertResponse(alertService.getAlertById(alertId))
        );
    }

    @PutMapping("/{alertId}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> activateAlert(@PathVariable Long alertId){
        return ResponseEntity.ok(
                alertService.activateAlert(alertId)
        );
    }

}
