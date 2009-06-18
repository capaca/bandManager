<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

<SCRIPT type="text/javascript">
	$(function() {
		$('#dataNascimento').datepicker();
		$('#dataNascimento').datepicker('option', {dateFormat: 'dd/mm/yy'});
	});
</SCRIPT>

	<h1>Editar 
		<s:if test="%{usuario!=null && usuario.username != ''}">
			<s:property value="usuario.username"/>
		</s:if>
		<s:else>
			Usuário
		</s:else>
	</h1>
	
	<s:actionerror/>
	<s:actionmessage/>
	
	<s:url id="urlSalvarUsuario" value="salvarPerfil">
	</s:url>
	
	<s:form action="%{#urlSalvarUsuario}" method="post">
		<table border="0" class="form">
			<tr>
				<th>
					Nome:
				</th>
				<td>
					<s:textfield name="usuario.perfil.nome" label="Nome" required="true" theme="simple"/>
				</td>
				<th>
					Data de Nascimento:
				</th>
				<td>
					<s:textfield name="usuario.perfil.dataNascimento" id="dataNascimento" required="true" theme="simple"/>
				</td>
			</tr>
			<tr>
				<th>
					E-mail:
				</th>
				<td>
					<s:textfield name="usuario.perfil.email" required="true" theme="simple"/>
				</td>
				<th>
					Sexo:
				</th>
				<td>
					<s:radio list="{'M','F'}" name="usuario.perfil.sexo" theme="simple"></s:radio>
				</td>
			</tr>
			<tr>
				<th>
					País:
				</th>
				<td>
					<s:select 	label="Pais de Origem"
	        			name="usuario.perfil.pais.id"
	        			list="paises"
	        			listKey="id"
	        			listValue="nome"
				        required="true"
				        theme="simple"
					/>			
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
	
	<s:url id="linkBandas" action="bandas">
	</s:url>
	
	<br><br>
	<s:a href="%{#linkBandas}" cssClass="botao_voltar">Voltar</s:a>
