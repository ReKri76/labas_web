package io.rekri.lab5.repository;

import io.rekri.lab5.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer>{
    Optional<Users> findByName(String name);
}
