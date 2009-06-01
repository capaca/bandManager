package com.bandManager.facade.implementacao;

import com.bandManager.dao.IShowDAO;
import com.bandManager.domain.Show;
import com.bandManager.exception.ObjetoNaoEncontradoException;
import com.bandManager.facade.IShowFacade;

public class ShowFacade implements IShowFacade {

	private IShowDAO showDAO;
	
	public Show salvar(Show show) {
		return this.showDAO.salvar(show);
	}
	
	public Show recuperar(int id) throws ObjetoNaoEncontradoException {
		return this.showDAO.recuperar(id);
	}

	public void excluir(int id) {
		this.showDAO.excluir(id);
	}

	/* Getters e Setters */
	
	public IShowDAO getShowDAO() {
		return showDAO;
	}

	public void setShowDAO(IShowDAO showDAO) {
		this.showDAO = showDAO;
	}
}
