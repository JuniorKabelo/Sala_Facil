package com.sala.facil.controller;

//import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sala.facil.service.SalaService;
import com.sala.facil.entity.Sala;

import java.util.List;

@RestController
@RequestMapping(value = "sala")
public class SalaController {

    @Autowired
    private SalaService service;

    @GetMapping
    public List<Sala> getSalas(){
        return service.findAll();
    }

    @PostMapping
    public Sala saveSala(@RequestBody Sala sala){
        Sala salaSalva = service.saveSala(sala);
        return salaSalva;
    }

    //findByID
    @GetMapping("/{id}")
    public Sala findSalaById(@PathVariable Long id){
        Sala sala = service.findById(id);
        if (sala == null) {
            throw new RuntimeException("Sala não encontrada com ID: " + id);
        }
        return sala;
    }

    //Delete
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }


}
