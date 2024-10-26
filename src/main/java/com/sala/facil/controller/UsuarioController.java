package com.sala.facil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sala.facil.service.UsuarioService;
import com.sala.facil.entity.Usuario;

import java.util.List;

@RestController
@RequestMapping(value = "usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;
    
    @GetMapping
    public List<Usuario> getUsuarios(){
        return service.findAll();
        
    }
    //Salvar novo usuario
    @PostMapping
    public Usuario saveUsuario(@RequestBody Usuario usuario){
        Usuario usuarioSalvo = service.saveUsuario(usuario);
        return usuarioSalvo;
    }

    //findByID
    @GetMapping("/{id}")
    public Usuario findUsuarioById(@PathVariable Long id){
        Usuario usuario = service.findById(id);
        if (usuario == null) {
            throw new RuntimeException("Usuario não encontrado com ID: " + id);
        }
        return usuario;
    }

    //Delete
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }

}
