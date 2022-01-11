package br.com.jpa.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
		return this.entityManager.createNamedQuery("Produto.produtosPorCategoria", Produto.class)
				.setParameter("nomeCategoria", nomeCategoria)
				.getResultList();
	}
	
	public BigDecimal buscaPrecoDoProdutoPorNome(String nome) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		return this.entityManager.createQuery(jpql, BigDecimal.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
	
	public List<Produto> buscaPorParametrosComCriteria(String nome, BigDecimal preco,
			LocalDate dataCadastro) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Produto> query = criteriaBuilder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);
		
		Predicate filtros = criteriaBuilder.and();
		if(Objects.nonNull(nome) && !nome.trim().isEmpty()) {
			filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("nome"), nome));
		}
		if(Objects.nonNull(preco)) {
			filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("preco"), preco));
		}
		if(Objects.nonNull(dataCadastro)) {
			filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("dataCadastro"), dataCadastro));
		}
		
		query.where(filtros);
		return this.entityManager.createQuery(query)
				.getResultList();
	}
	
}
