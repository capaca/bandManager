package com.bandManager.action.lancamento;

import java.io.IOException;

import com.bandManager.action.Action;
import com.bandManager.domain.Banda;
import com.bandManager.domain.Lancamento;
import com.bandManager.facade.ILancamentoFacade;

@SuppressWarnings("serial")
public class LancamentoCRUDAction extends Action{

	private ILancamentoFacade lancamentoFacade;
	private Lancamento lancamento;
	private Banda banda;
	
	public String salvar(){
		this.lancamentoFacade.salvar(this.lancamento);
		return SUCCESS;
	}

	public String recuperar(){
		if(this.lancamento!=null && this.lancamento.getId()>0){
			this.lancamento = this.lancamentoFacade.recuperar(this.lancamento.getId());
		}
		return SUCCESS;
	}
	
	public String excluir(){
		try{
			this .lancamentoFacade.excluir(lancamento.getId());
		}
		catch (IOException e) {
			return ERROR;
		}
		return SUCCESS;
	}

	public ILancamentoFacade getLancamentoFacade() {
		return lancamentoFacade;
	}

	public void setLancamentoFacade(ILancamentoFacade lancamentoFacade) {
		this.lancamentoFacade = lancamentoFacade;
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public Banda getBanda() {
		return banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}
}
