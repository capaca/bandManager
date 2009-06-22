package com.bandManager.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.domain.Usuario;
import com.bandManager.exception.SenhaInvalidaException;

public class TesteUsuarioFacade extends Teste {

	@Test
	public void salvar(){
		Usuario usuario = new Usuario("capaca", "123456");
		Usuario usuarioSalvo = super.getUsuarioFacade().salvar(usuario);
		utilVerificarAtributos(usuario, usuarioSalvo);
	}
	
	@Test
	public void recuperar(){
		Usuario usuario = utilCriarUsuario();
		Usuario usuarioRecuperado = super.getUsuarioFacade().recuperar(usuario.getId());
		utilVerificarAtributos(usuario, usuarioRecuperado);
	}
	
	@Test
	public void recuperarPorUserName(){
		Usuario usuario = utilCriarUsuario();
		Usuario usuarioRecuperado = super.getUsuarioFacade().recuperar(usuario.getUsername());
		utilVerificarAtributos(usuario, usuarioRecuperado);
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
