package br.com.jpa.loja;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.jpa.loja.dao.CategoriaDAO;
import br.com.jpa.loja.dao.ClienteDAO;
import br.com.jpa.loja.dao.PedidoDAO;
import br.com.jpa.loja.dao.ProdutoDAO;
import br.com.jpa.loja.modelo.Categoria;
import br.com.jpa.loja.modelo.Cliente;
import br.com.jpa.loja.modelo.ItemPedido;
import br.com.jpa.loja.modelo.Pedido;
import br.com.jpa.loja.modelo.Produto;
import br.com.jpa.loja.util.JPAUtil;

public class Main {
	
	public static void main(String[] args) {
		
		cadastraProduto();
		cadastraPedido();
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
		
		entityManager.getTransaction().begin();
		
		Produto produtoPersistido = produtoDAO.buscaPorId(1L);
		System.out.println(produtoPersistido);
		entityManager.getTransaction().commit();
		
		entityManager.close();		
	}

	private static void cadastraProduto() {
		Categoria celulares = new Categoria("CELULARES");
		
		Produto produto = new Produto("Moto G8", 
				"Celular da Motorola", 
				new BigDecimal("1000"), 
				celulares);
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
		CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);
		
		entityManager.getTransaction().begin();
		
		categoriaDAO.cadastra(celulares);
		produtoDAO.cadastra(produto);
		entityManager.getTransaction().commit();
		
		entityManager.close();
	}
	
	private static void cadastraPedido() {
		EntityManager entityManager = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);

		Produto produto = produtoDAO.buscaPorId(1L);

		entityManager.getTransaction().begin();
		
		Cliente cliente = new Cliente("Gustavo", "123456");
		Pedido pedido = new Pedido(cliente);
		pedido.adicionaItem(new ItemPedido(10, pedido, produto));
		
		ClienteDAO clienteDAO = new ClienteDAO(entityManager);
		clienteDAO.cadastra(cliente);
		PedidoDAO pedidoDAO = new PedidoDAO(entityManager);
		pedidoDAO.cadastra(pedido);
		System.out.println(pedido);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
