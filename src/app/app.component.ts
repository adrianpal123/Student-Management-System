import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Student } from './student';
import { StudentService } from './student.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  public students: Student[] | undefined;
  public editStudent: Student | undefined;
  public deleteStudent: Student | undefined;

  constructor(private studentService: StudentService) { }

  ngOnInit()
  {
    this.getStudents();
  }

  public getStudents(): void
  {
    this.studentService.getStudents().subscribe(
      (response: Student[]) =>
        {
          this.students = response;
        },
        (error: HttpErrorResponse) =>
        {
          alert(error.message);
        }
    );
  }

  public onAddStudent(addForm: NgForm): void {
    document.getElementById('add-student-form')?.click();
    this.studentService.addStudents(addForm.value).subscribe(
      (response: Student) => {
        console.log(response);
        this.getStudents();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateStudent(student: Student): void{
    this.studentService.updateStudents(student).subscribe(
      (response: Student) => {
        console.log(response);
        this.getStudents();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteStudent(StudentId: number): void {
    this.studentService.deleteStudents(StudentId).subscribe(
      (response: void) => {
        console.log(response);
        this.getStudents();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  public searchStudents(key: string): void {
    console.log(key);
    const results: Student[] = [];
    for (const student of this.students!) {
      if (student.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || student.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || student.phoneNumber.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || student.department.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(student);
      }
    }
    this.students = results;
    if (results.length === 0 || !key) {
      this.getStudents();
    }
  }
  public onOpenModal(student: Student | null, mode: string): void{
    const container = document.getElementById('main-container');
    const button = document.createElement('button')
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle','modal')
    if (mode == 'add'){
      button.setAttribute('data-target','#addStudentModal');
    }
    if (mode == 'edit'){
      button.setAttribute('data-target','#editStudentModal');
    }
    if (mode == 'delete'){
      button.setAttribute('data-target','#deleteStudentModal');
    }

    container?.append(button);
    button.click()
  }


}
