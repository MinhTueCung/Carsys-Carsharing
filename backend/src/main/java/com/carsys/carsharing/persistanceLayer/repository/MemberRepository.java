package com.carsys.carsharing.persistanceLayer.repository;

import com.carsys.carsharing.persistanceLayer.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findById(UUID id);

    @Query("from Member")
    List<Member> findAll();

    Optional<Member> findByLoginId(UUID id);
}