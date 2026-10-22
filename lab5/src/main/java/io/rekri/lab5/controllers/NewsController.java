package io.rekri.lab5.controllers;

import io.rekri.lab5.DTO.NewsRequest;
import io.rekri.lab5.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Обрабатывает HTTP-запросы на публикацию новостей.
 */
@Controller
@RequiredArgsConstructor
public class NewsController {

    private final NewsService service;

    /**
     * Создаёт новость от имени текущего пользователя и перенаправляет на главную страницу.
     *
     * @param authentication данные аутентификации текущего пользователя
     * @param news заголовок и текст новой новости
     * @return адрес главной страницы
     */
    @PostMapping("/create-news")
    public String createNews(final Authentication authentication, final NewsRequest news) {
        service.createNews(news, authentication.getName());
        return "redirect:/";
    }
}
