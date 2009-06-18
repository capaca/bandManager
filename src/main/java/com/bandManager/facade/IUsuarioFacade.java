package com.bandManager.facade;

import org.springframework.security.userdetails.UserDetailsService;

import com.bandManager.domain.Usuario;

public interface IUsuarioFacade extends UserDetailsService {

	public Usuario salvar(Usuario usuario);
	public Usuario recuperar(int id);
	public Usuario recuperar(String username);
}
