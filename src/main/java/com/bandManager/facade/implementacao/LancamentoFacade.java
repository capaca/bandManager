package com.bandManager.facade.implementacao;

import java.io.IOException;
import java.util.Set;

import com.bandManager.dao.ILancamentoDAO;
import com.bandManager.domain.Arquivo;
import com.bandManager.domain.Lancamento;
import com.bandManager.domain.Musica;
import com.bandManager.exception.ArquivoInvalidoException;
import com.bandManager.facade.ILancamentoFacade;
import com.bandManager.facade.IMusicaFacade;
import com.bandManager.util.FileUtil;

public class LancamentoFacade implements ILancamentoFacade {
	
	private ILancamentoDAO lancamentoDAO;
	private IMusicaFacade musicaFacade;
	
	public Lancamento salvar(Lancamento lancamento) {
		return this.lancamentoDAO.salvar(lancamento);
	}

	public Lancamento recuperar(int id){
		return this.lancamentoDAO.recuperar(id);
	}
	
	public void excluir(int id) throws IOException{
		Lancamento lancamento = this.recuperar(id);
		Set<Musica> musicas = lancamento.getMusicas();
		
		if(lancamento.getCapa()!=null){
			this.excluirCapa(lancamento, FileUtil.getCaminhoSitema());
		}
		
		if(musicas!=null){
			for(Musica musica : musicas){
				this.musicaFacade.excluirArquivoMusica(musica, FileUtil.getCaminhoSitema());
			}
		}
		
		this.lancamentoDAO.excluir(id);
	}
	
	public Lancamento adicionarCapa(Lancamento lancamento, Arquivo capa, String pathSistema) throws ArquivoInvalidoException, IOException{
		FileUtil.validarImagem(capa);
		
		//Recupera a banda com o logo atual
		lancamento = this.recuperar(lancamento.getId());
		
		//Exclui o logo existente
		excluirCapaDoServidor(lancamento, pathSistema);

		FileUtil.subirArquivo(capa.getFile(), pathSistema+"/"+capa.getCaminhoArquivo());
		
		//Seta o logo na banda e salva a banda
		lancamento.setCapa(capa);
		this.salvar(lancamento);
		
		return lancamento;
	}
	
	public void excluirCapa(Lancamento lancamento, String pathSistema) throws IOException{

		excluirCapaDoServidor(lancamento, pathSistema);
		
		lancamento.setCapa(null);
		this.salvar(lancamento);
	}

	private void excluirCapaDoServidor(Lancamento lancamento, String pathSistema) throws IOException {
		if(lancamento.getCapa()!=null){
			FileUtil.excluirArquivo(pathSistema+"/"+lancamento.getCapa().getCaminhoArquivo());
		}
	}

	
	/*Getters and Setters*/
	
	public void setLancamentoDAO(ILancamentoDAO lancamentoDAO) {
		this.lancamentoDAO = lancamentoDAO;
	}

	public void setMusicaFacade(IMusicaFacade musicaFacade) {
		this.musicaFacade = musicaFacade;
	}
}
