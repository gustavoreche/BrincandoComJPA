package br.com.jpa.loja.dao;

import javax.persistence.EntityManager;

import br.com.jpa.loja.modelo.Categoria;

public class CategoriaDAO {
	
	private EntityManager entityManager;

	public CategoriaDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastra(Categoria categoria) {
		this.entityManager.persist(categoria);
	}
	
	public void atualiza(Categoria categoria) {
		this.entityManager.merge(categoria);
	}
	
	public void deleta(Categoria categoria) {
		categoria = this.entityManager.merge(categoria);
		this.entityManager.remove(categoria);
	}
	
}
