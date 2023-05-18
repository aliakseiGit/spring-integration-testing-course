package com.linkedin.studentservice;

import com.linkedin.studentservice.data.model.Student;
import com.linkedin.studentservice.data.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testGetStudentByName_returnsStudentDetails() {
        //given
        Student savedStudent = testEntityManager.persistFlushFind(new Student(null, "Mark"));

        //when
        Student student = studentRepository.getStudentByName("Mark");

        //then
        then(student.getId()).isNotNull();
        then(student.getName()).isEqualTo(savedStudent.getName());
    }

    @Test
    void getAvgGradeForActiveStudents_calculateAvg() {
        //given
        final Student mark = Student.builder().name("Mark").active(true).grade(80).build();
        final Student alice = Student.builder().name("Alice").active(true).grade(100).build();
        final Student bob = Student.builder().name("Bob").active(false).grade(50).build();
        final List<Student> students = Arrays.asList(mark, alice, bob);
        students.forEach(testEntityManager::persistFlushFind);

        //when
        Double avgGrade = studentRepository.getAverageGradeFromActiveStudents();

        //then
        then(avgGrade).isEqualTo(90d);
    }

}
