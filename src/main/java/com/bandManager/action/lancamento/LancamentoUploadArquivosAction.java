package com.bandManager.action.lancamento;

import java.io.File;
import java.io.IOException;

import com.bandManager.action.Action;
import com.bandManager.domain.Arquivo;
import com.bandManager.domain.Banda;
import com.bandManager.domain.Lancamento;
import com.bandManager.exception.ArquivoInvalidoException;
import com.bandManager.facade.ILancamentoFacade;
import com.bandManager.util.FileUtil;

@SuppressWarnings("serial")
public class LancamentoUploadArquivosAction extends Action{

	private static final String DIR_LANCAMENTO_CAPA = "imagens/banda/lancamento/capa/";
	private Lancamento lancamento;
	private Banda banda;
	private File capa;
	private ILancamentoFacade lancamentoFacade;
	private String capaFileName;
	private String capaContentType;
	
	public String adicionarCapa() {
		//Recupera o lancamento
		this.lancamento = this.lancamentoFacade.recuperar(this.lancamento.getId());
		
		//Pega o caminho completo do arquivo
		String nomeArquivo = this.montarNomeArquivo("Capa",capaFileName); 
		
		//Instancia o arquivo
		Arquivo capa = new Arquivo(nomeArquivo, capaContentType, this.capa, DIR_LANCAMENTO_CAPA);
		
		//Tenta adicionar o logo
		try {
			this.lancamento = this.lancamentoFacade.adicionarCapa(this.lancamento, capa, FileUtil.getCaminhoSitema());
			
		} catch (ArquivoInvalidoException e) {
			return ERROR;
			
		} catch (IOException e) {
			return ERROR;
		}
			
		return SUCCESS;
	}
	
	public String excluirCapa(){
		//Recupera o lancamento e a banda
		this.lancamento = this.lancamentoFacade.recuperar(this.lancamento.getId());
		this.banda = this.lancamento.getBanda();
		
		try {
			this.lancamentoFacade.excluirCapa(lancamento, FileUtil.getCaminhoSitema());
		} catch (IOException e) {
			return ERROR;
		} 

		return SUCCESS;
	}
	
	private String montarNomeArquivo(String tipoArquivo, String nomeArquivoAtual) {
		StringBuilder sb = new StringBuilder();
		sb.append(this.lancamento.getNome());
		sb.append(tipoArquivo);
		sb.append(this.lancamento.getId());
		sb.append(".");
		sb.append(FileUtil.getExtensaoArquivo(nomeArquivoAtual));
		return sb.toString().replace(" ", "");
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

	public String getCapaContentType() {
		return capaContentType;
	}

	public void setCapaContentType(String capaContentType) {
		this.capaContentType = capaContentType;
	}
}
