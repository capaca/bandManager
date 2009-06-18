package com.bandManager.facade;

import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.domain.Usuario;

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

}
