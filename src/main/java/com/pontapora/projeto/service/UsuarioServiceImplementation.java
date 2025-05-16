package com.pontapora.projeto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pontapora.projeto.model.Papel;
import com.pontapora.projeto.model.Usuario;
import com.pontapora.projeto.repository.UsuarioRepository;

@Service
public class UsuarioServiceImplementation implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PapelService papelService;
	
	@Autowired
	private BCryptPasswordEncoder criptografia;
	
	@Override
	public void atribuirPapelParaUsuario(long idUsuario, int[] idsPapeis, boolean isAtivo) {
		
		List<Papel> papeis = new ArrayList<Papel>();			 
		for (int i = 0; i < idsPapeis.length; i++) {
			long idPapel = idsPapeis[i];
			Papel papel = papelService.buscarPapelPorId(idPapel);
			papeis.add(papel);
		}
		Usuario usuario = buscarUsuarioPorId(idUsuario);
		usuario.setPapeis(papeis);
		usuario.setAtivo(isAtivo);
		alterarUsuario(usuario);
	}

	@Override
	public Usuario buscarUsuarioPorId(Long id) {
		Optional<Usuario> opt = usuarioRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new IllegalArgumentException("Usuário com id : " + id + " não existe");
		}
	}

	@Override
	public Usuario gravarUsuario(Usuario usuario) {		
		// 👉 Validação de senha e confirmação
		if (!usuario.getPassword().equals(usuario.getConfirmarSenha())) {
			throw new IllegalArgumentException("As senhas não coincidem.");
		}
		
		// Busca o papel básico de usuário
		Papel papel = papelService.buscarPapel("USER");
		List<Papel> papeis = new ArrayList<>();
		papeis.add(papel);				
		usuario.setPapeis(papeis); // associa o papel de USER ao usuário
					
		String senhaCriptografada = criptografia.encode(usuario.getPassword());
		usuario.setPassword(senhaCriptografada);
					
		return usuarioRepository.save(usuario);
	}


	@Override
	public void apagarUsuarioPorId(Long id) {
		Usuario usuario = buscarUsuarioPorId(id);
		usuarioRepository.delete(usuario);		
	}

	@Override
	public List<Usuario> listarUsuario() {		
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios;
	}

	@Override
	public Usuario buscarUsuarioPorLogin(String login) {
		Usuario usuario = usuarioRepository.findByLogin(login);
		return usuario;
	}

	@Override
	public void alterarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);		
	}

}

