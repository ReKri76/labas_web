package io.rekri.lab5.DTO;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse{

    @Nonnull
    private Integer id;
    @Nonnull
    private String nickname;
    @Nonnull
    private String role;
}
