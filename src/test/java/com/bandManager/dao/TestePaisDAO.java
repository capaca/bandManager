package com.bandManager.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.domain.Pais;

public class TestePaisDAO extends Teste {

	@Test
	public void salvar(){
		//Instancia o pais
		Pais pais = new Pais(PAIS_NOME);
	
		//Salva o pais
		Pais paisSalvo = super.getPaisDAO().salvar(pais);
		
		//Verifica se o pais foi salvo corretamente
		assertNotNull(paisSalvo);
		assertEquals(PAIS_NOME, pais.getNome());
	}
	
	@Test
	public void recuperarTodos(){
		//Instancia e salva 3 paises
		Pais pais1 = new Pais("pais1");
		Pais pais2 = new Pais("pais2");
		Pais pais3 = new Pais("pais3");
		
		super.getPaisDAO().salvar(pais1);
		super.getPaisDAO().salvar(pais2);
		super.getPaisDAO().salvar(pais3);
		
		//Recupera todos os paises
		List<Pais> paises = super.getPaisDAO().recuperarTodos();
		
		//Verifica se os paises foram recuperados corretamente
		assertNotNull(paises);
		assertEquals(3, paises.size());
		assertEquals(pais1.getNome(), paises.get(0).getNome());
		assertEquals(pais2.getNome(), paises.get(1).getNome());
		assertEquals(pais3.getNome(), paises.get(2).getNome());
	}
}
