package com.bandManager.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Lancamento {

	private int id;
	private String nome;
	private int ano;
	private Set<Musica> musicas;
	private String informacoes;
	private Banda banda;
	private String capa;
	
	public Lancamento(){
		
	}

	public Lancamento(String nome, int ano, String informacoes) {
		this.nome = nome;
		this.ano = ano;
		this.informacoes = informacoes;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	@OneToMany(fetch=FetchType.EAGER, mappedBy="lancamento", cascade = CascadeType.REMOVE)
	@OrderBy(value = "numero")
	public Set<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(Set<Musica> musicas) {
		this.musicas = musicas;
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}
	
	@ManyToOne
	public Banda getBanda() {
		return banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}

	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}	
}
