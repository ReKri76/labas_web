package io.rekri.lab5.DTO;

import jakarta.annotation.Nonnull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Data
public class Picture {

    public static final Long MAX_IMAGE_SIZE = (1024L * 1024L) * 2 / 3 - 1;

    public Picture(@Nonnull MultipartFile file) throws IOException, IllegalArgumentException{
        if (file.isEmpty() || file.getSize() > MAX_IMAGE_SIZE)
            throw new IllegalArgumentException("Incorrect picture");
        this.setBase64(Base64.getEncoder().encodeToString(file.getBytes()));
    }

    @Nonnull
    private String base64;
}
