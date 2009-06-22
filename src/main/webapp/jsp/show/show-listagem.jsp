<%@taglib prefix="s" uri="/struts-tags"%>

<SCRIPT type="text/javascript">
	$(document).ready(function() { 
	    $("#tabelaShows").tablesorter({ 
	    	dateFormat: "uk",
	        headers: { 
    			4: { 
	                sorter: false 
	            }, 
	            5: { 
	                sorter: false 
	            } 
	        } 
	    }); 
	});

	isShowAberto = true;

	$(function() {
		$("#tituloShows").click(function() {
			if(isShowAberto){
				$("#shows").hide('blind');
				isShowAberto = false;
			}
			else{
				$("#shows").show('blind');
				isShowAberto = true;
			}
		});
	});
</SCRIPT>

<h2 id="tituloShows">
	<span class="tituloClick">Shows</span>
</h2>
	
<div id="shows" class="sub_conteudo">
	<s:if test="banda.shows.size() > 0">
	<table id="tabelaShows" class="listagem" border="0" width="80%" align="center">
		<thead>
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
		</thead>
		<tbody>
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
		</tbody>
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