<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

	<h1>Editar 
		<s:if test="%{banda!=null && banda.nome != ''}">
			<s:property value="banda.nome"/>
		</s:if>
		<s:else>
			Banda
		</s:else>
	</h1>
	
	<s:actionerror/>
	<s:actionmessage/>
	
	<s:url id="urlSalvarBanda" value="salvarBanda.action">
    	<s:param name="banda.id" value="banda.id" />
	</s:url>
	
	<s:form action="%{#urlSalvarBanda}" method="post">
		<table border="0" class="form">
			<tr>
				<th>
					Nome:
				</th>
				<td>
					<s:textfield name="banda.nome" label="Nome" required="true" theme="simple"/>
				</td>
				
				<th>
					Estilo:
				</th>
				<td>
					<s:textfield name="banda.estilo" label="Nome" required="true" theme="simple"/>
				</td>
			</tr>
			<tr>
				<th>
					Cidade:
				</th>
				<td>
					<s:textfield name="banda.cidade" label="Cidade" required="true" theme="simple"/>
				</td>
				
				<th>
					País:
				</th>
				<td>
					<s:select 	label="Pais de Origem"
	        			name="banda.pais.id"
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
					Ano de Formação:
				</th>
				<td>
					<s:textfield name="banda.anoFormacao" label="Ano de Formação" required="true" theme="simple"/>
				</td>
			</tr>
			<tr>
				<th valign="top">
					Influências:
				</th>
				<td colspan="3">
					<s:textarea name="banda.influencias" label="Release" cols="80" rows="3" required="true" theme="simple"/>
				</td>
			</tr>
			<tr>
				<th valign="top">
					Release:
				</th>
				<td colspan="3">
					<s:textarea name="banda.release" label="Release" cols="80" rows="5" required="true" theme="simple"/>
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
	
	<s:if test="%{banda != null && banda.id > 0 }">
		<s:form action="adicionarLogo" method="post"enctype="multipart/form-data">
			<s:hidden name="banda.id" value="%{banda.id}" />

			<table class="form">
				<tr>
					<th>
						Logo:
					</th>
					<td>
						<s:file  name="logo" theme="simple"/>
						<s:submit value="Upload" theme="simple"/>
					</td>
				</tr>
				<tr>
					<td>
					</td>
					<s:if test="%{banda.logo != null}">
						
						<td colspan="2">
							<s:url id="urlExcluirLogo" action="excluirLogo">
								<s:param value="%{banda.id}" name="banda.id"/>
							</s:url>
	
							<a href="${banda.logo}" target="_blank">Ver Logo</a> | <s:a href="%{#urlExcluirLogo}">Excluir Logo</s:a>
						</td>
					</s:if>
					<s:else>
						<td>
							<em>Nenhum Logo adicionado.</em>
						</td>
					</s:else>
				</tr>
			</table>
		</s:form>
		
		<s:form action="adicionarFoto" method="post"enctype="multipart/form-data">
			<s:hidden name="banda.id" value="%{banda.id}" />

			<table class="form">
				<tr>
					<th>
						Foto:
					</th>
					<td>
						<s:file  name="foto" theme="simple"/>
						<s:submit value="Upload" theme="simple"/>
					</td>
				</tr>
				<tr>
					<td>
					</td>
					<s:if test="%{banda.foto != null}">
						
						<td colspan="2">
							<s:url id="urlExcluirFoto" action="excluirFoto">
								<s:param value="%{banda.id}" name="banda.id"/>
							</s:url>
	
							<a href="${banda.foto}" target="_blank">Ver Foto</a> | <s:a href="%{#urlExcluirFoto}">Excluir Foto</s:a>
						</td>
					</s:if>
					<s:else>
						<td>
							<em>Nenhuma Foto adicionada.</em>
						</td>
					</s:else>
				</tr>
			</table>
		</s:form>
	</s:if>
	
	<s:url id="urlEditarLancamentos" action="editarLancamentos">
		<s:param name="banda.id" value="%{banda.id}" />
	</s:url>
	
	<br><br>
	<s:a href="%{#urlEditarLancamentos}" cssClass="botao_voltar">Voltar</s:a>
