package io.rekri.lab5.service;

import io.rekri.lab5.DTO.Notice;
import io.rekri.lab5.DTO.NoticeResponse;
import io.rekri.lab5.DTO.Picture;
import io.rekri.lab5.entities.Notices;
import io.rekri.lab5.repository.NoticesRepository;
import io.rekri.lab5.repository.UsersRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticesService {

    final private NoticesRepository noticesRepository;
    final private UsersRepository usersRepository;

    public List<NoticeResponse> getAllNotices() {
        return noticesRepository.findAll().stream()
                .<NoticeResponse>map(notice -> NoticeResponse.builder()
                        .id(notice.getId())
                        .picture(notice.getPicture())
                        .header(notice.getHead())
                        .content(notice.getContent())
                        .author(notice.getAuthor().getName())
                        .build())
                .toList();
    }

    public void createNotice(final Notice notice, @Nullable final Picture picture, final String author){
        noticesRepository.save(new Notices.Builder()
                .content(notice.getContent())
                .head(notice.getHeader())
                .picture(picture != null ? picture.getBase64() : null)
                .author(usersRepository.findByName(author).orElseThrow())
                .build());
    }

    public void deleteNotice(final Integer id){
        noticesRepository.deleteById(id);
    }

    public int countOfView(){
        try {
            final var path = Path.of("countOfView");
            final var val = Integer.parseInt(Files.readString(path).trim()) + 1;
            Files.writeString(path, String.valueOf(val));
            return val;
        }
        catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
