package io.rekri.lab5.DTO;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class NoticeResponse extends Notice{
    @Nonnull
    private Integer id;
    @Nullable
    private String picture;
    @Nonnull
    private String author;
}
