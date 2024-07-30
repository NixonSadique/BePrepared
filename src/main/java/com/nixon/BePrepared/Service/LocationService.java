package com.nixon.BePrepared.Service;

import com.nixon.BePrepared.Model.City;
import com.nixon.BePrepared.Model.Province;

import java.util.List;

public interface LocationService {

    List<Province> getAllProvinces();

    List<City> getAllCities();

    List<City> getAllCitiesOfProvince(Long id);

    Province getProvinceById(Long provinceId);

    City getCityById(Long CityId);

}
