package br.jus.cjf.mineiro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.google.common.base.Objects;
import java.math.BigDecimal;

@Entity
@Table(schema = "mineiro")
public class TipoDemanda {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Index(name = "idx_redmineTrackerId")	
    @Column(nullable = false, unique = true)
    private Integer redmineTrackerId;

    @Column(nullable = false)
    private BigDecimal deflator;

    public BigDecimal getDeflator() {
        return deflator;
    }

    public void setDeflator(BigDecimal deflator) {
        this.deflator = deflator;
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

    public Integer getRedmineTrackerId() {
            return redmineTrackerId;
    }

    public void setRedmineTrackerId(Integer redmineTrackerId) {
            this.redmineTrackerId = redmineTrackerId;
    }

    @Override
    public String toString() {
            return Objects.toStringHelper(this).add("id", getId())
                            .add("nome", getNome()).toString();
    }

}
