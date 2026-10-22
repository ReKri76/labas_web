package io.rekri.lab5.controllers;

import io.rekri.lab5.service.NewsService;
import io.rekri.lab5.service.NoticesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;

/**
 * Собирает данные и отображает главную страницу сайта.
 */
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final NewsService newsService;
    private final NoticesService noticesService;

    /**
     * Загружает новости и объявления для главной страницы.
     *
     * @param model модель данных представления
     * @return имя представления главной страницы
     */
    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("news", newsService.getAllNews());
        model.addAttribute("texts", noticesService.getAllNotices());
        model.addAttribute("time", Instant.now());
        model.addAttribute("countOfView", noticesService.countOfView());
        return "index";
    }
}
