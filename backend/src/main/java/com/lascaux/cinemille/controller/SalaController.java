package com.lascaux.cinemille.controller;

import com.lascaux.cinemille.model.Sala;
import com.lascaux.cinemille.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sala")
@CrossOrigin("*")
public class SalaController {

  @Autowired
  private SalaService salaService;

  @GetMapping
  public List<Sala> getAllSale() {
    return salaService.getAllSale();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Sala> getSalaById(@PathVariable int id) {
    return salaService.getSalaById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/imax")
  public List<Sala> getSaleIMAX() {
    return salaService.getSaleIMAX();
  }

}