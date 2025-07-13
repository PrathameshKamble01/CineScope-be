package com.cinescopefinal.CineScope.repository;

import com.cinescopefinal.CineScope.entities.Role;
import com.cinescopefinal.CineScope.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    Users findByRole(Role role);
    @Query("SELECT u FROM Users u")
    List<Users> getAllUsers();
}
