package com.bandManager.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.domain.Banda;
import com.bandManager.domain.Lancamento;
import com.bandManager.domain.Pais;

public class TesteLancamentoFacade extends Teste {

	private Pais pais;
	private Banda banda;
	
	@Before
	public void preparacao(){
		//Intancia o pais e salva
		this.pais = new Pais(PAIS_NOME);
		super.getPaisFacade().salvar(pais);
		
		//Instancia a banda
		this.banda = new Banda();
		banda.setNome(BANDA_NOME);
		banda.setAnoFormacao(BANDA_ANO_FORMACAO);
		banda.setPais(pais);
		banda.setRelease(BANDA_RELEASE);
		
		//Salva a banda
		super.getBandaFacade().salvar(banda);
	}
	
	@Test
	public void salvar(){
		
		//Instancia e salva o lancamento
		Lancamento lancamento = new Lancamento(LANCAMENTO_NOME,LANCAMENTO_ANO,LANCAMENTO_INFORMACOES);
		lancamento.setBanda(banda);
		super.getLancamentoFacade().salvar(lancamento);
		
		this.verificarAtributos(lancamento);
	}
	
	@Test
	public void recuperar(){
		
		//Instancia o lancamento
		Lancamento lancamento = new Lancamento(LANCAMENTO_NOME, LANCAMENTO_ANO, LANCAMENTO_INFORMACOES);
		lancamento.setBanda(banda);
		
		//Salva o lancamento
		super.getLancamentoFacade().salvar(lancamento);
		
		//Recupera o lancamento
		Lancamento lancamentoRecuperado = super.getLancamentoFacade().recuperar(lancamento.getId());
		
		//Verifica se o lancamento foi recuperado corretamente
		this.verificarAtributos(lancamentoRecuperado);
	}
	
	@Test
	public void excluir(){
		//Instancia o lancamento
		Lancamento lancamento = new Lancamento(LANCAMENTO_NOME, LANCAMENTO_ANO, LANCAMENTO_INFORMACOES);
		lancamento.setBanda(banda);
		
		//Salva o lancamento
		super.getLancamentoFacade().salvar(lancamento);
		
		//Exclui o lancamento
		super.getLancamentoFacade().excluir(lancamento.getId());
		
		//Tenta recuperar o lancamento excluido
		assertNull(super.getLancamentoFacade().recuperar(lancamento.getId()));
	}
	
	private void verificarAtributos(Lancamento lancamento){
		assertEquals(LANCAMENTO_NOME, lancamento.getNome());
		assertEquals(LANCAMENTO_ANO, lancamento.getAno());
		assertEquals(LANCAMENTO_INFORMACOES, lancamento.getInformacoes());
		assertEquals(BANDA_NOME, lancamento.getBanda().getNome());
	}
}
