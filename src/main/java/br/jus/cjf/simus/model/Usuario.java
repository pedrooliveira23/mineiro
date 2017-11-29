package br.jus.cjf.simus.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Representa um usuário do sistema, modelo vindo o SIMUS, tabela sig_servid
 * 
 * @author g
 * 
 */
@Entity
@Table(name = "Usuario", schema = "mineiro")
public class Usuario implements Serializable, UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1934274407628409889L;

	
	/**
	 * Chave primária e também número de matricula do servidor
	 */
	@Id
	@Column(name = "usuario_id")
	private Integer id;

	/**
	 * Número da matrícula como inteiro.
	 */
	@Column(name = "matricula")
	private String matricula;
        
        @Column(name = "nome")
	private String nome;
	

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="UsuarioGrupo", schema = "mineiro", joinColumns=@JoinColumn(name="usuario_id"),
			inverseJoinColumns=@JoinColumn(name="grupo_id"))
	private List<Grupo> grupos;
	
	@Column(name = "senha")
	private String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grupos;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return matricula.toString();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	/*
	public String getGruposFormatados(){
		getGrupos().toString()
	}
*/
}
