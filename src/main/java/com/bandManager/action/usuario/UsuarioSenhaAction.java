package com.bandManager.action.usuario;

import com.bandManager.action.Action;
import com.bandManager.domain.Usuario;
import com.bandManager.exception.SenhaInvalidaException;
import com.bandManager.facade.IUsuarioFacade;

@SuppressWarnings("serial")
public class UsuarioSenhaAction extends Action {

	private IUsuarioFacade usuarioFacade;
	private String senhaAtual;
	private String novaSenha;
	private String confirmacaoNovaSenha;
	
	public String trocarSenha(){
		Usuario usuarioLogado = this.getUsuarioLogado();
		usuarioLogado.setPassword(senhaAtual);
		
		if(!novaSenha.equalsIgnoreCase(confirmacaoNovaSenha)){
			adicionarErro("confirmacaoNovaSenha", 
					"A nova senha e a confirmação não conferem.");
			return ERROR;
		}
		
		try{
			usuarioFacade.trocarSenha(usuarioLogado, novaSenha);
		}
		catch (SenhaInvalidaException e) {
			adicionarErro("senhaAtual", 
					"A senha atual não confere.");
			return ERROR;
		}
		
		return SUCCESS;
	}

	/*Getters and Setters*/
	public IUsuarioFacade getUsuarioFacade() {
		return usuarioFacade;
	}

	public void setUsuarioFacade(IUsuarioFacade usuarioFacade) {
		this.usuarioFacade = usuarioFacade;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmacaoNovaSenha() {
		return confirmacaoNovaSenha;
	}

	public void setConfirmacaoNovaSenha(String confirmacaoNovaSenha) {
		this.confirmacaoNovaSenha = confirmacaoNovaSenha;
	}
}
