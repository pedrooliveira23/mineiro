package br.jus.cjf.redmine.model.enumerations;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/*
 * Baseado em http://code.google.com/p/redmine-java-model/
 */
@Entity
@DiscriminatorValue(value = "TimeEntryActivity")
public class Activity extends RedmineEnumeration {

//	private static final long serialVersionUID = 1L;

}
