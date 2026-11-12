package br.com.senai.deliveryavaliacao.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Informe o nome do produto")
    private String nome;

    @Column(precision = 10, scale = 2)
    @NotNull(message = "Informe o preço")
    @Min(value = 0, message = "O preço deve ser maior que zero")
    private BigDecimal preco;

    @ManyToOne(optional = false)
    @JoinColumn(name = "restaurante_id", nullable = false)
    @NotNull(message = "Informe o restaurante")
    private Restaurante restaurante;

    public Produto(String nome, BigDecimal preco, Restaurante restaurante) {
        this.nome = nome;
        this.preco = preco;
        this.restaurante = restaurante;
    }

    public Produto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}
