package br.com.jpa.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;

	private LocalDate data = LocalDate.now();

	@ManyToOne(fetch = FetchType.LAZY) //um Pedido esta vinculado a um Cliente. Um Cliente pode ter muitos Pedidos
	/*
	 Quando existe o relacionamento @ManyToOne, por padrão o JPA adota o seu carregamento com EAGER(ansioso), ou seja,
	 ele vai realizar o JOIN com o Cliente mesmo sem precisar usar.	
	 

	 Já quando existe o relacionamento @OneToMany, por padrão o JPA adota o seu carregamento com LAZY(preguiçoso), ou seja,
	 ele vai realizar o JOIN com o Cliente somente se for acessado.	 
	 */
	private Cliente cliente;
	
/*
	@ManyToMany //um Pedido esta vinculado a muitos Produtos. Um Produto pode estar em muitos Pedidos
	@JoinTable
	private List<Produto> produtos;
* Os passos acima seria caso fosse uma tabela apenas com as chaves primarias de Pedido e Produto. Porem como queremos
* adicionar mais campos a essa tabela, sera criado uma nova entidade. É o que chamados de RELACIONAMENTO BIDIRECIONAL
 */
	
	@OneToMany(mappedBy = "pedidoDaItemPedido", cascade = CascadeType.ALL) //Eh utilizado quando o relacionamento eh de muitos para muitos, 
												//porem, vai ser criada uma tabela com mais atributos.
	private List<ItemPedido> itens = new ArrayList<>();

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pedido() {
	}
	
	// Fazer com que o ItemPedido conheca o Pedido.
	public void adicionaItem(ItemPedido item) {
		item.setPedido(this);
		this.itens.add(item);
		this.valorTotal = this.valorTotal.add(item.getValor());
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public LocalDate getData() {
		return data;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public List<ItemPedido> getItens() {
		return itens;
	}
	
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", valorTotal=" + valorTotal + ", data=" + data + ", cliente=" + cliente
				+ ", itens=" + itens + "]";
	}

}
