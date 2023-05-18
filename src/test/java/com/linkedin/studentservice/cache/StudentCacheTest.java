package com.linkedin.studentservice.cache;

import com.linkedin.studentservice.data.model.Student;
import com.linkedin.studentservice.data.repository.StudentRepository;
import com.linkedin.studentservice.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StudentCacheTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    void getStudentById_forMultipleRequests_isRetrievedFromCache() {
        //given
        Long id = 123L;
        given(studentRepository.findById(id)).willReturn(Optional.of(new Student(id, "Mike")));

        //when
        studentService.getStudentById(id);
        studentService.getStudentById(id);
        studentService.getStudentById(id);

        //then
        then(studentRepository).should(times(1)).findById(id);
    }
}
