package com.carsys.carsharing.persistanceLayer.repository;

import com.carsys.carsharing.persistanceLayer.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import com.carsys.carsharing.persistanceLayer.model.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    Optional<Booking> findById(String bookingID);

    List<Booking> findByMember_Id(UUID memberID);

    List<Booking> findByCar_Id(UUID carID);

    List<Booking> findAllByMember(Member member);
}