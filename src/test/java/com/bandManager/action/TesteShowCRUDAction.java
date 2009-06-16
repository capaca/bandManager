package com.bandManager.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.domain.Show;

public class TesteShowCRUDAction extends Teste {

	@Test
	public void salvar(){
		
		Show show = utilCriarShow();
		
		super.getShowCRUDAction().setShow(show);
		assertEquals(Action.SUCCESS, super.getShowCRUDAction().salvar());
		
		utilVerificarShow(show, super.getShowCRUDAction().getShow());
	}
	
	@Test
	public void recuperar(){
		
		Show show = utilCriarShow();
		
		super.getShowCRUDAction().setShow(show);
		assertEquals(Action.SUCCESS, super.getShowCRUDAction().recuperar());
		
		utilVerificarShow(show, super.getShowCRUDAction().getShow());
	}
	
	@Test
	public void excluir(){
		
		Show show = utilCriarShow();
		
		super.getShowCRUDAction().setShow(show);
		assertEquals(Action.SUCCESS, super.getShowCRUDAction().excluir());
		
		assertEquals(Action.ERROR, super.getShowCRUDAction().recuperar());
	}
	
	@Test
	public void criar(){
		utilCriarPais("Brasil");
		
		assertEquals(Action.SUCCESS, super.getShowCRUDAction().criar());
		assertTrue(super.getShowCRUDAction().getPaises().size()>0);
	}
}
