package com.lascaux.cinemille.controller;

import com.lascaux.cinemille.model.Utente;
import com.lascaux.cinemille.repository.UtenteRepository;
import com.lascaux.cinemille.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/utente")
@CrossOrigin("*")
public class UtenteController {

  @Autowired
  private UtenteService utenteService;

  @GetMapping
  public List<Utente> getAllUtenti() {
    return utenteService.getUtenti();
  }

}
