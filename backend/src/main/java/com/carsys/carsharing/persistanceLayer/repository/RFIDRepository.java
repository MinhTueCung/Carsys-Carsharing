package com.carsys.carsharing.persistanceLayer.repository;

import com.carsys.carsharing.persistanceLayer.model.RFID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface RFIDRepository extends JpaRepository<RFID, UUID> {
    Optional<RFID> findById(UUID id);

    List<RFID> findAll();
}