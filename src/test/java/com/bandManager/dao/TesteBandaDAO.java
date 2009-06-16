package com.bandManager.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.bandManager.Teste;
import com.bandManager.domain.Banda;
import com.bandManager.domain.Lancamento;
import com.bandManager.domain.Pais;
import com.bandManager.exception.ObjetoNaoEncontradoException;

public class TesteBandaDAO extends Teste {
	
	private Pais pais;
	private String textoMuitoLongo = "" +
			"Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
			"Maecenas a dui sit amet massa ultricies porttitor posuere ut " +
			"lorem. Sed dignissim massa facilisis ante eleifend bibendum. Cum " +
			"sociis natoque penatibus et magnis dis parturient montes, nascetur " +
			"ridiculus mus. Donec lacinia, nunc sit amet feugiat bibendum, risus " +
			"metus eleifend sem, eget vulputate felis nisl eget nibh. Praesent " +
			"semper ante at elit accumsan quis interdum sem consectetur. " +
			"Suspendisse commodo venenatis erat, in venenatis dolor convallis non. " +
			"Mauris enim velit, ornare ut rutrum sit amet, varius sed quam. " +
			"Sed ac felis nisi, at euismod tortor. Quisque tincidunt faucibus nibh, " +
			"lobortis sagittis leo aliquam vitae. Vivamus consequat placerat quam, in " +
			"lobortis turpis dictum sed. In iaculis metus eu lacus dapibus eget tincidunt " +
			"massa molestie. Aenean vel nunc nulla. Maecenas condimentum varius gravida. " +
			"Nulla facilisi. ";
	
	@Before
	public void preparacao(){
		
		this.pais = utilCriarPais("Brasil");
	}
	
	@Test
	public void salvar(){

		//Instancia a banda e seta os atributos
		Banda banda = new Banda(BANDA_NOME, this.pais, BANDA_CIDADE, BANDA_ANO_FORMACAO, BANDA_RELEASE);
		
		//Salva a banda
		Banda bandaSalva = super.getBandaDAO().salvar(banda);
		
		//Intancia e seta os atributos dos lancamentos
		Lancamento lancamento1 = new Lancamento(LANCAMENTO_NOME,LANCAMENTO_ANO,LANCAMENTO_INFORMACOES);
		lancamento1.setBanda(banda);

		Lancamento lancamento2 = new Lancamento(LANCAMENTO_NOME,LANCAMENTO_ANO,LANCAMENTO_INFORMACOES);
		lancamento2.setBanda(banda);
		
		//Salva os lancamentos
		super.getLancamentoDAO().salvar(lancamento1);
		super.getLancamentoDAO().salvar(lancamento2);
		
		//Cria um set com os lancamentos e seta na banda
		Set<Lancamento> lancamentos = new HashSet<Lancamento>();
		lancamentos.add(lancamento1);
		lancamentos.add(lancamento2);
		
		//banda.setLancamentos(lancamentos);
		
		//Salva a banda novamente
		bandaSalva = super.getBandaDAO().salvar(banda);

		//Verifica se a banda foi salva corretamente
		this.utilVerificarAtributos(banda, bandaSalva);
		
	}
	
	@Test
	public void recuperar() throws ObjetoNaoEncontradoException{
		//Instancia a banda e seta os atributos
		Banda banda = new Banda(BANDA_NOME, BANDA_CIDADE, this.pais, BANDA_ANO_FORMACAO, BANDA_RELEASE);
		
		//Salva a banda
		super.getBandaDAO().salvar(banda);
		
		//Intancia e seta os atributos dos lancamentos
		Lancamento lancamento1 = new Lancamento(LANCAMENTO_NOME,LANCAMENTO_ANO,LANCAMENTO_INFORMACOES);
		lancamento1.setBanda(banda);

		Lancamento lancamento2 = new Lancamento(LANCAMENTO_NOME,LANCAMENTO_ANO,LANCAMENTO_INFORMACOES);
		lancamento2.setBanda(banda);
		
		//Salva os lancamentos
		super.getLancamentoDAO().salvar(lancamento1);
		super.getLancamentoDAO().salvar(lancamento2);
		
		//Recupera a banda
		Banda bandaRecuperada = super.getBandaDAO().recuperar(banda.getId());
		
		//Verifica se a banda foi recuperada corretamente
		this.utilVerificarAtributos(banda, bandaRecuperada);
	}
	
	@Test
	public void salvarBandaComReleaseLongo(){
		//Cria a banda e seta o texto longo
		Banda banda = utilCriarBanda();
		banda.setRelease(textoMuitoLongo);
		
		//Salva a banda novamente
		Banda bandaSalva = super.getBandaDAO().salvar(banda);

		//Verifica se a banda foi salva corretamente
		this.utilVerificarAtributos(banda, bandaSalva);
	}
	
	@Test
	public void recuperarTodas(){
		Banda destruction = utilCriarBanda("Destruction");
		Banda sodom =utilCriarBanda("Sodom");
		Banda kreator =utilCriarBanda("Kreator");
		
		List<Banda> bandas = new ArrayList<Banda>();
		bandas.add(destruction);
		bandas.add(sodom);
		bandas.add(kreator);
		
		List<Banda> bandasRecuperadas = super.getBandaDAO().recuperarTodas();
		
		assertEquals(3, bandas.size());
		utilVerificarAtributos(bandas, bandasRecuperadas);
	}
	
}
