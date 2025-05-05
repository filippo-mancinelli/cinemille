package com.lascaux.cinemille.service;

import com.lascaux.cinemille.model.Programmazione;
import com.lascaux.cinemille.repository.ProgrammazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProgrammazioneService {

  @Autowired
  private ProgrammazioneRepository programmazioneRepository;

  public List<Programmazione> getAllProgrammazioni() {
    return programmazioneRepository.findAll();
  }

  public Optional<Programmazione> getProgrammazioneById(int id) {
    return programmazioneRepository.findById(id);
  }

  public List<Programmazione> getProgrammazioniByFilmId(int filmId) {
    return programmazioneRepository.findByFilmId(filmId);
  }

  public List<Programmazione> getProgrammazioniBySalaId(int salaId) {
    return programmazioneRepository.findBySalaId(salaId);
  }

  public List<Programmazione> getProgrammazioniByDateRange(LocalDateTime inizio, LocalDateTime fine) {
    if (inizio != null && fine != null) {
      return programmazioneRepository.findByDataOraInizioGreaterThanEqualAndDataOraFineLessThanEqual(inizio, fine);
    } else if (inizio != null) {
      return programmazioneRepository.findByDataOraInizioGreaterThanEqual(inizio);
    } else if (fine != null) {
      return programmazioneRepository.findByDataOraFineLessThanEqual(fine);
    } else {
      return programmazioneRepository.findAll();
    }
  }
}