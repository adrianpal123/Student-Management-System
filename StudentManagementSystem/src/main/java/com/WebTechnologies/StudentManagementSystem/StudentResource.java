package com.WebTechnologies.StudentManagementSystem;

import com.WebTechnologies.StudentManagementSystem.Service.StudentService;
import com.WebTechnologies.StudentManagementSystem.model.Student;
import com.WebTechnologies.StudentManagementSystem.repo.StudentRepo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentResource {
    private final StudentService studentService;


    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents()
    {
        List<Student> students = studentService.FindAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long id)
    {
        Student student = studentService.findStudentById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student)
    {
        Student newStudent = studentService.addStudent(student);
        return new ResponseEntity<>(newStudent,HttpStatus.CREATED);
    }

    @GetMapping("/hello")
    ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student)
    {
        Student updateStudent = studentService.updateStudent(student);
        return new ResponseEntity<>(updateStudent,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") Long id)
    {
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Student>> orderedStudents()
    {
        List<Student> students = studentService.findByOrderByGrade();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/sortNames")
    public ResponseEntity<List<Student>> orderedStudentsNames()
    {
        List<Student> students = studentService.findByOrderByNames();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
/*
    @GetMapping("/donwload/ExcelReport")
    public void downloadExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=student.xlsx");
        ByteArrayInputStream stream = studentService.StudentListToExcelFile(studentService.FindAllStudents());
        IOUtils.copy(stream, response.getOutputStream());
        System.out.println("excel report downloaded successfully...........");
    }
*/


}
