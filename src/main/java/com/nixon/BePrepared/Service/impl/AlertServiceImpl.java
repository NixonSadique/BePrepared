package com.nixon.BePrepared.Service.impl;


import com.nixon.BePrepared.Exception.EntityNotFoundException;
import com.nixon.BePrepared.Model.Alert;
import com.nixon.BePrepared.Model.City;
import com.nixon.BePrepared.Model.Province;
import com.nixon.BePrepared.Repository.AlertRepository;
import com.nixon.BePrepared.Service.AlertService;
import com.nixon.BePrepared.Service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;

    private final LocationService locationService;

    @Override
    @Transactional
    public String createAlert(Alert alert, Long cityId, Long provinceId) {
        City city = this.locationService.getCityById(cityId);
        Province province = this.locationService.getProvinceById(provinceId);
        alert.setActive(false);
        alert.setCity(city);
        alert.setProvince(province);
        alertRepository.save(alert);
        return "Alerta criado com sucesso!";
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alert> getAllAlerts() {
        return this.alertRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alert> getAllAlertsByCityId(Long cityId) {
        return this.alertRepository.findAllByActiveAndCityId(true, cityId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alert> getAllAlertsByProvinceId(Long provinceId) {
        return this.alertRepository.findAllByActiveAndProvinceId(true, provinceId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alert> getAllActiveAlerts() {
        return this.alertRepository.findAllByActive(true);
    }

    @Override
    public Alert getAlertById(Long alertId) {
        return alertRepository.findById(alertId).orElseThrow(
                () -> new EntityNotFoundException("O alerta nao foi encontrado!")
        );
    }

    @Override
    @Transactional
    public String activateAlert(Long alertId) {

        Alert alert = this.getAlertById(alertId);
        alert.setActive(true);
        this.alertRepository.save(alert);

        //citizensService.sendAlertNotification();

        return "Perigo, proteja-se!";
    }
}
