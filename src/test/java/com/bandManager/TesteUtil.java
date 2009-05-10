package com.bandManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.bandManager.domain.Arquivo;
import com.bandManager.domain.Banda;
import com.bandManager.domain.Lancamento;
import com.bandManager.domain.Pais;

public class TesteUtil extends Dependencias{

//==/* Constantes dos testes */===========================================================================================================
	
	//Geral
	protected static final String PATH_SISTEMA = "/home/pedro/workspace/bandManager"; 
	protected static final String PATH_SISTEMA_TARGET = PATH_SISTEMA+"/target/bandManager";
	//Musica
	protected static final String MUSICA_NOME = "Nome";
	protected static final int MUSICA_NUMERO = 1;
	protected static final String MUSICA_LETRA = "Letra";
	protected static final String MUSICA_ARQUIVO = "Arquivo";
	
	//Album
	protected final String LANCAMENTO_NOME = "Nome";
	protected final int LANCAMENTO_ANO = 1985;
	protected final String LANCAMENTO_INFORMACOES = "Informacoes";
	
	//Pais
	protected final String PAIS_NOME = "Pais";
	
	//Banda
	protected final String BANDA_NOME = "Nome";
	protected final String BANDA_CIDADE = "Cidade";
	protected final String BANDA_RELEASE = "release";
	protected final String BANDA_ANO_FORMACAO = "1999";
	protected final String BANDA_LOGO_NOME = "imagens/banda/logo/logo.png";
	
//==/* Métodos Utilitários */=============================================================================================================
	
//==/* Banda */===========================================================================================================================
	/**
	 * Cria uma banda com o {@link Pais} passado por parâmetro e salva.
	 */
	protected Banda utilCriarBanda(Pais pais) {
		Banda banda = new Banda(BANDA_NOME, pais, BANDA_CIDADE, BANDA_ANO_FORMACAO, BANDA_RELEASE);
		return getBandaFacade().salvar(banda);
	}
	
	/**
	 * Cria uma banda e salva.
	 */
	protected Banda utilCriarBanda() {
		Pais pais = utilCriarPais(PAIS_NOME);
		Banda banda = new Banda(BANDA_NOME, pais, BANDA_CIDADE, BANDA_ANO_FORMACAO, BANDA_RELEASE);
		return getBandaFacade().salvar(banda);
	}
	
	/**
	 * Verifica os atributos da {@link Banda} a partir das constantes da classe.
	 * @param banda
	 */
	protected void utilVerificarAtributos(Banda bandaEsperada, Banda bandaAtual){
		assertNotNull(bandaAtual);
		assertEquals(bandaEsperada.getNome(), bandaAtual.getNome());
		assertEquals(bandaEsperada.getRelease(), bandaAtual.getRelease());
		assertEquals(bandaEsperada.getCidade(), bandaAtual.getCidade());
		assertEquals(bandaEsperada.getPais().getNome(), bandaAtual.getPais().getNome());
	}
	
//==/* Pais */=================================================================================================================

	/**
	 * Cria um {@link Pais} como nome passado por parâmetro e salva.
	 */
	protected Pais utilCriarPais(String nome) {
		Pais pais = new Pais(nome);
		return getPaisFacade().salvar(pais);
	}
	
//==/* Lancamento */============================================================================================================
	/**
	 * Cria um lancamento da {@link Banda} passada por parâmetro e salva
	 */
	protected Lancamento utilCriarLancamento(Banda banda) {
		Lancamento lancamento = new Lancamento(LANCAMENTO_NOME,LANCAMENTO_ANO,LANCAMENTO_INFORMACOES);
		lancamento.setBanda(banda);
		getLancamentoFacade().salvar(lancamento);
		return lancamento;
	}
	
//==/* Arquivo */================================================================================================================

	protected void utilVerificarArquivo(Arquivo arquivoEsperado, Arquivo arquivoAtual){
		assertNotNull(arquivoAtual);
		assertEquals(arquivoEsperado.getNome(), arquivoAtual.getNome());
		assertEquals(arquivoEsperado.getCaminhoArquivo(), arquivoAtual.getCaminhoArquivo());
		assertEquals(arquivoEsperado.getContentType(), arquivoAtual.getContentType());
		assertEquals(arquivoEsperado.getDiretorioRelativo(), arquivoAtual.getDiretorioRelativo());
	}
}



