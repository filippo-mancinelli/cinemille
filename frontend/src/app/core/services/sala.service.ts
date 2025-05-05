import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environment';

export interface Sala {
  id: number;
  nome: string;
  capienza: number;
  isIMAX: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class SalaService {
  private apiUrl = environment.apiUrl + '/api/sala';

  constructor(private http: HttpClient) { }

  getAllSale(): Observable<Sala[]> {
    return this.http.get<Sala[]>(this.apiUrl);
  }

  getSalaById(id: number): Observable<Sala> {
    return this.http.get<Sala>(`${this.apiUrl}/${id}`);
  }

  getSaleIMAX(): Observable<Sala[]> {
    return this.http.get<Sala[]>(`${this.apiUrl}/imax`);
  }

}
