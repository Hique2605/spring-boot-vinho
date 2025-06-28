package com.example.lucasappvinho.api.model;

import java.io.Serializable;

public class Vinho implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSafra() { return safra; }
    public void setSafra(String safra) { this.safra = safra; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getUva() { return uva; }
    public void setUva(String uva) { this.uva = uva; }

    public String getTeorAlcoolico() { return teorAlcoolico; }
    public void setTeorAlcoolico(String teorAlcoolico) { this.teorAlcoolico = teorAlcoolico; }

    public String getVolume() { return volume; }
    public void setVolume(String volume) { this.volume = volume; }

    public String getNotasDegustacao() { return notasDegustacao; }
    public void setNotasDegustacao(String notasDegustacao) { this.notasDegustacao = notasDegustacao; }

    public String getHarmonizacao() { return harmonizacao; }
    public void setHarmonizacao(String harmonizacao) { this.harmonizacao = harmonizacao; }

    public Double getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(Double precoUnitario) { this.precoUnitario = precoUnitario; }

    public String getImgUrl() { return imgUrl; }
    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }

    public Double getQuantidade() { return quantidade; }
    public void setQuantidade(Double quantidade) { this.quantidade = quantidade; }

    public Boolean getEmEstoque() { return emEstoque; }
    public void setEmEstoque(Boolean emEstoque) { this.emEstoque = emEstoque; }

}
