package com.lascaux.cinemille.repository;

import com.lascaux.cinemille.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {
  Optional<Utente> findUtenteByUsername(String username);
}
