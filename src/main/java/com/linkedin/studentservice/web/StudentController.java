package com.linkedin.studentservice.web;

import com.linkedin.studentservice.data.model.Student;
import com.linkedin.studentservice.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable long id){
        return studentService.getStudentById(id);
    }
}
