<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
</head>
<body>
	<h1>Editar Lançamento</h1>
	
	<s:url id="urlSalvarLancamento" value="salvarLancamento.action" escapeAmp="true">
    	<s:param name="banda.id" value="banda.id" />
	</s:url>
	
	<s:form action="%{#urlSalvarLancamento}" method="post">
		<s:hidden name="lancamento.banda.id" value="%{banda.id}"/>
		
		<s:if test="%{lancamento.id > 0}">
			<s:hidden name="lancamento.id" value="%{lancamento.id}"/>
		</s:if>
		<s:else>
			<s:hidden name="lancamento.id" value="0"/>
		</s:else>
		
		<s:textfield name="lancamento.nome" label="Nome" required="true"/>
		<s:textfield name="lancamento.ano" label="Ano" required="true"/>
		<s:textarea name="lancamento.informacoes" label="Informações" cols="20" rows="5" required="true"/>
		
		<s:submit value="Salvar"/>
	</s:form>
	
	<s:if test="%{lancamento.capa != null && lancamento.capa != ''}">
	
		<table border="1">
			<tr>
				<th>
					Capa
				</th>
			</tr>
			<tr>
				<td>
					<html:img src="${lancamento.capa}" width="150"/>	
				</td>
				<td>
					<s:url id="urlExcluirCapa" action="excluirCapa">
						<s:param value="%{lancamento.id}" name="lancamento.id"/>
					</s:url>
					
					<s:a href="%{#urlExcluirCapa}">Excluir Capa</s:a>	
				</td>
			</tr>
		</table>
		
	</s:if>
	<s:else>
		Nenhuma capa adicionada.
	</s:else>
	
	<s:form action="uploadCapa.action?lancamento.id=%{lancamento.id}" method="post"enctype="multipart/form-data">
		<s:hidden name="lancamento.id" value="%{lancamento.id}" />
		<s:file  name="capa" label="Capa"/>
		<s:submit value="Upload" />
	</s:form>
	
	<s:if test="%{lancamento.id>0}">
		<s:url id="urlAdicionarMusica" value="adicionarMusica">
			<s:param name="lancamento.id" value="lancamento.id"/>
			<s:param name="banda.id" value="banda.id"/>
		</s:url>
		<s:a href="%{#urlAdicionarMusica}">Adicionar Música</s:a>
		
		<s:if test="lancamento.musicas.size()<=0">
			<p align="center">Nenhuma música</p>
		</s:if>
		<s:else>
			<table border="1" width="100%">
				<tr>
					<th>
						Nome
					</th>
					<th>
						Número
					</th>
					<th>
						Letra
					</th>
					<th>
						Editar
					</th>
					<th>
						Excluir
					</th>
				</tr>
				<s:iterator value="lancamento.musicas">
				<tr>
					<td>
						<s:property value="nome"/>
					</td>
					
					<td>
						<s:property value="numero"/>
					</td>
					
					<td>
						<s:property value="letra"/>
					</td>
					
					<td>
						<s:url id="urlEditarMusica" action="editarMusica">
							<s:param name="banda.id" value="%{lancamento.banda.id}"/>
							<s:param name="lancamento.id" value="%{lancamento.id}"/>
							<s:param name="musica.id" value="%{id}"/>
						</s:url>
						<s:a href="%{#urlEditarMusica}">Editar</s:a>
					</td>
					
					<td>
						<s:url id="urlExcluirMusica" action="excluirMusica">
							<s:param name="banda.id" value="%{lancamento.banda.id}"/>
							<s:param name="lancamento.id" value="%{lancamento.id}"/>
							<s:param name="musica.id" value="%{id}"/>
						</s:url>
						<s:a href="%{#urlExcluirMusica}">Excluir</s:a>
					</td>
				</tr>
				</s:iterator>
			</table>
		</s:else>
	</s:if>
	
	<p align="center">
		<s:url id="urlEditarLancamentos" action="editarLancamentos">
			<s:param name="banda.id" value="%{banda.id}" />
		</s:url>
		
		<s:a href="%{#urlEditarLancamentos}">Voltar</s:a>
	</p>
</body>
</html>