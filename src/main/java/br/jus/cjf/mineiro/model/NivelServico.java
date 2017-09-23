package br.jus.cjf.mineiro.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import com.google.common.collect.Ranges;

@Entity
@Table(schema="mineiro")
public class NivelServico {
	
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Embedded
	private Faixa faixa;
	
	
	@Column(nullable=false)
	@Enumerated
	private CategoriaNivelServico categoriaNivelServico;

	@ManyToOne
	private Indicador indicador;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Faixa getFaixa() {
		return faixa;
	}

	public void setFaixa(Faixa faixa) {
		this.faixa = faixa;
	}

	public CategoriaNivelServico getCategoriaNivelServico() {
		return categoriaNivelServico;
	}

	public void setCategoriaNivelServico(CategoriaNivelServico categoriaNivelServico) {
		this.categoriaNivelServico = categoriaNivelServico;
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}
	
	public Boolean contemValor(BigDecimal valor){
		
		Range<BigDecimal> range = null;
		
		if(faixa.getTipoFaixa().equals(TipoFaixa.UNICO)){
			range = Ranges.singleton(faixa.getInicio());
		}
		else if(faixa.getTipoFaixa().equals(TipoFaixa.MAIOR)){
			range = Ranges.greaterThan(faixa.getInicio());
		}
		else if(faixa.getTipoFaixa().equals(TipoFaixa.MENOR)){
			range = Ranges.lessThan(faixa.getInicio());
		}
		else if(faixa.getTipoFaixa().equals(TipoFaixa.INTERVALO)){
			range = Ranges.range(faixa.getInicio(),dentroIntervalo(faixa.getInicioDentroIntervalo()), faixa.getFim(), dentroIntervalo(faixa.getFimDentroIntervalo()));
		}

		
		if(range.contains(valor)){
			return true;
		}
		return false;
		
	}
	

	
	private BoundType dentroIntervalo(Boolean dentroIntervalo){
		
		if(dentroIntervalo){
			return BoundType.CLOSED;
		}
		
		return BoundType.OPEN;
		
		
		
	}


	
	
	

}
