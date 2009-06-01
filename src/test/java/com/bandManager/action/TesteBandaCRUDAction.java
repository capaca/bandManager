package com.bandManager.action;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.domain.Banda;
import com.bandManager.domain.Lancamento;
import com.bandManager.domain.Pais;
import com.bandManager.exception.ObjetoNaoEncontradoException;

public class TesteBandaCRUDAction extends Teste {
	
	private Pais pais;
	private Lancamento lancamento1;
	private Lancamento lancamento2;
	private Banda banda;
	
	@Before
	public void preparacao(){
		this.pais = utilCriarPais("Brasil");
		
		this.banda = utilCriarBanda(pais);
		
		this.lancamento1 = utilCriarLancamento(banda);
		this.lancamento2 = utilCriarLancamento(banda);
	}
	
	@Test
	public void salvar() throws ObjetoNaoEncontradoException{
		
		//Seta a banda, salva e verifica o retorno
		super.getBandaCRUDAction().setBanda(this.banda);
		assertEquals(Action.SUCCESS, super.getBandaCRUDAction().salvar());
		
		//Recupera a banda e verifica
		Banda bandaRecuperada = super.getBandaFacade().recuperar(banda.getId());
		
		//Verifica se a banda foi salva
		utilVerificarAtributos(banda, bandaRecuperada);
	}
	
	@Test
	public void recuperar(){
		
		//Seta a banda, salva e verifica o retorno
		super.getBandaCRUDAction().setBanda(banda);
		super.getBandaCRUDAction().salvar();
		
		//Recupera a banda
		assertEquals(Action.SUCCESS, super.getBandaCRUDAction().recuperar());
		
		//Verifica se a banda foi recuperada com sucesso
		this.utilVerificarAtributos(banda,super.getBandaCRUDAction().getBanda());
		
		//Verifica o pais
		assertEquals(1, super.getBandaCRUDAction().getPaises().size());
		assertEquals(this.pais.getNome(), super.getBandaCRUDAction().getPaises().get(0).getNome());
	}
}
