package com.bandManager.dao;

import java.util.List;

import com.bandManager.domain.Pais;

public interface IPaisDAO {

	public Pais salvar(Pais pais);
	
	public List<Pais> recuperarTodos(); 
}
