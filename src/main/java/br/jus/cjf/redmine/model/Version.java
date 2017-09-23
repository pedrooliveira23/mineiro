package br.jus.cjf.redmine.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.base.Objects;

/*
 * Baseado em http://code.google.com/p/redmine-java-model/
 */
@Entity
@Table(name = "versions")
public class Version {

	@Id
	private Integer id;

	private String name;

	private String description;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;

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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String toString() {
		return Objects.toStringHelper(this)
		.add("description",getDescription())
		.add("id",getId())
		.add("name",getName())
		.add("project",getProject())
		.toString();
	}

}
