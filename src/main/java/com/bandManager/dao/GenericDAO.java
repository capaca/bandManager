package com.bandManager.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class GenericDAO {

	private EntityManager entityManager;
	
	public Object salvar(Object objeto) {
		this.entityManager.persist(objeto);
		return objeto;
	}
	
	/* Getters and Setters */

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
