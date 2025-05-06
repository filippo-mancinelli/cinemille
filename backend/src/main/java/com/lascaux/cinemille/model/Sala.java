package com.lascaux.cinemille.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sale")
public class Sala {

  @Id
  @GeneratedValue
  private int id;

  private String codice;
  private int capienza;
  private boolean isIMAX;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCodice() {
    return codice;
  }

  public void setCodice(String nome) {
    this.codice = nome;
  }

  public int getCapienza() {
    return capienza;
  }

  public void setCapienza(int capienza) {
    this.capienza = capienza;
  }

  public boolean isIMAX() {
    return isIMAX;
  }

  public void setIMAX(boolean isIMAX) {
    this.isIMAX = isIMAX;
  }
}