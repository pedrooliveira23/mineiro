package br.jus.cjf.mineiro.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import br.jus.cjf.mineiro.web.formatter.DiaFormat;

import com.google.common.base.Objects;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.OrderBy;

@Entity
@Table(schema = "mineiro")
public class TempoRolesDemanda {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne( fetch=FetchType.LAZY)
    private Demanda demanda;
    
    

    @ManyToOne( fetch=FetchType.LAZY)
    private Roles role;

    @Column(nullable = false)
    private int tempo;



    public Integer getId() {
            return id;
    }

    public void setId(Integer id) {
            this.id = id;
    }

    public Demanda getDemanda() {
            return demanda;
    }

    public void setDemanda(Demanda demanda) {
            this.demanda = demanda;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }


    @Override
    public String toString() {
            return Objects.toStringHelper(this).add("id", getId()).toString();
    }
}
