package com.bandManager.dao;

import com.bandManager.domain.Banda;

public interface IBandaDAO {

	public Banda salvar(Banda banda);
	
	public Banda recuperar(int id);
}
