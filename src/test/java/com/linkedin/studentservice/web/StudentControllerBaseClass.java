package com.linkedin.studentservice.web;

import com.linkedin.studentservice.data.model.Student;
import com.linkedin.studentservice.service.StudentService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudentControllerBaseClass {

    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void before() throws Exception {
        RestAssuredMockMvc.mockMvc(mockMvc);
        //given
        given(studentService.getStudentById(anyLong())).willReturn(
                Student.builder()
                        .id(1L)
                        .name("Mike")
                        .grade(60)
                        .build()
        );
    }
}
