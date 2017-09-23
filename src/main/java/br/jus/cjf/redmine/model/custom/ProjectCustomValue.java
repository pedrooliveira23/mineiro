package br.jus.cjf.redmine.model.custom;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.jus.cjf.redmine.model.Project;


@Entity
@DiscriminatorValue("Project")
public class ProjectCustomValue extends CustomValue<Project> {

	@ManyToOne
	@JoinColumn(name="customized_id")
	private Project project;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}


	
}
