package com.axa.softwareacademy.p6.repository;

import com.axa.softwareacademy.p6.model.Account;
import com.axa.softwareacademy.p6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Boolean existsByEmail(String email);

    List<User> findByEmail(String email);

    User findUserByEmail(String email);

    User findById(int id);

    User findByAccount(Account account);
}
