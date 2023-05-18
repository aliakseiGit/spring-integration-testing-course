package com.linkedin.studentservice.service;

import com.linkedin.studentservice.data.model.Student;
import com.linkedin.studentservice.data.repository.StudentRepository;
import com.linkedin.studentservice.exceptions.StudentNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class StudentServiceTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Test
    @DisplayName("Returned saved student from service layer")
    void getStudentById_forSavedStudent_isReturned() {
        //given
        final Student mike = studentRepository.save(new Student(null, "Mike"));

        //when
        Student student = studentService.getStudentById(mike.getId());

        //then
        then(student.getName()).isEqualTo("Mike");
        then(student.getId()).isNotNull();
    }

    @Test
    @DisplayName("StudentNotFoundException will thrown if student id is not found")
    void getStudentById_forMissingStudent_StudentNotFoundExceptionThrown() {
        //given
        final Long id = 10L;

        //when
        //then
        assertThatThrownBy(() -> {
            studentService.getStudentById(id);
        }).isInstanceOf(StudentNotFoundException.class)
                .hasMessage("Student with id=10 not found");
    }

    @Test
    @DisplayName("StudentNotFoundException will thrown if student id is not found (in BDD style)")
    void getStudentById_forMissingStudent_StudentNotFoundExceptionThrown_BDDStyle() {
        //given
        final Long id = 10L;

        //when
        Throwable thrown = catchThrowable(() ->  studentService.getStudentById(id));

        //then
        then(thrown).isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("id=10");
    }

}