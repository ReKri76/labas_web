package io.rekri.lab5.repository;

import io.rekri.lab5.entities.Notices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticesRepository extends JpaRepository<Notices, Integer> {
}
