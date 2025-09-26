package com.br.WFgeren.service;

import com.br.WFgeren.Config.SecurityConfig;
import com.br.WFgeren.DTO.CreateUser;
import com.br.WFgeren.Exception.NomeUsuarioException;
import com.br.WFgeren.Exception.SenhaUsuarioException;
import com.br.WFgeren.model.Inventario;
import com.br.WFgeren.model.Usuario;
import com.br.WFgeren.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }
    // Criação de novo usuário
    public Usuario novoUsuario(CreateUser user){
        // Validando a senha
        if (!user.senha().equalsIgnoreCase(user.confirmarSenha())){
            throw new SenhaUsuarioException("As senhas não coincidem");
        }
        // Verificando ser o usuário já existe
        if (usuarioRepository.existsByName(user.nome())){
            throw  new NomeUsuarioException("Usuário existente.");
        }
        // Criando o usuário
        Usuario usuario = new Usuario();
        usuario.setName(user.nome());
        usuario.setPassword(passwordEncoder.encode(user.senha()));
        // criando o inventario
        Inventario inventario = new Inventario();
        inventario.setUsuario(usuario);
        usuario.setInventario(inventario);
        // Salvando novo usuário no banco de dados
        return usuarioRepository.save(usuario);
    }
}
