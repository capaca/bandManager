package com.bandManager.action.banda;

import java.io.File;
import java.io.IOException;

import com.bandManager.action.Action;
import com.bandManager.domain.Arquivo;
import com.bandManager.domain.Banda;
import com.bandManager.exception.ArquivoInvalidoException;
import com.bandManager.facade.IBandaFacade;
import com.bandManager.util.FileUtil;

@SuppressWarnings("serial")
public class BandaUploadArquivosAction extends Action {

	private Banda banda;
	private File logo;
	private File foto;
	private String logoContentType;
    private String logoFileName;
    private String fotoContentType;
    private String fotoFileName;
	private IBandaFacade bandaFacade;
	
	public static final String DIR_BANDA_LOGO = "imagens/banda/logo/";
	public static final String DIR_BANDA_FOTO = "imagens/banda/foto/";
	
	public String adicionarLogo(){
		//Recupera a banda
		this.banda = this.bandaFacade.recuperar(banda.getId());
		
		//Pega o caminho completo do arquivo
		String nomeArquivo = this.montarNomeArquivo("Logo",logoFileName); 
		
		//Instancia o arquivo
		Arquivo logo = new Arquivo(nomeArquivo,logoContentType,this.logo,DIR_BANDA_LOGO);
		
		//Tenta adicionar o logo
		try {
			this.bandaFacade.adicionarLogo(banda,logo,FileUtil.getCaminhoSitema());
			
		} catch (ArquivoInvalidoException e) {
			return ERROR;
			
		} catch (IOException e) {
			return ERROR;
		}
			
		return SUCCESS;
	}


	public String excluirLogo(){
		//Recupera a banda 
		this.banda = this.bandaFacade.recuperar(this.banda.getId());
		
		try {
			this.bandaFacade.excluirLogo(banda, FileUtil.getCaminhoSitema());
		} catch (IOException e) {
			return ERROR;
		} catch (ArquivoInvalidoException e) {
			return ERROR;
		}

		return SUCCESS;
	}

	public String adicionarFoto(){
		//Recupera a banda
		this.banda = this.bandaFacade.recuperar(banda.getId());
		
		//Pega o caminho completo do arquivo
		String nomeArquivo = this.montarNomeArquivo("Foto",this.fotoFileName); 
		
		//Instancia o arquivo
		Arquivo foto = new Arquivo(nomeArquivo,fotoContentType,this.foto,DIR_BANDA_FOTO);
		
		//Tenta adicionar o logo
		try {
			this.bandaFacade.adicionarFoto(banda,foto,FileUtil.getCaminhoSitema());
			
		} catch (ArquivoInvalidoException e) {
			return ERROR;
			
		} catch (IOException e) {
			return ERROR;
		}
			
		return SUCCESS;
	}
	
	public String excluirFoto(){
		
		//Recupera a banda 
		this.banda = this.bandaFacade.recuperar(this.banda.getId());
		
		try {
			this.bandaFacade.excluirFoto(banda, FileUtil.getCaminhoSitema());
		} catch (IOException e) {
			return ERROR;
		} catch (ArquivoInvalidoException e) {
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	private String montarNomeArquivo(String tipoArquivo, String nomeArquivoAtual) {
		StringBuilder sb = new StringBuilder();
		sb.append(this.banda.getNome());
		sb.append(tipoArquivo);
		sb.append(this.banda.getId());
		sb.append(".");
		sb.append(FileUtil.getExtensaoArquivo(nomeArquivoAtual));
		return sb.toString().replace(" ", "");
	}

	public File getLogo() {
		return logo;
	}

	public void setLogo(File logo) {
		this.logo = logo;
	}

	public File getFoto() {
		return foto;
	}

	public void setFoto(File foto) {
		this.foto = foto;
	}

	public Banda getBanda() {
		return banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}
	
	public String getLogoContentType() {
		return logoContentType;
	}

	public void setLogoContentType(String logoContentType) {
		this.logoContentType = logoContentType;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}

	public String getFotoContentType() {
		return fotoContentType;
	}

	public void setFotoContentType(String fotoContentType) {
		this.fotoContentType = fotoContentType;
	}

	public String getFotoFileName() {
		return fotoFileName;
	}

	public void setFotoFileName(String fotoFileName) {
		this.fotoFileName = fotoFileName;
	}

	public IBandaFacade getBandaFacade() {
		return bandaFacade;
	}

	public void setBandaFacade(IBandaFacade bandaFacade) {
		this.bandaFacade = bandaFacade;
	}
}
