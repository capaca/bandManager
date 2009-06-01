package com.bandManager.dao;

import com.bandManager.domain.Show;
import com.bandManager.exception.ObjetoNaoEncontradoException;

public interface IShowDAO {

	public Show salvar(Show show);
	
	public Show recuperar(int id) throws ObjetoNaoEncontradoException;
	
	public void excluir(int id);
}
