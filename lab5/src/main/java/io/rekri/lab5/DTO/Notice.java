package io.rekri.lab5.DTO;

import jakarta.annotation.Nonnull;
import lombok.Data;

@Data
public class Notice {
    @Nonnull
    @NotBlank
    private String header;
    @Nonnull
    @NotBlank
    private String content;
}
