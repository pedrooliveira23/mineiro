package br.jus.cjf.redmine.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.jus.cjf.redmine.model.custom.ProjectCustomValue;

import com.google.common.base.Objects;

/*
 * Baseado em http://code.google.com/p/redmine-java-model/
 */
@Entity
@Table(name = "projects")
public class Project {

	@Id
	private Integer id;

	private String name;

	private String identifier;

	private String description;
	
	private Integer parent_id;
        
        private Integer inherit_members;
	
	@OneToMany
	@JoinColumn(name="customized_id")
	private List<ProjectCustomValue> projectCustomValues;

	public Integer getId() {
		return id;
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

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String indentifier) {
		this.identifier = indentifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

    public Integer getInherit_members() {
        return inherit_members;
    }

    public void setInherit_members(Integer inherit_members) {
        this.inherit_members = inherit_members;
    }
        
        

	public List<ProjectCustomValue> getProjectCustomValues() {
		return projectCustomValues;
	}

	public void setProjectCustomValues(List<ProjectCustomValue> projectCustomValues) {
		this.projectCustomValues = projectCustomValues;
	}

	public String toString() {
		return Objects.toStringHelper(this)
		.add("description",getDescription())
		.add("id",getId())
		.add("identifier",getIdentifier())
		.add("name",getName())
		.toString();
	}

}
