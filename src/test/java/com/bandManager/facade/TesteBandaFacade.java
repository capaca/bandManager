package com.bandManager.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.domain.Arquivo;
import com.bandManager.domain.Banda;
import com.bandManager.exception.ArquivoInvalidoException;
import com.bandManager.exception.ObjetoNaoEncontradoException;

public class TesteBandaFacade extends Teste {

	@Test
	public void salvar(){
		
		Banda banda = utilCriarBanda();
		
		//Salva a banda
		Banda bandaSalva = super.getBandaFacade().salvar(banda);
		
		//Verifica se a banda foi salva corretamente
		this.utilVerificarAtributos(banda, bandaSalva);
	}
	
	@Test
	public void recuperar() throws ObjetoNaoEncontradoException{
		
		Banda banda = utilCriarBanda();
		
		//Salva a banda
		super.getBandaFacade().salvar(banda);
		
		//Recupera a banda
		Banda bandaRecuperada = super.getBandaFacade().recuperar(banda.getId());
		
		//Verifica se a banda foi recuperada corretamente
		this.utilVerificarAtributos(banda, bandaRecuperada);
	}
	
	@Test
	public void adicionarLogo() throws ArquivoInvalidoException, IOException, ObjetoNaoEncontradoException{
		Banda banda = utilCriarBanda();
		
		//Cria o arquivo que vai ser transferido para o servidor
		File file = new File(PATH_SISTEMA+"/logo.jpg");
		assertTrue(file.exists());
		
		Arquivo logo = new Arquivo("logo.jpg","image",file,"imagens/banda/logo/");
		
		//Cria o arquivo de destino
		File file2 = new File(PATH_SISTEMA_TARGET+"/"+logo.getCaminhoArquivo());
		file2.delete();
		assertFalse(file2.exists());
		
		super.getBandaFacade().adicionarLogo(banda, logo,PATH_SISTEMA_TARGET);
		assertTrue(file2.exists());
		
		Banda bandaRecuperada = super.getBandaFacade().recuperar(banda.getId());
		utilVerificarArquivo(logo, bandaRecuperada.getLogo());
	}
	
	@Test
	public void excluirLogo() throws ArquivoInvalidoException, IOException, ObjetoNaoEncontradoException{
		Banda banda = utilCriarBanda();
		
		//Cria o arquivo que vai ser transferido para o servidor
		File file = new File(PATH_SISTEMA+"/logo.jpg");
		assertTrue(file.exists());
		
		Arquivo logo = new Arquivo("logo.jpg","image",file,"imagens/banda/logo/");
		
		//Cria o arquivo de destino
		File file2 = new File(PATH_SISTEMA_TARGET+"/"+logo.getCaminhoArquivo());
		file2.delete();
		assertFalse(file2.exists());
		
		super.getBandaFacade().adicionarLogo(banda, logo,PATH_SISTEMA_TARGET);
		assertTrue(file2.exists());
		
		//Exclui o logo
		super.getBandaFacade().excluirLogo(banda, PATH_SISTEMA_TARGET);
		
		//Verifica que o logo foi excluido com sucesso
		assertFalse(file2.exists());
		Banda bandaRecuperada = super.getBandaFacade().recuperar(banda.getId());
		assertNull(bandaRecuperada.getLogo());
	}
	
	@Test
	public void adicionarFoto() throws ArquivoInvalidoException, IOException, ObjetoNaoEncontradoException{
		Banda banda = utilCriarBanda();
		
		//Cria o arquivo que vai ser transferido para o servidor
		File file = new File(PATH_SISTEMA+"/foto.jpg");
		assertTrue(file.exists());
		
		Arquivo foto = new Arquivo("foto.jpg","image",file,"imagens/banda/foto/");
		
		//Cria o arquivo de destino
		File file2 = new File(PATH_SISTEMA_TARGET+"/"+foto.getCaminhoArquivo());
		file2.delete();
		assertFalse(file2.exists());
		
		super.getBandaFacade().adicionarFoto(banda, foto,PATH_SISTEMA_TARGET);
		assertTrue(file2.exists());
		
		Banda bandaRecuperada = super.getBandaFacade().recuperar(banda.getId());
		utilVerificarArquivo(foto, bandaRecuperada.getFoto());
	}
	
	@Test
	public void excluirFoto() throws ArquivoInvalidoException, IOException, ObjetoNaoEncontradoException{
		Banda banda = utilCriarBanda();
		
		//Cria o arquivo que vai ser transferido para o servidor
		File file = new File(PATH_SISTEMA+"/foto.jpg");
		assertTrue(file.exists());
		
		Arquivo foto = new Arquivo("foto.jpg","image",file,"imagens/banda/foto/");
		
		//Cria o arquivo de destino
		File file2 = new File(PATH_SISTEMA_TARGET+"/"+foto.getCaminhoArquivo());
		file2.delete();
		assertFalse(file2.exists());
		
		super.getBandaFacade().adicionarFoto(banda, foto,PATH_SISTEMA_TARGET);
		assertTrue(file2.exists());
		
		//Exclui o logo
		super.getBandaFacade().excluirFoto(banda, PATH_SISTEMA_TARGET);
		
		//Verifica que o logo foi excluido com sucesso
		assertFalse(file2.exists());
		Banda bandaRecuperada = super.getBandaFacade().recuperar(banda.getId());
		assertNull(bandaRecuperada.getFoto());
	}
	
	@Test
	public void recuperarTodas(){
		Banda destruction = utilCriarBanda("Destruction");
		Banda sodom =utilCriarBanda("Sodom");
		Banda kreator =utilCriarBanda("Kreator");
		
		List<Banda> bandas = new ArrayList<Banda>();
		bandas.add(destruction);
		bandas.add(sodom);
		bandas.add(kreator);
		
		List<Banda> bandasRecuperadas = super.getBandaFacade().recuperarTodas();
		
		assertEquals(3, bandas.size());
		utilVerificarAtributos(bandas, bandasRecuperadas);
	}
}
