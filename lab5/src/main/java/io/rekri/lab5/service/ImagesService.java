package io.rekri.lab5.service;

import io.rekri.lab5.DTO.Picture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
public class ImagesService {

    private final Path root;

    public ImagesService(
            @Value("${app.images.dir:./uploads}") String dir
    ) {
        root = Path.of(dir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Cannot create images directory", e);
        }
    }

    public void save(Picture picture) {
        String name = picture.getName();
        Path path = resolve(name);
        boolean created = false;

        try (InputStream in = picture.getInputStream()) {
            try (var out = Files.newOutputStream(
                    path,
                    StandardOpenOption.CREATE_NEW,
                    StandardOpenOption.WRITE
            )) {
                created = true;
                in.transferTo(out);
            } catch (IOException e) {
                if (created) {
                    try {
                        Files.deleteIfExists(path);
                    } catch (IOException ignored) {
                    }
                }
                throw e;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image: " + name, e);
        }
    }

    public InputStream get(String name) {
        try {
            return Files.newInputStream(resolve(name));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read image: " + name, e);
        }
    }

    public void delete(String name) {
        if (name == null || name.isBlank()) {
            return;
        }

        try {
            Files.deleteIfExists(resolve(name));
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image: " + name, e);
        }
    }

    private Path resolve(String name) {
        if (name == null || name.isBlank()
                || name.contains("/") || name.contains("\\")
                || name.equals(".") || name.equals("..")) {
            throw new IllegalArgumentException("Invalid image name");
        }

        Path path = root.resolve(name).normalize();

        if (!path.startsWith(root)) {
            throw new IllegalArgumentException("Invalid image name");
        }

        return path;
    }
}