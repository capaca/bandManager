package com.bandManager.dao;

import com.bandManager.domain.Musica;

public interface IMusicaDAO {

	public Musica salvar(Musica musica);

	public Musica recuperar(int id);
	
	public void excluir(int id);
}
