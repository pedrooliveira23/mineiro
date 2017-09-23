package br.jus.cjf.redmine.model.journal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.jus.cjf.redmine.model.User;

/*
 * Baseado em http://code.google.com/p/redmine-java-model/
 */

@Entity
@Table(name = "journals")
@DiscriminatorColumn(name = "journalized_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Journal {

	@Id
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private String notes;

	@Column(name = "created_on")
	private Date createdOnDate;
        
        //private boolean private_notes;

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

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getCreatedOnDate() {
		return this.createdOnDate;
	}

	public void setCreatedOnDate(Date createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

    

  
        


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Journal [notes=");
		builder.append(notes);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}

}
