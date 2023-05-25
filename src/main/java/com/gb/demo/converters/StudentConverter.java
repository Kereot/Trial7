package com.gb.demo.converters;

import com.gb.demo.dto.StudentDto;
import com.gb.demo.entities.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {
    public Student dtoToEntity(StudentDto studentDto) {
        return new Student(studentDto.getName(), studentDto.getAge());
    }

    public StudentDto entityToDto(Student student) {
        return new StudentDto(student.getId(), student.getName(), student.getAge());
    }
}
