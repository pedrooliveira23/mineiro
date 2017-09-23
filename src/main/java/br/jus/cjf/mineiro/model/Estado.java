package br.jus.cjf.mineiro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import org.hibernate.annotations.Index;

import com.google.common.base.Objects;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author rafael
 *
 */
@Entity
@Table(schema = "mineiro")
public class Estado {

	
		@Id
		@GeneratedValue
		private Integer id;
		
		@Index(name = "idx_redmineStatusId")
		@Column(nullable = true, unique=true)
		private Integer redmineStatusId;
		
		@Column(nullable = false, unique=true)
		private String nome;

		private Boolean concluido;
		
		@Index(name = "idx_contaTempoOS")
		private Boolean contaTempoOS;
		
		@Index(name = "idx_contaRecusaOS")
		private Boolean contaRecusaOS;
                
                @Column(nullable = false)
                private float mediaTempoPorPF;
                
                @OneToOne(fetch=FetchType.EAGER)
                private Roles role;
                
                private Boolean contaIndicador;

                @Index(name = "idx_Estado_mediaTempoPorPFInd")
                private float mediaTempoPorPFInd;
                

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getRedmineStatusId() {
			return redmineStatusId;
		}

		public void setRedmineStatusId(Integer redmineStatusId) {
			this.redmineStatusId = redmineStatusId;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public Boolean getConcluido() {
			return concluido;
		}

		public void setConcluido(Boolean concluido) {
			this.concluido = concluido;
		}

		public boolean isConcluido() {
			return this.concluido;
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

                public float getMediaTempoPorPF() {
                    return mediaTempoPorPF;
                }

                public void setMediaTempoPorPF(float mediaTempoPorPF) {
                    this.mediaTempoPorPF = mediaTempoPorPF;
                }

                public Roles getRole() {
                    return role;
                }

                public void setRole(Roles role) {
                    this.role = role;
                }
                
                public Boolean getContaIndicador() {
                    return contaIndicador;
                }

                public void setContaIndicador(Boolean contaIndicador) {
                    this.contaIndicador = contaIndicador;
                }

                public float getMediaTempoPorPFInd() {
                    return mediaTempoPorPFInd;
                }

                public void setMediaTempoPorPFInd(float mediaTempoPorPFInd) {
                    this.mediaTempoPorPFInd = mediaTempoPorPFInd;
                }
                
                
                

		@Override
		public String toString() {
			return Objects.toStringHelper(this).add("nome", getNome())
				.add("redmineStatusId", getRedmineStatusId())	
				.add("concluido", getConcluido()).toString();
		}

}
