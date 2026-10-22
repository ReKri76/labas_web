package io.rekri.lab5.repository;

import io.rekri.lab5.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findAllByOrderByCreatedAtDescIdDesc();
}
