import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { Film, FilmService } from '../../../../core/services/film.service';

@Component({
  selector: 'app-films-list',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatTableModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatButtonModule,
    MatCardModule,
    MatProgressSpinnerModule,
    DatePipe
  ],
  templateUrl: './films-list.component.html',
  styleUrl: './films-list.component.scss'
})
export class FilmsListComponent implements OnInit {
  films: Film[] = [];
  filteredFilms: Film[] = [];
  displayedColumns = ['titolo', 'genere', 'dataInizio', 'dataFine'];
  isLoading = false;
  errorMessage = '';

  startDate?: Date;
  endDate?: Date;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private filmService: FilmService) {}

  ngOnInit(): void {
    this.loadFilms();
  }

  loadFilms(): void {
    this.isLoading = true;
    this.errorMessage = '';

  }

  applyLocalFilters(): void {
    this.filteredFilms = this.films.filter((film) => {
      const filmStartDate = new Date(film.dataInizio);
      const filmEndDate = new Date(film.dataFine);

      const start = this.startDate ? filmStartDate >= this.startDate : true;
      const end = this.endDate ? filmEndDate <= this.endDate : true;

      return start && end;
    });
  }

  applyFilters(): void {
    this.loadFilms();
  }

  resetFilters(): void {
    this.startDate = undefined;
    this.endDate = undefined;
    this.loadFilms();
  }
}
