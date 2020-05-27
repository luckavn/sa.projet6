package com.axa.softwareacademy.p6.repository;

import com.axa.softwareacademy.p6.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
