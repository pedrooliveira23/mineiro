package br.jus.cjf.mineiro.web.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import br.jus.cjf.redmine.model.Issue;

@Component
public class IssueFormatter implements Formatter<Issue>  {

	@Override
	public String print(Issue issue, Locale locale) {
		
		return issue.getId().toString();
	}

	@Override
	public Issue parse(String id, Locale locale) throws ParseException {

		Issue issue = new Issue();
		issue.setId(Integer.parseInt(id));
		return issue;
	}

	
	
}
