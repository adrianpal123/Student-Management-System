package com.WebTechnologies.StudentManagementSystem.repo;

import com.WebTechnologies.StudentManagementSystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student,Long> {

    void deleteStudentById(Long id);

    //@Query("select u from studentmanager u where u.id = :id")
    Optional<Student> findStudentById(Long id);

    Student deleteStudentByName(String name);

    List<Student> findByOrderByGradeDesc();

}
