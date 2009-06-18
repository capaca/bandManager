package com.bandManager.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

import com.bandManager.domain.Usuario;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Action extends ActionSupport {

	protected Map<String, String> erros;
	protected List<String> mensagens;
	private Usuario usuarioLogado;
	
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
	
	public Usuario getUsuarioLogado(){
		if(usuarioLogado==null){
			SecurityContext sc = SecurityContextHolder.getContext();
			this.usuarioLogado = (Usuario) sc.getAuthentication().getPrincipal();
		}
		
		return this.usuarioLogado;	
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
