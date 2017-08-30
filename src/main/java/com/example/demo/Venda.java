package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//criando tabela Venda
@Entity
@Table(name="vendas")
public class Venda{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_venda")
	@SequenceGenerator(name="seq_venda", sequenceName="seq_venda", initialValue = 1, allocationSize = 1)
	private int id;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "venda_id")
	private List<ItemDeVenda> itensDeVenda;

	//gets e sets
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ItemDeVenda> getItensDeVenda() {
		return itensDeVenda;
	}

	public void setItensDeVenda(List<ItemDeVenda> itensDeVenda) {
		this.itensDeVenda = itensDeVenda;
	}
	
	
}
