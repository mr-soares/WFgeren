package com.br.WFgeren.controller;


import com.br.WFgeren.DTO.AtualizarUser;
import com.br.WFgeren.DTO.CreateUser;
import com.br.WFgeren.DTO.UsuarioDTO;
import com.br.WFgeren.DTO.UsuarioInventarioDTO;
import com.br.WFgeren.model.Usuario;
import com.br.WFgeren.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid; // Necessário para o @Valid

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder){
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<UsuarioDTO> todosUsuarios(){
        return usuarioService.todosUsuarios();
    }

    @GetMapping("/nome/{name}")
    public UsuarioInventarioDTO BuscarUsuarioPeloNome(@PathVariable String name){
        return usuarioService.BuscarUsuarioPorNome(name);
    }


    @PostMapping
    public Usuario criarUsuario(@Valid @RequestBody CreateUser user){
        return usuarioService.novoUsuario(user);
    }

    @PutMapping("/{id}")
    public UsuarioDTO atualizarUsuario(@PathVariable int id, @Valid @RequestBody AtualizarUser atualizarUser){
        return  usuarioService.atualizarUsuario(id,atualizarUser);
    }

    @DeleteMapping("/{id}")
    public void DeletarUsuario(@PathVariable int id){
        usuarioService.DeletarUsuario(id);
    }
}