package br.jus.cjf.mineiro.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema="mineiro")
public class Indicador{
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=false)
	private String clausula;
	
	@ManyToOne
	private Contrato contrato;
	
	@OneToMany(mappedBy="indicador")
	private List<NivelServico> niveisServico;
	
	@ManyToOne
	private TipoIndicador tipoIndicador;


	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClausula() {
		return clausula;
	}

	public void setClausula(String clausula) {
		this.clausula = clausula;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public List<NivelServico> getNiveisServico() {
		return niveisServico;
	}

	public void setNiveisServico(List<NivelServico> niveisServico) {
		this.niveisServico = niveisServico;
	}

	public TipoIndicador getTipoIndicador() {
		return tipoIndicador;
	}

	public void setTipoIndicador(TipoIndicador tipoIndicador) {
		this.tipoIndicador = tipoIndicador;
	}

	
	


}
