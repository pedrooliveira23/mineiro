package br.jus.cjf.redmine.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.base.Objects;

/*
 * Baseado em http://code.google.com/p/redmine-java-model/
 */
@Entity
@Table(name = "member_roles")
public class MemberRole {

	@Id
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
		.add("id",getId())
		.add("member",getMember())
		.add("role",getRole())
		.toString();
	}
}
