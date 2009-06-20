<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<SCRIPT type="text/javascript">
	$(function() {
		$('#showData').datepicker();
		$('#showData').datepicker('option', {dateFormat: 'dd/mm/yy'});
	});
</SCRIPT>
</head>

	<h1>Editar 
		<s:if test="%{banda!=null && banda.nome != null && banda.nome != ''}">
			<s:property value="show.nome"/>
		</s:if>
		<s:else>
			Show
		</s:else>
	</h1>
	
	<s:actionerror/>
	<s:actionmessage/>
		
	<s:url id="urlSalvarShow" value="salvarShow.action" escapeAmp="false">
		<s:param name="show.id" value="%{show.id}" />
		<s:param name="banda.id" value="%{banda.id}" />
	</s:url>
	
	<s:form action="%{#urlSalvarShow}" method="post">
		<s:hidden  name="show.banda.id" value="%{banda.id}"/>

		<table border="0" class="form">
			<tr>
				<th>
					Nome:
				</th>
				<td>
					<s:textfield name="show.nome" required="true" theme="simple"/>
				</td>
				
				<th>
					Data:
				</th>
				<td>
					<s:date  id="dataShow" name="show.data" format="dd/MM/yyyy"/>
					<s:textfield id="showData" name="show.data" value="%{dataShow}" required="true" theme="simple"/>
				</td>
			</tr>
			<tr>
				<th>
					Cidade:
				</th>
				<td>
					<s:textfield name="show.cidade" required="true" theme="simple"/>
				</td>

				<th>
					País:
				</th>
				<td>
					<s:select 	label="Pais de Origem"
	        			name="show.pais.id"
	        			list="paises"
	        			listKey="id"
	        			listValue="nome"
				        required="true"
				        theme="simple"
					/>			
				</td>
			</tr>
			<tr>
				<th>
					Local:
				</th>
				<td colspan="3">
					<s:textfield name="show.local" required="true" size="52" theme="simple"/>
				</td>
			</tr>
			<tr>
				<th valign="top">
					Descrição:
				</th>
				<td colspan="3">
					<s:textarea name="show.descricao" cols="60" rows="5" required="true" theme="simple"/>
				</td>
			</tr>
			<tr>
				<td>
				</td>
				<td>
					<s:submit value="Salvar" theme="simple"/>
				</td>
			</tr>
		</table>
	</s:form>
	
	<s:url id="urlEditarBanda" action="editarBanda">
		<s:param name="banda.id" value="%{banda.id}" />
	</s:url>
	
	<br><br>
	
	<s:a href="%{#urlEditarBanda}" cssClass="botao_voltar">Voltar</s:a>
