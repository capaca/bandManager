package com.bandManager.facade;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.domain.Arquivo;
import com.bandManager.domain.Banda;
import com.bandManager.domain.Lancamento;
import com.bandManager.domain.Pais;
import com.bandManager.exception.ArquivoInvalidoException;

public class TesteBandaFacade extends Teste {

	private Pais pais;
	private Lancamento lancamento1;
	private Lancamento lancamento2;
	private Banda banda;
	
	@Before
	public void preparacao(){
		this.pais = utilCriarPais("Brasil");
		this.banda = utilCriarBanda(this.pais);
		
		this.lancamento1 = utilCriarLancamento(this.banda);
		this.lancamento2 = utilCriarLancamento(this.banda);
				
		//Atualiza a referencia da banda
		this.banda = super.getBandaDAO().recuperar(this.banda.getId());
	}
	
	@Test
	public void salvar(){
		
		//Salva a banda
		Banda bandaSalva = super.getBandaFacade().salvar(this.banda);
		
		//Verifica se a banda foi salva corretamente
		this.utilVerificarAtributos(this.banda, bandaSalva);
	}
	
	@Test
	public void recuperar(){
		
		//Salva a banda
		super.getBandaFacade().salvar(this.banda);
		
		//Recupera a banda
		Banda bandaRecuperada = super.getBandaFacade().recuperar(banda.getId());
		
		//Verifica se a banda foi recuperada corretamente
		this.utilVerificarAtributos(this.banda, bandaRecuperada);
	}
	
	@Test
	public void adicionarLogo() throws ArquivoInvalidoException, IOException{
		//Cria o arquivo que vai ser transferido para o servidor
		File file = new File(PATH_SISTEMA+"/logo.jpg");
		assertTrue(file.exists());
		
		Arquivo logo = new Arquivo("logo.jpg","image",file,"imagens/banda/logo/");
		
		//Cria o arquivo de destino
		File file2 = new File(PATH_SISTEMA_TARGET+"/"+logo.getCaminhoArquivo());
		file2.delete();
		assertFalse(file2.exists());
		
		super.getBandaFacade().adicionarLogo(this.banda, logo,PATH_SISTEMA_TARGET);
		assertTrue(file2.exists());
		
		Banda bandaRecuperada = super.getBandaFacade().recuperar(this.banda.getId());
		utilVerificarArquivo(logo, bandaRecuperada.getLogo());
	}
	
	@Test
	public void excluirLogo() throws ArquivoInvalidoException, IOException{
		//Cria o arquivo que vai ser transferido para o servidor
		File file = new File(PATH_SISTEMA+"/logo.jpg");
		assertTrue(file.exists());
		
		Arquivo logo = new Arquivo("logo.jpg","image",file,"imagens/banda/logo/");
		
		//Cria o arquivo de destino
		File file2 = new File(PATH_SISTEMA_TARGET+"/"+logo.getCaminhoArquivo());
		file2.delete();
		assertFalse(file2.exists());
		
		super.getBandaFacade().adicionarLogo(this.banda, logo,PATH_SISTEMA_TARGET);
		assertTrue(file2.exists());
		
		//Exclui o logo
		super.getBandaFacade().excluirLogo(this.banda, PATH_SISTEMA_TARGET);
		
		//Verifica que o logo foi excluido com sucesso
		assertFalse(file2.exists());
		Banda bandaRecuperada = super.getBandaFacade().recuperar(this.banda.getId());
		assertNull(bandaRecuperada.getLogo());
	}
	
	@Test
	public void adicionarFoto() throws ArquivoInvalidoException, IOException{
		//Cria o arquivo que vai ser transferido para o servidor
		File file = new File(PATH_SISTEMA+"/foto.jpg");
		assertTrue(file.exists());
		
		Arquivo foto = new Arquivo("foto.jpg","image",file,"imagens/banda/foto/");
		
		//Cria o arquivo de destino
		File file2 = new File(PATH_SISTEMA_TARGET+"/"+foto.getCaminhoArquivo());
		file2.delete();
		assertFalse(file2.exists());
		
		super.getBandaFacade().adicionarFoto(this.banda, foto,PATH_SISTEMA_TARGET);
		assertTrue(file2.exists());
		
		Banda bandaRecuperada = super.getBandaFacade().recuperar(this.banda.getId());
		utilVerificarArquivo(foto, bandaRecuperada.getFoto());
	}
	
	@Test
	public void excluirFoto() throws ArquivoInvalidoException, IOException{
		//Cria o arquivo que vai ser transferido para o servidor
		File file = new File(PATH_SISTEMA+"/foto.jpg");
		assertTrue(file.exists());
		
		Arquivo foto = new Arquivo("foto.jpg","image",file,"imagens/banda/foto/");
		
		//Cria o arquivo de destino
		File file2 = new File(PATH_SISTEMA_TARGET+"/"+foto.getCaminhoArquivo());
		file2.delete();
		assertFalse(file2.exists());
		
		super.getBandaFacade().adicionarFoto(this.banda, foto,PATH_SISTEMA_TARGET);
		assertTrue(file2.exists());
		
		//Exclui o logo
		super.getBandaFacade().excluirFoto(this.banda, PATH_SISTEMA_TARGET);
		
		//Verifica que o logo foi excluido com sucesso
		assertFalse(file2.exists());
		Banda bandaRecuperada = super.getBandaFacade().recuperar(this.banda.getId());
		assertNull(bandaRecuperada.getFoto());
	}
}
