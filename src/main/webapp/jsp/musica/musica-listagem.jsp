<%@taglib prefix="s" uri="/struts-tags" %>

<h2>Músicas</h2>

<div class="sub_conteudo">
	<s:if test="%{lancamento.id>0}">
		
		<s:if test="lancamento.musicas.size()<=0">
			<em>Nenhuma música</em>
		</s:if>
		<s:else>
			<table class="listagem" border="0" width="80%" align="center">
				<tr>
					<th>
						Nome
					</th>
					<th>
						Número
					</th>
					<th>
						Letra
					</th>
					<th>
						Editar
					</th>
					<th>
						Excluir
					</th>
				</tr>
				<s:iterator value="lancamento.musicas">
				<tr>
					<td>
						<s:property value="nome"/>
					</td>
					
					<td>
						<s:property value="numero"/>
					</td>
					
					<td>
						<s:property value="letra"/>
					</td>
					
					<td>
						<s:url id="urlEditarMusica" action="editarMusica">
							<s:param name="banda.id" value="%{lancamento.banda.id}"/>
							<s:param name="lancamento.id" value="%{lancamento.id}"/>
							<s:param name="musica.id" value="%{id}"/>
						</s:url>
						<s:a href="%{#urlEditarMusica}">Editar</s:a>
					</td>
					
					<td>
						<s:url id="urlExcluirMusica" action="excluirMusica">
							<s:param name="banda.id" value="%{lancamento.banda.id}"/>
							<s:param name="lancamento.id" value="%{lancamento.id}"/>
							<s:param name="musica.id" value="%{id}"/>
						</s:url>
						<s:a href="%{#urlExcluirMusica}">Excluir</s:a>
					</td>
				</tr>
				</s:iterator>
			</table>
		</s:else>
	</s:if>
	
	<br><br>
	
	<s:url id="urlAdicionarMusica" value="adicionarMusica">
		<s:param name="lancamento.id" value="lancamento.id"/>
		<s:param name="banda.id" value="banda.id"/>
	</s:url>
	<s:a href="%{#urlAdicionarMusica}" cssClass="botao_adicionar" >Adicionar Música</s:a>
</div>