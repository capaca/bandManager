package com.bandManager.facade.implementacao;

import javax.persistence.NoResultException;

import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.bandManager.dao.IUsuarioDAO;
import com.bandManager.domain.Usuario;
import com.bandManager.exception.SenhaInvalidaException;
import com.bandManager.facade.IUsuarioFacade;

public class UsuarioFacade implements IUsuarioFacade {

	private IUsuarioDAO usuarioDAO;
	
	public Usuario salvar(Usuario usuario){
		return this.usuarioDAO.salvar(usuario);
	}
	
	public Usuario recuperar(int id){
		return this.usuarioDAO.recuperar(id);
	}
	
	public Usuario recuperar(String username){
		return this.usuarioDAO.recuperar(username);
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		try{
			return this.recuperar(username);
		}
		catch(NoResultException exception){
			throw new UsernameNotFoundException("Usuário ou senha inválidos.");
		}
	}
	
	public void trocarSenha(Usuario usuario, String novaSenha) throws SenhaInvalidaException{
		String senhaAtual = this.recuperar(usuario.getId()).getPassword();
		
		if(senhaAtual.equals(usuario.getPassword())){
			this.usuarioDAO.trocarSenha(usuario.getId(), novaSenha);
		}
		else{
			throw new SenhaInvalidaException();
		}
	}

	/*Getters and Setters*/

	public void setUsuarioDAO(IUsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
}
