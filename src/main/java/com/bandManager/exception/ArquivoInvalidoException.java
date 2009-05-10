package com.bandManager.exception;

@SuppressWarnings("serial")
public class ArquivoInvalidoException extends Exception {

	public ArquivoInvalidoException(){
		super();
	}
	
	public ArquivoInvalidoException(String mensagem){
		super(mensagem);
	}
}
