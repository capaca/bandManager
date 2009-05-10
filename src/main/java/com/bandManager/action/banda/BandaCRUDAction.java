package com.bandManager.action.banda;

import java.util.List;

import com.bandManager.action.Action;
import com.bandManager.domain.Banda;
import com.bandManager.domain.Pais;
import com.bandManager.facade.IBandaFacade;
import com.bandManager.facade.IPaisFacade;

public class BandaCRUDAction extends Action {

	/* Atributos */
	private IBandaFacade bandaFacade;
	private IPaisFacade paisFacade;
	private Banda banda;
	private List<Pais> paises;
	
	/* Métodos da Action */
	
	public String salvar(){
		this.bandaFacade.salvar(this.banda);
		
		this.adicionarMensagem("Banda salva com sucesso!");
		return SUCCESS;
	}
	
	public String recuperar(){
		this.paises = this.paisFacade.recuperarTodos();
		
		if(this.banda!=null && this.banda.getId()>0){
			this.banda = this.bandaFacade.recuperar(this.banda.getId());
		}
		
		return SUCCESS;
	}

	/* Getters and Setters */
	
	public Banda getBanda() {
		return banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}

	public IBandaFacade getBandaFacade() {
		return bandaFacade;
	}

	public void setBandaFacade(IBandaFacade bandaFacade) {
		this.bandaFacade = bandaFacade;
	}

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
}
