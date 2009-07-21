package com.bandManager.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.domain.Lancamento;
import com.bandManager.domain.Musica;
import com.opensymphony.xwork2.interceptor.annotations.Before;

public class TesteMusicaDAO extends Teste {
	
	private Lancamento lancamento;
	
	@Before
	public void setup(){
		this.lancamento = new Lancamento(LANCAMENTO_NOME, LANCAMENTO_ANO, LANCAMENTO_INFORMACOES);
		super.getLancamentoDAO().salvar(this.lancamento);
	}
	
	@Test
	public void salvar(){
		//Instancia a musica
		Musica musica = new Musica(MUSICA_NOME, MUSICA_NUMERO, MUSICA_LETRA, lancamento);
		
		//Salva a musica
		Musica musicaSalva = super.getMusicaDAO().salvar(musica);
		
		//Verifica se a musica foi salva
		assertNotNull(musicaSalva);
		assertTrue(musicaSalva.getId()>0);
		verificarAtributos(musica, musicaSalva);
		
		//Muda o nome
		musica.setNome("NomeMusica");
		
		//Salva a musica
		musicaSalva = super.getMusicaDAO().salvar(musica);
		
		//Verifica se a musica foi salva
		verificarAtributos(musica, musicaSalva);
	}
	
	@Test
	public void recuperar(){
		//Instancia a musica
		Musica musica = new Musica(MUSICA_NOME, MUSICA_NUMERO, MUSICA_LETRA, lancamento);
		
		//Salva a musica
		Musica musicaSalva = super.getMusicaDAO().salvar(musica);
		
		//Recupera a musica
		Musica musicaRecuperada = super.getMusicaDAO().recuperar(musicaSalva.getId());
		
		//Verifica se a musica foi recuperada corretamente
		assertNotNull(musicaRecuperada);
		assertTrue(musicaRecuperada.getId()>0);
		assertEquals(musica.getId(), musicaRecuperada.getId());
		verificarAtributos(musica, musicaRecuperada);
	}
	
	@Test
	public void excluir(){
		//Instancia a musica
		Musica musica = new Musica(MUSICA_NOME, MUSICA_NUMERO, MUSICA_LETRA, lancamento);
		
		//Salva a musica
		Musica musicaSalva = super.getMusicaDAO().salvar(musica);
		
		//Exclui a musica
		super.getMusicaDAO().excluir(musicaSalva.getId());
		
		//Recupera a musica
		Musica musicaRecuperada = super.getMusicaDAO().recuperar(musicaSalva.getId());
		
		//Verifica se a musica foi excluida
		assertNull(musicaRecuperada);
	}
}
