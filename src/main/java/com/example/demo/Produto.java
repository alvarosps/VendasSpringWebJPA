package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//tabela com nome de produtos
@Entity
@Table(name="produtos")
public class Produto{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_produto")
	@SequenceGenerator(name="seq_produto", sequenceName="seq_produto", initialValue=1, allocationSize=1)
	private int id;
	
	//o campo nome deverá ter no mínimo 2 caracteres ou nao pode ficar vazio
	@NotNull
	@Size(min=2, max=30)
	private String nome;
	
	//o campo preco nao pode ficar vazio nem ter valor menor que 1
	@NotNull
	@Min(1)
	private double preco;
	
	//construtor vazio da classe produto
	public Produto() {}
	
	//construtor inicializando os valores dos atributos
	public Produto(int id, String nome, double preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	//metodos get e set dos atributos(propriedades)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
}
