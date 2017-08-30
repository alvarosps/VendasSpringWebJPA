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

@Entity
@Table(name="ItensDeVenda")
public class ItemDeVenda {
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ITEMVENDA")
    @SequenceGenerator(name="SEQ_ITEMVENDA", sequenceName="SEQ_ITEMVENDA", initialValue = 1, allocationSize = 1)
	private int id;
	@ManyToOne(fetch=FetchType.EAGER)
	private Produto produto;
	private int quantidade;
	
	public int getId() {
		return id;
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
	public double getSubTotal() {
		return produto.getPreco() * quantidade;
	}
	
}
