package com.bandManager.util;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;


public class TesteFileUtil {

	@Test
	public void getExtensaoArquivo(){
		String nomeArquivo = "foto.jpg";
		
		String extensao = FileUtil.getExtensaoArquivo(nomeArquivo);
		
		assertEquals("jpg", extensao);
	}
	
	@Test
	public void getCaminhoSistema(){
		assertEquals("/home/pedro/workspace/bandManager/target", FileUtil.getCaminhoSitema());
	}
	
	@Test
	public void removerDiretorio(){
		
		File diretorioImagens = new File(FileUtil.getCaminhoSitema()+"/imagens");
		assertTrue(diretorioImagens.isDirectory());
		FileUtil.removerDiretorio(diretorioImagens);
		assertFalse(diretorioImagens.exists());
	}
}
