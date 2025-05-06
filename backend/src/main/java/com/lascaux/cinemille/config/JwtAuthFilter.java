package com.lascaux.cinemille.config;

import com.lascaux.cinemille.service.JwtService;
import com.lascaux.cinemille.service.UtenteService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter implements Filter {

  @Autowired
  private JwtService jwtService;

  @Autowired
  private UtenteService utenteService;

  private final AntPathMatcher pathMatcher = new AntPathMatcher();

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain)
    throws ServletException, IOException {

    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String username;

    // Assumiamo che la richiesta in arrivo sia sicura perchè già SecurityConfig si occupa di permettere gli URL corretti.
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    jwt = authHeader.substring(7);
    username = jwtService.extractUsername(jwt);

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      var userDetails = utenteService.getUtenteByUsername(username);

      if (jwtService.isTokenValid(jwt, userDetails.get())) {
        var authToken = new UsernamePasswordAuthenticationToken(
          userDetails,
          null,
          userDetails.get().getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }

    filterChain.doFilter(request, response);
  }
}