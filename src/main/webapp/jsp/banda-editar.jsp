<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<link href="../css/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
	<h1>Editar Banda</h1>
	
	<s:actionerror/>
	<s:actionmessage/>
	
	<s:url id="urlSalvarBanda" value="salvarBanda.action">
    	<s:param name="banda.id" value="banda.id" />
	</s:url>
	
	<s:form action="%{#urlSalvarBanda}" method="post">
		<s:textfield name="banda.nome" label="Nome" required="true"/>
		<s:textfield name="banda.cidade" label="Cidade" required="true"/>
		<s:select 	label="Pais de Origem"
        			name="banda.pais.id"
        			list="paises"
        			listKey="id"
        			listValue="nome"
			        required="true"
		/>
		<s:textfield name="banda.anoFormacao" label="Ano de Formação" required="true"/>
		<s:textarea name="banda.release" label="Release" cols="20" rows="5" required="true"/>
		
		<s:submit value="Salvar"/>
	</s:form>
	
	<s:if test="%{banda != null && banda.id > 0 }">
		<s:if test="%{banda.logo != null}">
		
			<table border="1">
				<tr>
					<th>
						Logo
					</th>
				</tr>
				<tr>
					<td>
						<html:img src="${banda.logo}" width="250"/>	
					</td>
					<td>
						<s:url id="urlExcluirLogo" action="excluirLogo">
							<s:param value="%{banda.id}" name="banda.id"/>
						</s:url>
						
						<s:a href="%{#urlExcluirLogo}">Excluir Logo</s:a>	
					</td>
				</tr>
			</table>
			
		</s:if>
		<s:else>
			Nenhum logo adicionado.
		</s:else>
		
		<s:form action="adicionarLogo" method="post"enctype="multipart/form-data">
			<s:hidden name="banda.id" value="%{banda.id}" />
			<s:file  name="logo" label="Logo"/>
			<s:submit value="Upload" />
		</s:form>
		
		<s:if test="%{banda.foto != null}">
		
			<table border="1">
				<tr>
					<th>
						Foto
					</th>
				</tr>
				<tr>
					<td>
						<html:img src="${banda.foto}" width="250"/>	
					</td>
					<td>
						<s:url id="urlExcluirFoto" action="excluirFoto">
							<s:param value="%{banda.id}" name="banda.id"/>
						</s:url>
						
						<s:a href="%{#urlExcluirFoto}">Excluir Foto</s:a>	
					</td>
				</tr>
			</table>
			
		</s:if>
		<s:else>
			Nenhuma foto adicionada.
		</s:else>
		
		<s:form action="adicionarFoto" method="post"enctype="multipart/form-data">
			<s:hidden name="banda.id" value="%{banda.id}" />
			<s:file  name="foto" label="Foto"/>
			<s:submit value="Upload" />
		</s:form>
	</s:if>
	
	<p align="center">
		<s:url id="urlEditarLancamentos" action="editarLancamentos">
			<s:param name="banda.id" value="%{banda.id}" />
		</s:url>
		
		<s:a href="%{#urlEditarLancamentos}">Voltar</s:a>
	</p>
</body>
</html>