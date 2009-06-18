package com.bandManager.action.musica;

import java.io.File;
import java.io.IOException;

import com.bandManager.action.Action;
import com.bandManager.domain.Arquivo;
import com.bandManager.domain.Banda;
import com.bandManager.domain.Lancamento;
import com.bandManager.domain.Musica;
import com.bandManager.exception.ArquivoInvalidoException;
import com.bandManager.facade.IMusicaFacade;
import com.bandManager.util.FileUtil;

@SuppressWarnings("serial")
public class MusicaUploadArquivosAction extends Action {

	private static final String DIR_MUSICA_ARQUIVO = "audio/";
	private File arquivo;
	private String arquivoFileName;
	private String arquivoContentType;
	private Musica musica;
	private Lancamento lancamento;
	private Banda banda;
	
	private IMusicaFacade musicaFacade;
	
	public String adicionarArquivoAudio() {
		//Recupera a musica
		this.musica = this.musicaFacade.recuperar(this.musica.getId());
		this.lancamento = this.musica.getLancamento();
		
		//Pega o caminho completo do arquivo
		String nomeArquivo = this.montarNomeArquivo(arquivoFileName); 
		
		//Instancia o arquivo
		Arquivo arquivoAudio = new Arquivo(nomeArquivo, arquivoContentType, this.arquivo, DIR_MUSICA_ARQUIVO);
		
		//Tenta adicionar o logo
		try {
			this.musica = this.musicaFacade.adicionarArquivoMusica(this.musica, arquivoAudio, FileUtil.getCaminhoSitema());
			
		} catch (ArquivoInvalidoException e) {
			return ERROR;
			
		} catch (IOException e) {
			return ERROR;
		}
			
		return SUCCESS;
	}
	
	public String excluirArquivoAudio(){
		//Recupera o lancamento e a banda
		this.musica = this.musicaFacade.recuperar(this.musica.getId());
		this.lancamento = this.musica.getLancamento();
		
		try {
			this.musicaFacade.excluirArquivoMusica(musica, FileUtil.getCaminhoSitema());
		} catch (IOException e) {
			return ERROR;
		} 

		return SUCCESS;
	}

	private String montarNomeArquivo(String arquivoFileName) {
		StringBuilder sb = new StringBuilder();
		sb.append(musica.getLancamento().getBanda().getNome());
		sb.append("-");
		sb.append(musica.getLancamento().getNome());
		sb.append("-");
		sb.append(musica.getNome());
		sb.append(musica.getId());
		sb.append(".");
		sb.append(FileUtil.getExtensaoArquivo(arquivoFileName));
		return sb.toString();
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

	public Banda getBanda() {
		return banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}
}
