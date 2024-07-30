package com.nixon.BePrepared.Controller;


import com.nixon.BePrepared.DTO.Response.CityResponseDTO;
import com.nixon.BePrepared.DTO.Response.ProvinceResponseDTO;
import com.nixon.BePrepared.Mapper.Mapper;
import com.nixon.BePrepared.Service.LocationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
@Tag(name = "4.Location Controller")
public class LocationController {

    private final Mapper mapper;
    private final LocationService locationService;

    @GetMapping("/provinces")
    public ResponseEntity<List<ProvinceResponseDTO>> getAllProvinces(){
        return ResponseEntity.ok(mapper.mapProvinceToResponseDtoList(
                locationService.getAllProvinces()
        ));
    }

    @GetMapping("/province")
    public ResponseEntity<ProvinceResponseDTO> getProvincesById(@RequestParam Long id){
        return ResponseEntity.ok(mapper.mapProvinceToResponseDto(
                locationService.getProvinceById(id)
        ));
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityResponseDTO>> getAllCities(){
        return ResponseEntity.ok(mapper.mapCitiesToResponseDtoList(
                locationService.getAllCities()
        ));
    }

    @GetMapping("/cities/{provinceId}")
    public ResponseEntity<List<CityResponseDTO>> getCityByProvinceId(@PathVariable Long provinceId){
        return ResponseEntity.ok(mapper.mapCitiesToResponseDtoList(
                locationService.getAllCitiesOfProvince(provinceId)
        ));
    }

    @GetMapping("/city")
    public ResponseEntity<CityResponseDTO> getCityById(@RequestParam Long id){
        return ResponseEntity.ok(mapper.mapCityToResponseDto(
                locationService.getCityById(id)
        ));
    }

}
