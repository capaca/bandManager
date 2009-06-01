package com.bandManager.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.action.lancamento.LancamentoUploadArquivosAction;
import com.bandManager.domain.Lancamento;
import com.bandManager.util.FileUtil;

public class TesteLancamentoUploadArquivosAction extends Teste{

	private Lancamento lancamento;
	private File capa;
	
	@Before
	public void preparacao(){
		this.lancamento = utilCriarLancamento();
		this.capa = new File(PATH_SISTEMA+"/capa.jpg");
	
		//Remove o diretorio de imagens
		File diretorioImagens = new File(FileUtil.getCaminhoSitema()+"/imagens");
		FileUtil.removerDiretorio(diretorioImagens);
	}
	
	@Test
	public void adicionarCapa(){
		
		LancamentoUploadArquivosAction lancamentoUploadArquivosAction = super.getLancamentoUploadArquivosAction();
		lancamentoUploadArquivosAction.setLancamento(this.lancamento);
		lancamentoUploadArquivosAction.setBanda(this.lancamento.getBanda());
		lancamentoUploadArquivosAction.setCapa(this.capa);
		lancamentoUploadArquivosAction.setCapaContentType("image/jpg");
		lancamentoUploadArquivosAction.setCapaFileName("capa.jpg");
		
		assertEquals(Action.SUCCESS, super.getLancamentoUploadArquivosAction().adicionarCapa());
		
		//Recupera o lancamento
		Lancamento lancamentoRecuperado = super.getLancamentoFacade().recuperar(this.lancamento.getId());
		
		//Verifica se a capa foi adicionada com sucesso
		utilVerificarArquivo(super.getLancamentoUploadArquivosAction().getLancamento().getCapa(), lancamentoRecuperado.getCapa());
		
		//Verifica que o arquivo de destino existe
		File file2 = new File(FileUtil.getCaminhoSitema()+"/"+super.getLancamentoUploadArquivosAction().getLancamento().getCapa().getCaminhoArquivo());
		assertTrue(file2.exists());
	}
	
	@Test
	public void excluirCapa(){
		LancamentoUploadArquivosAction lancamentoUploadArquivosAction = super.getLancamentoUploadArquivosAction();
		lancamentoUploadArquivosAction.setLancamento(this.lancamento);
		lancamentoUploadArquivosAction.setBanda(this.lancamento.getBanda());
		lancamentoUploadArquivosAction.setCapa(this.capa);
		lancamentoUploadArquivosAction.setCapaContentType("image/jpg");
		lancamentoUploadArquivosAction.setCapaFileName("capa.jpg");
		
		assertEquals(Action.SUCCESS, super.getLancamentoUploadArquivosAction().adicionarCapa());
		
		//Recupera o lancamento
		Lancamento lancamentoRecuperado = super.getLancamentoFacade().recuperar(this.lancamento.getId());
		
		//Exclui o logo
		assertEquals(Action.SUCCESS, super.getLancamentoUploadArquivosAction().excluirCapa());
		
		//Verifica que o arquivo foi deletado
		File file2 = new File(FileUtil.getCaminhoSitema()+"/"+lancamentoRecuperado.getCapa().getCaminhoArquivo());
		assertFalse(file2.exists());
	}
}
