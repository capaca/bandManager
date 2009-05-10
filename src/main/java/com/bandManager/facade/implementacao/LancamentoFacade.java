package com.bandManager.facade.implementacao;

import com.bandManager.dao.ILancamentoDAO;
import com.bandManager.domain.Lancamento;
import com.bandManager.facade.ILancamentoFacade;

public class LancamentoFacade implements ILancamentoFacade {
	
	private ILancamentoDAO lancamentoDAO;
	
	public Lancamento salvar(Lancamento lancamento) {
		return this.lancamentoDAO.salvar(lancamento);
	}

	public Lancamento recuperar(int id){
		return this.lancamentoDAO.recuperar(id);
	}
	
	public void excluir(int id){
		this.lancamentoDAO.excluir(id);
	}
	
	/*Getters and Setters*/
	
	public ILancamentoDAO getLancamentoDAO() {
		return lancamentoDAO;
	}

	public void setLancamentoDAO(ILancamentoDAO lancamentoDAO) {
		this.lancamentoDAO = lancamentoDAO;
	}
}
