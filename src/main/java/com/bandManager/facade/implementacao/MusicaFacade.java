package com.bandManager.facade.implementacao;

import com.bandManager.dao.IMusicaDAO;
import com.bandManager.domain.Musica;
import com.bandManager.facade.IMusicaFacade;

public class MusicaFacade implements IMusicaFacade {

	private IMusicaDAO musicaDAO;
	
	public Musica recuperar(int id) {
		return this.musicaDAO.recuperar(id);
	}

	public Musica salvar(Musica musica) {
		return this.musicaDAO.salvar(musica);
	}

	public void excluir(int id) {
		this.musicaDAO.excluir(id);
	}

	/*Getters and Setters*/
	
	public IMusicaDAO getMusicaDAO() {
		return musicaDAO;
	}

	public void setMusicaDAO(IMusicaDAO musicaDAO) {
		this.musicaDAO = musicaDAO;
	}
}
