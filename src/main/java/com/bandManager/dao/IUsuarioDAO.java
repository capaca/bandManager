package com.bandManager.dao;

import com.bandManager.domain.Usuario;

public interface IUsuarioDAO {

	/**
	 * Salva o {@link Usuario} passado por parâmetro
	 * @param {@link Usuario} a ser salvo
	 * @return {@link Usuario} salvo
	 */
	public Usuario salvar(Usuario usuario);
	
	/**
	 * Recupera o {@link Usuario} pelo id
	 * passado por parâmetro
	 * @param username
	 * @return {@link Usuario} recuperado
	 */
	public Usuario recuperar(int id);
	
	/**
	 * Recupera o {@link Usuario} pelo username 
	 * passado por parâmetro
	 * @param username
	 * @return {@link Usuario} recuperado
	 */
	public Usuario recuperar(String username);
}
