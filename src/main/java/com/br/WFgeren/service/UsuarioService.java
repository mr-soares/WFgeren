package com.br.WFgeren.service;

import com.br.WFgeren.Config.SecurityConfig;
import com.br.WFgeren.DTO.AtualizarUser;
import com.br.WFgeren.DTO.CreateUser;
import com.br.WFgeren.DTO.UsuarioDTO;
import com.br.WFgeren.DTO.UsuarioInventarioDTO;
import com.br.WFgeren.Exception.NomeUsuarioException;
import com.br.WFgeren.Exception.SenhaUsuarioException;
import com.br.WFgeren.Exception.UsuarioNaoExisteException;
import com.br.WFgeren.model.Inventario;
import com.br.WFgeren.model.Usuario;
import com.br.WFgeren.repository.InventarioRepository;
import com.br.WFgeren.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final InventarioRepository inventarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, InventarioRepository inventarioRepository){
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.inventarioRepository = inventarioRepository;
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
        usuario.setInventario(inventario);
        // Salvando novo usuário no banco de dados
        return usuarioRepository.save(usuario);
    }
    //Buscar todos usuários
    public List<UsuarioDTO> todosUsuarios(){
        return usuarioRepository.findAll().stream().map(usuario -> new UsuarioDTO(usuario.getId(),usuario.getName())).toList();
    }
    //Atualizar Usuario
    public UsuarioDTO atualizarUsuario(int id, AtualizarUser user){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NomeUsuarioException("Usuário não encontrado"));
        if (user.nome()!= null && !user.nome().isBlank()){
            usuario.setName(user.nome());
        }
        if (user.senha()!= null && user.senha().isBlank()){
            usuario.setPassword(passwordEncoder.encode(user.senha()));
        }
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuarioAtualizado.getId(),usuarioAtualizado.getName());
    }
    //Deletar Usuario
    public void DeletarUsuario(int id){
        if (!usuarioRepository.existsById(id)){
            throw new UsuarioNaoExisteException("Usuário não encontrado com o id: "+id);
        }
        usuarioRepository.deleteById(id);
    }
    //Buscar um usuário
    public UsuarioInventarioDTO BuscarUsuarioPorNome(String nome){
        Usuario usuario = usuarioRepository.findByNameIgnoreCase(nome).orElseThrow(() ->new UsuarioNaoExisteException("Usuário não encontrado com o nome: " + nome));
        return new UsuarioInventarioDTO(usuario.getId(),usuario.getName(),usuario.getInventario());
    }
}