<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@taglib prefix="s" uri="/struts-tags"%>
</head>
<body>
	<h1>Lançamentos</h1>
	
	<s:actionerror/>
	<s:actionmessage/>
	
	<table border="1" width="100%">
		<tr>
			<th>
				Nome:
			</th>
			<td>
				<s:property value="banda.nome"/>
			</td>
			
			<th>
				Cidade:
			</th>
			<td>
				<s:property value="banda.cidade"/>
			</td>
			
			<th>
				Pais
			</th>
			<td>
				<s:property value="banda.pais"/>
			</td>
		
			<th>
				Ano de formação:
			</th>
			<td>
				<s:property value="banda.anoFormacao"/>
			</td>
		</tr>
		<tr>
			<th>
				Release:
			</th>
			<td colspan="8">
				<s:property value="banda.release"/>
			</td>
		</tr>
		<tr>
			<td>
				<s:url id="urlEditarBanda" value="editarBanda.action">
					<s:param name="banda.id" value="%{banda.id}" />
				</s:url>
				<s:a href="%{#urlEditarBanda}">Editar</s:a>
			</td>
		</tr>
	</table>
	
	<p>
		<s:url id="urlAdicionarLancamento" value="adicionarLancamento">
			<s:param name="banda.id" value="%{banda.id}" />
		</s:url>
		<s:a href="%{#urlAdicionarLancamento}">Adicionar Lançamento</s:a>
	</p>
	
	<s:if test="banda.lancamentos.size() > 0">
	<table border="1" width="100%">
		<tr>
			<th>
				Nome
			</th>
			<th>
				Ano
			</th>
			<th>
				Informações
			</th>
			<th>
				Editar
			</th>
			<th>
				Excluir
			</th>
		</tr>
		<s:iterator value="banda.lancamentos">
			<tr>
				<td>
					<s:property value="nome"/>
				</td>
				<td>
					<s:property value="ano"/>
				</td>
				<td>
					<s:property value="informacoes"/>
				</td>
				<td>
					<s:url id="urlEditarLancamento" value="editarLancamento">
						<s:param name="banda.id" value="banda.id" />
						
						<s:param name="lancamento.id">
							<s:property value="id" />
						</s:param>
					</s:url>
					<s:a href="%{#urlEditarLancamento}">Editar</s:a>
				</td>
				<td>
					<s:url id="urlExcluirLancamento" value="excluirLancamento">
						<s:param name="banda.id" value="%{banda.id}" />
						
						<s:param name="lancamento.id">
							<s:property value="id" />
						</s:param>
					</s:url>
					<s:a href="%{#urlExcluirLancamento}">Excluir</s:a>
				</td>
			</tr>
		</s:iterator>
	</table>
	</s:if>
	<s:else>
		<p align="center">Nenhum lançamento</p>
	</s:else>
</body>
</html>