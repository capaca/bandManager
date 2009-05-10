package com.bandManager.action;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.domain.Banda;
import com.bandManager.domain.Lancamento;
import com.bandManager.domain.Pais;

public class TesteLancamentoCRUDAction extends Teste {

	private Pais pais;
	private Banda banda;
	
	@Before
	public void preparacao(){
		//Instancia e seta os atributos do pais
		this.pais = new Pais(PAIS_NOME);
		super.getPaisFacade().salvar(pais);
		
		//Instancia e seta os atributos da banda
		this.banda = new Banda();
		banda.setAnoFormacao(BANDA_ANO_FORMACAO);
		banda.setCidade(BANDA_CIDADE);
		banda.setNome(BANDA_NOME);
		banda.setPais(pais);
		banda.setRelease(BANDA_RELEASE);
		
		//Salva a banda
		super.getBandaCRUDAction().setBanda(banda);
		super.getBandaCRUDAction().salvar();

	}
	
	@Test
	public void salvar(){
		
		//Instancia o lancamento
		Lancamento lancamento = new Lancamento(LANCAMENTO_NOME,LANCAMENTO_ANO,LANCAMENTO_INFORMACOES);
		lancamento.setBanda(banda);
		
		//Seta o lancamento e salva
		super.getLancamentoCRUDAction().setLancamento(lancamento);
		assertEquals(Action.SUCCESS, super.getLancamentoCRUDAction().salvar());
	}
	
	@Test
	public void recuperar(){
				
		//Instancia o lancamento
		Lancamento lancamento = new Lancamento(LANCAMENTO_NOME,LANCAMENTO_ANO,LANCAMENTO_INFORMACOES);
		lancamento.setBanda(banda);
		
		//Seta o lancamento e salva
		super.getLancamentoCRUDAction().setLancamento(lancamento);
		assertEquals(Action.SUCCESS, super.getLancamentoCRUDAction().salvar());
		
		//Recupera o lancamento
		assertEquals(Action.SUCCESS, super.getLancamentoCRUDAction().recuperar());
	}
	
	@Test
	public void excluir(){
				
		//Instancia o lancamento
		Lancamento lancamento = new Lancamento(LANCAMENTO_NOME,LANCAMENTO_ANO,LANCAMENTO_INFORMACOES);
		lancamento.setBanda(banda);
		
		//Seta o lancamento e salva
		super.getLancamentoCRUDAction().setLancamento(lancamento);
		assertEquals(Action.SUCCESS, super.getLancamentoCRUDAction().salvar());
		
		//Exclui o lancamento
		assertEquals(Action.SUCCESS, super.getLancamentoCRUDAction().excluir());
		
	}
}
