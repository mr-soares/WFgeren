package com.br.WFgeren.controller;


import com.br.WFgeren.DTO.AtualizarUser;
import com.br.WFgeren.DTO.UsuarioDTO;
import com.br.WFgeren.DTO.UsuarioInventarioDTO;
import com.br.WFgeren.model.Usuario;
import com.br.WFgeren.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Caminho de requisição para api de usuário
@RequestMapping("/api/User")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder){
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    //Realizar a solicitação de pegar todos os usuários
    @GetMapping
    public List<UsuarioDTO> todosUsuarios(){
       return usuarioService.todosUsuarios();
    }
    //Deletar um usuário pelo id
    @DeleteMapping("/{id}")
    public void DeletarUsuario(@PathVariable int id){
        usuarioService.DeletarUsuario(id);
    }
    //atualizar usuário
    @PutMapping("/{id}")
    public UsuarioDTO atualizarUsuario(@PathVariable int id, @RequestBody AtualizarUser atualizarUser){
        return  usuarioService.atualizarUsuario(id,atualizarUser);
    }
    //Buscar usuario pelo nome
    @GetMapping("/{name}")
    public UsuarioInventarioDTO BuscarUsuarioPeloNome(@PathVariable String name){
        return usuarioService.BuscarUsuarioPorNome(name);
    }
}