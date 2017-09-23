/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.cjf.mineiro.model;

import com.google.common.base.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author diogo.araujo
 */

@Entity
@Table(schema = "mineiro")
public class Roles {
    
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String sigla;

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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

  
        
    @Override
	public String toString() {
		return Objects.toStringHelper(this)
                            .add("id", getId())
                            .add("nome", getNome())
                            .add("valor", getSigla())
                            .toString();
	}    
        
        
        
}
