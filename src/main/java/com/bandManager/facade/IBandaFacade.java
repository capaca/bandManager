package com.bandManager.facade;

import java.io.IOException;
import java.util.List;

import com.bandManager.domain.Arquivo;
import com.bandManager.domain.Banda;
import com.bandManager.exception.ArquivoInvalidoException;
import com.bandManager.exception.ObjetoNaoEncontradoException;

public interface IBandaFacade {

	/**
	 * @descricao Salva uma {@link Banda}.
	 * @param {@link Banda}.
	 * @return retorna a {@link Banda} salva.
	 */
	public Banda salvar(Banda banda);
	
	/**
	 * @descricao Recuperar uma {@link Banda} pelo id.
	 * @param id da {@link Banda}.
	 * @return retorna a {@link Banda} recuperada
	 */
	public Banda recuperar(int id) throws ObjetoNaoEncontradoException;
	
	/**
	 * @descricao Adiciona um logo a {@link Banda} passada por parâmetro.
	 * Caso a banda já tenha um logo anteriormente, exclui o logo.
	 * Envia o arquivo ao servidor e salva a banda com o novo logo.
	 * @param {@link Banda}, {@link Arquivo}
	 * @param pathSistema - Caminho da pasta do sistema para o upload
	 * @throws ArquivoInvalidoException caso o logo não seja uma imagem.
	 * @throws IOException caso haja erro na transmissão do arquivo.
	 */
	public void adicionarLogo(Banda banda, Arquivo logo, String pathSistema) throws ArquivoInvalidoException, IOException, ObjetoNaoEncontradoException;
	
	/**
	 * @descricao Exclui o logo da banda. O aperação de excluão deleta o arquivo do servidor
	 * e seta o logo da banda como null.
	 * @param {@link Banda}, {@link Arquivo}
	 * @param pathSistema - Caminho da pasta do sistema para o upload
	 * @throws IOException caso haja erro na transmissão do arquivo.
	 */
	public void excluirLogo(Banda banda, String pathSistema) throws IOException;

	/**
	 * @descricao Adiciona uma foto a {@link Banda} passada por parâmetro.
	 * Caso a banda já tenha uma foto anteriormente, exclui a foto.
	 * Envia o arquivo ao servidor e salva a banda com a nova foto.
	 * @param {@link Banda}
	 * @param {@Link Arquivo}
	 * @param pathSistema - Caminho da pasta do sistema para o upload
	 * @throws ArquivoInvalidoException
	 * @throws IOException
	 */
	public void adicionarFoto(Banda banda, Arquivo foto, String pathSistema) throws ArquivoInvalidoException, IOException, ObjetoNaoEncontradoException;

	/**
	 * @descricao Exclui a foto de uma banda. 
	 * A operação de exclusão deleta o arquivo do servidor e seta a foto como null
	 * @param {@link Banda}
	 * @param pathSistema - Caminho da pasta do sistema para o upload
	 * @throws IOException
	 */
	public void excluirFoto(Banda banda, String pathSistema) throws IOException;
	
	/**
	 * @descricao Recupera todas as bandas cadastradas.
	 * @return Uma lista de @{link Banda}s
	 */
	public List<Banda> recuperarTodas();
}
