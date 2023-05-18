package com.linkedin.studentservice.web;

import com.linkedin.studentservice.data.model.Student;
import com.linkedin.studentservice.exceptions.StudentNotFoundException;
import com.linkedin.studentservice.service.StudentService;
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
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void getStudent_forSavedStudent_isReturned() throws Exception {
        //given
        given(studentService.getStudentById(anyLong())).willReturn(
                Student.builder()
                        .id(1L)
                        .name("Mike")
                        .grade(60)
                        .build()
        );

        //when //then
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("name").value("Mike"))
                .andExpect(jsonPath("grade").value(60));

    }

    @Test
    void getStudent_forMissingStudent_status404() throws Exception {
        //given
        given(studentService.getStudentById(anyLong())).willThrow(new StudentNotFoundException());
        //when //then
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isNotFound());
    }

}
