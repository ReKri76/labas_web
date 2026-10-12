package io.rekri.lab5.service;

import io.rekri.lab5.DTO.Notice;
import io.rekri.lab5.DTO.Picture;
import io.rekri.lab5.entities.Notices;
import io.rekri.lab5.repository.NoticesRepository;
import io.rekri.lab5.repository.UsersRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticesService {

    final private NoticesRepository noticesRepository;
    final private UsersRepository usersRepository;
    final private ImagesService imagesService;

    public List<Notice> getAllNotices(){
        return noticesRepository.findAll().stream().map(notice -> {
            return new Notice(notice.getHead(), notice.getContent(), notice.getAuthor().getName());
        }).toList();
    }

    public void createNotice(Notice notice, @Nullable Picture picture){
        if (picture!=null)
            imagesService.save(picture.getName(), picture.getInputStream());

        noticesRepository.save(new Notices.Builder()
                .content(notice.getContent())
                .head(notice.getHeader())
                .picture(picture.getName()) // TODO: имя может можно будет генернировать лучше я хз
                .author(usersRepository.findByName(notice.getAuthor()).orElseThrow())
                .build());
    }
}
