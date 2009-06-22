package com.bandManager.dao;

import org.junit.Test;
import static org.junit.Assert.*;

import com.bandManager.Teste;
import com.bandManager.domain.Usuario;


public class TesteUsuarioDAO extends Teste {

	@Test
	public void salvar(){
		Usuario usuario = new Usuario("capaca", "123456");
		Usuario usuarioSalvo = super.getUsuarioDAO().salvar(usuario);
		utilVerificarAtributos(usuario, usuarioSalvo);
	}
	
	
	@Test
	public void recuperar(){
		Usuario usuario = utilCriarUsuario();
		Usuario usuarioRecuperado = super.getUsuarioDAO().recuperar(usuario.getId());
		utilVerificarAtributos(usuario, usuarioRecuperado);
	}
	
	@Test
	public void recuperarPorUserName(){
		Usuario usuario = utilCriarUsuario();
		Usuario usuarioRecuperado = super.getUsuarioDAO().recuperar(usuario.getUsername());
		utilVerificarAtributos(usuario, usuarioRecuperado);
	}
	
	@Test
	public void trocarSenha(){
		Usuario usuario = utilCriarUsuario();
		super.getUsuarioDAO().trocarSenha(usuario.getId(), "654321");
		Usuario usuarioRecuperado = super.getUsuarioDAO().recuperar(usuario.getId());
		assertEquals("654321", usuarioRecuperado.getPassword());
	}

}
