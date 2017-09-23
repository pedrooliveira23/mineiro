package br.jus.cjf.redmine.model;

import javax.persistence.Column;
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
@Table(name = "issue_relations")
public class IssueRelation {

	@Id
	private Integer id;

	@JoinColumn(name = "issue_from_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Issue from;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "issue_to_id")
	private Issue to;

	@Column(name = "relation_type")
	private String relation;

	private Integer delay;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Issue getFrom() {
		return from;
	}

	public void setFrom(Issue from) {
		this.from = from;
	}

	public Issue getTo() {
		return to;
	}

	public void setTo(Issue to) {
		this.to = to;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Integer getDelay() {
		return delay;
	}

	public void setDelay(Integer delay) {
		this.delay = delay;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
		.add("delay",getDelay())
		.add("from",getFrom())
		.add("id",getId())
		.add("relation",getRelation())
		.add("to",getTo())
		.toString();
	}

}
