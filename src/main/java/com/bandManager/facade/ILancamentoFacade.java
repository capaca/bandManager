package com.bandManager.facade;

import java.io.IOException;

import com.bandManager.domain.Arquivo;
import com.bandManager.domain.Lancamento;
import com.bandManager.exception.ArquivoInvalidoException;

public interface ILancamentoFacade {

	/**
	 * Salva um {@link Lancamento}.
	 * @param {@link Lancamento}
	 * @return O {@link Lancamento} salvo.
	 */
	public Lancamento salvar(Lancamento lancamento);
	
	/**
	 * Recuperara um {@link Lancamento} salvo anteriormente.
	 * @param id do {@link Lancamento}
	 * @return O {@link Lancamento} recuperado.
	 */
	public Lancamento recuperar(int id);
	
	/**
	 * Exclui o {@link Lancamento} com ID igual ao valor passado por parâmetro.
	 * @param id do {@link Lancamento} a ser excluído.
	 */
	public void excluir(int id);
	
	/**
	 * Adiciona a capa a um {@link Lancamento}. O método exclui alguma capa que possa existir,
	 * move o arquivo para o servidor, seta o atributo e salva o lançamento.
	 * @param {@link Lancamento}
	 * @param capa : {@link Arquivo}
	 * @param pathSistema - Caminho do sistema.
	 * @throws ArquivoInvalidoException - Caso a capa não seja uma imagem válida.
	 * @throws IOException - Caso tenha ocorrido algum erro ao transferir o arquivo para o servidor.
	 */
	public Lancamento adicionarCapa(Lancamento lancamento, Arquivo capa, String pathSistema) throws ArquivoInvalidoException, IOException;
	
	/**
	 * Exclui a capa de um {@link Lancamento}. O método exclui o arquivo do servidor,
	 * seta o atributo capa como null e salva o {@link Lancamento}.
	 * @param {@link Lancamento}
	 * @param pathSistema -  Caminho do sistema.
	 * @throws IOException - Caso haja algum erro ao excluir a imagem do servidor.
	 */
	public void excluirCapa(Lancamento lancamento, String pathSistema) throws IOException;
}
