package com.bandManager.action;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bandManager.Teste;

public class TesteLoginAction extends Teste {

	@Test
	public void execute(){
		assertEquals(Action.SUCCESS, super.getLoginAction().execute());
	}
}
