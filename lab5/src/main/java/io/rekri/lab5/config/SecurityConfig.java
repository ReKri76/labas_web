package io.rekri.lab5.config;

import io.rekri.lab5.config.security.Roles;
import io.rekri.lab5.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return
                http
                        .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/", "/login", "/register", "/css/**", "/js/**", "/error").permitAll()
                                .requestMatchers(HttpMethod.GET, "/").permitAll()
                                .requestMatchers(HttpMethod.POST).authenticated()
                                .requestMatchers(HttpMethod.DELETE).hasAnyAuthority(Roles.ROLE_ADMIN.toString(),
                                        Roles.ROLE_MODERATOR.toString())
                                .requestMatchers("/users/**").hasAuthority(Roles.ROLE_ADMIN.toString())
                                .anyRequest().denyAll()
                        )
                        .formLogin(form -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/", true)
                                .permitAll()
                        )
                        .logout(logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                        )
                        .headers(headers -> headers
                                .contentSecurityPolicy(csp -> csp.policyDirectives(
                                        "default-src 'none'; script-src 'self'; style-src 'self'; " +
                                                "img-src 'self'; connect-src 'self'; font-src 'self'; " +
                                                "frame-ancestors 'none'; base-uri 'self'; form-action 'self'"))
                                .httpStrictTransportSecurity(hsts -> hsts
                                        .includeSubDomains(true)
                                        .maxAgeInSeconds(31536000))
                        )
                        .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    UserDetailsService userDetailsService(final UsersRepository users) {
        return username -> users.findByName(username)
                .map(user -> User.withUsername(user.getName())
                        .password(user.getPassword())
                        .authorities(user.getRole())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}