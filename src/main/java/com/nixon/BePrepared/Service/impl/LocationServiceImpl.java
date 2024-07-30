package com.nixon.BePrepared.Service.impl;

import com.nixon.BePrepared.Exception.EntityNotFoundException;
import com.nixon.BePrepared.Model.City;
import com.nixon.BePrepared.Model.Province;
import com.nixon.BePrepared.Repository.CityRepository;
import com.nixon.BePrepared.Repository.ProvinceRepository;
import com.nixon.BePrepared.Service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor/*Creates a constructor with all required arguments(int this case cityRepo and provinceRepo)*/
public class LocationServiceImpl implements LocationService {

    private final CityRepository cityRepo;

    private final ProvinceRepository provinceRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Province> getAllProvinces() {
        return provinceRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> getAllCities() {
        return cityRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> getAllCitiesOfProvince(Long id) {
        return cityRepo.findAllByProvinceId(id);
    }

    @Override
    public Province getProvinceById(Long provinceId) {
        return provinceRepo.findById(provinceId).orElseThrow(() -> new EntityNotFoundException("" +
                "A Provincia com esse id nao foi encontrada!"));
    }

    @Override
    @Transactional(readOnly = true)
    public City getCityById(Long cityId) {
        return cityRepo.findById(cityId).orElseThrow(() -> new EntityNotFoundException("" +
                "O Distrito com esse id nao foi encontrado!"));
    }
}
