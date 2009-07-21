package com.bandManager.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.bandManager.Teste;
import com.bandManager.dao.implementacao.UsuarioDAO;
import com.bandManager.domain.Usuario;
import com.bandManager.exception.SenhaInvalidaException;

public class TesteUsuarioFacade extends Teste {

	@Test
	public void salvar(){
		Usuario usuario = new Usuario("capaca", "123456");
		Usuario usuarioSalvo = super.getUsuarioFacade().salvar(usuario);
		verificarAtributos(usuario, usuarioSalvo);
	}
	
	@Test
	public void recuperar(){
		Usuario usuario = utilCriarUsuario();
		Usuario usuarioRecuperado = super.getUsuarioFacade().recuperar(usuario.getId());
		verificarAtributos(usuario, usuarioRecuperado);
	}
	
	@Test
	public void recuperarPorUserName(){
		Usuario usuario = utilCriarUsuario();
		Usuario usuarioRecuperado = super.getUsuarioFacade().recuperar(usuario.getUsername());
		verificarAtributos(usuario, usuarioRecuperado);
	}
	
	@Test
	public void loadByUsername(){
		Usuario usuario = utilCriarUsuario();
		UserDetails usuarioRecuperado = super.getUsuarioFacade().loadUserByUsername(usuario.getUsername());
		verificarAtributos(usuario, usuarioRecuperado);
	}
	
	@Test
	public void erroloadByUsernameNotFound(){
		Usuario usuario =  new Usuario("usuario","senha");

		try{
			super.getUsuarioFacade().loadUserByUsername(usuario.getUsername());
			fail("Recuperou mas não deveria");
		}
		catch (UsernameNotFoundException e) {
			//ok
		}
	}
	
	@Test
	public void erroloadByUsernameNoResult(){
		Usuario usuario =  new Usuario("usuario","senha");

		try{
			super.getUsuarioFacade().loadUserByUsername(usuario.getUsername());
			fail("Recuperou mas não deveria");
		}
		catch (UsernameNotFoundException e) {
			//ok
		}
	}
	
	@Test
	public void trocarSenha() throws SenhaInvalidaException{
		Usuario usuario = utilCriarUsuario();
		super.getUsuarioFacade().trocarSenha(usuario, "654321");
		Usuario usuarioRecuperado = super.getUsuarioFacade().recuperar(usuario.getId());
		assertEquals("654321", usuarioRecuperado.getPassword());
	}

	@Test
	public void erroTrocarSenha(){
		Usuario usuario = utilCriarUsuario();
		usuario.setPassword("666");
		
		try{
			super.getUsuarioFacade().trocarSenha(usuario, "654321");
			fail("Trocou a senha, mas não deveria");
		}
		catch (SenhaInvalidaException e) {
			//ok
		}
	}
}
