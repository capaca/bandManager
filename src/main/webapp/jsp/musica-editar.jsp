<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@taglib prefix="s" uri="/struts-tags" %>
</head>
<body>
	<h1>Editar Música</h1>
	
	<s:url id="urlSalvarMusica" value="salvarMusica.action" escapeAmp="false">
		<s:param name="lancamento.id" value="%{lancamento.id}" />
		<s:param name="banda.id" value="%{banda.id}" />
	</s:url>
	
	<s:form action="%{#urlSalvarMusica}" method="post">
		<s:hidden name="musica.lancamento.id" value="%{lancamento.id}"/>
		
		<s:if test="%{musica.id > 0}">
			<s:hidden name="musica.id" value="%{musica.id}"/>
		</s:if>
		<s:else>
			<s:hidden name="musica.id" value="0"/>
		</s:else>
		
		<s:textfield name="musica.nome" label="Nome"/>
		<s:textfield name="musica.numero" label="Número" size="2"/>
		<s:textarea name="musica.letra" rows="5" cols="20" label="Letra"/>
		<s:submit value="Salvar" />
	</s:form>
	
	<s:if test="%{musica.arquivo != null && lancamento.capa != ''}">
	
		<table border="1">
			<tr>
				<th>
					Arquivo de Áudio
				</th>
			</tr>
			<tr>
				<td>
					<s:a href="%{musica.arquivo}">Baixar música</s:a>	
				</td>
				<td>
					<s:url id="urlExcluirArquivoMusica" action="excluirArquivoMusica">
						<s:param value="%{musica.id}" name="musica.id"/>
					</s:url>
					
					<s:a href="%{#urlExcluirArquivoMusica}">Excluir Musica</s:a>	
				</td>
			</tr>
		</table>
		
	</s:if>
	<s:else>
		Nenhum arquivo de musica adicionado.
	</s:else>
	
	<s:form action="uploadMusica.action?musica.id=%{musica.id}" method="post" enctype="multipart/form-data">
		<s:hidden name="musica.id" value="%{musica.id}" />
		<s:file  name="arquivo" label="Arquivo de Música" />
		<s:submit value="Upload" />
	</s:form>
	
	<p align="center">
		<s:url id="urlEditarLancamento" action="editarLancamento">
			<s:param name="lancamento.id" value="%{lancamento.id}" />
			<s:param name="banda.id" value="%{banda.id}" />
		</s:url>
		
		<s:a href="%{#urlEditarLancamento}">Voltar</s:a>
	</p>
</body>
</html>