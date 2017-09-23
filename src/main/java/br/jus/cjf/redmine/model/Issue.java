package br.jus.cjf.redmine.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.jus.cjf.redmine.model.custom.IssueCustomValue;

import com.google.common.base.Objects;

/*
 * Baseado em http://code.google.com/p/redmine-java-model/
 */
@Entity
@Table(name = "issues")
public class Issue {


	@Id
	private Integer id;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "tracker_id")
	private Tracker tracker;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "assigned_to_id")
	private User assignedTo;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "author_id")
	private User author;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Issue parent;

	@OneToMany(mappedBy = "issue")
	private List<IssueCustomValue> issueCustomValues;
//	@ManyToOne
//	@JoinColumn(name = "root_id")
//	private Issue root;

	private String subject;

	private String description;

	@Column(name = "created_on")
	private Date createdOnDate;
	
	@Column(name = "updated_on")
	private Date updatedOnDate;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "due_date")
	private Date dueDate;

	@Column(name = "done_ratio")
	private Integer doneRatio;

	@Column(name = "estimated_hours")
	private Double estimatedHours;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "fixed_version_id")
	private Version version;
        
        @Column(name = "closed_on")
	private Date closedOn;
        

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Tracker getTracker() {
		return tracker;
	}

	public void setTracker(Tracker tracker) {
		this.tracker = tracker;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getAssignedTo() {
		return this.assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Issue getParent() {
		return parent;
	}

	public void setParent(Issue parent) {
		this.parent = parent;
	}

//	public Issue getRoot() {
//		return this.root;
//	}
//
//	public void setRoot(Issue root) {
//		this.root = root;
//	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedOnDate() {
		return this.createdOnDate;
	}

	public void setCreatedOnDate(Date createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

	public Date getUpdatedOnDate() {
		return updatedOnDate;
	}

	public void setUpdatedOnDate(Date updatedOnDate) {
		this.updatedOnDate = updatedOnDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getDoneRatio() {
		return doneRatio;
	}

	public void setDoneRatio(Integer doneRatio) {
		this.doneRatio = doneRatio;
	}

	public Double getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(Double estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public Version getVersion() {
		return this.version;
	}

    public Date getClosedOn() {
        return closedOn;
    }

    public void setClosedOn(Date closedOn) {
        this.closedOn = closedOn;
    }
        
        
	
	public List<IssueCustomValue> getIssueCustomValues() {
		return issueCustomValues;
	}

	public void setIssueCustomValues(List<IssueCustomValue> issueCustomValues) {
		this.issueCustomValues = issueCustomValues;
	}

	public String toString() {
		return Objects.toStringHelper(this)
		.add("assignedTo",getAssignedTo())
		.add("author",getAuthor())
		.add("createdOn",getCreatedOnDate())
		.add("description",getDescription())
		.add("doneRatio",getDoneRatio())
		.add("dueDate",getDueDate())
		.add("estimatedHours",getEstimatedHours())
		.add("id",getId())
		.add("parent",getParent())
		.add("project",getProject())
//		.add("root",getRoot())
		.add("startDate",getStartDate())
		.add("status",getStatus())
		.add("subject",getSubject())
		.add("tracker",getTracker())
		.add("version",getVersion())
		.toString();
		
	}

}