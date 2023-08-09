package com.cory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cory.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
} 