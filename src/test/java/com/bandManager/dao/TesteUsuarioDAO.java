package com.bandManager.dao;

import javax.persistence.NoResultException;

import org.junit.Test;
import static org.junit.Assert.*;

import com.bandManager.Teste;
import com.bandManager.domain.Usuario;


public class TesteUsuarioDAO extends Teste {

	@Test
	public void salvar(){
		Usuario usuario = new Usuario("capaca", "123456");
		Usuario usuarioSalvo = super.getUsuarioDAO().salvar(usuario);
		verificarAtributos(usuario, usuarioSalvo);

		usuario.setUsername("usuario");
		usuarioSalvo = super.getUsuarioDAO().salvar(usuario);
		verificarAtributos(usuario, usuarioSalvo);
	}
	
	
	@Test
	public void recuperar(){
		Usuario usuario = utilCriarUsuario();
		Usuario usuarioRecuperado = super.getUsuarioDAO().recuperar(usuario.getId());
		verificarAtributos(usuario, usuarioRecuperado);
	}
	
	@Test
	public void recuperarPorUserName(){
		Usuario usuario = utilCriarUsuario();
		Usuario usuarioRecuperado = super.getUsuarioDAO().recuperar(usuario.getUsername());
		verificarAtributos(usuario, usuarioRecuperado);
	}
	
	@Test
	public void erroRecuperarUsuarioNaoEncontrado(){
		Usuario usuario = new Usuario("usuario","123456");
		try{
			super.getUsuarioDAO().recuperar(usuario.getUsername());
			fail("Recuperou mas não deveria.");
		}
		catch(NoResultException e){
			//ok
		}
	}
	
	@Test
	public void trocarSenha(){
		Usuario usuario = utilCriarUsuario();
		super.getUsuarioDAO().trocarSenha(usuario.getId(), "654321");
		Usuario usuarioRecuperado = super.getUsuarioDAO().recuperar(usuario.getId());
		assertEquals("654321", usuarioRecuperado.getPassword());
	}

}
