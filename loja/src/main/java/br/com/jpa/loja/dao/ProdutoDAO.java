package br.com.jpa.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.jpa.loja.modelo.Produto;

public class ProdutoDAO {
	
	private EntityManager entityManager;

	public ProdutoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastra(Produto produto) {
		this.entityManager.persist(produto);
	}
	
	public void atualiza(Produto produto) {
		this.entityManager.merge(produto);
	}
	
	public void deleta(Produto produto) {
		produto = this.entityManager.merge(produto);
		this.entityManager.remove(produto);
	}
	
	public Produto buscaPorId(Long id) {
		return this.entityManager.find(Produto.class, id);
	}
	
	public List<Produto> buscaTodos() {
		String jpql = "SELECT p FROM Produto p";
		return this.entityManager.createQuery(jpql, Produto.class)
				.getResultList();
	}
	
	public List<Produto> buscaPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		return this.entityManager.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public List<Produto> buscaPorNomeDaCategoria(String nomeCategoria) {
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nomeCategoria";
		return this.entityManager.createQuery(jpql, Produto.class)
				.setParameter("nomeCategoria", nomeCategoria)
				.getResultList();
	}
	
	public BigDecimal buscaPrecoDoProdutoPorNome(String nome) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		return this.entityManager.createQuery(jpql, BigDecimal.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
	
}
