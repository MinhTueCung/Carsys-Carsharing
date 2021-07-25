package com.carsys.carsharing.persistanceLayer.repository;

import com.carsys.carsharing.persistanceLayer.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface LoginRepository extends JpaRepository<Login, UUID> {
    Optional<Login> findById(UUID id);

    Login findByUsername(String username);
    Login findByEmail(String email);
}