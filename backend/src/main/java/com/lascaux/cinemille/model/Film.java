package com.lascaux.cinemille.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Film {

  @Id
  @GeneratedValue
  private int id;

  private String titolo;
  private String genere;
  private LocalDate dataUscita;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitolo() {
    return titolo;
  }

  public void setTitolo(String titolo) {
    this.titolo = titolo;
  }

  public String getGenere() {
    return genere;
  }

  public void setGenere(String genere) {
    this.genere = genere;
  }

  public LocalDate getDataUscita() {
    return dataUscita;
  }

  public void setDataUscita(LocalDate dataUscita) {
    this.dataUscita = dataUscita;
  }
}