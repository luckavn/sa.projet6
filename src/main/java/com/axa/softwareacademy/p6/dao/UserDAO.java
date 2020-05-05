package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;

@Transactional
public interface UserDAO extends JpaRepository<User, Integer> {
}
