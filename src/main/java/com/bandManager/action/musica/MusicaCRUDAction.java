package com.bandManager.action.musica;

import com.bandManager.action.Action;
import com.bandManager.domain.Banda;
import com.bandManager.domain.Lancamento;
import com.bandManager.domain.Musica;
import com.bandManager.facade.IMusicaFacade;

@SuppressWarnings("serial")
public class MusicaCRUDAction extends Action {

	private IMusicaFacade musicaFacade;
	private Lancamento lancamento;
	private Musica musica;
	private Banda banda;
	
	public String salvar(){
		this.musicaFacade.salvar(this.musica);
		return SUCCESS;
	}
	
	public String recuperar(){
		if(this.musica!=null && this.musica.getId()>0){
			this.musica = this.musicaFacade.recuperar(musica.getId());
		}
		return SUCCESS;
	}
	
	public String excluir(){
		this.musicaFacade.excluir(musica.getId());
		return SUCCESS;
	}

	public IMusicaFacade getMusicaFacade() {
		return musicaFacade;
	}

	public void setMusicaFacade(IMusicaFacade musicaFacade) {
		this.musicaFacade = musicaFacade;
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

	public Banda getBanda() {
		return banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}
}
