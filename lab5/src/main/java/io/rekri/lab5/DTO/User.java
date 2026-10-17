package io.rekri.lab5.DTO;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
public class User {

    @Nonnull
    private String nickname;
    @Nonnull
    private String password;
}
