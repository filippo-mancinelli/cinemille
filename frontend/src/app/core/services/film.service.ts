import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, map } from 'rxjs';
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
      params = params.set('from', this.formatDate(startDate));
    }

    if (endDate) {
      params = params.set('to', this.formatDate(endDate));
    }

    return this.http.get<Film[]>(this.apiUrl, { params })
      .pipe( map(films => { //Ordina per data
          return films.sort((a, b) =>
            new Date(a.dataInizio).getTime() - new Date(b.dataInizio).getTime()
          );
        })
      );
  }

  getFilmById(id: number): Observable<Film> {
    return this.http.get<Film>(`${this.apiUrl}/${id}`);
  }

  private formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');

    return `${year}-${month}-${day}`;
  }
}
