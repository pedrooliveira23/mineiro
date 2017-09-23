package br.jus.cjf.redmine.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.jus.cjf.mineiro.model.Transicao;
import br.jus.cjf.redmine.model.enumerations.Activity;
import br.jus.cjf.redmine.model.enumerations.Priority;
import br.jus.cjf.redmine.model.enumerations.RedmineEnumeration;
import br.jus.cjf.redmine.model.journal.IssueJournal;
import br.jus.cjf.redmine.model.journal.Journal;
import br.jus.cjf.redmine.model.journal.JournalDetail;
import br.jus.cjf.redmine.model.journal.StatusJournalDetail;

@SuppressWarnings("rawtypes")
public class ModelToString {

	public static void main(String[] args) {
		
		Class classes[] = new Class[] { Issue.class, 
				IssueRelation.class, 
				Member.class,
				MemberRole.class,
				Project.class,
				Role.class,
				Status.class,
				TimeEntry.class,
				Tracker.class,
				User.class,
				Version.class,
				Activity.class,
				Priority.class,
				RedmineEnumeration.class,
				IssueJournal.class,
				Journal.class,
				JournalDetail.class,
				StatusJournalDetail.class,
				Transicao.class};
		for (Class clazz : classes) {
			StringBuilder sb = new StringBuilder();
			sb.append(clazz.getName()).append("\n");
			sb.append("return Objects.toStringHelper(this)");
			List<String> methodNames = new ArrayList<String>();
			for (Method method : clazz.getMethods()) {
				String methodName = method.getName();
				if (methodName != null && methodName.contains("get")
						&& !"getClass".equals(methodName)) {
					methodNames.add(methodName);
				}
			}
			Collections.sort(methodNames);
			for (String methodName : methodNames) {
				sb.append(String.format("\n.add(\"%s%s\",%s())", methodName.substring(3, 4).toLowerCase(), methodName.substring(4), methodName));
			}
			sb.append("\n.toString();");
			System.out.println(sb.toString());
			System.out.println();
		}
	}
}
