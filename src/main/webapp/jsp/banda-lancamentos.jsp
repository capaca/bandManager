<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<SCRIPT type="text/javascript">
	$(function() {
		$("#botaoMostrarShows").click(function() {
			$("#shows").show('blind');
		});
	
		$("#botaoEsconderShows").click(function() {
			$("#shows").hide('blind');
		});
	});
	

</SCRIPT>
</head>

	<h1><s:property value="banda.nome"/></h1>
	
	<s:actionerror/>
	<s:actionmessage/>

	<div>
		<s:if test="banda.foto!=null">
			<div style="float: left;">
				<img src="${banda.foto}" width="300" class="img_perfil"/>
			</div>
		</s:if>
		
		<div>
			<table border="0" class="form">
				<tr>
					<th>
						Nome:
					</th>
					<td>
						<s:property value="banda.nome"/>
					</td>
					
					<th>
						Estilo:
					</th>
					
					<td>
						<span style="text-transform:capitalize"><s:property value="banda.estilo"/></span>
					</td>
				</tr>
				<tr>
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
				</tr>
				<tr>
					<th valign="top">
						Influências:
					</th>
					<td colspan="8">
						<s:property value="banda.influencias"/>
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
					<td colspan="4" style="text-align: right">
						<s:url id="urlEditarBanda" value="editarBanda.action">
							<s:param name="banda.id" value="%{banda.id}" />
						</s:url>
						<s:a href="%{#urlEditarBanda}" cssClass="botao_editar">Editar</s:a> 
					</td>
				</tr>
			</table>
		</div>
		<div style="clear: both; text-align: right;">
		</div>
	</div>
	<div style="clear: both;">
	</div>
	
	<h2>Shows</h2>
	
	<div id="shows" class="sub_conteudo">
		<s:if test="banda.shows.size() > 0">
		<table  class="listagem" border="0" width="80%" align="center">
			<tr>
				<th width="15%">
					Data
				</th>
				<th width="31%">
					Nome
				</th>
				<th width="15%">
					Cidade
				</th>
				<th width="15%">
					País
				</th>
				<th width="12%">
					Editar
				</th>
				<th width="12%">
					Excluir
				</th>
			</tr>
			<s:iterator value="banda.shows">
				<tr>
					<td>
						<s:date name="data" format="dd/MM/yyyy"/>
					</td>
					<td>
						<s:property value="nome"/>
					</td>
					<td>
						<s:property value="cidade"/>
					</td>
					<td>
						<s:property value="pais"/>
					</td>
					<td>
						<s:url id="urlEditarShow" value="editarShow">
							<s:param name="banda.id" value="banda.id" />
							
							<s:param name="show.id">
								<s:property value="id" />
							</s:param>
						</s:url>
						<s:a href="%{#urlEditarShow}">Editar</s:a>
					</td>
					<td>
						<s:url id="urlExcluirShow" value="excluirShow">
							<s:param name="banda.id" value="%{banda.id}" />
							
							<s:param name="show.id">
								<s:property value="id" />
							</s:param>
						</s:url>
						<s:a href="%{#urlExcluirShow}">Excluir</s:a>
					</td>
				</tr>
			</s:iterator>
		</table>
		</s:if>
		<s:else>
			<em>Nenhum show</em>
		</s:else>
		
		<br><br>
		
		<s:url id="urlAdicionarShow" value="novoShow">
			<s:param name="banda.id" value="%{banda.id}" />
		</s:url>
		<s:a href="%{#urlAdicionarShow}" cssClass="botao_adicionar">Adicionar Show</s:a> 
	</div>
	
	<h2>Lançamentos</h2>
	<div class="sub_conteudo">
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
			<em>Nenhum lançamento</em>
		</s:else>
		
		<br><br>
		
		<s:url id="urlAdicionarLancamento" value="adicionarLancamento">
			<s:param name="banda.id" value="%{banda.id}" />
		</s:url>
		<s:a href="%{#urlAdicionarLancamento}" cssClass="botao_adicionar">Adicionar Lançamento</s:a>
	</div>