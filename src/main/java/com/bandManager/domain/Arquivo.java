package com.bandManager.domain;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Arquivo {

	private int id;
	private File file;
	private String contentType;
	private String nome;
	private String diretorioRelativo;
	
	public Arquivo(){
		
	}
	
	public Arquivo(String nome, String contentType, File file, String diretorioRelativo) {
		this.nome = nome;
		this.contentType = contentType;
		this.file = file;
		this.diretorioRelativo = diretorioRelativo;
	}

	@Id
	@GeneratedValue
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDiretorioRelativo() {
		return diretorioRelativo;
	}

	public void setDiretorioRelativo(String diretorioRelativo) {
		this.diretorioRelativo = diretorioRelativo;
	}
	
	public String getCaminhoArquivo(){
		return this.diretorioRelativo+this.nome;
	}
	
	public void setCaminhoArquivo(String caminhoArquivo){
		
	}
	
	public String toString(){
		return this.diretorioRelativo+this.nome;
	}
}
