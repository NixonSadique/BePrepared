package com.nixon.BePrepared.Service.impl;

import com.nixon.BePrepared.Exception.BadRequestException;
import com.nixon.BePrepared.Exception.EntityNotFoundException;
import com.nixon.BePrepared.Model.Citizen;
import com.nixon.BePrepared.Model.City;
import com.nixon.BePrepared.Model.enums.Role;
import com.nixon.BePrepared.Repository.CitizenRepository;
import com.nixon.BePrepared.Service.CitizenService;
import com.nixon.BePrepared.Service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CitizenServiceImpl implements CitizenService {

    private final LocationService locationService;
    private final CitizenRepository citizenRepository;
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public String createCitizen(Citizen citizen, Long cityId) {
        if (this.citizenRepository.existsByPhone(citizen.getPhone())){
            throw new BadRequestException("Um cidadao com esse contacto ja existe!");
        }
        City city = locationService.getCityById(cityId);
        citizen.setCity(city);
        citizen.setVerified(false);
        citizen.setRole(Role.USER);
        citizen.setOtp(generateOtp(6));
        var savedCitizen = citizenRepository.save(citizen);

        return "Cidadao criado com Sucesso!\nO Seu Codigo de verificacao e: " + savedCitizen.getOtp();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Citizen> getAllCitizens() {
        return citizenRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Citizen> getAllCitizensByCityId(Long cityId) {
        return citizenRepository.findAllByCityId(cityId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Citizen> getAllCitizensByProvinceId(Long provinceId) {
        return citizenRepository.findAllByCityProvinceId(provinceId);
    }

    @Override
    @Transactional(readOnly = true)
    public Citizen getCitizenById(Long id) {
        return citizenRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("O cidadao com esse id nao foi encontrado!")
        );
    }

    @Override
    @Transactional
    public String verifyAccount(String otp) {
        Citizen citizen = citizenRepository.findByOtp(otp).orElseThrow(
                () -> new EntityNotFoundException("O cidadao com esse id nao foi encontrado!@")
        );
        citizen.setVerified(true);
        citizen.setOtp(null);
        citizenRepository.save(citizen);
        return "A conta foi Verificada com Sucesso!";
    }

    @Override
    @Transactional
    public String generateOtpForCitizen(String phone) {
        Citizen citizen = citizenRepository.findByPhone(phone).orElseThrow(
                () -> new EntityNotFoundException("Cidadao nao encontrado, nao foi possivel gerar o seu OTP!!!")
        );
        citizen.setOtp(null);
        String otp = generateOtp(6);

        citizen.setOtp(encoder.encode(otp));

        return "O Seu Codigo de acesso e: " + otp;
    }

    @Override
    public String updateCitizen(Citizen citizen, Long citizenId, Long cityId) {

        Citizen updatedCitizen = this.citizenRepository.findById(citizenId).orElseThrow();

        updatedCitizen.setPhone(citizen.getPhone());
        updatedCitizen.setDeviceId(citizen.getDeviceId());
        updatedCitizen.setCity(locationService.getCityById(cityId));

        citizenRepository.save(updatedCitizen);

        return "Ciidadao Atualizado com Sucesso!!";
    }

    private static String generateOtp(int length){
        String otp = "";
        int x;
        char[] chars = new char[length];

        for (int i = 0; i < length; i++){
            Random random = new Random();
            x = random.nextInt(9);
            chars[i] = Integer.toString(x).toCharArray()[0];
        }
        return String.valueOf(chars).trim();
    }
}
