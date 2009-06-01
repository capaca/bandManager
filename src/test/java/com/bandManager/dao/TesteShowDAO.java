package com.bandManager.dao;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.domain.Show;
import com.bandManager.exception.ObjetoNaoEncontradoException;

public class TesteShowDAO extends Teste {

	private Show show;
	
	@Before
	public void antes(){
		this.show = super.utilCriarShowNaoSalvo();
	}
	
	@Test
	public void salvar(){
		Show showSalvo = super.getShowDAO().salvar(this.show);
		super.utilVerificarShow(this.show, showSalvo);
	}
	
	@Test
	public void recuperar() throws ObjetoNaoEncontradoException {
		super.getShowDAO().salvar(this.show);
		Show showRecuperado = super.getShowDAO().recuperar(this.show.getId());
		super.utilVerificarShow(this.show, showRecuperado);
	}
	
	@Test
	public void excluir(){
		super.getShowDAO().salvar(this.show);
		super.getShowDAO().excluir(this.show.getId());
		
		//tenta recuperar
		try{
			super.getShowDAO().recuperar(this.show.getId());
			fail("Encontrou mas não deveria");
		}
		catch (ObjetoNaoEncontradoException e) {
			//ok
		}
	}
}
