package com.cydeo.repository;

import com.cydeo.entity.Transaction;
import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Transaction, Long> {


    User findByUserName(String username);
}
