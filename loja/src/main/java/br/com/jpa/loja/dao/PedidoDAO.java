package br.com.jpa.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.jpa.loja.modelo.Pedido;
import br.com.jpa.loja.vo.RelatorioDeVendasVO;

public class PedidoDAO {
	
	private EntityManager entityManager;

	public PedidoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastra(Pedido pedido) {
		this.entityManager.persist(pedido);
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return entityManager.createQuery(jpql, BigDecimal.class)
				.getSingleResult();
	}
	
	public List<RelatorioDeVendasVO> relatorioDeVendas() {
		String jpql = "SELECT new br.com.jpa.loja.vo.RelatorioDeVendasVO("
				+ "produto.nome, SUM(item.quantidade), MAX(pedido.data)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";
		
		return entityManager.createQuery(jpql, RelatorioDeVendasVO.class)
				.getResultList();
	}
	
	//JOIN FETCH serve para trazer outro Objeto que esta configurado como LAZY e o EntityManager esta fechado.
	public Pedido buscaPedidoComCliente(Long id) {
		return entityManager.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
}
