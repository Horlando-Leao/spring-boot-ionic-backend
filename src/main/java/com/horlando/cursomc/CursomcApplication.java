package com.horlando.cursomc;

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
import com.horlando.cursomc.domain.Produto;
import com.horlando.cursomc.domain.enums.TipoCliente;
import com.horlando.cursomc.repositories.CategoriaRepository;
import com.horlando.cursomc.repositories.CidadeRepository;
import com.horlando.cursomc.repositories.ClienteRepository;
import com.horlando.cursomc.repositories.EnderecoRepository;
import com.horlando.cursomc.repositories.EstadoRepository;
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
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//estancias os objetos
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		//estancias os objetos
		Produto p1 = new Produto(null, "Computador", 5000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 800.00);
		
		//relaciona os objetos Categoria a  Produtos
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		//relaciona os objetos Produto a Categorias 
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		//salvando no banco
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		//estancias os objetos
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		//estancias os objetos
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		//relaciona objetos Cidade a estado
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		//salvando no banco
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363355", "336458744"));
		
		Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto303", "Jardim", "382645751", cli1, c1);
		Endereco e2 = new Endereco(null, "av matos", "105", "sala 800", "centro", "69875751", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		
	}
}
