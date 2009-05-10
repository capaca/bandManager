package com.bandManager.action;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.domain.Lancamento;
import com.bandManager.domain.Musica;

public class TesteMusicaCRUDAction extends Teste {

	@Test
	public void salvar(){
		//Instancia o lancamento e salva
		Lancamento lancamento = new Lancamento(LANCAMENTO_NOME, LANCAMENTO_ANO, LANCAMENTO_INFORMACOES);
		super.getLancamentoCRUDAction().setLancamento(lancamento);
		super.getLancamentoCRUDAction().salvar();
		
		//Instancia e salva a musica
		Musica musica = new Musica(MUSICA_NOME, MUSICA_NUMERO, MUSICA_LETRA,MUSICA_ARQUIVO,lancamento);
		super.getMusicaCRUDAction().setMusica(musica);
		assertEquals(Action.SUCCESS, super.getMusicaCRUDAction().salvar());
	}
	
	@Test
	public void recuperar(){
		//Instancia o lancamento e salva
		Lancamento lancamento = new Lancamento(LANCAMENTO_NOME, LANCAMENTO_ANO, LANCAMENTO_INFORMACOES);
		super.getLancamentoCRUDAction().setLancamento(lancamento);
		super.getLancamentoCRUDAction().salvar();
		
		//Instancia e salva a musica
		Musica musica = new Musica(MUSICA_NOME, MUSICA_NUMERO, MUSICA_LETRA,MUSICA_ARQUIVO,lancamento);
		super.getMusicaCRUDAction().setMusica(musica);
		super.getMusicaCRUDAction().salvar();
		assertEquals(Action.SUCCESS, super.getMusicaCRUDAction().recuperar());
	}
	
	@Test
	public void excluir(){
		//Instancia o lancamento e salva
		Lancamento lancamento = new Lancamento(LANCAMENTO_NOME, LANCAMENTO_ANO, LANCAMENTO_INFORMACOES);
		super.getLancamentoCRUDAction().setLancamento(lancamento);
		super.getLancamentoCRUDAction().salvar();
		
		//Instancia e salva a musica
		Musica musica = new Musica(MUSICA_NOME, MUSICA_NUMERO, MUSICA_LETRA,MUSICA_ARQUIVO,lancamento);
		super.getMusicaCRUDAction().setMusica(musica);
		super.getMusicaCRUDAction().salvar();
		assertEquals(Action.SUCCESS, super.getMusicaCRUDAction().excluir());
	}
}
