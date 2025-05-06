import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import {map, Observable} from 'rxjs';
import { environment } from '../../../environment';
import { Film } from './film.service';
import { Sala } from './sala.service';

export interface Programmazione {
  id: number;
  film: Film;
  sala: Sala;
  dataOraInizio: string;
  dataOraFine: string;
}

@Injectable({
  providedIn: 'root'
})
export class ProgrammazioneService {
  private apiUrl = environment.apiUrl + '/api/programmazione';

  constructor(private http: HttpClient) { }

  getFilms(startDate?: Date, endDate?: Date): Observable<Programmazione[]> {
    let params = new HttpParams();

    if (startDate) {
      params = params.set('inizio', this.formatDate(startDate));
    }

    if (endDate) {
      params = params.set('fine', this.formatDate(endDate));
    }

    return this.http.get<Programmazione[]>(this.apiUrl, { params })
      .pipe( map(films => { //Ordina per data
          return films.sort((a, b) =>
            new Date(a.dataOraInizio).getTime() - new Date(b.dataOraFine).getTime()
          );
        })
      );
  }

  private formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');

    return `${year}-${month}-${day}`;
  }
}
