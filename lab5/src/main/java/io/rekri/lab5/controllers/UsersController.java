package io.rekri.lab5.controllers;

import io.rekri.lab5.DTO.User;
import io.rekri.lab5.config.security.Roles;
import io.rekri.lab5.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Обрабатывает HTTP-запросы регистрации, входа и управления личным кабинетом и пользователями.
 */
@Controller
@RequiredArgsConstructor
public class UsersController {

    private final UsersService service;
    private final UserDetailsService userDetailsService;
    private final SecurityContextRepository securityContextRepository;
    private final SecurityContextHolderStrategy securityContextHolderStrategy;

    /**
     * Регистрирует пользователя, аутентифицирует его и перенаправляет на главную страницу.
     *
     * @param user данные нового пользователя
     * @param request текущий HTTP-запрос
     * @param response текущий HTTP-ответ
     * @return адрес перенаправления на главную страницу
     */
    @PostMapping("/register")
    public String register(final User user,
                           final HttpServletRequest request,
                           final HttpServletResponse response) {

        service.createUser(user);

        final var details = userDetailsService.loadUserByUsername(user.getNickname());

        final var auth = UsernamePasswordAuthenticationToken.authenticated(
                details, null, details.getAuthorities());

        final var context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(auth);
        securityContextRepository.saveContext(context, request, response);

        return "redirect:/";
    }

    /**
     * Показывает личный кабинет и список объявлений текущего пользователя.
     * Список пользователей добавляется в модель только для администратора.
     *
     * @param model модель данных для представления
     * @param authentication данные аутентификации текущего пользователя
     * @param userDetails данные текущего пользователя
     * @return имя представления личного кабинета
     */
    @GetMapping("/cabinet")
    public String cabinet(final Model model, final Authentication authentication, @
            AuthenticationPrincipal UserDetails userDetails) {
        final var isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority ->
                        Roles.ROLE_ADMIN.toString().equals(authority.getAuthority()));

        if (isAdmin)
            model.addAttribute("users", service.getAllUsers());

        model.addAttribute("notices", service.getAllNoticesByUsername(userDetails.getUsername()));

        return "cabinet";
    }

    /**
     * Назначает пользователю роль {@code USER}.
     *
     * @param id идентификатор пользователя
     * @return ответ с HTTP-статусом 200 при успешном изменении роли
     */
    @PatchMapping("/users/make-user/{id}")
    public ResponseEntity<Void> makeUser(@PathVariable("id") Integer id){
        service.changeRoleToUser(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Назначает пользователю роль {@code MODERATOR}.
     *
     * @param id идентификатор пользователя
     * @return ответ с HTTP-статусом 200 при успешном изменении роли
     */
    @PatchMapping("/users/make-moder/{id}")
    public ResponseEntity<Void> makeModer(@PathVariable("id") Integer id){
        service.changeRoleToModer(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Назначает пользователю роль {@code ADMIN}.
     *
     * @param id идентификатор пользователя
     * @return ответ с HTTP-статусом 200 при успешном изменении роли
     */
    @PatchMapping("/users/make-admin/{id}")
    public ResponseEntity<Void> makeAdmin(@PathVariable("id") Integer id){
        service.changeRoleToAdmin(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Удаляет пользователя по идентификатору.
     *
     * @param id идентификатор удаляемого пользователя
     * @return ответ с HTTP-статусом 200 при успешном удалении
     */
    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        service.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Показывает страницу входа.
     *
     * @return имя представления страницы входа
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Показывает страницу регистрации.
     *
     * @return имя представления страницы регистрации
     */
    @GetMapping("/register")
    public String register() {
        return "register";
    }

}
