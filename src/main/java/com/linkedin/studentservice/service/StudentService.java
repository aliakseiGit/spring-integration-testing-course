package com.linkedin.studentservice.service;

import com.linkedin.studentservice.data.model.Student;
import com.linkedin.studentservice.data.repository.StudentRepository;
import com.linkedin.studentservice.exceptions.StudentNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Cacheable("students")
    public Student getStudentById(Long id) {
        final Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            throw new StudentNotFoundException("Student with id=" + id + " not found");
        }

        return studentOptional.get();
    }
}
