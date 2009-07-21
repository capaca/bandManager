package com.bandManager.dao.implementacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.bandManager.dao.IShowDAO;
import com.bandManager.domain.Show;
import com.bandManager.exception.ObjetoNaoEncontradoException;

@Transactional
public class ShowDAO implements IShowDAO {

	private EntityManager entityManager;
	
	public Show salvar(Show show) {
		if (show.getId()>0){
			this.entityManager.merge(show);
		}
		else {
			this.entityManager.persist(show);
		}
		
		return show;
	}

	public Show recuperar(int id) throws ObjetoNaoEncontradoException {
		Show show = this.entityManager.find(Show.class, id);
		
		if(show == null) {
			throw new ObjetoNaoEncontradoException();
		}
		
		return show;
	}

	public void excluir(int id) throws ObjetoNaoEncontradoException {
		Show show;
		
		try {
			show = this.recuperar(id);
			this.entityManager.remove(show);

		} catch (ObjetoNaoEncontradoException e) {
			throw e;
		}
	}


	/* Getters e Setters */

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
