package br.jus.cjf.redmine.model.custom;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ProjectCustomField")
public class ProjectCustomField extends CustomField {

}
