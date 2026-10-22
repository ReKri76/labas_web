package io.rekri.lab5.controllers;

import io.rekri.lab5.service.NewsService;
import io.rekri.lab5.service.NoticesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final NewsService newsService;
    private final NoticesService noticesService;

    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("news", newsService.getAllNews());
        model.addAttribute("texts", noticesService.getAllNotices());
        model.addAttribute("time", Instant.now());
        model.addAttribute("countOfView", noticesService.countOfView());
        return "index";
    }
}
