package com.nixon.BePrepared.Mapper;


import com.nixon.BePrepared.DTO.Request.AlertRequestDTO;
import com.nixon.BePrepared.DTO.Request.CitizenRequestDTO;
import com.nixon.BePrepared.DTO.Request.UserRequestDTO;
import com.nixon.BePrepared.DTO.Response.*;
import com.nixon.BePrepared.Model.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final ModelMapper modelMapper;

    public ProvinceResponseDTO mapProvinceToResponseDto(Province province){
        return modelMapper.map(province, ProvinceResponseDTO.class);
    }

    public List<ProvinceResponseDTO> mapProvinceToResponseDtoList(List<Province> provinces){
        return provinces.stream().map(this::mapProvinceToResponseDto)
                .collect(Collectors.toList());
    }

    public CityResponseDTO mapCityToResponseDto(City city){
        return modelMapper.map(city, CityResponseDTO.class);
    }

    public List<CityResponseDTO> mapCitiesToResponseDtoList(List<City> cities){
        return cities.stream().map(this::mapCityToResponseDto)
                .collect(Collectors.toList());
    }

    public Alert mapAlertRequestToAlertModel(AlertRequestDTO alertRequestDTO){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(alertRequestDTO, Alert.class);
    }

    public AlertResponseDTO mapAlertToAlertResponse(Alert alert){
        return AlertResponseDTO.builder()
                .id(alert.getId())
                .title(alert.getTitle())
                .message(alert.getMessage())
                .severity(alert.getSeverity())
                .province(alert.getProvince().getDesignation())
                .city(alert.getCity().getDesignation())
                .isActive(alert.getActive())
                .build();
    }

    public List<AlertResponseDTO> mapAlertToResponseList(List<Alert> alert){
        return alert.stream().map(this::mapAlertToAlertResponse)
                .collect(Collectors.toList());
    }

    public User mapUserRequestToUserModel(UserRequestDTO userRequestDTO){
        return modelMapper.map(userRequestDTO, User.class);
    }

    public UserResponseDTO mapUserToResponseDto(User user){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public List<UserResponseDTO> mapUserToResponseDtoList(List<User> users){
        return users.stream().map(this::mapUserToResponseDto)
                .collect(Collectors.toList());
    }


    public Citizen mapCitizenRequestToModel(CitizenRequestDTO citizenRequestDTO){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(citizenRequestDTO, Citizen.class);
    }

    public CitizenResponseDTO mapCitizenToResponseDTO(Citizen citizen){
        return CitizenResponseDTO.builder()
                .id(citizen.getId())
                .phone(citizen.getPhone())
                .deviceId(citizen.getDeviceId())
                .city(citizen.getCity().getDesignation())
                .province(citizen.getCity().getProvince().getDesignation())
                .verified(citizen.getVerified())
                .build();
    }

    public List<CitizenResponseDTO> mapCitizensToResponseDtoList(List<Citizen> citizens){
        return citizens.stream().map(this::mapCitizenToResponseDTO).collect(Collectors.toList());
    }
}
