package com.lascaux.cinemille.service;

import com.lascaux.cinemille.model.Sala;
import com.lascaux.cinemille.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

  @Autowired
  private SalaRepository salaRepository;

  public List<Sala> getAllSale() {
    return salaRepository.findAll();
  }

  public Optional<Sala> getSalaById(int id) {
    return salaRepository.findById(id);
  }

  public List<Sala> getSaleIMAX() {
    return salaRepository.findByIsIMAX(true);
  }

}