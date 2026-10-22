package io.rekri.lab5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Новость, опубликованная пользователем на главной странице.
 */
@Entity
@Getter
@Setter
@Table(name = "\"News\"")
public class News {

    /** Идентификатор новости. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Заголовок новости. */
    @Column(name = "head")
    private String head;

    /** Текст новости. */
    @Column(name = "content", nullable = false)
    private String content;

    /** Пользователь, опубликовавший новость. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private Users author;

    /** Дата создания, установленная базой данных. */
    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private String createdAt;
}
