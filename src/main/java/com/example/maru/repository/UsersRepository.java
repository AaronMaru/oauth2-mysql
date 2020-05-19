package com.example.maru.repository;

import com.example.maru.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by : maru
 * Date  : 5/18/2020
 * Time  : 4:10 PM
 */

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByName(String username);
}
