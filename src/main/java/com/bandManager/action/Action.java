package com.bandManager.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

import com.bandManager.domain.Usuario;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Action extends ActionSupport implements SessionAware{

	private Usuario usuarioLogado;
	private Map<String, Object> sessao;
	
	protected void adicionarErro(String campo, String erro){
		this.getErros().put(campo,erro);
		this.getMensagens().clear();
	}
	
	protected void adicionarMensagem(String mensagem){
		this.getMensagens().add(mensagem);
	}
	
	public void apresentarErrosEMensagens(){
		Map<String, String> erros = this.getErros();
		List<String> mensagens = this.getMensagens();
		
		for(String campoErro : erros.keySet()){
			this.addFieldError(campoErro, erros.get(campoErro));
		}
		
		for(String mensagem : mensagens){
			this.addActionMessage(mensagem);
		}
		
		erros.clear();
		mensagens.clear();
		
		this.sessao.put("erros", erros);
		this.sessao.put("mensagens", mensagens);
	}
	
	public Usuario getUsuarioLogado(){
		if(usuarioLogado==null){
			SecurityContext sc = SecurityContextHolder.getContext();
			this.usuarioLogado = (Usuario) sc.getAuthentication().getPrincipal();
		}
		
		return this.usuarioLogado;	
	}

	@SuppressWarnings("unchecked")
	private List<String> getMensagens(){
		
		List<String> mensagens = (List<String>) this.sessao.get("mensagens");
		
		if(mensagens == null){
			mensagens = new ArrayList<String>();
		}
		
		return mensagens;
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, String> getErros(){
		
		Map<String, String> erros = (Map<String, String>) this.sessao.get("erros");
		
		if(erros == null){
			erros = new HashMap<String, String>();
		}
		
		return erros;
	}

	public void setSession(Map<String, Object> session) {
		this.sessao = session;
	}
}
