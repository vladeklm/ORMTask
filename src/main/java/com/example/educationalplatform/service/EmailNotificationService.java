package com.example.educationalplatform.service;

import com.example.educationalplatform.entity.Assignment;
import com.example.educationalplatform.entity.Submission;
import com.example.educationalplatform.entity.User;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService {

    private final JavaMailSender mailSender;

    public EmailNotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Отправляет уведомление ученику о новом задании.
     *
     * @param student   ученик, которому отправляется уведомление
     * @param assignment новое задание
     */
    @Override
    public void sendAssignmentNotification(User student, Assignment assignment) {
        SimpleMailMessage message = createSimpleMailMessage(
                student.getEmail(),
                "Новое задание: " + assignment.getTitle(),
                """
                    Здравствуйте, %s!\n\n\
                    Вам назначено новое задание: "%s"\n\n\
                    Описание задания:\n%s\n\n\
                    С уважением,\nКоманда образовательной платформы
                    """.formatted(student.getName(), assignment.getTitle(), assignment.getDescription())
        );

        try {
            mailSender.send(message);
            System.out.println("Email уведомление отправлено студенту " + student.getEmail());
        } catch (MailSendException e) {
            handleError(e, "Ошибка при отправке email уведомления о задании.");
        }
    }

    /**
     * Отправляет уведомление ученику об оценке за выполненное задание.
     *
     * @param student    ученик, которому отправляется уведомление
     * @param submission объект выполненного задания
     */
    @Override
    public void sendGradeNotification(User student, Submission submission) {
        SimpleMailMessage message = createSimpleMailMessage(
                student.getEmail(),
                "Оценка за задание: " + submission.getAssignment().getTitle(),
                """
                    Здравствуйте, %s!\n\n\
                    Ваше решение по заданию \"%s\" было проверено.\n\n\
                    Оценка: %d\n\n\
                    С уважением,\nКоманда образовательной платформы
                    """.formatted(student.getName(), submission.getAssignment().getTitle(), submission.getGrade())
        );

        try {
            mailSender.send(message);
            System.out.println("Email уведомление об оценке отправлено студенту " + student.getEmail());
        } catch (MailSendException e) {
            handleError(e, "Ошибка при отправке email уведомления об оценке.");
        }
    }

    private SimpleMailMessage createSimpleMailMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        return message;
    }

    private void handleError(MailSendException exception, String errorMessage) {
        System.err.println(errorMessage + ": " + exception.getMessage());
    }
}