package com.bandManager.facade;

import org.springframework.security.userdetails.UserDetailsService;

import com.bandManager.domain.Usuario;
import com.bandManager.exception.SenhaInvalidaException;

public interface IUsuarioFacade extends UserDetailsService {

	//FIXME Documentar
	public Usuario salvar(Usuario usuario);
	public Usuario recuperar(int id);
	public Usuario recuperar(String username);
	public void trocarSenha(Usuario usuario, String novaSenha) throws SenhaInvalidaException;
}
