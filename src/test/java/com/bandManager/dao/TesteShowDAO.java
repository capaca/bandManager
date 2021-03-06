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
		
		//Troca o nome do show
		this.show.setNome("NomeShow");
		showSalvo = super.getShowDAO().salvar(this.show);
		super.utilVerificarShow(this.show, showSalvo);
	}
	
	@Test
	public void recuperar() throws ObjetoNaoEncontradoException {
		super.getShowDAO().salvar(this.show);
		Show showRecuperado = super.getShowDAO().recuperar(this.show.getId());
		super.utilVerificarShow(this.show, showRecuperado);
	}
	
	@Test
	public void erroRecuperarObjetoNaoEncontrado() throws ObjetoNaoEncontradoException {
		this.show = new Show();
		
		try{
			super.getShowDAO().recuperar(this.show.getId());
			fail("Recuperou mas não deveria");
		}
		catch(ObjetoNaoEncontradoException e){
			//ok
		}
	}
	
	@Test
	public void excluir() throws ObjetoNaoEncontradoException{
		super.getShowDAO().salvar(this.show);
		super.getShowDAO().excluir(this.show.getId());
		
		try{
			super.getShowDAO().recuperar(this.show.getId());
			fail("Recuperou mas não deveria");
		}
		catch (ObjetoNaoEncontradoException e) {
			//ok
		}

	}
	
	@Test
	public void erroExcluirObjetoNaoEncontrado(){
		this.show = new Show();
		
		try{
			super.getShowDAO().excluir(this.show.getId());
			fail("Excluiu mas não deveria");
		}
		catch(ObjetoNaoEncontradoException e){
			//ok
		}
	}
}
