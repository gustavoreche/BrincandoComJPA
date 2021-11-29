package br.com.jpa.loja.dao;

import javax.persistence.EntityManager;

import br.com.jpa.loja.modelo.Cliente;

public class ClienteDAO {
	
	private EntityManager entityManager;

	public ClienteDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastra(Cliente cliente) {
		this.entityManager.persist(cliente);
	}
	
}
