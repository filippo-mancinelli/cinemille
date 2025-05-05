package com.lascaux.cinemille.service;

import com.lascaux.cinemille.model.Film;
import com.lascaux.cinemille.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

  @Autowired
  private FilmRepository filmRepository;

  public List<Film> getAllFilms() {
    return filmRepository.findAll();
  }

  public Optional<Film> getFilmById(int id) {
    return filmRepository.findById(id);
  }

  public List<Film> getFilmsByGenere(String genere) {
    return filmRepository.findByGenere(genere);
  }
}