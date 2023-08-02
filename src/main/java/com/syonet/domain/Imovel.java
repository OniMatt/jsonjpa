package com.syonet.domain;

import javax.persistence.*;

@Entity
@Table( name = "imovel" )
public class Imovel {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column( name = "descricao" )
    private String descricao;

    @Column( name = "valor_aluguel" )
    private double valorAluguel;

    @ManyToOne
    @JoinColumn( name = "id_condominio" )
    private Condominio condominio;

    public Imovel( String descricao, double valorAluguel, Condominio condominio ) {
        this.descricao = descricao;
        this.valorAluguel = valorAluguel;
        this.condominio = condominio;
    }

    public Imovel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao( String descricao ) {
        this.descricao = descricao;
    }

    public double getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel( double valorAluguel ) {
        this.valorAluguel = valorAluguel;
    }
}
