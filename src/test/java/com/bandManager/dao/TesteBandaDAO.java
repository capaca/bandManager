package com.bandManager.dao;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.domain.Banda;
import com.bandManager.domain.Lancamento;
import com.bandManager.domain.Pais;

public class TesteBandaDAO extends Teste {
	
	private Pais pais;
	
	@Before
	public void preparacao(){
		
		this.pais = utilCriarPais("Brasil");
	}
	
	@Test
	public void salvar(){

		//Instancia a banda e seta os atributos
		Banda banda = new Banda(BANDA_NOME, this.pais, BANDA_CIDADE, BANDA_ANO_FORMACAO, BANDA_RELEASE);
		
		//Salva a banda
		Banda bandaSalva = super.getBandaDAO().salvar(banda);
		
		//Intancia e seta os atributos dos lancamentos
		Lancamento lancamento1 = new Lancamento(LANCAMENTO_NOME,LANCAMENTO_ANO,LANCAMENTO_INFORMACOES);
		lancamento1.setBanda(banda);

		Lancamento lancamento2 = new Lancamento(LANCAMENTO_NOME,LANCAMENTO_ANO,LANCAMENTO_INFORMACOES);
		lancamento2.setBanda(banda);
		
		//Salva os lancamentos
		super.getLancamentoDAO().salvar(lancamento1);
		super.getLancamentoDAO().salvar(lancamento2);
		
		//Cria um set com os lancamentos e seta na banda
		Set<Lancamento> lancamentos = new HashSet<Lancamento>();
		lancamentos.add(lancamento1);
		lancamentos.add(lancamento2);
		
		//banda.setLancamentos(lancamentos);
		
		//Salva a banda novamente
		bandaSalva = super.getBandaDAO().salvar(banda);

		//Verifica se a banda foi salva corretamente
		this.utilVerificarAtributos(banda, bandaSalva);
		
	}
	
	@Test
	public void recuperar(){
		//Instancia a banda e seta os atributos
		Banda banda = new Banda(BANDA_NOME, BANDA_CIDADE, this.pais, BANDA_ANO_FORMACAO, BANDA_RELEASE);
		
		//Salva a banda
		super.getBandaDAO().salvar(banda);
		
		//Intancia e seta os atributos dos lancamentos
		Lancamento lancamento1 = new Lancamento(LANCAMENTO_NOME,LANCAMENTO_ANO,LANCAMENTO_INFORMACOES);
		lancamento1.setBanda(banda);

		Lancamento lancamento2 = new Lancamento(LANCAMENTO_NOME,LANCAMENTO_ANO,LANCAMENTO_INFORMACOES);
		lancamento2.setBanda(banda);
		
		//Salva os lancamentos
		super.getLancamentoDAO().salvar(lancamento1);
		super.getLancamentoDAO().salvar(lancamento2);
		
		//Recupera a banda
		Banda bandaRecuperada = super.getBandaDAO().recuperar(banda.getId());
		
		//Verifica se a banda foi recuperada corretamente
		this.utilVerificarAtributos(banda, bandaRecuperada);
	}
	
}
