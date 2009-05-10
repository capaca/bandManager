package com.bandManager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;

@Entity
public class Musica  {

	private int id;
	private String nome;
	private int numero;
	private String letra;
	private String arquivo;
	private Lancamento lancamento;
	
	public Musica(){
		
	}

	public Musica(String nome, int numero, String letra, String arquivo, Lancamento lancamento) {
		this.nome = nome;
		this.numero = numero;
		this.letra = letra;
		this.arquivo = arquivo;
		this.lancamento = lancamento;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	@ManyToOne
	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
}
