package com.example.educationalplatform.controller;

import com.example.educationalplatform.entity.Submission;
import com.example.educationalplatform.entity.User;
import com.example.educationalplatform.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.persistence.EntityNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final UserService userService;

    public AnalyticsController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получает статистику успеваемости конкретного студента.
     *
     * @param id ID пользователя
     * @return Информация о средней оценке и количестве выполненных заданий
     */
    @GetMapping("/user/{id}/performance")
    public ResponseEntity<Map<String, Object>> getUserPerformance(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            List<Submission> submissions = userService.getUserSubmissions(id);

            if (submissions.isEmpty()) {
                throw new EntityNotFoundException("Нет решений заданий для данного пользователя");
            }

            // Средняя оценка вычисляется только среди проверенных работ
            double averageGrade = submissions.stream()
                    .filter(Submission::hasValidGrade)
                    .mapToDouble(Submission::getNumericGrade)
                    .average()
                    .orElse(0.0);

            long completedAssignments = submissions.stream()
                    .filter(Submission::hasValidGrade)
                    .count();

            Map<String, Object> performanceData = Map.of(
                    "userId", user.getId(),
                    "userName", user.getName(),
                    "averageGrade", averageGrade,
                    "completedAssignments", completedAssignments,
                    "totalAssignments", submissions.size()
            );

            return ResponseEntity.ok(performanceData);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Возвращает рейтинг студентов по средней оценке.
     *
     * @return Список пользователей отсортированный по средней оценке
     */
    @GetMapping("/students/rating")
    public ResponseEntity<List<Map<String, Object>>> getStudentsRating() {
        try {
            List<User> students = userService.getUsersByRole(com.example.educationalplatform.entity.Role.STUDENT);

            List<Map<String, Object>> ratingTable = students.stream()
                    .map(this::calculateStudentStats)
                    .sorted(MapComparator.byAverageGradeDescending())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(ratingTable);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private Map<String, Object> calculateStudentStats(User student) {
        List<Submission> submissions = userService.getUserSubmissions(student.getId());

        double averageGrade = submissions.stream()
                .filter(Submission::hasValidGrade)
                .mapToDouble(Submission::getNumericGrade)
                .average()
                .orElse(0.0);

        long completedAssignments = submissions.stream()
                .filter(Submission::hasValidGrade)
                .count();

        return Map.of(
                "userId", student.getId(),
                "userName", student.getName(),
                "averageGrade", averageGrade,
                "completedAssignments", completedAssignments
        );
    }

    static class MapComparator {
        public static Comparator<Map<String, Object>> byAverageGradeDescending() {
            return (m1, m2) -> {
                double avg1 = ((double) m1.get("averageGrade"));
                double avg2 = ((double) m2.get("averageGrade"));
                return Double.compare(avg2, avg1); // сортировка по убыванию
            };
        }
    }
}