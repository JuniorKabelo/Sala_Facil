package com.sala.facil.repository;

import com.sala.facil.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sala.facil.entity.Reserva;
import com.sala.facil.entity.Usuario;

import java.time.LocalDateTime; // Alterado para LocalDateTime
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Método para buscar reservas de um usuário
    List<Reserva> findByUsuario(Usuario usuario);

    // Método para buscar reservas por data
    List<Reserva> findByDataReserva(LocalDateTime data); // Alterado para LocalDateTime

    // Método para buscar por sala
    List<Reserva> findBySala(Sala sala);
}
