package com.gb.demo.services;

import com.gb.demo.entities.Student;
import com.gb.demo.repositories.StudentRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RandomStudentGenerator {
    private static final Faker faker = new Faker();
    private final StudentRepository studentRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void generateStudentsOnStartup() {
        for (int i = 0; i < 10; i++) {
            studentRepository.save(generate());
        }
    }

    public static Student generate() {
        Student student = new Student();
        student.setName(faker.funnyName().name());
        student.setAge(faker.number().numberBetween(17, 90));
        return student;
    }
}
