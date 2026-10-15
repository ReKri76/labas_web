package io.rekri.lab5.controllers;

import io.rekri.lab5.DTO.User;
import io.rekri.lab5.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UsersController {

    private final UsersService service;
    private final UserDetailsService userDetailsService;
    private final SecurityContextRepository securityContextRepository;
    private final SecurityContextHolderStrategy securityContextHolderStrategy;

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

    @GetMapping("/cabinet")
    public String cabinet(){
        return "cabinet";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

}
