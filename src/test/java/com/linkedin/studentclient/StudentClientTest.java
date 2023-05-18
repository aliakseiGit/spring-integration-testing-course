package com.linkedin.studentclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@AutoConfigureStubRunner(ids = "com.linkedin.student-service:student-service:8080", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class StudentClientTest
{
    @Autowired
    private StudentClient studentClient;

    @Test
    void getStudent_forGivenStudent_isReturned()
    {
        //given
        Long studentId = 1l;

        //when
        Student student = studentClient.getStudent(studentId);

        //then
        then(student.getId()).isNotNull();
        then(student.getName()).isEqualTo("Mike");
        then(student.getGrade()).isEqualTo(60);
    }
}
