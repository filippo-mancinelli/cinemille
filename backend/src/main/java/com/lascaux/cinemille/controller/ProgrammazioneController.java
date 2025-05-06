package com.lascaux.cinemille.controller;

import com.lascaux.cinemille.model.Programmazione;
import com.lascaux.cinemille.service.ProgrammazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@RestController
@RequestMapping("/api/programmazione")
@CrossOrigin("*")
public class ProgrammazioneController {

  @Autowired
  private ProgrammazioneService programmazioneService;

  /*
   * Questo endpoint è accessibile solo agli utenti autenticati (admin).
   * Restituisce storico completo della programmazione, filtrabile per data inizio/fine
   * */
  @GetMapping
  public List<Programmazione> getAllProgrammazioni(
    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inizio,
    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fine) {

    if (inizio != null || fine != null) {
      LocalDateTime inizioDateTime = inizio != null ? inizio.atStartOfDay() : null;
      LocalDateTime fineDateTime = fine != null ? fine.atTime(23, 59, 59) : null;

      return programmazioneService.getProgrammazioniByDateRange(inizioDateTime, fineDateTime);
    }

    return programmazioneService.getAllProgrammazioni();
  }


  /*
  * Questo endpoint deve essere accessibile a utenti/servizi esterni, anche non autenticati.
  * Restituisce le programmazioni della settimana attuale
  * */
  @GetMapping("/settimana-corrente")
  public List<Programmazione> getCurrentWeekProgrammazioni() {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).withHour(0).withMinute(0).withSecond(0);
    LocalDateTime endOfWeek = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).withHour(23).withMinute(59).withSecond(59);

    return programmazioneService.getProgrammazioniByDateRange(startOfWeek, endOfWeek);
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