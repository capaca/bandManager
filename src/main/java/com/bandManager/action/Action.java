package com.bandManager.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class Action extends ActionSupport{

	protected Map<String, String> erros;
	protected List<String> mensagens;
	
	protected void adicionarErro(String campo, String erro){
		if(erros == null)
			erros = new HashMap<String, String>();
		
		erros.put(campo,erro);
	}
	
	protected void adicionarMensagem(String mensagem){
		if(mensagens == null)
			mensagens = new ArrayList<String>();
		
		mensagens.add(mensagem);
	}
	
	public void apresentarErrosEMensagens(){
		if(erros!=null){
			for(String campoErro : erros.keySet()){
				this.addFieldError(campoErro, erros.get(campoErro));
			}
		}
		
		if(mensagens!=null){
			for(String mensagem : mensagens){
				this.addActionMessage(mensagem);
			}
		}
	}

	public Map<String, String> getErros() {
		return erros;
	}

	public void setErros(Map<String, String> erros) {
		this.erros = erros;
	}

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}
}
