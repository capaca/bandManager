package com.bandManager.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.domain.Banda;
import com.bandManager.domain.Lancamento;
import com.bandManager.domain.Pais;

public class TesteLancamentoDAO extends Teste {
	
	private Banda banda;
	private Pais pais;
	
	@Before
	public void preparacao(){
		//Instancia e salva o pais
		this.pais = new Pais(PAIS_NOME);
		super.getPaisDAO().salvar(pais);
		
		//Instancia a banda
		this.banda = new Banda();
		banda.setNome(BANDA_NOME);
		banda.setAnoFormacao(BANDA_ANO_FORMACAO);
		banda.setCidade(BANDA_CIDADE);
		banda.setPais(pais);
		banda.setRelease(BANDA_RELEASE);
		
		//Salva a banda
		super.getBandaDAO().salvar(banda);
	}
	
	@Test
	public void salvar(){
		
		//Instancia o lancamento
		Lancamento lancamento = new Lancamento(LANCAMENTO_NOME, LANCAMENTO_ANO, LANCAMENTO_INFORMACOES);
		lancamento.setBanda(banda);
		
		//Salva o lancamento
		Lancamento albumSalvo = super.getLancamentoDAO().salvar(lancamento);
		
		//Verifica se o lancamento foi salvo corretamente
		assertNotNull(albumSalvo);
		assertTrue(albumSalvo.getId()>0);
		verificarAtributos(lancamento, albumSalvo);
		
		//Muda o nome do lancamento
		lancamento.setNome("NomeLancamento");
		albumSalvo = super.getLancamentoDAO().salvar(lancamento);
		verificarAtributos(lancamento, albumSalvo);
		
	}

	@Test
	public void recuperar(){
		
		//Instancia o lancamento
		Lancamento lancamento = new Lancamento(LANCAMENTO_NOME, LANCAMENTO_ANO, LANCAMENTO_INFORMACOES);
		lancamento.setBanda(banda);
		
		//Salva o lancamento
		super.getLancamentoDAO().salvar(lancamento);
		
		//Recupera o lancamento
		Lancamento lancamentoRecuperado = super.getLancamentoDAO().recuperar(lancamento.getId());
		
		//Verifica se o lancamento foi recuperado corretamente
		this.verificarAtributos(lancamento, lancamentoRecuperado);
	}
	
	@Test
	public void excluir(){

		//Instancia o lancamento
		Lancamento lancamento = new Lancamento(LANCAMENTO_NOME, LANCAMENTO_ANO, LANCAMENTO_INFORMACOES);
		lancamento.setBanda(banda);
		
		//Salva o lancamento
		super.getLancamentoDAO().salvar(lancamento);
		
		//Exclui o lancamento
		super.getLancamentoDAO().excluir(lancamento.getId());
		
		//Tenta recuperar o lancamento excluido
		assertNull(super.getLancamentoDAO().recuperar(lancamento.getId()));
	}
}
