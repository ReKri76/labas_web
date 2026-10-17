package io.rekri.lab5.service;

import io.rekri.lab5.DTO.NoticeResponse;
import io.rekri.lab5.DTO.User;
import io.rekri.lab5.DTO.UserResponse;
import io.rekri.lab5.config.security.Roles;
import io.rekri.lab5.entities.Users;
import io.rekri.lab5.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для управления пользователями и их объявлениями.
 */
@Service
@RequiredArgsConstructor
public class UsersService {

    final private UsersRepository repository;
    final private PasswordEncoder passwordEncoder;

    /**
     * Создаёт пользователя с ролью {@code USER}, сохраняя пароль в закодированном виде.
     *
     * @param user данные создаваемого пользователя
     */
    public void createUser(final User user){
        repository.save(new Users.Builder()
                .name(user.getNickname())
                .role(Roles.ROLE_USER.toString())
                .password(passwordEncoder.encode(user.getPassword()))
                .build()
        );
    }

    /**
     * Возвращает список пользователей без паролей.
     *
     * @return список пользователей с идентификаторами, именами и ролями
     */
    public List<UserResponse> getAllUsers(){
        return repository.findAll().stream().map(
                users -> {return new UserResponse(users.getId(), users.getName(), users.getRole());}
        ).toList();
    }

    /**
     * Назначает пользователю роль {@code USER}.
     *
     * @param id идентификатор пользователя
     * @throws java.util.NoSuchElementException если пользователь не найден
     */
    @Transactional
    public void changeRoleToUser(final Integer id){
        repository.findById(id).orElseThrow().setRole(Roles.ROLE_USER.toString());
    }

    /**
     * Назначает пользователю роль {@code MODERATOR}.
     *
     * @param id идентификатор пользователя
     * @throws java.util.NoSuchElementException если пользователь не найден
     */
    @Transactional
    public void changeRoleToModer(final Integer id){
        repository.findById(id).orElseThrow().setRole(Roles.ROLE_MODERATOR.toString());
    }

    /**
     * Назначает пользователю роль {@code ADMIN}.
     *
     * @param id идентификатор пользователя
     * @throws java.util.NoSuchElementException если пользователь не найден
     */
    @Transactional
    public void changeRoleToAdmin(final Integer id){
        repository.findById(id).orElseThrow().setRole(Roles.ROLE_ADMIN.toString());
    }

    /**
     * Удаляет пользователя по идентификатору.
     *
     * @param id идентификатор пользователя
     */
    @Transactional
    public void deleteUser(final Integer id) {
        repository.deleteById(id);
    }

    /**
     * Возвращает объявления указанного пользователя.
     *
     * @param userName имя пользователя, чьи объявления нужно получить
     * @return список объявлений пользователя
     * @throws java.util.NoSuchElementException если пользователь не найден
     */
    @Transactional
    public List<NoticeResponse> getAllNoticesByUsername(final String userName){
        return repository.loadFullUser(userName).orElseThrow().getNotices().stream()
                .<NoticeResponse>map(notice -> NoticeResponse.builder()
                        .id(notice.getId())
                        .picture(notice.getPicture())
                        .header(notice.getHead())
                        .content(notice.getContent())
                        .author(notice.getAuthor().getName())
                        .build())
                .toList();
    }
}
