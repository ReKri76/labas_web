package io.rekri.lab5.repository;

import io.rekri.lab5.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий для чтения и сохранения новостей.
 */
public interface NewsRepository extends JpaRepository<News, Integer> {
    /**
     * Возвращает новости от новых к старым; при одинаковой дате сначала идут записи
     * с большим идентификатором.
     *
     * @return список новостей в порядке публикации
     */
    List<News> findAllByOrderByCreatedAtDescIdDesc();
}
