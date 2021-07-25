package com.carsys.carsharing.persistanceLayer.repository;

import com.carsys.carsharing.persistanceLayer.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Optional<Admin> findById(UUID id);
    @Query("from Admin")
    List<Admin> findAll();
}