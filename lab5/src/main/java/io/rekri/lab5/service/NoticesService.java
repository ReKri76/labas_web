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
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticesService {

    final private NoticesRepository noticesRepository;
    final private UsersRepository usersRepository;
    final private ImagesService imagesService;

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

    public void createNotice(final Notice notice, @Nullable final Picture picture, final String author)
            throws IOException {
        if (picture!=null)
            imagesService.save(picture);

        noticesRepository.save(new Notices.Builder()
                .content(notice.getContent())
                .head(notice.getHeader())
                .picture(picture != null ? picture.getName() : null)
                .author(usersRepository.findByName(author).orElseThrow())
                .build());
    }

    public void deleteNotice(final Integer id){
        final var notice = noticesRepository.findById(id).orElseThrow();
        imagesService.delete(notice.getPicture());
        noticesRepository.delete(notice);
    }

    public InputStream getPictureByName(final String name) {
        return imagesService.get(name);
    }
}
