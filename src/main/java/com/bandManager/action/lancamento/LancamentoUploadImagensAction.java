package com.bandManager.action.lancamento;

import java.io.File;
import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.bandManager.action.Action;
import com.bandManager.domain.Banda;
import com.bandManager.domain.Lancamento;
import com.bandManager.facade.ILancamentoFacade;
import com.bandManager.util.FileUtil;

public class LancamentoUploadImagensAction extends Action{

	private static final String DIR_LANCAMENO_CAPA = "imagens/lancamento/capa/";
	private Lancamento lancamento;
	private Banda banda;
	private File capa;
	private ILancamentoFacade lancamentoFacade;
	private String capaFileName;
	
	public String uploadCapa(){
		
		if(this.capa == null){
			return ERROR;
		}
		
		this.lancamento = this.lancamentoFacade.recuperar(this.lancamento.getId());
		this.banda = this.lancamento.getBanda();
		
		//Verifica se já existe um logo valido
		if(this.lancamento.getCapa()!=null && this.lancamento.getCapa().trim()!=""){
			
			//Monta o caminho do arquivo
			String caminhoArquivo = ServletActionContext.getServletContext().getRealPath(this.lancamento.getCapa());
			
			try {
				//Exclui o arquivo
				FileUtil.excluirArquivo(caminhoArquivo);
				
			} catch (IOException e) {
				return ERROR;
			}
		}
		
		//Monta o caminho do arquivo
		StringBuilder sb = new StringBuilder();
		sb.append(this.lancamento.getNome().replace(" ", ""));
		sb.append("Capa");
		sb.append(this.lancamento.getId());
		sb.append(".");
		sb.append(FileUtil.getExtensaoArquivo(this.capaFileName));
		
		String caminhoArquivo = ServletActionContext.getServletContext().getRealPath(DIR_LANCAMENO_CAPA+sb);
		
		//Faz o upload
		try {
			//Move o arquivo para o servidor
			FileUtil.subirArquivo(this.capa, caminhoArquivo);
			
			//Seta o caminho do arquivo e salva a banda
			this.lancamento.setCapa(DIR_LANCAMENO_CAPA+sb);
			this.lancamentoFacade.salvar(lancamento);
			
		} catch (IOException e) {
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String excluirCapa(){
		
		//Recupera o lancamento
		this.lancamento = this.lancamentoFacade.recuperar(this.lancamento.getId());
		this.banda = this.lancamento.getBanda();
		
		//Verifica se a capa é valida
		if(this.lancamento.getCapa()!=null && this.lancamento.getCapa().trim()!=""){
			
			//Monta o caminho do arquivo
			String caminhoArquivo = ServletActionContext.getServletContext().getRealPath(this.lancamento.getCapa());
			
			try {
				//Exclui o arquivo
				FileUtil.excluirArquivo(caminhoArquivo);
				
				//Seta o logo como vazio e salva a banda
				this.lancamento.setCapa("");
				this.lancamentoFacade.salvar(lancamento);
				
			} catch (IOException e) {
				return ERROR;
			}
		}
		
		return SUCCESS;
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public File getCapa() {
		return capa;
	}

	public void setCapa(File capa) {
		this.capa = capa;
	}

	public ILancamentoFacade getLancamentoFacade() {
		return lancamentoFacade;
	}

	public void setLancamentoFacade(ILancamentoFacade lancamentoFacade) {
		this.lancamentoFacade = lancamentoFacade;
	}

	public String getCapaFileName() {
		return capaFileName;
	}

	public void setCapaFileName(String capaFileName) {
		this.capaFileName = capaFileName;
	}

	public Banda getBanda() {
		return banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}
}
