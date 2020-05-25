package com.axa.softwareacademy.p6.repository;

import com.axa.softwareacademy.p6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    Boolean existsByEmail(String email);

    List<User> findByEmail(String email);

    User findUserByEmail(String email);

    User findById(int id);
}
