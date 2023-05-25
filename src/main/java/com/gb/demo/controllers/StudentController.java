package com.gb.demo.controllers;

import com.gb.demo.converters.StudentConverter;
import com.gb.demo.dto.StudentDto;
import com.gb.demo.entities.Student;
import com.gb.demo.services.StudentService;
import com.gb.demo.validators.StudentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final StudentConverter studentConverter;
    private final StudentValidator studentValidator;

    @GetMapping("/{id}")
    public StudentDto getById(@PathVariable Long id) {
        Student student = studentService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return studentConverter.entityToDto(student);
    }

    @GetMapping()
    public List<StudentDto> findAll() {
        return studentService.findAll().stream().map(studentConverter::entityToDto).toList();
    }

    @PostMapping()
    public StudentDto addStudent(@RequestBody StudentDto studentDto){
        studentValidator.validate(studentDto);
        Student student = studentConverter.dtoToEntity(studentDto);
        student.setId(null);
        student = studentService.save(student);
        return studentConverter.entityToDto(student);
    }

    @PutMapping()
    public StudentDto updateStudent(@RequestBody StudentDto studentDto) {
        if (studentDto.getName() == null && studentDto.getAge() <= 0) {
            return studentDto;
        }
        Student student = studentService.update(studentDto);
        return studentConverter.entityToDto(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }
}
