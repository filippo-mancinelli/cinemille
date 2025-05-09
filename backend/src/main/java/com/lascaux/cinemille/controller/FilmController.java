package com.lascaux.cinemille.controller;

import com.lascaux.cinemille.repository.FilmRepository;
import com.lascaux.cinemille.model.Film;
import com.lascaux.cinemille.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/film")
@CrossOrigin("*")
public class FilmController {

  @Autowired
  FilmRepository filmRepository;

  @Autowired
  FilmService filmService;

  @GetMapping
  public List<Film> getAllFilms() {
      return filmRepository.findAll();
  }

  @GetMapping("/{id}")
  public Film getFilmById(@PathVariable int id) {
    return this.filmRepository.findById(id).orElse(null);
  }

  @GetMapping("/{genere}")
  public List<Film> getFilmByGenere(@PathVariable String genere) {
    return this.filmRepository.findByGenere(genere);
  }
}
