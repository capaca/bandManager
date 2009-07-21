package com.bandManager.action;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.bandManager.TesteUtil;
import com.bandManager.domain.Arquivo;
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
	public void erroAdicionarLogoInvalido() throws ObjetoNaoEncontradoException{
		//Logo não é uma imagem
		this.logo = new File(PATH_SISTEMA+"/musica.mp3");
		
		//Verifica que o arquivo a ser transferido existe
		assertTrue(this.logo.exists());
		
		//Seta os atributos para adicionar o logo
		super.getBandaUploadArquivosAction().setBanda(banda);
		super.getBandaUploadArquivosAction().setLogo(this.logo);
		super.getBandaUploadArquivosAction().setLogoContentType("audio/mpeg");
		super.getBandaUploadArquivosAction().setLogoFileName("logo.jpg");
		
		//Tenta adicionar
		assertEquals(Action.ERROR, super.getBandaUploadArquivosAction().adicionarLogo());	
	}
	
	@Test
	public void erroAdicionarLogoNaoExistente() throws ObjetoNaoEncontradoException{
		//Logo não existe
		this.logo = new File(PATH_SISTEMA+"/logoNaoExistente.jpg");
		
		//Seta os atributos para adicionar o logo
		super.getBandaUploadArquivosAction().setBanda(banda);
		super.getBandaUploadArquivosAction().setLogo(this.logo);
		super.getBandaUploadArquivosAction().setLogoContentType("image");
		super.getBandaUploadArquivosAction().setLogoFileName("logo.jpg");
		
		//Tenta adicionar
		assertEquals(Action.ERROR, super.getBandaUploadArquivosAction().adicionarLogo());	
	}
	
	@Test
	public void erroAdicionarLogoParaBandaNaoExistente() throws ObjetoNaoEncontradoException{
		//Logo não existe
		this.logo = new File(PATH_SISTEMA+"/logo.jpg");
		
		//Verifica que o arquivo a ser transferido existe
		assertTrue(this.logo.exists());
		
		//Seta o id de uma banda não existente
		this.banda.setId(20);
		
		//Seta os atributos para adicionar o logo
		super.getBandaUploadArquivosAction().setBanda(banda);
		super.getBandaUploadArquivosAction().setLogo(this.logo);
		super.getBandaUploadArquivosAction().setLogoContentType("image");
		super.getBandaUploadArquivosAction().setLogoFileName("logo.jpg");
		
		//Tenta adicionar
		assertEquals(Action.ERROR, super.getBandaUploadArquivosAction().adicionarLogo());	
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
	public void erroExcluirLogoNaoExistente() throws ObjetoNaoEncontradoException{
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
		
		this.banda.setLogo(new Arquivo("nome", "contentType", new File(""), "/"));
		super.getBandaFacade().salvar(this.banda);
		
		//Exclui o logo
		assertEquals(Action.ERROR, super.getBandaUploadArquivosAction().excluirLogo());
	}
	
	@Test
	public void erroExcluirLogoBandaNaoExistente() throws ObjetoNaoEncontradoException{
		//Verifica que o arquivo a ser transferido existe
		assertTrue(this.logo.exists());
		
		//Id de banda não existente
		this.banda.setId(20);
		
		//Seta os atributos para adicionar o logo
		super.getBandaUploadArquivosAction().setBanda(banda);
		super.getBandaUploadArquivosAction().setLogo(this.logo);
		super.getBandaUploadArquivosAction().setLogoContentType("image");
		super.getBandaUploadArquivosAction().setLogoFileName("logo.jpg");
		
		//Exclui o logo
		assertEquals(Action.ERROR, super.getBandaUploadArquivosAction().excluirLogo());
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
		assertTrue(this.foto.exists());
		
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
	
	@Test
	public void erroExcluirFotoNaoExistente() throws ObjetoNaoEncontradoException{
		
		//Seta os atributos para adicionar o logo
		super.getBandaUploadArquivosAction().setBanda(banda);
		super.getBandaUploadArquivosAction().setFoto(this.foto);
		super.getBandaUploadArquivosAction().setFotoContentType("image");
		super.getBandaUploadArquivosAction().setFotoFileName("foto.jpg");
		
		this.banda.setFoto(new Arquivo("nome", "contentType", new File(""), "/"));
		super.getBandaFacade().salvar(this.banda);
		
		//Tenta excluir a foto
		assertEquals(Action.ERROR, super.getBandaUploadArquivosAction().excluirFoto());
		
	}
	
	@Test
	public void erroExcluirFotoBandaNaoExistente() throws ObjetoNaoEncontradoException{

		this.banda.setId(20);
		
		//Seta os atributos para adicionar o logo
		super.getBandaUploadArquivosAction().setBanda(banda);
		super.getBandaUploadArquivosAction().setFoto(this.foto);
		super.getBandaUploadArquivosAction().setFotoContentType("image");
		super.getBandaUploadArquivosAction().setFotoFileName("foto.jpg");
		
		//Tenta excluir a foto
		assertEquals(Action.ERROR, super.getBandaUploadArquivosAction().excluirFoto());
		
	}
}
