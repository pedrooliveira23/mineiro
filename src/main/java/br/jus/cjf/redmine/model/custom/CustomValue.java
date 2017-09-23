package br.jus.cjf.redmine.model.custom;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "custom_values")
@DiscriminatorColumn(name = "customized_type", discriminatorType = DiscriminatorType.STRING)
public abstract class CustomValue<T>{

	@Id
	private Integer id;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "custom_field_id")
	private CustomField field;

	private String value;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CustomField getField() {
		return this.field;
	}

	public void setField(CustomField field) {
		this.field = field;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
