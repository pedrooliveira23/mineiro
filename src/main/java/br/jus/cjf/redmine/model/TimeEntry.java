package br.jus.cjf.redmine.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.jus.cjf.redmine.model.enumerations.Activity;

import com.google.common.base.Objects;

/*
 * Baseado em http://code.google.com/p/redmine-java-model/
 */

@Entity
@Table(name = "time_entries")
public class TimeEntry {

	@Id
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "issue_id")
	private Issue issue;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	@Column(name = "hours")
	private Double time;

	private String comments;

	@Column(name = "spent_on")
	private Date date;

	@ManyToOne
	@JoinColumn(name = "activity_id")
	private Activity activity;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Issue getIssue() {
		return this.issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Double getTime() {
		return this.time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
		.add("activity",getActivity())
		.add("comments",getComments())
		.add("date",getDate())
		.add("id",getId())
		.add("issue",getIssue())
		.add("project",getProject())
		.add("time",getTime())
		.add("user",getUser())
		.toString();
	}
}
