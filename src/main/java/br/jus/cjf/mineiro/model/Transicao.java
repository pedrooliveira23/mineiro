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
import org.hibernate.annotations.OrderBy;

@Entity
@Table(schema = "mineiro")
public class Transicao {

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne( fetch=FetchType.EAGER)
	private Demanda demanda;
	
	@Column(nullable = true)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private DateTime dataFim;

	@Column(nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime dataInicio;

	@DiaFormat
	@Embedded
	@Column(nullable = false)
	@AttributeOverrides( { 
		@AttributeOverride(name="valor", column = @Column(name = "duracao_transicao")),
		@AttributeOverride(name="tipoDuracao", column = @Column(name = "tipoDuracao__transicao")) 
		
	})
	private Duracao duracaoTotal;

	@DiaFormat
	@Embedded
	@Column(nullable = false)
	@AttributeOverrides( { 
		@AttributeOverride(name="valor", column = @Column(name = "duracao_contandoTempo_transicao")),
		@AttributeOverride(name="tipoDuracao", column = @Column(name = "tipoDuracao_contandoTempo_transicao")) 
		
	})
	private Duracao duracaoContandoTempo;

	@Index(name = "idx_contaTempoOS")
	private Boolean contaTempoOS;

	@Index(name = "idx_contaRecusaOS")
	private Boolean contaRecusaOS;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Estado estado;

	
	@Embedded
	@Column(nullable = true)
	private Justificativa justificativa;
	
	@OneToMany(mappedBy="transicao")
	private List<Nota> notas;
	
	


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

	public DateTime getDataFim() {
		return dataFim;
	}

	public DateTime getDataInicio() {
		return dataInicio;
	}

	public Duracao getDuracaoTotal() {
		return duracaoTotal;
	}

	public Duracao getDuracaoContandoTempo() {
		return duracaoContandoTempo;
	}


	public void setDataFim(DateTime dataFim) {
		this.dataFim = dataFim;
	}

	public void setDataInicio(DateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public void setDuracaoTotal(Duracao duracaoTotal) {
		this.duracaoTotal = duracaoTotal;
	}

	public void setDuracaoContandoTempo(Duracao duracaoContandoTempo) {
		this.duracaoContandoTempo = duracaoContandoTempo;
	}

	public Boolean getContaTempoOS() {
		return contaTempoOS;
	}

	public void setContaTempoOS(Boolean contaTempoOS) {
		this.contaTempoOS = contaTempoOS;
	}

	public Boolean getContaRecusaOS() {
		return contaRecusaOS;
	}

	public void setContaRecusaOS(Boolean contaRecusaOS) {
		this.contaRecusaOS = contaRecusaOS;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Justificativa getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(Justificativa justificativa) {
		this.justificativa = justificativa;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", getId())
				.add("dataFim", getDataFim())
				.add("dataInicio", getDataInicio())
				.add("duracao", getDuracaoTotal().getValor().toStandardHours())
				.add("contaTempoOS", getContaTempoOS())
				.add("estado", getEstado()).toString();
	}
}
