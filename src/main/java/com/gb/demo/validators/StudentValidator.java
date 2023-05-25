package com.gb.demo.validators;

import com.gb.demo.dto.StudentDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentValidator {
    public void validate (StudentDto studentDto) {
        List<String> errors = new ArrayList<>();
        if (studentDto.getAge() <= 0) {
            errors.add("Student's age can't be zero or lower");
        }
        if (studentDto.getName() == null || studentDto.getName().isBlank()) {
            errors.add("Student's name can't be blank");
        }
        if (!errors.isEmpty()) {
            System.out.println(errors);
            throw new IllegalArgumentException(String.join(", ", errors));
        }
    }
}
