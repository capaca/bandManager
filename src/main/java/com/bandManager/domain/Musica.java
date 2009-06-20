package com.bandManager.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Musica  {

	private int id;
	private String nome;
	private int numero;
	private String letra;
	private Arquivo arquivoAudio;
	private Lancamento lancamento;
	
	public Musica(){
		
	}

	public Musica(String nome, int numero, String letra, Lancamento lancamento) {
		this.nome = nome;
		this.numero = numero;
		this.letra = letra;
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

	@OneToOne(cascade = CascadeType.ALL)
	public Arquivo getArquivoAudio() {
		return arquivoAudio;
	}

	public void setArquivoAudio(Arquivo arquivoAudio) {
		this.arquivoAudio = arquivoAudio;
	}
}
