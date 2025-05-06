package com.lascaux.cinemille.config;

import com.lascaux.cinemille.config.JwtAuthFilter;
import com.lascaux.cinemille.service.UtenteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

  private final JwtAuthFilter jwtAuthFilter;
  private final LoggingFilter loggingFilter;
  private final UtenteService utenteService;

  public SecurityConfig(JwtAuthFilter jwtAuthFilter, UtenteService utenteService, LoggingFilter loggingFilter) {
    this.jwtAuthFilter = jwtAuthFilter;
    this.utenteService = utenteService;
    this.loggingFilter = loggingFilter;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/api/auth/**").permitAll()
        .requestMatchers("/api/programmazione/settimana-corrente").permitAll()
        .anyRequest().authenticated()
      )
      .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .addFilterBefore(loggingFilter, UsernamePasswordAuthenticationFilter.class) // Aggiungi il filtro per loggare richieste/risposte
      .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // Aggiungi il filtro per verificare JWT nell'header della richiesta
      .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
    throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
