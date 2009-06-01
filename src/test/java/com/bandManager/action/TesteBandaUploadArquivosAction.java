package com.bandManager.action;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.bandManager.TesteUtil;
import com.bandManager.domain.Banda;
import com.bandManager.exception.ObjetoNaoEncontradoException;
import com.bandManager.util.FileUtil;

public class TesteBandaUploadArquivosAction extends TesteUtil {

	private Banda banda;
	private File logo;
	private File foto;
	
	@Before
	public void preparacao(){
		this.banda = utilCriarBanda();
		this.logo = new File(PATH_SISTEMA+"/logo.jpg");
		this.foto = new File(PATH_SISTEMA+"/foto.jpg");
	
		//Remove o diretorio de imagens
		File diretorioImagens = new File(FileUtil.getCaminhoSitema()+"/imagens");
		FileUtil.removerDiretorio(diretorioImagens);
	}
	
	@Test
	public void adicionarLogo() throws ObjetoNaoEncontradoException{
		//Verifica que o arquivo a ser transferido existe
		assertTrue(this.logo.exists());
		
		//Seta os atributos para adicionar o logo
		super.getBandaUploadArquivosAction().setBanda(banda);
		super.getBandaUploadArquivosAction().setLogo(this.logo);
		super.getBandaUploadArquivosAction().setLogoContentType("image");
		super.getBandaUploadArquivosAction().setLogoFileName("logo.jpg");
		
		//Adiciona o logo
		assertEquals(Action.SUCCESS, super.getBandaUploadArquivosAction().adicionarLogo());
		
		//Recupera a banda
		Banda bandaRecuperada = super.getBandaFacade().recuperar(this.banda.getId());
		
		//Verifica se o logo foi adicionado com sucesso
		
		utilVerificarArquivo(super.getBandaUploadArquivosAction().getBanda().getLogo(), bandaRecuperada.getLogo());
		
		//Verifica que o arquivo de destino existe
		File file2 = new File(FileUtil.getCaminhoSitema()+"/"+super.getBandaUploadArquivosAction().getBanda().getLogo().getCaminhoArquivo());
		assertTrue(file2.exists());
		
	}
	
	@Test
	public void excluirLogo() throws ObjetoNaoEncontradoException{
		//Verifica que o arquivo a ser transferido existe
		assertTrue(this.logo.exists());
		
		//Seta os atributos para adicionar o logo
		super.getBandaUploadArquivosAction().setBanda(banda);
		super.getBandaUploadArquivosAction().setLogo(this.logo);
		super.getBandaUploadArquivosAction().setLogoContentType("image");
		super.getBandaUploadArquivosAction().setLogoFileName("logo.jpg");
		
		//Adiciona o logo
		assertEquals(Action.SUCCESS, super.getBandaUploadArquivosAction().adicionarLogo());
		
		//Recupera a banda
		Banda bandaRecuperada = super.getBandaFacade().recuperar(this.banda.getId());
		
		//Exclui o logo
		assertEquals(Action.SUCCESS, super.getBandaUploadArquivosAction().excluirLogo());
		
		//Verifica que o arquivo foi deletado
		File file2 = new File(FileUtil.getCaminhoSitema()+"/"+bandaRecuperada.getLogo().getCaminhoArquivo());
		assertFalse(file2.exists());
	}
	
	@Test
	public void adicionarFoto() throws ObjetoNaoEncontradoException{
		//Verifica que o arquivo a ser transferido existe
		assertTrue(this.foto.exists());
		
		//Seta os atributos para adicionar a foto
		super.getBandaUploadArquivosAction().setBanda(banda);
		super.getBandaUploadArquivosAction().setFoto(this.foto);
		super.getBandaUploadArquivosAction().setFotoContentType("image");
		super.getBandaUploadArquivosAction().setFotoFileName("foto.jpg");
		
		//Adiciona o logo
		assertEquals(Action.SUCCESS, super.getBandaUploadArquivosAction().adicionarFoto());
		
		//Recupera a banda
		Banda bandaRecuperada = super.getBandaFacade().recuperar(this.banda.getId());
		
		//Verifica se o logo foi adicionado com sucesso		
		utilVerificarArquivo(super.getBandaUploadArquivosAction().getBanda().getFoto(), bandaRecuperada.getFoto());
		
		//Verifica que o arquivo de destino existe
		File file2 = new File(FileUtil.getCaminhoSitema()+"/"+super.getBandaUploadArquivosAction().getBanda().getFoto().getCaminhoArquivo());
		assertTrue(file2.exists());
	}
	
	@Test
	public void excluirFoto() throws ObjetoNaoEncontradoException{
		//Verifica que o arquivo a ser transferido existe
		assertTrue(this.logo.exists());
		
		//Seta os atributos para adicionar o logo
		super.getBandaUploadArquivosAction().setBanda(banda);
		super.getBandaUploadArquivosAction().setFoto(this.foto);
		super.getBandaUploadArquivosAction().setFotoContentType("image");
		super.getBandaUploadArquivosAction().setFotoFileName("foto.jpg");
		
		//Adiciona o logo
		assertEquals(Action.SUCCESS, super.getBandaUploadArquivosAction().adicionarFoto());
		
		//Recupera a banda
		Banda bandaRecuperada = super.getBandaFacade().recuperar(this.banda.getId());
		
		//Exclui o logo
		assertEquals(Action.SUCCESS, super.getBandaUploadArquivosAction().excluirFoto());
		
		//Verifica que o arquivo foi deletado
		File file2 = new File(FileUtil.getCaminhoSitema()+"/"+bandaRecuperada.getFoto().getCaminhoArquivo());
		assertFalse(file2.exists());
	}
}
