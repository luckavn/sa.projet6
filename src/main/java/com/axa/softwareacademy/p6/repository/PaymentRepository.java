package com.axa.softwareacademy.p6.repository;

import com.axa.softwareacademy.p6.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
