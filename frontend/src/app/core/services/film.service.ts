import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { formatDate } from '@angular/common';
import {environment} from '../../../environment';

export interface Film {
  id: number;
  titolo: string;
  genere: string;
  dataInizio: string;
  dataFine: string;
}

@Injectable({
  providedIn: 'root'
})
export class FilmService {
  private apiUrl = environment.apiUrl + '/api/film';

  constructor(private http: HttpClient) { }

  getFilms(startDate?: Date, endDate?: Date): Observable<Film[]> {
    let params = new HttpParams();

    if (startDate) {
      params = params.set('from', formatDate(startDate, 'yyyy-MM-dd', 'en-US'));
    }

    if (endDate) {
      params = params.set('to', formatDate(endDate, 'yyyy-MM-dd', 'en-US'));
    }

    return this.http.get<Film[]>(this.apiUrl, { params });
  }

  getFilmById(id: number): Observable<Film> {
    return this.http.get<Film>(`${this.apiUrl}/${id}`);
  }
}
