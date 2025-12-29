package com.example.educationalplatform.config;

import com.example.educationalplatform.service.JwtService;
import com.example.educationalplatform.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // Проверяем, что заголовок Authorization существует и начинается с "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtService.extractUsername(jwt);
            } catch (Exception e) {
                logger.error("Ошибка при извлечении имени пользователя из токена", e);
            }
        }

        // Если имя пользователя извлечено и контекст безопасности не установлен
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Загружаем userDetails
            UserDetails userDetails = userService.loadUserByUsername(username);

            // Проверяем валидность токена
            if (jwtService.validateToken(jwt, userDetails.getUsername())) {
                // Создаем аутентификацию
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Устанавливаем аутентификацию в контекст безопасности
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}