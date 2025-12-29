package com.example.educationalplatform.service;

import com.example.educationalplatform.entity.User;
import com.example.educationalplatform.entity.Role;
import com.example.educationalplatform.entity.Course;
import com.example.educationalplatform.entity.Enrollment;
import com.example.educationalplatform.entity.Submission;
import com.example.educationalplatform.repository.UserRepository;
import com.example.educationalplatform.repository.SubmissionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    public User saveUser(User user) {
        // Хешируем пароль перед сохранением, если он не хеширован
        if (user.getPassword() != null && !user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    public Course enrollUserToCourse(Long userId, Long courseId) {
        User user = getUserById(userId);
        Course course = courseService.getCourseById(courseId);
        Enrollment enrollment = enrollmentService.enrollStudentToCourse(user, course);
        return course;
    }

    public List<Submission> getUserSubmissions(Long userId) {
        User user = getUserById(userId);
        return submissionRepository.findByStudent(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isPresent()) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.get().getEmail())
                    .password(user.get().getPassword())
                    .roles(user.get().getRole().name())
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
    }
}