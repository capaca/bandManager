<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

	<h1>Bandas</h1>
	
	<s:actionerror/>
	<s:actionmessage/>
	
	<table  class="listagem" border="0" width="80%" align="center">
		<tr>
			<th width="25%">
				Nome
			</th>
			<th width="15%">
				Estilo
			</th>
			<th width="15%">
				Cidade
			</th>
			<th width="15%">
				Pais
			</th>
			<th width="15%">
				Ano de Formação
			</th>
			<th width="15%">
				Editar
			</th>
		</tr>
		<s:iterator value="bandas">
			<tr>
				<td>
					<s:property value="nome"/>
				</td>
				<td>
					<s:property value="estilo"/>
				</td>
				<td>
					<s:property value="cidade"/>
				</td>
				<td>
					<s:property value="pais"/>
				</td>
				<td>
					<s:property value="anoFormacao"/>
				</td>
				<td>
					<s:url id="urlEditarBanda" value="editarBanda">
						<s:param name="banda.id" value="id" />
					</s:url>
					<s:a href="%{#urlEditarBanda}">Editar</s:a>
				</td>
			</tr>
		</s:iterator>
	</table>