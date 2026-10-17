package io.rekri.lab5.controllers;

import io.rekri.lab5.DTO.Notice;
import io.rekri.lab5.DTO.Picture;
import io.rekri.lab5.service.NoticesService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;

/**
 * Обрабатывает HTTP-запросы, связанные с просмотром и управлением объявлениями.
 */
@Controller
@RequiredArgsConstructor
public class NoticesControllers {

    private final NoticesService service;

    /**
     * Показывает главную страницу со списком объявлений.
     *
     * @param model модель данных для представления
     * @return имя главного представления
     */
    @GetMapping("/")
    public String index(final Model model){
        final var texts = service.getAllNotices();
        model.addAttribute("texts", texts);
        model.addAttribute("time", Instant.now());
        model.addAttribute("countOfView", service.countOfView());
        return "index";
    }

    /**
     * Создаёт объявление от имени текущего пользователя и возвращает на главную страницу.
     *
     * @param authentication данные аутентификации текущего пользователя
     * @param notice данные создаваемого объявления
     * @param file необязательное изображение объявления
     * @return адрес перенаправления на главную страницу
     * @throws IOException если не удалось прочитать загруженное изображение
     */
    @PostMapping("/create-notice")
    public String createNotice(final Authentication authentication, final Notice notice,
                               @RequestParam(value = "image", required = false) final MultipartFile file) throws IOException {
        final var picture = (file != null && !file.isEmpty()) ? new Picture(file) : null;
        service.createNotice(notice, picture, authentication.getName());
        return "redirect:/";
    }

    /**
     * Удаляет объявление, если текущий пользователь имеет на это право.
     *
     * @param id идентификатор удаляемого объявления
     * @param userDetails данные текущего пользователя
     * @return ответ с HTTP-статусом 200 при успешном удалении
     */
    @DeleteMapping("/delete-notice/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteNotice(@PathVariable(name = "id") Integer id,
                                             @AuthenticationPrincipal UserDetails userDetails) {

        service.deleteNotice(id, userDetails.getUsername());
        return ResponseEntity.ok().build();
    }
}
