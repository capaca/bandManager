package com.bandManager.facade.implementacao;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.bandManager.dao.ILancamentoDAO;
import com.bandManager.domain.Arquivo;
import com.bandManager.domain.Banda;
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
	
	public void excluir(int id){
		Lancamento lancamento = this.recuperar(id);
		Set<Musica> musicas = lancamento.getMusicas();
		
		if(lancamento.getCapa()!=null){
			try {
				this.excluirCapa(lancamento, FileUtil.getCaminhoSitema());
			} catch (IOException e1) {
				System.err.println("Não foi possível remover a capa do lancamento");
				e1.printStackTrace();
			}
		}
		
		if(musicas!=null){
			for(Musica musica : musicas){
				try {
					this.musicaFacade.excluirArquivoMusica(musica, FileUtil.getCaminhoSitema());
				} catch (IOException e) {
					System.err.println("Não foi possível excluir o arquivo de áudio da musica.");
					e.printStackTrace();
				}
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
	
	public ILancamentoDAO getLancamentoDAO() {
		return lancamentoDAO;
	}

	public void setLancamentoDAO(ILancamentoDAO lancamentoDAO) {
		this.lancamentoDAO = lancamentoDAO;
	}

	public IMusicaFacade getMusicaFacade() {
		return musicaFacade;
	}

	public void setMusicaFacade(IMusicaFacade musicaFacade) {
		this.musicaFacade = musicaFacade;
	}
}
