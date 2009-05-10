package com.bandManager.action.musica;

import java.io.File;
import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.bandManager.action.Action;
import com.bandManager.domain.Lancamento;
import com.bandManager.domain.Musica;
import com.bandManager.facade.IMusicaFacade;
import com.bandManager.util.FileUtil;

public class MusicaUploadArquivoAction extends Action {

	private static final String DIR_MUSICA_ARQUIVO = "musica/arquivo/";
	private File arquivo;
	private String arquivoFileName;
	private String arquivoContentType;
	private Musica musica;
	private Lancamento lancamento;
	
	private IMusicaFacade musicaFacade;
	
	public String uploadMusica(){
		
		if(this.arquivo == null){
			return ERROR;
		}
		
		this.musica = this.musicaFacade.recuperar(this.musica.getId());
		this.lancamento = this.musica.getLancamento();
		
		//Verifica se já existe um logo valido
		if(this.musica.getArquivo()!=null && this.musica.getArquivo().trim()!=""){
			
			//Monta o caminho do arquivo
			String caminhoArquivo = ServletActionContext.getServletContext().getRealPath(this.musica.getArquivo());
			
			try {
				//Exclui o arquivo
				FileUtil.excluirArquivo(caminhoArquivo);
				
			} catch (IOException e) {
				return ERROR;
			}
		}
		
		//Monta o caminho do arquivo
		StringBuilder sb = new StringBuilder();
		sb.append(this.lancamento.getBanda().getNome().replace(" ", ""));
		sb.append(" - ");
		sb.append(this.lancamento.getNome().replace(" ", ""));
		sb.append(" - ");
		sb.append(this.musica.getNome().replace(" ", ""));
		sb.append(this.musica.getId());
		sb.append(".");
		sb.append(FileUtil.getExtensaoArquivo(this.arquivoFileName));
		
		String caminhoArquivo = ServletActionContext.getServletContext().getRealPath(DIR_MUSICA_ARQUIVO+sb);
		
		//Faz o upload
		try {
			//Move o arquivo para o servidor
			FileUtil.subirArquivo(this.arquivo, caminhoArquivo);
			
			//Seta o caminho do arquivo e salva a banda
			this.musica.setArquivo(DIR_MUSICA_ARQUIVO+sb);
			this.musicaFacade.salvar(this.musica);
			
		} catch (IOException e) {
			return ERROR;
		}
		
		return SUCCESS;
	}

	/*
	 * Getters and Setters
	 */
	
	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}

	public String getArquivoFileName() {
		return arquivoFileName;
	}

	public void setArquivoFileName(String arquivoFileName) {
		this.arquivoFileName = arquivoFileName;
	}

	public String getArquivoContentType() {
		return arquivoContentType;
	}

	public void setArquivoContentType(String arquivoContentType) {
		this.arquivoContentType = arquivoContentType;
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public Musica getMusica() {
		return musica;
	}

	public void setMusica(Musica musica) {
		this.musica = musica;
	}

	public IMusicaFacade getMusicaFacade() {
		return musicaFacade;
	}

	public void setMusicaFacade(IMusicaFacade musicaFacade) {
		this.musicaFacade = musicaFacade;
	}
}
