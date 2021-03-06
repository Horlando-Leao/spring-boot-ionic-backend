package com.horlando.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.horlando.cursomc.domain.Categoria;
import com.horlando.cursomc.domain.Cidade;
import com.horlando.cursomc.domain.Cliente;
import com.horlando.cursomc.domain.Endereco;
import com.horlando.cursomc.domain.Estado;
import com.horlando.cursomc.domain.Pagamento;
import com.horlando.cursomc.domain.PagamentoComBoleto;
import com.horlando.cursomc.domain.PagamentoComCartao;
import com.horlando.cursomc.domain.Pedido;
import com.horlando.cursomc.domain.Produto;
import com.horlando.cursomc.domain.enums.EstadoPagamento;
import com.horlando.cursomc.domain.enums.TipoCliente;
import com.horlando.cursomc.repositories.CategoriaRepository;
import com.horlando.cursomc.repositories.CidadeRepository;
import com.horlando.cursomc.repositories.ClienteRepository;
import com.horlando.cursomc.repositories.EnderecoRepository;
import com.horlando.cursomc.repositories.EstadoRepository;
import com.horlando.cursomc.repositories.PagamentoRepository;
import com.horlando.cursomc.repositories.PedidoRepository;
import com.horlando.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	//automaticamente instanciada, pelo mecanismo de injeção de dependecias
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "informatica");//estancias os objetos
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 5000.00);//estancias os objetos
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 800.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));//relaciona os objetos Categoria a  Produtos
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));//relaciona os objetos Produto a Categorias 
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));//salvando no banco
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");//estancias os objetos
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);//estancias os objetos
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));//relaciona objetos Cidade a estado
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));//salvando no banco
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);//estancias os objetos
		
		cli1.getTelefones().addAll(Arrays.asList("27363355", "336458744"));//relaciona objetos cliente e telefone
		
		Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto303", "Jardim", "382645751", cli1, c1);//estancias os objetos
		Endereco e2 = new Endereco(null, "av matos", "105", "sala 800", "centro", "69875751", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));//relaciona objetos cliente e enderecos
		
		clienteRepository.saveAll(Arrays.asList(cli1));//salvando no banco
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");//objeto auxiliar
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);//estancias os objetos
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);//estancias os objetos
		ped1.setPagamento(pagto1);//relacionando o pedido com o pagamento
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		//os pedidos do cliente cli1 são ped1 e ped2
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));//salvando no banco
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		
		
		
		
		
		
	}
}
