package com.bandManager.facade;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.domain.Arquivo;
import com.bandManager.domain.Banda;
import com.bandManager.domain.Lancamento;
import com.bandManager.domain.Musica;
import com.bandManager.domain.Pais;
import com.bandManager.exception.ArquivoInvalidoException;

public class TesteLancamentoFacade extends Teste {

	private Pais pais;
	private Banda banda;
	private Lancamento lancamento;
	
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
		
		this.lancamento = utilCriarLancamento(banda);
	}
	
	@Test
	public void salvar(){
		
		//Instancia e salva o lancamento
		Lancamento lancamento = new Lancamento(LANCAMENTO_NOME,LANCAMENTO_ANO,LANCAMENTO_INFORMACOES);
		lancamento.setBanda(banda);
		Lancamento lancamentoSalvo = super.getLancamentoFacade().salvar(lancamento);
		
		this.verificarAtributos(lancamento, lancamentoSalvo);
	}
	
	@Test
	public void recuperar(){
		
		//Recupera o lancamento
		Lancamento lancamentoRecuperado = super.getLancamentoFacade().recuperar(lancamento.getId());
		
		//Verifica se o lancamento foi recuperado corretamente
		this.verificarAtributos(lancamentoRecuperado, lancamento);
	}
	
	@Test
	public void excluir(){
		//Exclui o lancamento
		super.getLancamentoFacade().excluir(lancamento.getId());
		
		//Tenta recuperar o lancamento excluido
		assertNull(super.getLancamentoFacade().recuperar(lancamento.getId()));
	}
	
	@Test
	public void excluirLancamentoECapa() throws ArquivoInvalidoException, IOException{
		//Cria o arquivo que vai ser transferido para o servidor
		File file = new File(PATH_SISTEMA+"/capa.jpg");
		assertTrue(file.exists());
		
		Arquivo capa = new Arquivo("capa.jpg","image",file,"imagens/banda/lancamento/capa/");
		
		//Cria o arquivo de destino
		File file2 = new File(PATH_SISTEMA+"/target/"+capa.getCaminhoArquivo());
		file2.delete();
		assertFalse(file2.exists());
		
		super.getLancamentoFacade().adicionarCapa(lancamento, capa, PATH_SISTEMA+"/target");
		assertTrue(file2.exists());
		
		super.getLancamentoFacade().excluir(this.lancamento.getId());
		assertFalse(file2.exists());
		
		Lancamento lancamentoRecuperado = super.getLancamentoFacade().recuperar(this.lancamento.getId());
		assertNull(lancamentoRecuperado);
	}
	
	@Test
	public void excluirLancamentoEMusicas() throws ArquivoInvalidoException, IOException{
		//Cria a musica
		Musica musica = utilCriarMusica(this.lancamento);
		
		//Cria o arquivo que vai ser transferido para o servidor
		File file = new File(PATH_SISTEMA+"/musica.mp3");
		assertTrue(file.exists());
		
		Arquivo arquivoAudio = new Arquivo("musica.mp3","audio",file,"audio/");
		
		//Cria o arquivo de destino e transfere para o servidor
		File file2 = new File(PATH_SISTEMA+"/target/"+arquivoAudio.getCaminhoArquivo());		
		super.getMusicaFacade().adicionarArquivoMusica(musica, arquivoAudio, PATH_SISTEMA+"/target");
		assertTrue(file2.exists());
		
		super.getLancamentoFacade().excluir(this.lancamento.getId());
		assertFalse(file2.exists());
		
		Lancamento lancamentoRecuperado = super.getLancamentoFacade().recuperar(this.lancamento.getId());
		assertNull(lancamentoRecuperado);
		
		Musica musicaRecuperada = super.getMusicaFacade().recuperar(musica.getId());
		assertNull(musicaRecuperada);
	}
	
	@Test
	public void adicionarCapa() throws ArquivoInvalidoException, IOException{
		
		//Cria o arquivo que vai ser transferido para o servidor
		File file = new File(PATH_SISTEMA+"/capa.jpg");
		assertTrue(file.exists());
		
		Arquivo capa = new Arquivo("capa.jpg","image",file,"imagens/banda/lancamento/capa/");
		
		//Cria o arquivo de destino
		File file2 = new File(PATH_SISTEMA_TARGET+"/"+capa.getCaminhoArquivo());
		file2.delete();
		assertFalse(file2.exists());
		
		super.getLancamentoFacade().adicionarCapa(lancamento, capa, PATH_SISTEMA_TARGET);
		assertTrue(file2.exists());
		
		Lancamento lancamentoRecuperado = super.getLancamentoFacade().recuperar(lancamento.getId());
		utilVerificarArquivo(capa, lancamentoRecuperado.getCapa());
	}
	
	@Test
	public void excluirCapa() throws ArquivoInvalidoException, IOException{
		//Cria o arquivo que vai ser transferido para o servidor
		File file = new File(PATH_SISTEMA+"/capa.jpg");
		assertTrue(file.exists());
		
		Arquivo capa = new Arquivo("capa.jpg","image",file,"imagens/banda/lancamento/capa/");
		
		//Cria o arquivo de destino
		File file2 = new File(PATH_SISTEMA_TARGET+"/"+capa.getCaminhoArquivo());
		file2.delete();
		assertFalse(file2.exists());
		
		super.getLancamentoFacade().adicionarCapa(this.lancamento, capa,PATH_SISTEMA_TARGET);
		assertTrue(file2.exists());
		
		this.lancamento = super.getLancamentoFacade().recuperar(this.lancamento.getId());
		
		//Exclui a capa
		super.getLancamentoFacade().excluirCapa(this.lancamento, PATH_SISTEMA_TARGET);
		
		//Verifica que a capa foi excluida com sucesso
		assertFalse(file2.exists());
		Lancamento lancamentoRecuperado = super.getLancamentoFacade().recuperar(this.lancamento.getId());
		assertNull(lancamentoRecuperado.getCapa());
	}
}
