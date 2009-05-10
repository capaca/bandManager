package com.bandManager.facade;

import com.bandManager.domain.Lancamento;

public interface ILancamentoFacade {

	public Lancamento salvar(Lancamento lancamento);
	
	public Lancamento recuperar(int id);
	
	public void excluir(int id);
}
