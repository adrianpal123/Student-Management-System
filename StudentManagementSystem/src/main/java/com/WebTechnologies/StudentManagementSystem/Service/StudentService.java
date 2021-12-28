package com.WebTechnologies.StudentManagementSystem.Service;

import com.WebTechnologies.StudentManagementSystem.exception.UserNotFoundException;
import com.WebTechnologies.StudentManagementSystem.model.Student;
import com.WebTechnologies.StudentManagementSystem.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;


import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



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

    public List<Student> findByOrderByGrade()
    {
        return studentRepo.findByOrderByGradeDesc();
    }

    public List<Student> findByOrderByNames() {return studentRepo.findByOrderByName();}
/*
    public ByteArrayInputStream StudentListToExcelFile(List<Student> students) {

        String[] row_heading = {"Name","Grade"};


        try{
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Students");

            Row headerRow = sheet.createRow(0);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());

            Font font = workbook.createFont();
            font.setColor(IndexedColors.RED.getIndex());
            headerCellStyle.setFont(font);

            // Creating header
            for (int i = 0; i < row_heading.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(row_heading[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // Creating data rows for each user
            for(int i = 0; i < students.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(students.get(i).getName());
                dataRow.createCell(1).setCellValue(students.get(i).getGrade());
            }

            // Making size of column auto resize to fit with data
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

 */
}
