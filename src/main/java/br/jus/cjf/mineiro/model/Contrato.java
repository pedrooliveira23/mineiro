package br.jus.cjf.mineiro.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.Duration;
import org.joda.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(schema = "mineiro")
public class Contrato implements Serializable{

    
    
	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false, unique = true)
        @NotEmpty
	private String numero;

	@Column(nullable = false)
	private String contratada;

	@Column(nullable = false)
	private BigDecimal valorUnitario;

	@Column(nullable = false)
	private BigDecimal quantitativo;

	@Column(nullable = false)
	private BigDecimal valorTotal;

	@JsonIgnore
	@OneToMany(mappedBy = "contrato")
	private List<Indicador> indicadores;

	@JsonIgnore
	@OneToMany(mappedBy = "contrato")
	private List<Penalidade> penalidades;

	@Column(nullable = false)
        @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
	private LocalTime inicioExpediente;

	@Column(nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
        @DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime fimExpediente;
        
        @NotEmpty
        @Transient
	private String inicioExpediente1;
        
        @NotEmpty
        @Transient
	private String fimExpediente1;
        

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getContratada() {
		return contratada;
	}

	public void setContratada(String contratada) {
		this.contratada = contratada;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getQuantitativo() {
		return quantitativo;
	}

	public void setQuantitativo(BigDecimal quantitativo) {
		this.quantitativo = quantitativo;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}

	public List<Penalidade> getPenalidades() {
		return penalidades;
	}

	public void setPenalidades(List<Penalidade> penalidades) {
		this.penalidades = penalidades;
	}

	public LocalTime getInicioExpediente() {
		return inicioExpediente;
	}

	public void setInicioExpediente(LocalTime inicioExpediente) {
		this.inicioExpediente = inicioExpediente;
	}

	public LocalTime getFimExpediente() {
		return fimExpediente;
	}

	public void setFimExpediente(LocalTime fimExpediente) {
		this.fimExpediente = fimExpediente;
	}

        
        public String getInicioExpediente1() {
		return inicioExpediente1;
	}

	public void setInicioExpediente1(String inicioExpediente) {
		this.inicioExpediente1 = inicioExpediente;
	}

	public String getFimExpediente1() {
		return fimExpediente1;
	}

	public void setFimExpediente1(String fimExpediente) {
		this.fimExpediente1 = fimExpediente;
	}
        
        
	public int getDuracaoHorasExpediente() {

		int duracao = (int) new Duration(getInicioExpediente()
				.toDateTimeToday(), getFimExpediente().toDateTimeToday())
				.getStandardHours();
		return duracao;

	}

	public int getDuracaoSegundosExpediente() {

		return (int) new Duration(getInicioExpediente().toDateTimeToday(),
				getFimExpediente().toDateTimeToday()).getStandardSeconds();

	}

}
