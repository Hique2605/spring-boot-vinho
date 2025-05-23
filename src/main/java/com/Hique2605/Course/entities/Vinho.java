package com.Hique2605.Course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_vinho")
public class Vinho implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String safra;
	private String tipo;
	private String uva;
	private String teorAlcoolico;
	private String volume;
	private String notasDegustacao;
	private String harmonizacao;
	private Double precoUnitario;
	private String imgUrl;
	private Double quantidade;
	private Boolean emEstoque;

	@OneToMany(mappedBy = "id.vinho")
	private Set<OrderItem> items = new HashSet<>();


	public Vinho(){

	}

	public Vinho(Long id, String nome, String safra, String tipo, String uva, String teorAlcoolico, String volume, String notasDegustacao, String harmonizacao, Double precoUnitario, String imgUrl, Double quantidade, Boolean emEstoque) {
		this.id = id;
		this.nome = nome;
		this.safra = safra;
		this.tipo = tipo;
		this.uva = uva;
		this.teorAlcoolico = teorAlcoolico;
		this.volume = volume;
		this.notasDegustacao = notasDegustacao;
		this.harmonizacao = harmonizacao;
		this.precoUnitario = precoUnitario;
		this.imgUrl = imgUrl;
		this.quantidade = quantidade;
		this.emEstoque = emEstoque;
	}

	public Boolean getEmEstoque() {
		return emEstoque;
	}

	public void setEmEstoque(Boolean emEstoque) {
		this.emEstoque = emEstoque;
	}

	public String getHarmonizacao() {
		return harmonizacao;
	}

	public void setHarmonizacao(String harmonizacao) {
		this.harmonizacao = harmonizacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNotasDegustacao() {
		return notasDegustacao;
	}

	public void setNotasDegustacao(String notasDegustacao) {
		this.notasDegustacao = notasDegustacao;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public String getSafra() {
		return safra;
	}

	public void setSafra(String safra) {
		this.safra = safra;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTeorAlcoolico() {
		return teorAlcoolico;
	}

	public void setTeorAlcoolico(String teorAlcoolico) {
		this.teorAlcoolico = teorAlcoolico;
	}

	public String getUva() {
		return uva;
	}

	public void setUva(String uva) {
		this.uva = uva;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}
	@JsonIgnore
	public Set<Order> getOrders(){
		Set<Order> set = new HashSet<>();
		for(OrderItem x : items) {
			set.add(x.getOrder());
		}
		return set;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Vinho vinho = (Vinho) o;
		return Objects.equals(id, vinho.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}


