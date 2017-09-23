package br.jus.cjf.redmine.model.enumerations;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/*
 * Baseado em http://code.google.com/p/redmine-java-model/
 */
@Entity
@DiscriminatorValue(value = "IssuePriority")
public class Priority extends RedmineEnumeration {

}
