import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from './student';
import { HttpClient } from '@angular/common/http';
import { environment } from './../environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private apiServiceUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) { }

    public getStudents(): Observable<Student[]>{
        return this.http.get<Student[]>(`${this.apiServiceUrl}/student/all`);
    }

    public addStudents(student: Student): Observable<Student>{
        return this.http.post<Student>(`${this.apiServiceUrl}/student/add`, student);
    }

    public updateStudents(student: Student): Observable<Student>{
        return this.http.put<Student>(`${this.apiServiceUrl}/student/update`, student);
    }

    public deleteStudents(studentId: number): Observable<void>{
        return this.http.delete<void>(`${this.apiServiceUrl}/student/delete/${studentId}`);
    }

}
