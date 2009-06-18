package com.bandManager.action;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.domain.Banda;
import com.bandManager.domain.Lancamento;
import com.bandManager.domain.Pais;
import com.bandManager.exception.ObjetoNaoEncontradoException;

public class TesteBandaCRUDAction extends Teste {
	
	@Test
	public void salvar() throws ObjetoNaoEncontradoException{
		
		Banda banda = utilCriarBanda();
		
		//Seta a banda, salva e verifica o retorno
		super.getBandaCRUDAction().setBanda(banda);
		assertEquals(Action.SUCCESS, super.getBandaCRUDAction().salvar());
		
		//Recupera a banda e verifica
		Banda bandaRecuperada = super.getBandaFacade().recuperar(banda.getId());
		
		//Verifica se a banda foi salva
		utilVerificarAtributos(banda, bandaRecuperada);
	}
	
	@Test
	public void recuperar(){
		
		Pais pais = utilCriarPais("Brasil");
		Banda banda = utilCriarBanda(pais);
		
		//Seta a banda, salva e verifica o retorno
		super.getBandaCRUDAction().setBanda(banda);
		super.getBandaCRUDAction().salvar();
		
		//Recupera a banda
		assertEquals(Action.SUCCESS, super.getBandaCRUDAction().recuperar());
		
		//Verifica se a banda foi recuperada com sucesso
		this.utilVerificarAtributos(banda,super.getBandaCRUDAction().getBanda());
		
		//Verifica o pais
		assertEquals(1, super.getBandaCRUDAction().getPaises().size());
		assertEquals(pais.getNome(), super.getBandaCRUDAction().getPaises().get(0).getNome());
	}
	
	@Test
	public void recuperarTodas(){
		Banda destruction = utilCriarBanda("Destruction");
		Banda sodom =utilCriarBanda("Sodom");
		Banda kreator =utilCriarBanda("Kreator");
		
		List<Banda> bandas = new ArrayList<Banda>();
		bandas.add(destruction);
		bandas.add(sodom);
		bandas.add(kreator);
		
		assertEquals(Action.SUCCESS, super.getBandaCRUDAction().recuperarTodas());
		List<Banda> bandasRecuperadas = super.getBandaCRUDAction().getBandas();
		
		assertEquals(3, bandas.size());
		utilVerificarAtributos(bandas, bandasRecuperadas);
	}
}
