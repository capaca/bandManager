package com.bandManager.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.bandManager.domain.Arquivo;
import com.bandManager.exception.ArquivoInvalidoException;

public class FileUtil {


	public static void subirArquivo(File arquivo, String caminho) throws IOException{
		FileUtils.copyFile(arquivo, new File(caminho));
	}

	public static void excluirArquivo(String caminhoArquivo) throws IOException{
		File arquivo = new File(caminhoArquivo);
		FileUtils.forceDelete(arquivo);
	}
	
	public static String getExtensaoArquivo(String nomeArquivo){
		if(nomeArquivo!=null){
			String[] array = nomeArquivo.split("\\.");
			
			if(array.length>1){
				return array[array.length-1];
			}
		}
		
		return "";
	}
	
	public static void validarImagem(Arquivo arquivo) throws ArquivoInvalidoException {
		if(arquivo ==null || arquivo.getFile() == null || !arquivo.getContentType().contains("image") ){
			throw new ArquivoInvalidoException("O logo deve ser um arquivo válido do tipo imagem");
		}
	}
	
	public static String getCaminhoSitema(){
		String caminho = FileUtil.class.getResource("/struts.xml").getPath();
		caminho = caminho.replaceAll("/classes/struts.xml", "").replaceAll("/WEB-INF", "");
		
		System.out.println("Caminho do sistema: "+caminho);
		
		return caminho;
	}

	 public static boolean removerDiretorio(File dir) {
		 if (dir.isDirectory()) {
			 String[] children = dir.list();
			 for (int i=0; i<children.length; i++) {
				 boolean success = removerDiretorio(new File(dir, children[i]));
				 if (!success) {
					 return false;
				 }
            }
        }
	    
	     return dir.delete();
	 }
}
