package com.lascaux.cinemille.controller;

import com.lascaux.cinemille.model.Ruolo;
import com.lascaux.cinemille.model.Utente;
import com.lascaux.cinemille.service.JwtService;
import com.lascaux.cinemille.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private JwtService jwtService;

  @Autowired
  private UtenteService utenteService;

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    String username = loginRequest.getUsername();
    String password = loginRequest.getPassword();

    // Controlliamo prima se l'utente esiste o se la password non è valida
    Optional<Utente> utente = utenteService.getUtenteByUsername(username);

    if(!utente.isPresent()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non trovato");
    } else {
      // Usiamo l'encoder per verificare le password hashate
      PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

      if (!passwordEncoder.matches(password, utente.get().getPassword())) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password non valida");
      }
    }

    // Costruiamo l'oggetto per Spring Security da dare al generatore del tokenjwt
    var userDetails = User.withUsername(utente.get().getUsername())
      .password(utente.get().getPassword())
      .authorities(utente.get().getAuthorities())
      .build();

    String token = jwtService.generateToken(userDetails);
    return ResponseEntity.ok().body(token);
  }

  @PostMapping("/register-admin")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> registerAdmin(@RequestBody RegisterRequest registerRequest) {
    // Verifica se username è già presente
    if (utenteService.getUtenteByUsername(registerRequest.getUsername()).isPresent()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username già esistente");
    }

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    Utente nuovoUtente = new Utente();
    nuovoUtente.setUsername(registerRequest.getUsername());
    nuovoUtente.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
    nuovoUtente.setRuolo(Ruolo.ADMIN);

    utenteService.salvaUtente(nuovoUtente);

    return ResponseEntity.status(HttpStatus.CREATED).body("Utente ADMIN creato con successo");
  }

  public static class LoginRequest {
    private String username;
    private String password;

    public LoginRequest() {}

    public String getUsername() {
      return username;
    }

    public String getPassword() {
      return password;
    }
  }

  public static class RegisterRequest {
    private String username;
    private String password;

    public String getUsername() {
      return username;
    }

    public String getPassword() {
      return password;
    }

  }

}
