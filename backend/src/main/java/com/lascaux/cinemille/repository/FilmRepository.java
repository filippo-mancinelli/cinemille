package com.lascaux.cinemille.repository;

import com.lascaux.cinemille.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
  List<Film> findAll();

  List<Film> findByDataInizioGreaterThanEqualAndDataFineLessThanEqual(LocalDate from, LocalDate to);
  List<Film> findByDataInizioGreaterThanEqual(LocalDate from);
  List<Film> findByDataFineLessThanEqual(LocalDate to);
}
