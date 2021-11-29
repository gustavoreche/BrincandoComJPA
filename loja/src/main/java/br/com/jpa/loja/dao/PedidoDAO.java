package br.com.jpa.loja.dao;

import javax.persistence.EntityManager;

import br.com.jpa.loja.modelo.Pedido;

public class PedidoDAO {
	
	private EntityManager entityManager;

	public PedidoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastra(Pedido pedido) {
		this.entityManager.persist(pedido);
	}
	
}
