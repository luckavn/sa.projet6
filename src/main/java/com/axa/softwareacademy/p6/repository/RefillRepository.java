package com.axa.softwareacademy.p6.repository;

import com.axa.softwareacademy.p6.model.Refill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RefillRepository extends JpaRepository<Refill, Integer> {
}
