package com.lascaux.cinemille.repository;

import com.lascaux.cinemille.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
  List<Film> findAll();
  List<Film> findByGenere(String genere);
}