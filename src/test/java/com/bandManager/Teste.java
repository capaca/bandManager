package com.bandManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.springframework.transaction.annotation.Transactional;

public class Teste extends TesteUtil{

	EntityManager entityManager;
	
	@Before
	public void zerarTabelas(){
		this.entityManager = super.getEntityManager();
		this.entityManager.getTransaction().begin();
		
		Query query = this.entityManager.createNativeQuery("" +
				"truncate table banda, lancamento, musica, show, arquivo, pais, perfil, usuario cascade");
		
		query.executeUpdate();
		this.entityManager.getTransaction().commit();
	}
	
	/*@Before
	public void inicializarContexto(){
		ActionContext.setContext(super.getActionContext());
	}*/
}
