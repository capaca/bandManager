package com.bandManager.facade;

import java.io.IOException;

import com.bandManager.domain.Arquivo;
import com.bandManager.domain.Musica;
import com.bandManager.exception.ArquivoInvalidoException;

public interface IMusicaFacade {

	/**
	 * Salva uma música
	 * 
	 * @param {@link Musica}
	 * @return {@link Musica} salva.
	 */
	public Musica salvar(Musica musica);
	
	/**
	 * Recupera uma música existente pelo id.
	 * @param id
	 * @return {@link Musica} recuperada.
	 */
	public Musica recuperar(int id);
	
	/**
	 * Exclui a música com o id passdo por parâmetro.
	 * @param id 
	 */
	public void excluir(int id);
	
	/**
	 * Adiciona um arquivo de audio a música. O processo de adição
	 * consiste em transferir o arquivo do cliente para o servidor
	 * e associar o caminho da música transferida com a {@Musica}.
	 * @param musica
	 * @param arquivoAudio
	 * @param pathSistema
	 * @return A {@link Musica} que teve o arquivo adicionado
	 * @throws ArquivoInvalidoException
	 * @throws IOException
	 */
	public Musica adicionarArquivoMusica(Musica musica, Arquivo arquivoAudio, String pathSistema) throws ArquivoInvalidoException, IOException;

	/**
	 * Exclui o arquivo de audio da música. O processo de exclusão
	 * remove o arquivo do servidor e seta e retira o caminho deste
	 * arquivo da {@link Musica}
	 * @param musica
	 * @param pathSistema
	 * @throws IOException
	 */
	public void excluirArquivoMusica(Musica musica, String pathSistema) throws IOException;
}
