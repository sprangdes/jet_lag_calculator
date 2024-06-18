package com.jerry.jet_lag_calculator.repository;

import com.jerry.jet_lag_calculator.entity.CityTimeZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityTimeZoneRepository extends JpaRepository<CityTimeZone, Long> {
}
