package com.bandManager.facade.implementacao;

import java.util.List;

import com.bandManager.dao.IPaisDAO;
import com.bandManager.domain.Pais;
import com.bandManager.facade.IPaisFacade;

public class PaisFacade implements IPaisFacade{

	private IPaisDAO paisDAO;
	
	public Pais salvar(Pais pais) {
		return this.paisDAO.salvar(pais);
	}

	public List<Pais> recuperarTodos(){
		return this.paisDAO.recuperarTodos();
	}
	
	/*Getters and Setters*/

	public void setPaisDAO(IPaisDAO paisDAO) {
		this.paisDAO = paisDAO;
	}

}
