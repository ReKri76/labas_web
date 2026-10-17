package io.rekri.lab5.service;

import io.rekri.lab5.DTO.Notice;
import io.rekri.lab5.DTO.NoticeResponse;
import io.rekri.lab5.DTO.Picture;
import io.rekri.lab5.config.security.Roles;
import io.rekri.lab5.entities.Notices;
import io.rekri.lab5.repository.NoticesRepository;
import io.rekri.lab5.repository.UsersRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Сервис для получения, создания и удаления объявлений и учёта просмотров.
 */
@Service
@RequiredArgsConstructor
public class NoticesService {

    final private NoticesRepository noticesRepository;
    final private UsersRepository usersRepository;

    /**
     * Возвращает все объявления в формате, предназначенном для отображения.
     *
     * @return список объявлений с данными об авторе и изображении
     */
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

    /**
     * Создаёт объявление от имени указанного пользователя.
     *
     * @param notice текстовые данные объявления
     * @param picture необязательное изображение объявления
     * @param author имя пользователя, создающего объявление
     * @throws java.util.NoSuchElementException если пользователь не найден
     */
    public void createNotice(final Notice notice, @Nullable final Picture picture, final String author){
        noticesRepository.save(new Notices.Builder()
                .content(notice.getContent())
                .head(notice.getHeader())
                .picture(picture != null ? picture.getBase64() : null)
                .author(usersRepository.findByName(author).orElseThrow())
                .build());
    }

    /**
     * Удаляет объявление, если пользователь является администратором, модератором
     * или автором этого объявления.
     *
     * @param id идентификатор объявления
     * @param userName имя пользователя, запросившего удаление
     * @throws java.util.NoSuchElementException если объявление или пользователь не найдены
     */
    @Transactional
    public void deleteNotice(final Integer id, final String userName){
        final var notice = noticesRepository.findById(id).orElseThrow();
        final var user = usersRepository.findByName(userName).orElseThrow();

        if (user.getRole().equals(Roles.ROLE_ADMIN.toString()) || user.getRole().equals(Roles.ROLE_MODERATOR.toString())
            || notice.getAuthor().getName().equals(userName)) {
            noticesRepository.delete(notice);
        }
    }

    /**
     * Увеличивает и возвращает счётчик просмотров, хранящийся в файле {@code countOfView}.
     *
     * @return новое значение счётчика просмотров
     * @throws RuntimeException если файл счётчика не удалось прочитать или записать
     */
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
