package com.bandManager.facade.implementacao;

import java.io.IOException;

import com.bandManager.dao.IMusicaDAO;
import com.bandManager.domain.Arquivo;
import com.bandManager.domain.Musica;
import com.bandManager.exception.ArquivoInvalidoException;
import com.bandManager.facade.IMusicaFacade;
import com.bandManager.util.FileUtil;

public class MusicaFacade implements IMusicaFacade {

	private IMusicaDAO musicaDAO;
	
	public Musica recuperar(int id) {
		return this.musicaDAO.recuperar(id);
	}

	public Musica salvar(Musica musica) {
		return this.musicaDAO.salvar(musica);
	}

	public void excluir(int id) {
		Musica musica = this.recuperar(id);
		
		if(musica.getArquivoAudio()!=null){
			try {
				this.excluirArquivoMusica(musica, FileUtil.getCaminhoSitema());
			} catch (IOException e) {
				System.err.println("Não foi possível excluir o arquivo de audio da musica");
				e.printStackTrace();
			}
		}
		this.musicaDAO.excluir(id);
	}

	public Musica adicionarArquivoMusica(Musica musica, Arquivo arquivoAudio, String pathSistema) throws ArquivoInvalidoException, IOException{
		FileUtil.validarAudio(arquivoAudio);
		
		//Recupera a musica com o arquio de audio atual
		musica = this.recuperar(musica.getId());
		
		//Exclui o logo existente
		excluirArquivoAudioDoServidor(musica, pathSistema);

		FileUtil.subirArquivo(arquivoAudio.getFile(), pathSistema+"/"+arquivoAudio.getCaminhoArquivo());
		
		//Seta o logo na banda e salva a banda
		musica.setArquivoAudio(arquivoAudio);
		this.salvar(musica);
		
		return musica;
	}
	
	public void excluirArquivoMusica(Musica musica, String pathSistema) throws IOException{

		excluirArquivoAudioDoServidor(musica, pathSistema);
		
		musica.setArquivoAudio(null);
		this.salvar(musica);
	}
	
	private void excluirArquivoAudioDoServidor(Musica musica, String pathSistema) throws IOException {
		if(musica.getArquivoAudio()!=null){
			FileUtil.excluirArquivo(pathSistema+"/"+musica.getArquivoAudio().getCaminhoArquivo());
		}
	}
	
	/*Getters and Setters*/
	
	public IMusicaDAO getMusicaDAO() {
		return musicaDAO;
	}

	public void setMusicaDAO(IMusicaDAO musicaDAO) {
		this.musicaDAO = musicaDAO;
	}
}
