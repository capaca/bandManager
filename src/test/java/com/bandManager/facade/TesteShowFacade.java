package com.bandManager.facade;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.domain.Show;
import com.bandManager.exception.ObjetoNaoEncontradoException;

public class TesteShowFacade extends Teste {

	private Show show;
	
	@Before
	public void antes(){
		this.show = super.utilCriarShowNaoSalvo();
	}
	
	@Test
	public void salvar(){
		Show showSalvo = super.getShowFacade().salvar(this.show);
		super.utilVerificarShow(this.show, showSalvo);
	}
	
	@Test
	public void recuperar() throws ObjetoNaoEncontradoException {
		super.getShowFacade().salvar(this.show);
		Show showRecuperado = super.getShowFacade().recuperar(this.show.getId());
		super.utilVerificarShow(this.show, showRecuperado);
	}
	
	@Test
	public void excluir(){
		super.getShowFacade().salvar(this.show);
		super.getShowFacade().excluir(this.show.getId());
		
		//tenta recuperar
		try{
			super.getShowFacade().recuperar(this.show.getId());
			fail("Encontrou mas não deveria");
		}
		catch (ObjetoNaoEncontradoException e) {
			//ok
		}
	}
}
