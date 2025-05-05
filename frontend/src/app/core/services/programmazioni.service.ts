import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
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

  getAllProgrammazioni(): Observable<Programmazione[]> {
    return this.http.get<Programmazione[]>(this.apiUrl);
  }

  getProgrammazioneById(id: number): Observable<Programmazione> {
    return this.http.get<Programmazione>(`${this.apiUrl}/${id}`);
  }

  getProgrammazioniByFilmId(filmId: number): Observable<Programmazione[]> {
    return this.http.get<Programmazione[]>(`${this.apiUrl}/film/${filmId}`);
  }

  getProgrammazioniBySalaId(salaId: number): Observable<Programmazione[]> {
    return this.http.get<Programmazione[]>(`${this.apiUrl}/sala/${salaId}`);
  }


}
