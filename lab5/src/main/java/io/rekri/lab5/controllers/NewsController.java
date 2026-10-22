package io.rekri.lab5.controllers;

import io.rekri.lab5.DTO.NewsRequest;
import io.rekri.lab5.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class NewsController {

    private final NewsService service;

    @PostMapping("/create-news")
    public String createNews(final Authentication authentication, final NewsRequest news) {
        service.createNews(news, authentication.getName());
        return "redirect:/";
    }
}
