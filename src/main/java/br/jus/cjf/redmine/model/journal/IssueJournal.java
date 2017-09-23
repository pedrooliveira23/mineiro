package br.jus.cjf.redmine.model.journal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.jus.cjf.redmine.model.Issue;

import com.google.common.base.Objects;

/*
 * Baseado em http://code.google.com/p/redmine-java-model/
 */
@Entity
@DiscriminatorValue("Issue")
public class IssueJournal extends Journal {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "journalized_id")
	private Issue issue;
	
	public Issue getIssue() {
		return this.issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
		.add("creationDate",getCreatedOnDate())
		.add("id",getId())
		.add("issue",getIssue())
		.add("notes",getNotes())
		.add("user",getUser())
		.toString();
	}

}
