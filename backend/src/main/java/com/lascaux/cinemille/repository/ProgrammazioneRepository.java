package com.lascaux.cinemille.repository;

import com.lascaux.cinemille.model.Programmazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProgrammazioneRepository extends JpaRepository<Programmazione, Integer> {
  List<Programmazione> findByFilmId(int filmId);
  List<Programmazione> findBySalaId(int salaId);
  List<Programmazione> findByDataOraInizioGreaterThanEqualAndDataOraFineLessThanEqual(LocalDateTime inizio, LocalDateTime fine);
  List<Programmazione> findByDataOraInizioGreaterThanEqual(LocalDateTime inizio);
  List<Programmazione> findByDataOraFineLessThanEqual(LocalDateTime fine);

}