package com.lascaux.cinemille.controller;

import com.lascaux.cinemille.model.Programmazione;
import com.lascaux.cinemille.service.ProgrammazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/programmazione")
@CrossOrigin("*")
public class ProgrammazioneController {

  @Autowired
  private ProgrammazioneService programmazioneService;

  @GetMapping
  public List<Programmazione> getAllProgrammazioni(
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inizio,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fine) {

    if (inizio != null || fine != null) {
      return programmazioneService.getProgrammazioniByDateRange(inizio, fine);
    }

    return programmazioneService.getAllProgrammazioni();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Programmazione> getProgrammazioneById(@PathVariable int id) {
    return programmazioneService.getProgrammazioneById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/film/{filmId}")
  public List<Programmazione> getProgrammazioniByFilmId(@PathVariable int filmId) {
    return programmazioneService.getProgrammazioniByFilmId(filmId);
  }

  @GetMapping("/sala/{salaId}")
  public List<Programmazione> getProgrammazioniBySalaId(@PathVariable int salaId) {
    return programmazioneService.getProgrammazioniBySalaId(salaId);
  }
}