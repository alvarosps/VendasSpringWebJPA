package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//tabela com nome ItensDeVenda
@Entity
@Table(name="ItensDeVenda")
public class ItemDeVenda{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_itemdevenda")
	@SequenceGenerator(name="seq_itemdevenda", sequenceName="seq_itemdevenda", initialValue = 1, allocationSize = 1)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Produto produto;
	private int quantidade;
	
	//get e set dos atributos
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	//calculando subtotal dos produtos
	public double getSubTotal() {
		return produto.getPreco() * quantidade;
	}
	
}
