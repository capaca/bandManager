package com.bandManager.facade;

import com.bandManager.domain.Musica;

public interface IMusicaFacade {

	public Musica salvar(Musica musica);
	
	public Musica recuperar(int id);
	
	public void excluir(int id);
}
