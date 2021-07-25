package com.carsys.carsharing.persistanceLayer.repository;


import com.carsys.carsharing.persistanceLayer.model.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;


@Repository
public interface TariffRepository extends JpaRepository<Tariff, UUID> {
    Optional<Tariff> findById(UUID id);

    Tariff findByIdentifier(String identifier);
}