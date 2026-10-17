package io.rekri.lab5.repository;

import io.rekri.lab5.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer>{
    Optional<Users> findByName(String name);

    @Query("from Users u left join fetch u.notices where u.name = :name")
    Optional<Users> loadFullUser(@Param("name")String name);
}
