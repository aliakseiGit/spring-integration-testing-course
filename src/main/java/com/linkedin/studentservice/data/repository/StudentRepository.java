package com.linkedin.studentservice.data.repository;

import com.linkedin.studentservice.data.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student getStudentByName(String name);

    @Query(
            value = "SELECT AVG(grade) FROM Student WHERE active = 1",
            nativeQuery = true)
    Double getAverageGradeFromActiveStudents();
}
