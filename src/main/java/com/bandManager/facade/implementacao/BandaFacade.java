package com.bandManager.facade.implementacao;

import java.io.IOException;
import java.util.List;

import com.bandManager.dao.IBandaDAO;
import com.bandManager.domain.Arquivo;
import com.bandManager.domain.Banda;
import com.bandManager.exception.ArquivoInvalidoException;
import com.bandManager.exception.ObjetoNaoEncontradoException;
import com.bandManager.facade.IBandaFacade;
import com.bandManager.util.FileUtil;

public class BandaFacade implements IBandaFacade {

	private IBandaDAO bandaDAO;
	
	public Banda salvar(Banda banda) {
		return this.bandaDAO.salvar(banda);
	}

	public Banda recuperar(int id) throws ObjetoNaoEncontradoException {
		return this.bandaDAO.recuperar(id);
	}
	
	public List<Banda> recuperarTodas() {
		return this.bandaDAO.recuperarTodas();
	}

	public void adicionarLogo(Banda banda, Arquivo logo, String pathSistema) throws ArquivoInvalidoException, IOException, ObjetoNaoEncontradoException{
		FileUtil.validarImagem(logo);
		
		//Recupera a banda com o logo atual
		Banda bandaAtual = this.recuperar(banda.getId());
		
		//Exclui o logo existente
		excluirLogoDoServidor(bandaAtual, pathSistema);

		FileUtil.subirArquivo(logo.getFile(), pathSistema+"/"+logo.getCaminhoArquivo());
		
		//Seta o logo na banda e salva a banda
		banda.setLogo(logo);
		this.salvar(banda);
	}

	public void excluirLogo(Banda banda, String pathSistema) throws IOException{

		excluirLogoDoServidor(banda, pathSistema);
		
		banda.setLogo(null);
		this.salvar(banda);
	}

	private void excluirLogoDoServidor(Banda banda, String pathSistema) throws IOException {
		if(banda.getLogo()!=null){
			FileUtil.excluirArquivo(pathSistema+"/"+banda.getLogo().getCaminhoArquivo());
		}
	}
	
	public void adicionarFoto(Banda banda, Arquivo foto, String pathSistema) throws ArquivoInvalidoException, IOException, ObjetoNaoEncontradoException{
		FileUtil.validarImagem(foto);
		
		//Recupera a banda com o logo atual
		Banda bandaAtual = this.recuperar(banda.getId());
		
		//Exclui o logo existente
		excluirFotoDoServidor(bandaAtual, pathSistema);

		FileUtil.subirArquivo(foto.getFile(), pathSistema+"/"+foto.getCaminhoArquivo());
		
		//Seta o logo na banda e salva a banda
		banda.setFoto(foto);
		this.salvar(banda);
	}

	public void excluirFoto(Banda banda, String pathSistema) throws IOException{

		excluirFotoDoServidor(banda, pathSistema);
		
		banda.setFoto(null);
		this.salvar(banda);
	}
	
	private void excluirFotoDoServidor(Banda banda, String pathSistema) throws IOException {
		if(banda.getFoto()!=null){
			FileUtil.excluirArquivo(pathSistema+"/"+banda.getFoto().getCaminhoArquivo());
		}
	}
	
	/* Getters and Setters */
	
	public IBandaDAO getBandaDAO() {
		return bandaDAO;
	}

	public void setBandaDAO(IBandaDAO bandaDAO) {
		this.bandaDAO = bandaDAO;
	}
}
