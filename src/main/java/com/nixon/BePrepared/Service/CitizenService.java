package com.nixon.BePrepared.Service;


import com.nixon.BePrepared.Model.Citizen;

import java.util.List;

public interface CitizenService {

    String createCitizen(Citizen citizen, Long cityId);

    List<Citizen> getAllCitizens();

    List<Citizen> getAllCitizensByCityId(Long cityId);

    List<Citizen> getAllCitizensByProvinceId(Long provinceId);

    Citizen getCitizenById(Long id);

    String verifyAccount(String otp);

    String generateOtpForCitizen(String phone);

    String updateCitizen(Citizen citizen, Long citizenId, Long cityId);
}
