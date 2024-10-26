package com.sala.facil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sala.facil.entity.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

}
