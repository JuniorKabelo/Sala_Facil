package com.sala.facil.entity;

import java.io.Serializable;
import java.time.LocalDateTime; // Alterado para LocalDateTime
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "reserva")
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_reserva")
    private long id_reserva;

    @Column(name = "data_reserva")
    private LocalDateTime dataReserva; // Alterado para LocalDateTime

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido; // Alterado para LocalDateTime

    @Column(name = "status")
    private int status; // Alterado para int

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Reserva() {
    }

    public Reserva(long id_reserva, LocalDateTime dataReserva, LocalDateTime dataPedido, int status, Sala sala, Usuario usuario) {
        this.id_reserva = id_reserva;
        this.dataReserva = dataReserva;
        this.dataPedido = dataPedido;
        this.status = status; // Atualizado para int
        this.sala = sala;
        this.usuario = usuario;
    }

    // Getters e Setters
    public long getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(long id_reserva) {
        this.id_reserva = id_reserva;
    }

    public LocalDateTime getData_reserva() {
        return dataReserva;
    }

    public void setData_reserva(LocalDateTime dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalDateTime getData_pedido() {
        return dataPedido;
    }

    public void setData_pedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public int getStatus() { // Atualizado para int
        return status;
    }

    public void setStatus(int status) { // Atualizado para int
        this.status = status;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
