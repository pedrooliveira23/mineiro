package br.jus.cjf.redmine.model.custom;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.jus.cjf.redmine.model.Issue;


@Entity
@DiscriminatorValue("Issue")
public class IssueCustomValue extends CustomValue<Issue> {
	
	@ManyToOne
	@JoinColumn(name="customized_id")
	private Issue issue;

	public Issue getIssue() {
		return this.issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

}
