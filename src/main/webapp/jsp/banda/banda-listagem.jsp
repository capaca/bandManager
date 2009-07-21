<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

	<SCRIPT type="text/javascript">
		$(document).ready(function() { 
		    $("#tabelaBandas").tablesorter({
		    	headers: {  
		            5: { 
		                sorter: false 
		            } 
		        } 
			}); 
		});
	</SCRIPT>

	<h1>Bandas</h1>
	
	<s:fielderror/>
	<s:actionmessage/>
	
	<s:if test="bandas.size()>0">
	<table id="tabelaBandas" class="listagem" border="0" align="center">
		<thead>
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
				Ano
			</th>
			<th width="15%">
				Editar
			</th>
		</tr>
		</thead>
		<tbody>
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
		</tbody>
	</table>
	</s:if>
	<s:else>
		<center>
			Nenhuma banda adicionada.
		</center>
	</s:else>
	
	<br>
	<center>
	 	<s:url id="urlNovaBanda" action="novaBanda"/>
		<s:a href="%{#urlNovaBanda}" cssClass="botao_adicionar">Nova Banda</s:a>
	</center>
	
	
	
	