package com.sala.facil.service;

import com.sala.facil.entity.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sala.facil.entity.Reserva;
import com.sala.facil.entity.Usuario;
import com.sala.facil.repository.ReservaRepository;

import java.time.LocalDateTime; // Alterado para LocalDateTime
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repository;

    public List<Reserva> findAll() {
        return repository.findAll();
    }

    public Reserva saveReserva(Reserva reserva) {
        return repository.save(reserva);
    }

    public Reserva findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Reserva> findReservasAtivasPorUsuario(Usuario usuario) {
        List<Reserva> reservas = repository.findByUsuario(usuario);
        System.out.println("Reservas encontradas: " + reservas.size());
        return reservas;
    }

    // Método para buscar reservas por data
    public List<Reserva> findByDataReserva(LocalDateTime dataReserva) { // Alterado para LocalDateTime
        return repository.findByDataReserva(dataReserva); // Certifique-se de que o repositório tenha esse método
    }

    // Novo método para buscar reservas por sala
    public List<Reserva> findReservasBySala(Sala sala) {
        return repository.findBySala(sala);
    }
}
