package com.bandManager.facade;

import java.util.List;

import com.bandManager.domain.Pais;

public interface IPaisFacade {

	public Pais salvar(Pais pais);
	
	public List<Pais> recuperarTodos();
}
