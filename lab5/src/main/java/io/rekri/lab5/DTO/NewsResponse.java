package io.rekri.lab5.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NewsResponse {
    private Integer id;
    private String head;
    private String content;
    private String author;
    private String createdAt;
}
