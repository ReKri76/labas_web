package io.rekri.lab5.service;

import io.rekri.lab5.DTO.Picture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class ImagesService {

     final public static String PATH_TO_SAVE_PICTURE = "./src/main/resources/images/";

    public void save(Picture picture) throws IOException{

        try (var in = picture.getInputStream()) {
            Files.copy(in, Path.of(PATH_TO_SAVE_PICTURE+picture.getName()+"."+picture.getContentType()),
                    StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
