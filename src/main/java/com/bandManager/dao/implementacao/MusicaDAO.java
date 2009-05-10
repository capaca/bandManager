package com.bandManager.dao.implementacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.bandManager.dao.IMusicaDAO;
import com.bandManager.domain.Musica;

@Transactional
public class MusicaDAO implements IMusicaDAO {

	private EntityManager entityManager;
	
	public Musica salvar(Musica musica) {
		if(musica.getId()>0){
			this.entityManager.merge(musica);
		}
		else{
			this.entityManager.persist(musica);
		}
		return musica;
	}
	
	public Musica recuperar(int id) {
		return this.entityManager.find(Musica.class, id);
	}
	
	public void excluir(int id){
		Musica musica = recuperar(id);
		
		if(musica!=null){
			this.entityManager.remove(musica);
		}
	}
	
	/* Getters and Setters */

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	

	
}
