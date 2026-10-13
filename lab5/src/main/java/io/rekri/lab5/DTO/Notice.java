package io.rekri.lab5.DTO;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Notice {
    @Nullable
    private String header;
    @Nonnull
    private String content;
}
