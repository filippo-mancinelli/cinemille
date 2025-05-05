package com.lascaux.cinemille.repository;

import com.lascaux.cinemille.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Integer> {
  List<Sala> findByIsIMAX(boolean isIMAX);
}