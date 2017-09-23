package br.jus.cjf.redmine.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.Objects;

/*
 * Baseado em http://code.google.com/p/redmine-java-model/
 */
@Entity
@Table(name = "trackers")
public class Tracker {

	@Id
	private Integer id;

	private String name;

	private Integer position;
        

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
		.add("id",getId())
		.add("name",getName())
		.add("position",getPosition())
		.toString();
	}

}
