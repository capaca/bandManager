package com.bandManager.facade;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.domain.Arquivo;
import com.bandManager.domain.Lancamento;
import com.bandManager.domain.Musica;
import com.bandManager.exception.ArquivoInvalidoException;

public class TesteMusicaFacade extends Teste {

	private Lancamento lancamento;
	private Musica musica;
	
	@Before
	public void setup(){
		this.lancamento = utilCriarLancamento();
		this.musica = utilCriarMusica();
	}
	
	@Test
	public void salvar(){
		//Instancia a musica
		Musica musica = new Musica(MUSICA_NOME, MUSICA_NUMERO, MUSICA_LETRA, lancamento);
		
		//Salva a musica
		Musica musicaSalva = super.getMusicaFacade().salvar(musica);
		
		//Verifica se a musica foi salva
		assertNotNull(musicaSalva);
		assertTrue(musicaSalva.getId()>0);
		verificarAtributos(musica, musicaSalva);
	}
	
	@Test
	public void recuperar(){
		//Instancia a musica
		Musica musica = new Musica(MUSICA_NOME, MUSICA_NUMERO, MUSICA_LETRA, lancamento);
		
		//Salva a musica
		Musica musicaSalva = super.getMusicaFacade().salvar(musica);
		
		//Recupera a musica
		Musica musicaRecuperada = super.getMusicaFacade().recuperar(musicaSalva.getId());
		
		//Verifica se a musica foi recuperada corretamente
		assertNotNull(musicaRecuperada);
		assertTrue(musicaRecuperada.getId()>0);
		assertEquals(musica.getId(), musicaRecuperada.getId());
		verificarAtributos(musica, musicaRecuperada);
	}
	
	@Test
	public void excluir() throws IOException{
		//Instancia a musica
		Musica musica = new Musica(MUSICA_NOME, MUSICA_NUMERO, MUSICA_LETRA, lancamento);
		
		//Salva a musica
		Musica musicaSalva = super.getMusicaFacade().salvar(musica);
		
		//Exclui a musica
		super.getMusicaFacade().excluir(musicaSalva.getId());
		
		//Recupera a musica
		Musica musicaRecuperada = super.getMusicaFacade().recuperar(musicaSalva.getId());
		
		//Verifica se a musica foi excluida
		assertNull(musicaRecuperada);
	}
	
	@Test
	public void adicionarArquivoMusica() throws ArquivoInvalidoException, IOException{
		
		//Cria o arquivo que vai ser transferido para o servidor
		File file = new File(PATH_SISTEMA+"/musica.mp3");
		assertTrue(file.exists());
		
		Arquivo arquivoAudio = new Arquivo("musica.mp3","audio",file,"audio/");
		
		//Cria o arquivo de destino
		File file2 = new File(PATH_SISTEMA_TARGET+"/"+arquivoAudio.getCaminhoArquivo());
		file2.delete();
		assertFalse(file2.exists());
		
		super.getMusicaFacade().adicionarArquivoMusica(this.musica, arquivoAudio, PATH_SISTEMA_TARGET);
		assertTrue(file2.exists());
		
		Musica musicaRecuperada = super.getMusicaFacade().recuperar(this.musica.getId());
		utilVerificarArquivo(arquivoAudio, musicaRecuperada.getArquivoAudio());
	}
	
	@Test
	public void excluirArquivoMusica() throws ArquivoInvalidoException, IOException{
		//Cria o arquivo que vai ser transferido para o servidor
		File file = new File(PATH_SISTEMA+"/musica.mp3");
		assertTrue(file.exists());
		
		Arquivo arquivoAudio = new Arquivo("musica.mp3","audio",file,"audio/");
		
		//Cria o arquivo de destino
		File file2 = new File(PATH_SISTEMA_TARGET+"/"+arquivoAudio.getCaminhoArquivo());
		file2.delete();
		assertFalse(file2.exists());
		
		super.getMusicaFacade().adicionarArquivoMusica(this.musica, arquivoAudio, PATH_SISTEMA_TARGET);
		assertTrue(file2.exists());
		
		this.musica = super.getMusicaFacade().recuperar(this.musica.getId());
		
		//Exclui a capa
		super.getMusicaFacade().excluirArquivoMusica(this.musica, PATH_SISTEMA_TARGET);
		
		//Verifica que a capa foi excluida com sucesso
		assertFalse(file2.exists());
		Musica musicaRecuperada = super.getMusicaFacade().recuperar(this.musica.getId());
		assertNull(musicaRecuperada.getArquivoAudio());
	}
	
	@Test
	public void erroExcluirArquivoMusica(){
		
		this.musica = utilCriarMusica();
		this.musica.setArquivoAudio(new Arquivo());
		
		try {
			super.getMusicaFacade().excluirArquivoMusica(this.musica, "");
			fail("Excluiu mas não deveria");
		} catch (IOException e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void excluirMusicaEArquivo() throws ArquivoInvalidoException, IOException{
		//Cria o arquivo que vai ser transferido para o servidor
		File file = new File(PATH_SISTEMA+"/musica.mp3");
		assertTrue(file.exists());
		
		Arquivo arquivoAudio = new Arquivo("musica.mp3","audio",file,"audio/");
		
		//Cria o arquivo de destino
		File file2 = new File(PATH_SISTEMA+"/target/"+arquivoAudio.getCaminhoArquivo());
		file2.delete();
		assertFalse(file2.exists());
		
		super.getMusicaFacade().adicionarArquivoMusica(this.musica, arquivoAudio, PATH_SISTEMA+"/target");
		assertTrue(file2.exists());
		
		this.musica = super.getMusicaFacade().recuperar(this.musica.getId());
		
		//Exclui a capa
		super.getMusicaFacade().excluir(this.musica.getId());
		
		//Verifica que a capa foi excluida com sucesso
		assertFalse(file2.exists());
		Musica musicaRecuperada = super.getMusicaFacade().recuperar(this.musica.getId());
		assertNull(musicaRecuperada);
	}
}
