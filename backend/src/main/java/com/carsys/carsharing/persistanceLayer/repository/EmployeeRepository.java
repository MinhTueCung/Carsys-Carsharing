package com.carsys.carsharing.persistanceLayer.repository;

import com.carsys.carsharing.persistanceLayer.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findById(UUID id);

    @Query("from Employee")
    List<Employee> findAll();
}