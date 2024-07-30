package com.nixon.BePrepared.Service;

import com.nixon.BePrepared.Model.Alert;

import java.util.List;

public interface AlertService {

    String createAlert(Alert alert, Long cityId, Long provinceId);

    List<Alert> getAllAlerts();

    List<Alert> getAllAlertsByCityId(Long cityId);

    List<Alert> getAllAlertsByProvinceId(Long provinceId);

    List<Alert> getAllActiveAlerts();

    Alert getAlertById(Long alertId);

    String activateAlert(Long alertId);
}
