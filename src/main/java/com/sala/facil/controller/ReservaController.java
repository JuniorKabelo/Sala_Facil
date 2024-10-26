package com.sala.facil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sala.facil.service.ReservaService;
import com.sala.facil.service.SalaService;
import com.sala.facil.service.UsuarioService;
import com.sala.facil.entity.Reserva;
import com.sala.facil.entity.Sala;
import com.sala.facil.entity.Usuario;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "reserva")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SalaService salaService;

    @GetMapping
    public List<Reserva> getReservas() {
        return service.findAll();
    }

    @PostMapping
    public Reserva saveReserva(@RequestBody Reserva reserva) {
        LocalDateTime hoje = LocalDateTime.now();
        if (reserva.getData_reserva().isBefore(hoje)) {
            throw new RuntimeException("Não é possível reservar uma data passada.");
        }
        LocalDateTime limiteMaximo = hoje.plusDays(30);
        if (reserva.getData_reserva().isAfter(limiteMaximo)) {
            throw new RuntimeException("A reserva só pode ser feita com até 30 dias de antecedência.");
        }
        List<Reserva> reservasAtivas = service.findReservasAtivasPorUsuario(reserva.getUsuario());
        if (!reservasAtivas.isEmpty()) {
            throw new RuntimeException("O usuário já possui uma reserva ativa.");
        }

        reserva.setStatus(1); // Definindo o status como 1 (Ativa)
        return service.saveReserva(reserva);
    }

    @GetMapping("/{id}")
    public Reserva findReservaById(@PathVariable Long id) {
        Reserva reserva = service.findById(id);
        if (reserva == null) {
            throw new RuntimeException("Reserva não encontrada com ID: " + id);
        }
        return reserva;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public Reserva editarReserva(@PathVariable Long id, @RequestBody Reserva reservaAtualizada) {
        Reserva reservaExistente = service.findById(id);
        if (reservaExistente == null) {
            throw new RuntimeException("Reserva não encontrada com ID: " + id);
        }

        reservaExistente.setData_reserva(reservaAtualizada.getData_reserva());
        reservaExistente.setData_pedido(reservaAtualizada.getData_pedido());
        reservaExistente.setSala(reservaAtualizada.getSala());
        reservaExistente.setUsuario(reservaAtualizada.getUsuario());
        reservaExistente.setStatus(reservaAtualizada.getStatus()); // Agora é um int

        return service.saveReserva(reservaExistente);
    }

    // Novo endpoint para buscar reservas por sala
    @GetMapping("/sala/{id}")
    public List<Reserva> findReservasBySala(@PathVariable Long id) {
        Sala sala = salaService.findById(id);
        if (sala == null) {
            throw new RuntimeException("Sala não encontrada com ID: " + id);
        }
        return service.findReservasBySala(sala);
    }

    @GetMapping("/usuario/{id}")
    public List<Reserva> findReservasByUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
        return service.findReservasAtivasPorUsuario(usuario);
    }

    @GetMapping("/data/{data}")
    public List<Reserva> findReservasByData(@PathVariable String data) {
        LocalDateTime dataParseada = LocalDateTime.parse(data);  // Usando LocalDateTime
        return service.findByDataReserva(dataParseada);  // Passando LocalDateTime para o service
    }
}
