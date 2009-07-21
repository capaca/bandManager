package com.bandManager.action.show;

import java.util.List;

import com.bandManager.action.Action;
import com.bandManager.domain.Banda;
import com.bandManager.domain.Pais;
import com.bandManager.domain.Show;
import com.bandManager.exception.ObjetoNaoEncontradoException;
import com.bandManager.facade.IPaisFacade;
import com.bandManager.facade.IShowFacade;

@SuppressWarnings("serial")
public class ShowCRUDAction extends Action {

	private IShowFacade showFacade;
	private IPaisFacade paisFacade;
	private List<Pais> paises;
	private Show show;
	private Banda banda;
	
	public String criar(){
		this.paises = this.paisFacade.recuperarTodos();
		return SUCCESS;
	}
	
	public String salvar(){
		this.showFacade.salvar(this.show);
		return SUCCESS;
	}
	
	public String recuperar(){
		this.paises = this.paisFacade.recuperarTodos();
		
		if(this.show!=null){
			try {
				this.show = this.showFacade.recuperar(this.show.getId());
				this.banda = this.show.getBanda();
			} catch (ObjetoNaoEncontradoException e) {
				return ERROR;
			}
		}
		else {
			return ERROR;
		}
		
		return SUCCESS;
	}

	public String excluir(){
		try{
			this.showFacade.excluir(this.show.getId());
		}
		catch(ObjetoNaoEncontradoException e){
			return ERROR;
		}

		return SUCCESS;
	}
	
	/* Getters e Setters */

	public IShowFacade getShowFacade() {
		return showFacade;
	}

	public void setShowFacade(IShowFacade showFacade) {
		this.showFacade = showFacade;
	}

	public IPaisFacade getPaisFacade() {
		return paisFacade;
	}

	public void setPaisFacade(IPaisFacade paisFacade) {
		this.paisFacade = paisFacade;
	}

	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public Banda getBanda() {
		return banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}
}
