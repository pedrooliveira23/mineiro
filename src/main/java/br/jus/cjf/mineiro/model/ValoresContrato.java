package br.jus.cjf.mineiro.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

// incluída por Luis Sergio
@Entity
@Table(schema="mineiro")
public class ValoresContrato{
	
	@Id
	@GeneratedValue
	private Integer id;
	
        @ManyToOne
	private Contrato contrato;
	
	@Column(nullable = false)
	private BigDecimal quantitativo;
        
        @Column(nullable = false)
	private BigDecimal valorUnitario;

	@Column(nullable = false)
	private BigDecimal valorTotal;
        
        @Column(nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private DateTime dataInicioVigencia;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private DateTime dataFimVigencia;


	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

        public Contrato getContrato() {
            return contrato;
        }

        public void setContrato(Contrato contrato) {
            this.contrato = contrato;
        }

        public BigDecimal getQuantitativo() {
            return quantitativo;
        }

        public void setQuantitativo(BigDecimal quantitativo) {
            this.quantitativo = quantitativo;
        }

        public BigDecimal getValorUnitario() {
            return valorUnitario;
        }

        public void setValorUnitario(BigDecimal valorUnitario) {
            this.valorUnitario = valorUnitario;
        }

        public BigDecimal getValorTotal() {
            return valorTotal;
        }

        public void setValorTotal(BigDecimal valorTotal) {
            this.valorTotal = valorTotal;
        }

        public DateTime getDataInicioVigencia() {
            return dataInicioVigencia;
        }

        public void setDataInicioVigencia(DateTime dataInicioVigencia) {
            this.dataInicioVigencia = dataInicioVigencia;
        }

        public DateTime getDataFimVigencia() {
            return dataFimVigencia;
        }

        public void setDataFimVigencia(DateTime dataFimVigencia) {
            this.dataFimVigencia = dataFimVigencia;
        }

	
	


}
