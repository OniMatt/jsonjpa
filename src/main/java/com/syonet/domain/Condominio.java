package com.syonet.domain;

import java.util.List;

import javax.persistence.*;

@Entity
@Table( name = "condominio" )
public class Condominio {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column( name = "nome" )
    private String nome;


    @OneToMany( mappedBy = "condominio", cascade = CascadeType.ALL )
    private List< Imovel > imoveis;
    
    public Condominio() {
    }
    
    public Condominio( String nome, List< Imovel > imoveis ) {
        this.nome = nome;
        this.imoveis = imoveis;
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }

    public List< Imovel > getImoveis() {
        return imoveis;
    }

    public void setImoveis( List< Imovel > imoveis ) {
        this.imoveis = imoveis;
    }

    @Override
    public String toString() {
        return "Condominio [id=" + id + ", nome=" + nome + ", imoveis=" + imoveis + "]";
    }

}
