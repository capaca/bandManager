package com.bandManager.dao.implementacao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.bandManager.dao.IBandaDAO;
import com.bandManager.domain.Banda;
import com.bandManager.exception.ObjetoNaoEncontradoException;

@Transactional
public class BandaDAO implements IBandaDAO{

	EntityManager entityManager;
	
	public Banda salvar(Banda banda) {
		if(banda.getId()>0){
			this.entityManager.merge(banda);
		}
		else{
			this.entityManager.persist(banda);
		}
		return banda;
	}
	
	public Banda recuperar(int id) throws ObjetoNaoEncontradoException {
		Banda banda = this.entityManager.find(Banda.class, id);
		
		if(banda == null) {
			throw new ObjetoNaoEncontradoException();
		} 

		return banda;
	}
	
	/* Getters and Setters */

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
