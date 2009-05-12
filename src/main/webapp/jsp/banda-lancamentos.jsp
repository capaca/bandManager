<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

	<h1><s:property value="banda.nome"/></h1>
	
	<s:actionerror/>
	<s:actionmessage/>
	
	<table border="0" class="form">
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
				País:
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
			<th valign="top">
				Release:
			</th>
			<td colspan="8">
				<s:property value="banda.release"/>
			</td>
		</tr>
		<tr>
			<td>
			</td>
			<td align="right" colspan="7">
				<s:url id="urlEditarBanda" value="editarBanda.action">
					<s:param name="banda.id" value="%{banda.id}" />
				</s:url>
				
				<s:url id="urlAdicionarLancamento" value="adicionarLancamento">
					<s:param name="banda.id" value="%{banda.id}" />
				</s:url>
				
				<s:a href="%{#urlEditarBanda}">Editar</s:a> | 
				<s:a href="%{#urlAdicionarLancamento}">Adicionar Lançamento</s:a>
			</td>
		</tr>
	</table>
	
	<h2>Lançamentos</h2>
	
	<s:if test="banda.lancamentos.size() > 0">
	<table  class="listagem" border="0" width="80%" align="center">
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
		<p align="center"><em>Nenhum lançamento</em></p>
	</s:else>