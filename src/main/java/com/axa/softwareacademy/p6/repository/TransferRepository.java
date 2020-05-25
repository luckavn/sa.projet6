package com.axa.softwareacademy.p6.repository;

import com.axa.softwareacademy.p6.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {
}
