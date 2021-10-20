package com.WebTechnologies.StudentManagementSystem.Service;

import com.WebTechnologies.StudentManagementSystem.exception.UserNotFoundException;
import com.WebTechnologies.StudentManagementSystem.model.Student;
import com.WebTechnologies.StudentManagementSystem.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;



@Service
@Transactional
public class StudentService {
    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public Student addStudent(Student student)
    {
        student.setStudentCode(UUID.randomUUID().toString());
        return studentRepo.save(student);
    }

    public List<Student> FindAllStudents()
    {
        return studentRepo.findAll();
    }

    public Student updateStudent(Student student)
    {
        return studentRepo.save(student);
    }

    public Student findStudentById(Long Id)
    {
        return studentRepo.findStudentById(Id).orElseThrow(() -> new UserNotFoundException("User by is " + Id + " was not found"));
    }

    public void deleteStudent(Long id)
    {
        studentRepo.deleteStudentById(id);
    }


}
