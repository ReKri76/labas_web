package io.rekri.lab5.DTO;

import lombok.Data;

/**
 * Данные новости, отправляемые при её публикации.
 */
@Data
public class NewsRequest {
    /** Заголовок новости. */
    private String head;

    /** Текст новости. */
    private String content;
}
