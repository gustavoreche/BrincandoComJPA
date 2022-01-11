package br.com.jpa.loja.modelo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private DadosPessoais dadosPessoais;

	public Cliente(String nome, String cpf) {
		this.dadosPessoais = new DadosPessoais(nome, cpf);
	}
	
	public Cliente() {
	}
	
	public Long getId() {
		return id;
	}
	
	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}
	
	@Override
	public String toString() {
		return "Cliente: id=" + this.id + " || nome=" + this.dadosPessoais.getNome() 
			+ " || cpf=" + this.dadosPessoais.getCpf();
	}

}
