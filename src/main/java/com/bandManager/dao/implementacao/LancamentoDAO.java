package com.bandManager.dao.implementacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.bandManager.dao.ILancamentoDAO;
import com.bandManager.domain.Lancamento;

@Transactional
public class LancamentoDAO implements ILancamentoDAO{

	private EntityManager entityManager;
	
	public Lancamento salvar(Lancamento lancamento) {
		
		if(lancamento.getId()>0){
			this.entityManager.merge(lancamento);
		}
		else{
			this.entityManager.persist(lancamento);
		}
		return lancamento;
	}
	
	public Lancamento recuperar(int id){
		return this.entityManager.find(Lancamento.class, id);
	}

	public void excluir(int id){
		Lancamento lancamento = this.recuperar(id);
		
		if(lancamento!=null){
			this.entityManager.remove(lancamento);
		}
	}
	
	/* Getters and Setters */
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
