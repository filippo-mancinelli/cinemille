package com.lascaux.cinemille.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "programmazione")
public class Programmazione {

  @Id
  @GeneratedValue
  private int id;

  @ManyToOne
  @JoinColumn(name = "film_id")
  private Film film;

  @ManyToOne
  @JoinColumn(name = "sala_id")
  private Sala sala;

  private LocalDateTime dataOraInizio;
  private LocalDateTime dataOraFine;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Film getFilm() {
    return film;
  }

  public void setFilm(Film film) {
    this.film = film;
  }

  public Sala getSala() {
    return sala;
  }

  public void setSala(Sala sala) {
    this.sala = sala;
  }

  public LocalDateTime getDataOraInizio() {
    return dataOraInizio;
  }

  public void setDataOraInizio(LocalDateTime dataOraInizio) {
    this.dataOraInizio = dataOraInizio;
  }

  public LocalDateTime getDataOraFine() {
    return dataOraFine;
  }

  public void setDataOraFine(LocalDateTime dataOraFine) {
    this.dataOraFine = dataOraFine;
  }
}