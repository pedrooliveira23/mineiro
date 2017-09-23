package br.jus.cjf.redmine.model.journal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.jus.cjf.redmine.model.Status;

import com.google.common.base.Objects;

@Entity
@Table(name = "journal_details")
@DiscriminatorValue(value = "attr#status_id")
public class StatusJournalDetail extends JournalDetail {

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name = "old_value")
	private Status oldStatus;

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name = "value")
	private Status currentStatus;
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name = "journal_id")
	private IssueJournal issueJournal;

	public Status getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(Status oldStatus) {
		this.oldStatus = oldStatus;
	}

	public Status getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}

	public IssueJournal getIssueJournal() {
		return issueJournal;
	}

	public void setIssueJournal(IssueJournal issueJournal) {
		this.issueJournal = issueJournal;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
		.add("currentStatus",getCurrentStatus())
		.add("id",getId())
		.add("issueJournal",getIssueJournal())
		.add("oldStatus",getOldStatus())
		.toString();
	}
}
