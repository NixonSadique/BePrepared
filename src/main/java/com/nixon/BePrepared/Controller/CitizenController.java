package com.nixon.BePrepared.Controller;

import com.nixon.BePrepared.DTO.Request.CitizenRequestDTO;
import com.nixon.BePrepared.DTO.Response.CitizenResponseDTO;
import com.nixon.BePrepared.Mapper.Mapper;
import com.nixon.BePrepared.Model.Citizen;
import com.nixon.BePrepared.Repository.CitizenRepository;
import com.nixon.BePrepared.Service.CitizenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/citizens")
@Tag(name = "3.Citizen Controller")
public class CitizenController {

    private final Mapper mapper;
    private final CitizenService citizenService;

    @PostMapping("/create")
    public ResponseEntity<String> createCitizen(@Valid @RequestBody CitizenRequestDTO citizenRequestDTO){
        return new ResponseEntity<>(citizenService.createCitizen(
                mapper.mapCitizenRequestToModel(citizenRequestDTO),
                citizenRequestDTO.getCityId()),
                HttpStatus.CREATED
        );
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CitizenResponseDTO>> getAllCitizen(){
        return ResponseEntity.ok(mapper.mapCitizensToResponseDtoList(citizenService.getAllCitizens()));
    }

    @GetMapping("/city/{cityId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CitizenResponseDTO>> getAllCitizenByCityId(@PathVariable Long cityId){
        return ResponseEntity.ok(mapper.mapCitizensToResponseDtoList(citizenService.getAllCitizensByCityId(cityId)));
    }

    @GetMapping("/province/{provinceId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CitizenResponseDTO>> getAllCitizenByProvinceId(@PathVariable Long provinceId){
        return ResponseEntity.ok(mapper.mapCitizensToResponseDtoList(citizenService.getAllCitizensByCityId(provinceId)));
    }

    @GetMapping("/id")
    public ResponseEntity<CitizenResponseDTO> getCitizenById(@AuthenticationPrincipal Citizen citizen){
        return ResponseEntity.ok(mapper.mapCitizenToResponseDTO(citizenService.getCitizenById(citizen.getId())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitizenResponseDTO> getCitizenById(@PathVariable Long id){
        return ResponseEntity.ok(mapper.mapCitizenToResponseDTO(citizenService.getCitizenById(id)));
    }

    @PutMapping("/verify")
    public ResponseEntity<String> verifyAccount(@RequestParam String otp){
        return ResponseEntity.ok(citizenService.verifyAccount(otp));
    }

    @PutMapping("/new-otp")
    public ResponseEntity<String> generateOtpForCitizen(@RequestParam String phone){
        return ResponseEntity.ok(citizenService.generateOtpForCitizen(phone));
    }

    @PutMapping("/update-citizen")
    public ResponseEntity<String> updateCitizen(@RequestBody CitizenRequestDTO citizenRequest, @AuthenticationPrincipal Citizen citizen){
        return ResponseEntity.ok(
                citizenService.updateCitizen(
                        mapper.mapCitizenRequestToModel(citizenRequest),
                        citizen.getId(),
                        citizenRequest.getCityId()
                )
        );
    }

}
