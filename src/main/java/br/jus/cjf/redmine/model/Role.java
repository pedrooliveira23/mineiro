package br.jus.cjf.redmine.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.Objects;

/*
 * Baseado em http://code.google.com/p/redmine-java-model/
 */
@Entity
@Table(name = "roles")
public class Role {

	@Id
	private Integer id;

	private String name;

	private String permissions;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermissions() {
		return this.permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
		.add("id",getId())
		.add("name",getName())
		.add("permissions",getPermissions())
		.toString();
	}
}
