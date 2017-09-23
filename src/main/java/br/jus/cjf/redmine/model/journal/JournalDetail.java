package br.jus.cjf.redmine.model.journal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorFormula;
import org.hibernate.annotations.DiscriminatorOptions;

/*
 * Baseado em http://code.google.com/p/redmine-java-model/
 */

@Entity
@Table(name = "journal_details")
@DiscriminatorFormula(value = "concat( property , '#' , prop_key)")
@DiscriminatorOptions(force = true)
public abstract class JournalDetail {

	@Id
	private Integer id;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
