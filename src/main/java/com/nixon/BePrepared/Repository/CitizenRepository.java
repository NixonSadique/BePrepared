package com.nixon.BePrepared.Repository;

import com.nixon.BePrepared.Model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen,Long> {
    boolean existsByPhone(String phone);

    List<Citizen> findAllByCityId(Long cityId);

    List<Citizen> findAllByCityProvinceId(Long provinceId);

    Optional<Citizen> findByOtp(String otp);

    Optional<Citizen> findByPhone(String phone);

}
