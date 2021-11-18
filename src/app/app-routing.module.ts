import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SortedStudentsComponent } from './sorted-students/sorted-students.component';

const routes: Routes = [
  {path:'sortedStudents', component: SortedStudentsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [SortedStudentsComponent]
