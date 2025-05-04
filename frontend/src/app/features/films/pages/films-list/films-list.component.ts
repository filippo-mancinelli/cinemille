import { Component, OnInit } from '@angular/core';
import {MatPaginator } from '@angular/material/paginator';
import {MatTable} from '@angular/material/table';
import {MatFormField, MatLabel} from '@angular/material/form-field';
import {
  MatDatepicker,
  MatDatepickerInput,
  MatDatepickerToggle
} from '@angular/material/datepicker';
import {DatePipe} from '@angular/common';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // ✅ Per ngModel

@Component({
  selector: 'app-films-list',
  templateUrl: './films-list.component.html',
  imports: [
    MatLabel,
    MatFormField,
    MatDatepickerToggle,
    MatDatepicker,
    MatDatepickerInput,
    MatTable,
    MatPaginator,
    DatePipe,
    CommonModule,
    FormsModule
  ]
})
export class FilmsListComponentList implements OnInit {
  films = [
    { title: 'Dune', startDate: new Date('2025-04-01'), endDate: new Date('2025-04-21') },
    { title: 'Interstellar', startDate: new Date('2025-03-15'), endDate: new Date('2025-04-04') },
    { title: 'Batman 2', startDate: new Date('2025-04-10'), endDate: new Date('2025-04-24') },
  ];
  filteredFilms = [...this.films];
  displayedColumns = ['title', 'startDate', 'endDate'];

  startDate?: Date;
  endDate?: Date;

  ngOnInit(): void {}

  applyFilters() {
    this.filteredFilms = this.films.filter((film) => {
      const start = this.startDate ? film.startDate >= this.startDate : true;
      const end = this.endDate ? film.endDate <= this.endDate : true;
      return start && end;
    });
  }
}
