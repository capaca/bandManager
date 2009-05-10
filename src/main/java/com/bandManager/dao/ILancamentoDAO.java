package com.bandManager.dao;

import com.bandManager.domain.Lancamento;

public interface ILancamentoDAO {

	public Lancamento salvar(Lancamento lancamento);
	
	public Lancamento recuperar(int id);
	
	public void excluir(int id);
}
