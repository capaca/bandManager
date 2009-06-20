<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>

	<s:url id="urlEditarBanda" action="editarBanda">
		<s:param name="banda.id" value="%{banda.id}" />
	</s:url>
	
	<p class="migalha">
		<s:a href="%{#urlEditarBanda}">Banda</s:a> :: Lancamento
	</p>
	
	<h1>Editar 
		<s:if test="lancamento!=null && lancamento.nome!=''">
			<s:property value="lancamento.nome"/>
		</s:if>
		<s:else>
			Lançamento
		</s:else>
	</h1>
	
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
		
		<table class="form" border="0">
			<tr>
				<th>
					Nome:
				</th>
				<td>
					<s:textfield name="lancamento.nome" theme="simple"/>
				</td>
				
				<th>
					Ano:
				</th>
				<td>
					<s:textfield name="lancamento.ano" theme="simple"/>
				</td>
			</tr>
			<tr>
				<th valign="top">
					Informações:
				</th>
				<td colspan="3">
					<s:textarea name="lancamento.informacoes" cols="58" rows="8" theme="simple"/>
				</td>				
			</tr>
			<tr>
				<td>
				</td>
				<td colspan="3">
					<s:submit value="Salvar" theme="simple"/>
				</td>
			</tr>
		</table>
	</s:form>
	
	<s:if test="%{lancamento.id>0}">
		<s:form action="adicionarCapa" method="post"enctype="multipart/form-data">
			<s:hidden name="banda.id" value="%{banda.id}" />
			<s:hidden name="lancamento.id" value="%{lancamento.id}" />
			
			<table class="form">
				<tr>
					<th>
						Capa:
					</th>
					<td>
						<s:file  name="capa" theme="simple"/>
						<s:submit value="Upload" theme="simple"/>
					</td>
				</tr>
				<tr>
					<td>
					</td>
					<s:if test="%{lancamento.capa != null}">
						<td>
							<s:url id="urlExcluirCapa" action="excluirCapa">
								<s:param value="%{lancamento.id}" name="lancamento.id"/>
							</s:url>
							<a href="${lancamento.capa}" target="_blank">Ver Capa</a> | 
							<s:a href="%{#urlExcluirCapa}">Excluir Capa</s:a>	
						</td>
					</s:if>
					<s:else>
						<td>
							<em>Nenhuma Capa adicionada.</em>	
						</td>
					</s:else>
				</tr>
			</table>
		</s:form>
	</s:if>
	
	<!-- INCLUI A LISTAGEM DE MUSICAS -->
	<jsp:include page="../musica/musica-listagem.jsp" />
	
	<br><br>
		
	<s:a href="%{#urlEditarBanda}" cssClass="botao_voltar">Voltar</s:a>
