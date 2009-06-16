package com.bandManager.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

@Entity
public class Banda {

	private int id;
	private String nome;
	private String estilo;
	private String cidade;
	private Pais pais;
	private Set<Lancamento> lancamentos;
	private String anoFormacao;
	private String release;
	private String influencias;
	private Set<Show> shows;
	private Arquivo logo;
	private Arquivo foto;
	
	public Banda(){
		
	}

	public Banda(String nome, Pais pais, String cidade, String anoFormacao, String release) {
		super();
		this.nome = nome;
		this.pais = pais;
		this.cidade = cidade;
		this.anoFormacao = anoFormacao;
		this.release = release;
	}

	public Banda(String nome, String cidade, Pais pais, String anoFormacao, String release) {
		super();
		this.nome = nome;
		this.cidade = cidade;
		this.pais = pais;
		this.anoFormacao = anoFormacao;
		this.release = release;
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
	
	@ManyToOne
	public Pais getPais() {
		return pais;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "banda")
	@OrderBy(value="ano DESC")
	public Set<Lancamento> getLancamentos() {
		return lancamentos;
	}
	
	public void setLancamentos(Set<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	public String getAnoFormacao() {
		return anoFormacao;
	}
	
	public void setAnoFormacao(String anoFormacao) {
		this.anoFormacao = anoFormacao;
	}

	@Column(length = 2000)
	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}
	
	@Column(length = 1000)
	public String getInfluencias() {
		return influencias;
	}

	public void setInfluencias(String influencias) {
		this.influencias = influencias;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	public Arquivo getLogo() {
		return logo;
	}

	public void setLogo(Arquivo logo) {
		this.logo = logo;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public Arquivo getFoto() {
		return foto;
	}

	public void setFoto(Arquivo foto) {
		this.foto = foto;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "banda")
	@OrderBy(value="data DESC")
	public Set<Show> getShows() {
		return shows;
	}

	public void setShows(Set<Show> shows) {
		this.shows = shows;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
}
