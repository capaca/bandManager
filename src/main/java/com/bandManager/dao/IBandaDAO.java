package com.bandManager.dao;

import java.util.List;

import com.bandManager.domain.Banda;
import com.bandManager.exception.ObjetoNaoEncontradoException;

public interface IBandaDAO {

	public Banda salvar(Banda banda);
	
	public Banda recuperar(int id) throws ObjetoNaoEncontradoException;
	
	public List<Banda> recuperarTodas();
}
