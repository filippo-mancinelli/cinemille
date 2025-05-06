import { Routes } from '@angular/router';
import { LoginComponent } from './features/auth/pages/login/login.component';
import { FilmsListComponent } from './features/films/pages/films-list/films-list.component';
import {AuthGuard} from './core/guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'films', pathMatch: 'full' },
  {path: 'films', component: FilmsListComponent, canActivate: [AuthGuard]},
  { path: 'login', component: LoginComponent },
  { path: '**', redirectTo: 'login' }
];
