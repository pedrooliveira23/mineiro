package br.jus.cjf.mineiro.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.google.common.base.Objects;

@Entity
@Table(schema = "mineiro")
public class OrdemServico {

	@Id
	@GeneratedValue
	private Integer id;

	@OneToOne(fetch=FetchType.EAGER)
	private Demanda demanda;
	

	
	@Enumerated(EnumType.STRING)
	private CategoriaNivelServico nivelServicoAtraso;
	
	@Enumerated(EnumType.STRING)
	private CategoriaNivelServico nivelServicoRecusa;
	
	@Enumerated(EnumType.STRING)
	private CategoriaNivelServico nivelServicoConformidade;
	
	@ManyToOne
	private Projeto projeto;
	
	@ManyToOne
	private Contrato contrato;
	
	private BigDecimal multaAtraso;
	
	private BigDecimal valorMultaAtraso;
	
	private BigDecimal GlosaAtraso;
	
	private BigDecimal GlosaRecusa;
	
	private BigDecimal GlosaConformidade;
	
	private BigDecimal valorGlosaAtraso;
	
	private BigDecimal valorGlosaRecusa;
	
	private BigDecimal valorGlosaConformidade;
	
	private BigDecimal valorBruto;
	
	private BigDecimal valorBrutoDeflacionado;
	
	private BigDecimal valorTotal; 
	
	private String autorFinalizacao;
        
        private transient BigDecimal multaAtrasoDemanda;
        
        private transient BigDecimal valorMultaAtrasoDemanda;

        private transient BigDecimal totalGlosas;

        public BigDecimal getTotalGlosas() {
            return totalGlosas;
        }

        public void setTotalGlosas(BigDecimal totalGlosas) {
            this.totalGlosas = totalGlosas;
        }
	
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	
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

	public CategoriaNivelServico getNivelServicoAtraso() {
		return nivelServicoAtraso;
	}

	public void setNivelServicoAtraso(CategoriaNivelServico nivelServicoAtraso) {
		this.nivelServicoAtraso = nivelServicoAtraso;
	}

	public CategoriaNivelServico getNivelServicoRecusa() {
		return nivelServicoRecusa;
	}

	public void setNivelServicoRecusa(CategoriaNivelServico nivelServicoRecusa) {
		this.nivelServicoRecusa = nivelServicoRecusa;
	}

	public CategoriaNivelServico getNivelServicoConformidade() {
		return nivelServicoConformidade;
	}

	public void setNivelServicoConformidade(
			CategoriaNivelServico nivelServicoConformidade) {
		this.nivelServicoConformidade = nivelServicoConformidade;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public BigDecimal getMultaAtraso() {
		return multaAtraso;
	}

	public void setMultaAtraso(BigDecimal multaAtraso) {
		this.multaAtraso = multaAtraso;
	}

	public BigDecimal getValorMultaAtraso() {
		return valorMultaAtraso;
	}

	public void setValorMultaAtraso(BigDecimal valorMultaAtraso) {
		this.valorMultaAtraso = valorMultaAtraso;
	}

	public BigDecimal getGlosaAtraso() {
		return GlosaAtraso;
	}

	public void setGlosaAtraso(BigDecimal glosaAtraso) {
		GlosaAtraso = glosaAtraso;
	}

	public BigDecimal getGlosaRecusa() {
		return GlosaRecusa;
	}

	public void setGlosaRecusa(BigDecimal glosaRecusa) {
		GlosaRecusa = glosaRecusa;
	}

	public BigDecimal getGlosaConformidade() {
		return GlosaConformidade;
	}

	public void setGlosaConformidade(BigDecimal glosaConformidade) {
		GlosaConformidade = glosaConformidade;
	}
	
	public BigDecimal getValorGlosaAtraso() {
		return valorGlosaAtraso;
	}

	public void setValorGlosaAtraso(BigDecimal valorGlosaAtraso) {
		this.valorGlosaAtraso = valorGlosaAtraso;
	}

	public BigDecimal getValorGlosaRecusa() {
		return valorGlosaRecusa;
	}

	public void setValorGlosaRecusa(BigDecimal valorGlosaRecusa) {
		this.valorGlosaRecusa = valorGlosaRecusa;
	}

	public BigDecimal getValorGlosaConformidade() {
		return valorGlosaConformidade;
	}

	public void setValorGlosaConformidade(BigDecimal valorGlosaConformidade) {
		this.valorGlosaConformidade = valorGlosaConformidade;
	}

	public BigDecimal getValorBrutoDeflacionado() {
		return valorBrutoDeflacionado;
	}

	public void setValorBrutoDeflacionado(BigDecimal valorBrutoDeflacionado) {
		this.valorBrutoDeflacionado = valorBrutoDeflacionado;
	}

	public BigDecimal getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getAutorFinalizacao() {
		return autorFinalizacao;
	}

	public void setAutorFinalizacao(String autorFinalizacao) {
		this.autorFinalizacao = autorFinalizacao;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
        
        public BigDecimal getMultaAtrasoDemanda() {
            return multaAtrasoDemanda;
        }

        public void setMultaAtrasoDemanda(BigDecimal multaAtrasoContrato) {
            this.multaAtrasoDemanda = multaAtrasoContrato;
        }
        
        public BigDecimal getValorMultaAtrasoDemanda() {
            return valorMultaAtrasoDemanda;
        }

        public void setValorMultaAtrasoDemanda(BigDecimal valorMultaAtrasoDemanda) {
            this.valorMultaAtrasoDemanda = valorMultaAtrasoDemanda;
        }

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", getId())
				.add("demanda", getDemanda()).toString();
	}
	

}
