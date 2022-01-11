package br.com.jpa.loja.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;
	
	private int quantidade;

	@ManyToOne(fetch = FetchType.LAZY) //um ItemPedido esta vinculado a um Pedido. Um Pedido pode ter muitos ItemPedido
	private Pedido pedidoDaItemPedido;
	
	@ManyToOne(fetch = FetchType.LAZY) //um ItemPedido esta vinculado a um Produto. Um Pedido pode ter muitos ItemPedido
	private Produto produto;

	public ItemPedido(int quantidade, Pedido pedidoDaItemPedido, Produto produto) {
		this.quantidade = quantidade;
		this.pedidoDaItemPedido = pedidoDaItemPedido;
		this.produto = produto;
		this.precoUnitario = produto.getPreco();
	}

	public ItemPedido() {
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Pedido getPedidoDaItemPedido() {
		return pedidoDaItemPedido;
	}
	
	public void setPedido(Pedido pedidoDaItemPedido) {
		this.pedidoDaItemPedido = pedidoDaItemPedido;
	}

	public Produto getProduto() {
		return produto;
	}
	
	@Override
	public String toString() {
		return "\nItemPedido: id=" + this.id + " || precoUnitario=" + this.precoUnitario + " || quantidade=" + this.quantidade
				+ " || produto=" + this.produto;
	}

	public BigDecimal getValor() {
		return this.precoUnitario.multiply(new BigDecimal(this.quantidade));
	}

}
