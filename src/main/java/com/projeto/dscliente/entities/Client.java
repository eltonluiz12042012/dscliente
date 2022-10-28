package com.projeto.dscliente.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_client")
public class Client  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	private Double income;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant birtDate;
	private Integer children;
	
	
	public Client() {
		
	}
	
	public Client(Long id, String nome, String cpf, Double income, Instant birtDate, Integer children) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.income = income;
		this.birtDate = birtDate;
		this.children = children;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
	public Instant getBirtDate() {
		return birtDate;
	}
	public void setBirtDate(Instant birtDate) {
		this.birtDate = birtDate;
	}
	public Integer getChildren() {
		return children;
	}
	public void setChildren(Integer children) {
		this.children = children;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
