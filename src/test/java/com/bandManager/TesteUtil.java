package com.bandManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import com.bandManager.domain.Arquivo;
import com.bandManager.domain.Banda;
import com.bandManager.domain.Lancamento;
import com.bandManager.domain.Musica;
import com.bandManager.domain.Pais;
import com.bandManager.domain.Show;
import com.bandManager.domain.Usuario;

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
	
	//Show
	protected static final String SHOW_NOME = "NomeDoShow";
	protected static final String SHOW_LOCAL = "LocalDoShow";
	protected static final String SHOW_CIDADE = "CidadeDoShow";
	protected static final String SHOW_DESCRICAO = "DescricaoDoShow";
	
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
	 * Cria uma banda com o nome passado por parâmetro e salva.
	 */
	protected Banda utilCriarBanda(String nome) {
		Pais pais = utilCriarPais(PAIS_NOME);
		Banda banda = new Banda(nome, pais, BANDA_CIDADE, BANDA_ANO_FORMACAO, BANDA_RELEASE);
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
	
	/**
	 * Verifica os atributos de um conjunto de {@link Banda}s.
	 * @param banda
	 */
	protected void utilVerificarAtributos(List<Banda> bandasEsperadas, List<Banda> bandas){
		for(int i=0; i<bandas.size(); i++){
			this.utilVerificarAtributos(bandasEsperadas.get(i), bandas.get(i));
		}
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
	
	/**
	 * Cria um lancamento completo
	 */
	protected Lancamento utilCriarLancamento() {
		Banda banda = utilCriarBanda();
		Lancamento lancamento = new Lancamento(LANCAMENTO_NOME,LANCAMENTO_ANO,LANCAMENTO_INFORMACOES);
		lancamento.setBanda(banda);
		getLancamentoFacade().salvar(lancamento);
		return lancamento;
	}
	
	/**
	 * Verifica o lancamento comparando os dados do lancamento esperado
	 * e o lancamento obtido.
	 * @param lancamento
	 */
	protected void verificarAtributos(Lancamento lancamentoEsperado, Lancamento lancamento){
		assertEquals(lancamentoEsperado.getNome(), lancamento.getNome());
		assertEquals(lancamentoEsperado.getAno(), lancamento.getAno());
		assertEquals(lancamentoEsperado.getInformacoes(), lancamento.getInformacoes());
		assertEquals(lancamentoEsperado.getBanda().getNome(), lancamento.getBanda().getNome());
	}
	
//==/* Musica */================================================================================================================
	
	/**
	 * Cria uma musica completa
	 */
	protected Musica utilCriarMusica() {
		Lancamento lancamento = utilCriarLancamento();
		Musica musica = new Musica(MUSICA_NOME, MUSICA_NUMERO, MUSICA_LETRA, lancamento);
		super.getMusicaFacade().salvar(musica);
		return musica;
	}
	
	/**
	 * Cria uma musica completa
	 */
	protected Musica utilCriarMusica(Lancamento lancamento) {
		Musica musica = new Musica(MUSICA_NOME, MUSICA_NUMERO, MUSICA_LETRA, lancamento);
		super.getMusicaFacade().salvar(musica);
		return musica;
	}
	
	/**
	 * Verifica os atributos entre das músicas.
	 * @param musica
	 */
	protected void verificarAtributos(Musica musicaEsperada, Musica musica){
		assertEquals(musicaEsperada.getNome(), musica.getNome());
		assertEquals(musicaEsperada.getNumero(), musica.getNumero());
		assertEquals(musicaEsperada.getLetra(), musica.getLetra());
	}
	
//==/* Show */================================================================================================================
	protected Show utilCriarShowNaoSalvo(){
		Show show = new Show(SHOW_NOME, SHOW_LOCAL, SHOW_CIDADE, this.utilCriarPais("Pais"), SHOW_DESCRICAO);
		show.setBanda(this.utilCriarBanda());
		
		return show;
	}
	
	protected Show utilCriarShow(){
		Show show = new Show(SHOW_NOME, SHOW_LOCAL, SHOW_CIDADE, this.utilCriarPais("Pais"), SHOW_DESCRICAO);
		show.setBanda(this.utilCriarBanda());
		return super.getShowFacade().salvar(show);
	}
	
	protected void utilVerificarShow(Show showEsperado, Show show){
		assertNotNull(show);
		assertEquals(showEsperado.getNome(), show.getNome());
		assertEquals(showEsperado.getLocal(), show.getLocal());
		assertEquals(showEsperado.getCidade(), show.getCidade());
		assertEquals(showEsperado.getPais().getNome(), show.getPais().getNome());
		assertEquals(showEsperado.getDescricao(), show.getDescricao());
	}
	
//==/* Arquivo */================================================================================================================

	protected Usuario utilCriarUsuario(){
		return utilCriarUsuario("capaca", "123456");
	}
	
	protected Usuario utilCriarUsuario(String username, String password){
		Usuario usuario = new Usuario(username,password);
		return super.getUsuarioDAO().salvar(usuario);
	}
	
	protected void utilVerificarArquivo(Arquivo arquivoEsperado, Arquivo arquivoAtual){
		assertNotNull(arquivoAtual);
		assertEquals(arquivoEsperado.getNome(), arquivoAtual.getNome());
		assertEquals(arquivoEsperado.getCaminhoArquivo(), arquivoAtual.getCaminhoArquivo());
		assertEquals(arquivoEsperado.getContentType(), arquivoAtual.getContentType());
		assertEquals(arquivoEsperado.getDiretorioRelativo(), arquivoAtual.getDiretorioRelativo());
	}

//==/* Usuario */================================================================================================================

	
	
	protected void utilVerificarAtributos(Usuario usuarioEsperado, Usuario usuario) {
		assertNotNull(usuarioEsperado);
		assertNotNull(usuario);
		
		assertEquals(usuarioEsperado.getUsername(), usuario.getUsername());
		assertEquals(usuarioEsperado.getPassword(), usuario.getPassword());
	}
}

