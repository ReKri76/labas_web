package io.rekri.lab5.DTO;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Notice {
    @Nullable
    private String header;
    @Nonnull
    private String content;
}
