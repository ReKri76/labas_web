package io.rekri.lab5.DTO;

import lombok.Builder;
import lombok.Getter;

/**
 * Данные новости, подготовленные для отображения.
 */
@Getter
@Builder
public class NewsResponse {
    /** Идентификатор новости. */
    private Integer id;

    /** Заголовок новости. */
    private String head;

    /** Текст новости. */
    private String content;

    /** Имя автора новости. */
    private String author;

    /** Дата создания новости. */
    private String createdAt;
}
