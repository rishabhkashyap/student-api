import { AddCourseComponent } from './app/course/addCourse.component';
import { ModuleWithProviders } from '@angular/core';
import { SearchComponent } from './app/search/search.component';
import { ROUTES } from '@angular/router';
import { Routes, RouterModule } from '@angular/router';



export const routes: Routes = [
      {path: 'search', component: SearchComponent},
      {path: 'add-course', component: AddCourseComponent}
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);