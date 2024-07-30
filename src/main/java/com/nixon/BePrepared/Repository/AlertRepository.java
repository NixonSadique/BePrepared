package com.nixon.BePrepared.Repository;

import com.nixon.BePrepared.Model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert,Long> {

    List<Alert> findAllByActive(boolean isActive);

    List<Alert> findAllByActiveAndCityId(boolean isActive, Long cityId);

    List<Alert> findAllByActiveAndProvinceId(boolean isActive, Long provinceId);

    Long countByActive(boolean isActive);
}
