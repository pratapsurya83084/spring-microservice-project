package com.Authmicroservice.Authmicroservice.repository;

import com.Authmicroservice.Authmicroservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long> {

    Optional<User> findByUsername(String username);

}
