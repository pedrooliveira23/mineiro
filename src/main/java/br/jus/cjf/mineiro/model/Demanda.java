package br.jus.cjf.mineiro.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import br.jus.cjf.mineiro.web.formatter.DiaFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.OrderBy;

@Entity
@Table(schema = "mineiro")
public class Demanda {

	@Id
	@GeneratedValue
	private Integer id;
        
	private String nome;

	@Column(columnDefinition="TEXT")
	private String descricao;

	@Column(nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private DateTime dataCriacao;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private DateTime dataFinalizacao;
	
	@Column(nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private DateTime dataAtualizacao;
	
	private Boolean concluida = false;
	
	private Boolean incompleta = false;
	
	private Boolean cancelada = false;
	
	@JsonIgnore
	@OneToMany(mappedBy = "demanda",fetch=FetchType.EAGER)
        @OrderBy(clause = "id")
	private List<Transicao> transicoes;
	
	@Embedded
	@AttributeOverrides( { 
		@AttributeOverride(name="valor", column = @Column(name = "duracao_prazoMaximo")),
		@AttributeOverride(name="tipoDuracao", column = @Column(name = "tipoDuracao_prazoMaximo"))
		
	})
	@DiaFormat
	private Duracao duracaoPrazoMaximo;
	
	@Embedded
	@AttributeOverrides( { 
	@AttributeOverride(name="valor", column = @Column(name = "duracao_total")),
	@AttributeOverride(name="tipoDuracao", column = @Column(name = "tipoDuracao_total")) 
	
	})
	@DiaFormat
	private Duracao duracaoTotal;

	@Embedded
	@AttributeOverrides( { 
		@AttributeOverride(name="valor", column = @Column(name = "duracao_contandoTempoOS")),
		@AttributeOverride(name="tipoDuracao", column = @Column(name = "tipoDuracao_contandoTempoOS")) 
		
	})
	@DiaFormat
	private Duracao duracaoContandoTempoOS;
	
	@Embedded
	@AttributeOverrides( { 
		@AttributeOverride(name="valor", column = @Column(name = "duracao_contandoTempoCJF")),
		@AttributeOverride(name="tipoDuracao", column = @Column(name = "tipoDuracao_contandoTempoCJF")) 
		
	})
	@DiaFormat
	private Duracao duracaoContandoTempoCJF;
	
	@Embedded
	@AttributeOverrides( { 
		@AttributeOverride(name="valor", column = @Column(name = "duracao_contandoTempoContratada")),
		@AttributeOverride(name="tipoDuracao", column = @Column(name = "tipoDuracao_contandoTempoContratada")) 
		
	})
	@DiaFormat
	private Duracao duracaoContandoTempoContratada;

	@JsonIgnore
	@OneToMany(mappedBy = "demanda")
	private List<Inspecao> inspecoes;

	@Index(name = "idx_redmineIssueId")
	@Column(nullable = false,unique = true)
	private Integer redmineIssueId;
	
	private BigDecimal conformidade = new BigDecimal(100);
	
	private Integer quantidadeRecusas = new Integer(0);
	
	private BigDecimal percentualAtraso = new BigDecimal(0);
	
	@Embedded
	@AttributeOverrides( { 
		@AttributeOverride(name="valor", column = @Column(name = "duracaoAtraso")),
		@AttributeOverride(name="tipoDuracao", column = @Column(name = "tipoDuracao_atraso")) 
		
	})
	@DiaFormat
	private Duracao duracaoAtraso;
	
	@Index(name = "idx_contagemEstimada")
	private BigDecimal contagemEstimada;

	@Index(name = "idx_contagemDetalhada")
	private BigDecimal contagemDetalhada;
	
	@Enumerated
	private TipoSistema tipoSistema;
	
	@ManyToOne
	private TipoDemanda tipoDemanda;
        
        @Column(nullable = true)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private DateTime dataPrevista;
        
        @Column(columnDefinition="TEXT")
	private String autor;
        
        
        @JsonIgnore
	@OneToMany(mappedBy = "demanda",fetch=FetchType.LAZY)
        @OrderBy(clause = "id")
	private Set<TempoRolesDemanda> tempoRoles;

    public Set<TempoRolesDemanda> getTempoRoles() {
        return tempoRoles;
    }

    public void setTempoRoles(Set<TempoRolesDemanda> tempoRoles) {
        this.tempoRoles = tempoRoles;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public DateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(DateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
        
        public DateTime getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(DateTime dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	
	public DateTime getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(DateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public DateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(DateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Boolean getConcluida() {
		return concluida;
	}

	public void setConcluida(Boolean concluida) {
		this.concluida = concluida;
	}

	public List<Transicao> getTransicoes() {
		return transicoes;
	}

	public void setTransicoes(List<Transicao> transicoes) {
		
		this.transicoes = transicoes;

	}

	public Duracao getDuracaoPrazoMaximo() {
		return duracaoPrazoMaximo;
	}

	public void setDuracaoPrazoMaximo(Duracao duracaoPrazoMaximo) {
		this.duracaoPrazoMaximo = duracaoPrazoMaximo;
	}

	public Duracao getDuracaoTotal() {
		return duracaoTotal;
	}

	public void setDuracaoTotal(Duracao duracaoTotal) {
		this.duracaoTotal = duracaoTotal;
	}

	public Duracao getDuracaoContandoTempoOS() {
		return duracaoContandoTempoOS;
	}

	public void setDuracaoContandoTempoOS(Duracao duracaoContandoTempoOS) {
		this.duracaoContandoTempoOS = duracaoContandoTempoOS;
	}
	
	public Duracao getDuracaoContandoTempoCJF() {
		return duracaoContandoTempoCJF;
	}

	public void setDuracaoContandoTempoCJF(Duracao duracaoContandoTempoCJF) {
		this.duracaoContandoTempoCJF = duracaoContandoTempoCJF;
	}

	public Duracao getDuracaoContandoTempoContratada() {
		return duracaoContandoTempoContratada;
	}

	public void setDuracaoContandoTempoContratada(
			Duracao duracaoContandoTempoContratada) {
		this.duracaoContandoTempoContratada = duracaoContandoTempoContratada;
	}

	public List<Inspecao> getInspecoes() {
		return inspecoes;
	}

	public void setInspecoes(List<Inspecao> inspecoes) {
		this.inspecoes = inspecoes;
	}

	public Integer getRedmineIssueId() {
		return redmineIssueId;
	}

	public void setRedmineIssueId(Integer redmineIssueId) {
		this.redmineIssueId = redmineIssueId;
	}

	public BigDecimal getConformidade() {
		return conformidade;
	}

	public void setConformidade(BigDecimal conformidade) {
		this.conformidade = conformidade;
	}

	public Integer getQuantidadeRecusas() {
		return quantidadeRecusas;
	}

	public void setQuantidadeRecusas(Integer quantidadeRecusas) {
		this.quantidadeRecusas = quantidadeRecusas;
	}
	
	public BigDecimal getPercentualAtraso() {
		return percentualAtraso;
	}

	public void setPercentualAtraso(BigDecimal percentualAtraso) {
		this.percentualAtraso = percentualAtraso;
	}

	public Duracao getDuracaoAtraso() {
		return duracaoAtraso;
	}

	public void setDuracaoAtraso(Duracao duracaoAtraso) {
		this.duracaoAtraso = duracaoAtraso;
	}

	public BigDecimal getContagemEstimada() {
		return contagemEstimada;
	}

	public void setContagemEstimada(BigDecimal contagemEstimada) {
		this.contagemEstimada = contagemEstimada;
	}

	public BigDecimal getContagemDetalhada() {
		return contagemDetalhada;
	}

	public void setContagemDetalhada(BigDecimal contagemDetalhada) {
		this.contagemDetalhada = contagemDetalhada;
	}

	public TipoSistema getTipoSistema() {
		return tipoSistema;
	}

	public void setTipoSistema(TipoSistema tipoSistema) {
		this.tipoSistema = tipoSistema;
	}
	
	public TipoDemanda getTipoDemanda() {
		return tipoDemanda;
	}

	public void setTipoDemanda(TipoDemanda tipoDemanda) {
		this.tipoDemanda = tipoDemanda;
	}

	public Boolean getIncompleta() {
		return incompleta;
	}

	public void setIncompleta(Boolean incompleta) {
		this.incompleta = incompleta;
	}
	
	

	public Boolean getCancelada() {
		return cancelada;
	}

	public void setCancelada(Boolean cancelada) {
		this.cancelada = cancelada;
	}

        public String getAutor() {
            return autor;
        }

        public void setAutor(String autor) {
            this.autor = autor;
        }

       /* public List<TempoRolesDemanda> getTempoRoles() {
            return tempoRoles;
        }

        public void setTempoRoles(List<TempoRolesDemanda> tempoRoles) {
            this.tempoRoles = tempoRoles;
        }*/
        
        

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", getId())
				.add("nome", getNome()).add("descricao", getDescricao())
				.add("numeroIssueRedmine", getRedmineIssueId()).toString();
	}
        
        

}
