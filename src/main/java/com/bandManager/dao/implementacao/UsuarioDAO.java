package com.bandManager.dao.implementacao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.bandManager.dao.IUsuarioDAO;
import com.bandManager.domain.Usuario;

@Transactional
public class UsuarioDAO implements IUsuarioDAO {

	EntityManager entityManager;
	
	public Usuario salvar(Usuario usuario) {
		if(usuario.getId()>0){
			entityManager.merge(usuario);
		}
		else{
			entityManager.persist(usuario);
		}
		
		return usuario;
	}
	 
	public Usuario recuperar(int id){
		return entityManager.find(Usuario.class, id);
	}
	
	public Usuario recuperar(String username){
		String sql = "from Usuario u where u.username = :username";
		Query query = entityManager.createQuery(sql);
		query.setParameter("username", username);
		
		try{
			return (Usuario) query.getSingleResult();
		}
		catch(NoResultException exception){
			throw exception;
		}
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}

}
