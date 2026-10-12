package io.rekri.lab5.DTO;

import jakarta.annotation.Nonnull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Data
public class Picture {

    public Picture(@Nonnull MultipartFile file) throws IOException {
        this.setInputStream(file.getInputStream());
        this.setName(UUID.randomUUID() + "_" + System.currentTimeMillis());
        this.setSize(file.getSize());
        this.setContentType(file.getContentType() != null ? file.getContentType() : "application/octet-stream");
    }

    @Nonnull
    private InputStream inputStream;
    private long size;
    @Nonnull
    private String name;
    @Nonnull
    private String contentType;
}
