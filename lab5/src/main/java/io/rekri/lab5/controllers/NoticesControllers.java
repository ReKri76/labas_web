package io.rekri.lab5.controllers;

import io.rekri.lab5.DTO.Notice;
import io.rekri.lab5.DTO.Picture;
import io.rekri.lab5.service.NoticesService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class NoticesControllers {

    private final NoticesService service;

    @GetMapping("/")
    public String index(final Model model, final Authentication authentication){
        final var texts = service.getAllNotices();
        model.addAttribute("texts", texts);
        model.addAttribute("isAuthorized", authentication!=null && authentication.isAuthenticated());
        return "index";
    }

    @PostMapping("/create-notice")
    public String createNotice(@RequestPart("notice") Notice textToCreate,
                               @RequestPart(value = "image", required = false) @Nullable MultipartFile file) throws IOException {
        final var picture = file!=null ? new Picture(file) : null;
        service.createNotice(textToCreate, picture);

        return "redirect:/";
    }

    @DeleteMapping("/delete-notice/{id}")
    public String deleteNotice(@PathVariable(name = "id") Integer id){

    }
}
