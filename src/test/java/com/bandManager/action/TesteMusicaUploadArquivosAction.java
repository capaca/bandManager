package com.bandManager.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.action.musica.MusicaUploadArquivosAction;
import com.bandManager.domain.Musica;
import com.bandManager.util.FileUtil;

public class TesteMusicaUploadArquivosAction extends Teste {

	private Musica musica;
	private File arquivoMusica;
	
	@Before
	public void preparacao(){
		this.musica = utilCriarMusica();
		this.arquivoMusica = new File(PATH_SISTEMA+"/musica.mp3");
	
		//Remove o diretorio de imagens
		File diretorioImagens = new File(FileUtil.getCaminhoSitema()+"/audio");
		FileUtil.removerDiretorio(diretorioImagens);
	}
	
	@Test
	public void adicionarArquivoMusica(){
		
		MusicaUploadArquivosAction musicaUploadArquivosAction = super.getMusicaUploadArquivosAction();
		musicaUploadArquivosAction.setMusica(this.musica);
		musicaUploadArquivosAction.setArquivo(this.arquivoMusica);
		musicaUploadArquivosAction.setArquivoContentType("audio/mp3");
		musicaUploadArquivosAction.setArquivoFileName("musica.mp3");
		
		assertEquals(Action.SUCCESS, super.getMusicaUploadArquivosAction().adicionarArquivoAudio());
		
		//Recupera a musica
		Musica musicaRecuperada = super.getMusicaFacade().recuperar(this.musica.getId());
		
		//Verifica se a capa foi adicionada com sucesso
		utilVerificarArquivo(super.getMusicaUploadArquivosAction().getMusica().getArquivoAudio(), musicaRecuperada.getArquivoAudio());
		
		//Verifica que o arquivo de destino existe
		File file2 = new File(FileUtil.getCaminhoSitema()+"/"+super.getMusicaUploadArquivosAction().getMusica().getArquivoAudio().getCaminhoArquivo());
		assertTrue(file2.exists());
	}
	
	@Test
	public void excluirArquivoAudio(){
		MusicaUploadArquivosAction musicaUploadArquivosAction = super.getMusicaUploadArquivosAction();
		musicaUploadArquivosAction.setMusica(this.musica);
		musicaUploadArquivosAction.setArquivo(this.arquivoMusica);
		musicaUploadArquivosAction.setArquivoContentType("audio/mp3");
		musicaUploadArquivosAction.setArquivoFileName("musica.mp3");
		
		assertEquals(Action.SUCCESS, super.getMusicaUploadArquivosAction().adicionarArquivoAudio());
		
		//Recupera a musica
		Musica musicaRecuperada = super.getMusicaFacade().recuperar(this.musica.getId());
		
		//Exclui o arquivo
		assertEquals(Action.SUCCESS, super.getMusicaUploadArquivosAction().excluirArquivoAudio());
		
		//Verifica que o arquivo foi deletado
		File file2 = new File(FileUtil.getCaminhoSitema()+"/"+musicaRecuperada.getArquivoAudio().getCaminhoArquivo());
		assertFalse(file2.exists());
	}
}
