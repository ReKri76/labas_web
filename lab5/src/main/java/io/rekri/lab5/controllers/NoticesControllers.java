package io.rekri.lab5.controllers;

import io.rekri.lab5.DTO.Notice;
import io.rekri.lab5.DTO.Picture;
import io.rekri.lab5.service.NoticesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequiredArgsConstructor
public class NoticesControllers {

    private final NoticesService service;

    @GetMapping("/")
    public String index(final Model model){
        final var texts = service.getAllNotices();
        model.addAttribute("texts", texts);
        return "index";
    }

    @PostMapping("/create-notice")
    public String createNotice(final Authentication authentication, @ModelAttribute final Notice notice,
                               @RequestParam(value = "image", required = false) final MultipartFile file) throws IOException {
        final var picture = (file != null && !file.isEmpty()) ? new Picture(file) : null;
        service.createNotice(notice, picture, authentication.getName());
        return "redirect:/";
    }

    @DeleteMapping("/delete-notice/{id}")
    public String deleteNotice(@PathVariable(name = "id") Integer id){
        service.deleteNotice(id);
        return "redirect:/";
    }

    @GetMapping("/image/{name}")
    public InputStream image(@PathVariable("name") String name){
        return service.getPictureByName(name);
    }
}
