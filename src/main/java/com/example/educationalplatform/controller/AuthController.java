package com.example.educationalplatform.controller;

import com.example.educationalplatform.config.JwtUtil;
import com.example.educationalplatform.entity.User;
import com.example.educationalplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            // Проверяем, что пользователя с таким email еще нет
            if (userService.getUserByEmail(user.getEmail()).isPresent()) {
                return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
            }

            // Хешируем пароль перед сохранением
            user.setPassword(user.getPassword());

            // Сохраняем нового пользователя
            User createdUser = userService.saveUser(user);

            // Генерируем JWT токен
            String token = jwtUtil.generateToken(user.getName(), user.getId(), user.getRole().name());

            // Возвращаем токен и информацию о пользователе
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", createdUser);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Registration failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            // Проверяем существование пользователя
            User user = userService.getUserByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));

            // Проверяем пароль
            if (loginRequest.getPassword()== user.getPassword()) {
                return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
            }

            // Генерируем JWT токен
            String token = jwtUtil.generateToken(user.getName(), user.getId(), user.getRole().name());

            // Возвращаем токен и информацию о пользователе
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", user);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("Login failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Вспомогательный класс для запроса на вход
    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}