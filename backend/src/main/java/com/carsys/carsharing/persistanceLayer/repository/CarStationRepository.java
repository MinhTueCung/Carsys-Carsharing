package com.carsys.carsharing.persistanceLayer.repository;

import com.carsys.carsharing.persistanceLayer.model.CarStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface CarStationRepository extends JpaRepository<CarStation, UUID> {
    Optional<CarStation> findById(UUID id);

    List<CarStation> findAll();
}