package com.bandManager.action.usuario;

import java.util.List;

import com.bandManager.action.Action;
import com.bandManager.domain.Pais;
import com.bandManager.domain.Perfil;
import com.bandManager.domain.Usuario;
import com.bandManager.facade.IPaisFacade;
import com.bandManager.facade.IUsuarioFacade;

@SuppressWarnings("serial")
public class UsuarioCRUDAction extends Action {

	private List<Pais> paises;
	private IPaisFacade paisFacade;
	private IUsuarioFacade usuarioFacade;
	private Usuario usuario;
	
	public String recuperar(){
		this.paises = this.paisFacade.recuperarTodos();
		this.usuario = this.usuarioFacade.recuperar(this.getUsuarioLogado().getUsername());
		
		return SUCCESS;
	}
	
	public String salvar(){
		Perfil perfil = this.usuario.getPerfil();
		this.usuario = this.usuarioFacade.recuperar(this.getUsuarioLogado().getUsername());
		perfil.setId(usuario.getPerfil().getId());
		this.usuario.setPerfil(perfil);
		
		this.usuarioFacade.salvar(this.usuario);
		
		return SUCCESS;
	}

	/* Getters e Setters */
	
	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	public IPaisFacade getPaisFacade() {
		return paisFacade;
	}

	public void setPaisFacade(IPaisFacade paisFacade) {
		this.paisFacade = paisFacade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public IUsuarioFacade getUsuarioFacade() {
		return usuarioFacade;
	}

	public void setUsuarioFacade(IUsuarioFacade usuarioFacade) {
		this.usuarioFacade = usuarioFacade;
	}
}
