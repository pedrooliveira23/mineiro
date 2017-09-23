package br.jus.cjf.redmine.model.custom;



import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("IssueCustomField")
public class IssueCustomField extends CustomField {

}
