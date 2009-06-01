package com.bandManager.facade;

import java.io.IOException;

import com.bandManager.domain.Arquivo;
import com.bandManager.domain.Musica;
import com.bandManager.exception.ArquivoInvalidoException;

//FIXME Documentar
public interface IMusicaFacade {

	public Musica salvar(Musica musica);
	
	public Musica recuperar(int id);
	
	public void excluir(int id);
	
	public Musica adicionarArquivoMusica(Musica musica, Arquivo arquivoAudio, String pathSistema) throws ArquivoInvalidoException, IOException;

	public void excluirArquivoMusica(Musica musica, String pathSistema) throws IOException;
}
