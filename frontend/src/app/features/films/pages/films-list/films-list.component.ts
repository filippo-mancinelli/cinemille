import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
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
import { MatTableDataSource } from '@angular/material/table';
import { Film, FilmService } from '../../../../core/services/film.service';
import {Programmazione, ProgrammazioneService} from '../../../../core/services/programmazioni.service';
import {MatTooltipModule} from '@angular/material/tooltip';

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
    MatTooltipModule,
    MatCardModule,
    MatProgressSpinnerModule,
    DatePipe
  ],
  templateUrl: './films-list.component.html',
  styleUrl: './films-list.component.scss'
})
export class FilmsListComponent implements OnInit {
  films: Programmazione[] = [];
  dataSource = new MatTableDataSource<Programmazione>([]);
  displayedColumns = ['titolo', 'genere', 'sala', 'dataInizio', 'dataFine'];
  isLoading = false;
  errorMessage = '';
  startDate?: Date;
  endDate?: Date;

  constructor(private programmazioneService: ProgrammazioneService) {}

  ngOnInit(): void {
    this.loadFilms();
    this.dataSource._updateChangeSubscription();
  }

  loadFilms(): void {
    this.isLoading = true;
    this.errorMessage = '';

    this.programmazioneService.getFilms(this.startDate, this.endDate).subscribe({
      next: (films) => {
        this.films = films;
        this.dataSource.data = films; // riempi
        this.isLoading = false;
      },
      error: (error) => {
        this.isLoading = false;
        console.error('Error fetching films:', error);
        this.errorMessage = 'Errore durante il caricamento dei film';
        this.films = [];
        this.dataSource.data = []; // svuota
      }
    });
  }

  applyFilters(): void {
    console.log("this.startDate", this.startDate);
    console.log("this.endDate", this.endDate);
    this.loadFilms();
  }

  resetFilters(): void {
    this.startDate = undefined;
    this.endDate = undefined;
    this.loadFilms();
  }
}
