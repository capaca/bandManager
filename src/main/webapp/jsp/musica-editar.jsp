<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

	<s:url id="urlEditarLancamentos" action="editarLancamentos">
		<s:param name="banda.id" value="%{banda.id}" />
	</s:url>
	
	<s:url id="urlEditarLancamento" action="editarLancamento">
		<s:param name="lancamento.id" value="%{lancamento.id}" />
		<s:param name="banda.id" value="%{banda.id}" />
	</s:url>
	
	<p class="migalha">
		<s:a href="%{#urlEditarLancamentos}">Banda</s:a> :: 
		<s:a href="%{#urlEditarLancamento}">Lancamento</s:a> :: Música 
	</p>
	
	<h1>Editar 
		<s:if test="%{musica!=null && musica.nome!=''}">
			<s:property value="musica.nome"/>
		</s:if>
		<s:else>
			Música
		</s:else>
	</h1>
	
	<s:url id="urlSalvarMusica" value="salvarMusica.action" escapeAmp="false">
		<s:param name="lancamento.id" value="%{lancamento.id}" />
		<s:param name="banda.id" value="%{banda.id}" />
	</s:url>
	
	<s:form action="%{#urlSalvarMusica}" method="post">
		<s:hidden name="musica.lancamento.id" value="%{lancamento.id}"/>
		
		<s:if test="%{musica.id > 0}">
			<s:hidden name="musica.id" value="%{musica.id}"/>
		</s:if>
		<s:else>
			<s:hidden name="musica.id" value="0"/>
		</s:else>
		
		<table class="form" border="0">
			<tr>
				<th>
					Nome:
				</th>
				<td>
					<s:textfield name="musica.nome" theme="simple"/>
				</td>
				
				<th>
					Número:
				</th>
				<td>
					<s:textfield name="musica.numero" size="2" theme="simple"/>
				</td>
			</tr>
			<tr>
				<th valign="top">
					Letra:
				</th>
				<td colspan="3">
					<s:textarea name="musica.letra" rows="10" cols="42" theme="simple"/>
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
	
	<s:if test="musica!= null && musica.id>0">
		<s:form action="adicionarArquivoAudio.action?musica.id=%{musica.id}" method="post" enctype="multipart/form-data">
			<s:hidden name="lancamento.id" value="%{lancamento.id}" />
			<s:hidden name="banda.id" value="%{banda.id}" />
			<s:hidden name="musica.id" value="%{musica.id}" />
			<table class="form" border="0">
				<tr>
					<th>
						Arquivo de áudio:
					</th>
					<td>
						<s:file  name="arquivo" label="Arquivo de Música" theme="simple"/>
						<s:submit value="Upload" theme="simple"/>
					</td>
				</tr>
				<tr>
					<td>
					</td>
					<td>
						<s:if test="%{musica.arquivoAudio!=null}">
								<s:url id="urlExcluirArquivoMusica" action="excluirArquivoAudio">
									<s:param value="%{musica.id}" name="musica.id"/>
								</s:url>
		
								<a href="${musica.arquivoAudio}">Baixar Música</a> | <s:a href="%{#urlExcluirArquivoMusica}">Excluir Musica</s:a>	
							
						</s:if>
						<s:else>
							<em>Nenhum arquivo de música adicionado.</em>
						</s:else>
					</td>
				</tr>
			</table>
		</s:form>
	</s:if>
	
	<br><br>
	<s:a href="%{#urlEditarLancamento}" cssClass="botao_voltar">Voltar</s:a>
