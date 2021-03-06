package com.bandManager.action.banda;

import java.util.List;

import com.bandManager.action.Action;
import com.bandManager.domain.Banda;
import com.bandManager.domain.Pais;
import com.bandManager.exception.ObjetoNaoEncontradoException;
import com.bandManager.facade.IBandaFacade;
import com.bandManager.facade.IPaisFacade;

@SuppressWarnings("serial")
public class BandaCRUDAction extends Action {

	/* Atributos */
	private IBandaFacade bandaFacade;
	private IPaisFacade paisFacade;
	private Banda banda;
	private List<Banda> bandas;
	private List<Pais> paises;
	
	/* Métodos da Action */
	
	public String criar(){
		this.paises = this.paisFacade.recuperarTodos();
		return SUCCESS;
	}
	
	public String salvar(){
		this.bandaFacade.salvar(this.banda);
		this.adicionarMensagem("Banda salva com sucesso!");
		return SUCCESS;
	}
	
	public String recuperar(){
		this.paises = this.paisFacade.recuperarTodos();
		
		if(this.banda!=null){
			try {
				this.banda = this.bandaFacade.recuperar(this.banda.getId());
			} catch (ObjetoNaoEncontradoException e) {
				this.adicionarErro("banda", "A banda não existe");
				return ERROR;
			}
		}
		else {
			return ERROR;
		}
		
		this.apresentarErrosEMensagens();
		return SUCCESS;
	}
	
	public String recuperarTodas(){
		this.bandas = this.bandaFacade.recuperarTodas();
		apresentarErrosEMensagens();
		return SUCCESS;
	}

	/* Getters and Setters */
	
	public Banda getBanda() {
		return banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}

	public void setBandaFacade(IBandaFacade bandaFacade) {
		this.bandaFacade = bandaFacade;
	}

	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaisFacade(IPaisFacade paisFacade) {
		this.paisFacade = paisFacade;
	}

	public List<Banda> getBandas() {
		return bandas;
	}
}
