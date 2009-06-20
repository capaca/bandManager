package com.bandManager.dao.implementacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.bandManager.dao.IPaisDAO;
import com.bandManager.domain.Pais;

@Transactional
public class PaisDAO implements IPaisDAO {

	private EntityManager entityManager;
	
	public Pais salvar(Pais pais) {
		if(pais.getId()>0){
			this.entityManager.merge(pais);
		}
		else{
			this.entityManager.persist(pais);
		}
		return pais;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pais> recuperarTodos(){
		Query query = entityManager.createQuery("from Pais order by nome");
		return (List<Pais>) query.getResultList();
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
