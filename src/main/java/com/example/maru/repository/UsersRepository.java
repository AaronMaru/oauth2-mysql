package com.example.maru.repository;

import com.example.maru.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by : maru
 * Date  : 5/18/2020
 * Time  : 4:10 PM
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query(value = "SELECT * FROM `user` WHERE email = :username OR `name` = :username", nativeQuery=true)
    Optional<Users> findUser(String username);

}
