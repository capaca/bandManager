<%@taglib prefix="s" uri="/struts-tags"%>

<SCRIPT type="text/javascript">
	$(document).ready(function() { 
	    $("#tabelaLancamentos").tablesorter({ 
	        headers: { 
		    	2: { 
		            sorter: false 
		        }, 
				3: { 
	                sorter: false 
	            }, 
	            4: { 
	                sorter: false 
	            } 
	        } 
	    }); 
	});

	$(function() {
		$("#tituloLancamentos").click(function() {
			if($("#lancamentos").css("display") == "none"){
				$("#lancamentos").slideDown();
			}
			else{
				$("#lancamentos").slideUp();
			}
		});
	});
</SCRIPT>

<div id="tituloLancamentos" class="barra">Lancamentos</div>

<div id="lancamentos" class="sub_conteudo">
	<s:if test="banda.lancamentos.size() > 0">
	<table id="tabelaLancamentos" class="listagem" border="0" width="80%" align="center">
		<thead>
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
		</thead>
		<tbody>
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
		</tbody>
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