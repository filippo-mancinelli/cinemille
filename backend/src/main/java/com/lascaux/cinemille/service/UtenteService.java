package com.lascaux.cinemille.service;

import com.lascaux.cinemille.model.Utente;
import com.lascaux.cinemille.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

  @Autowired
  private UtenteRepository utenteRepository;

  public List<Utente> getUtenti() {
    return utenteRepository.findAll();
  }

  public Optional<Utente> getUtenteByUsername(String username) {
    return utenteRepository.findUtenteByUsername(username);
  }

  public Utente salvaUtente(Utente utente) {
    return utenteRepository.save(utente);
  }
}
