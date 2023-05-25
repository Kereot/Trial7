package com.gb.demo.services;

import com.gb.demo.dto.StudentDto;
import com.gb.demo.entities.Student;
import com.gb.demo.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public Student update(StudentDto studentDto) {
        Student student = studentRepository.findById(studentDto.getId()).orElseThrow(() -> new RuntimeException(("Can't update the student (not found in the DB) id: " + studentDto.getId())));
        if (studentDto.getName() != null && !studentDto.getName().isBlank()) {
            student.setName(studentDto.getName());
        }
        if (studentDto.getAge() > 0) {
            student.setAge(studentDto.getAge());
        }
        return student;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
