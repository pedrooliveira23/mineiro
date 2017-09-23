package br.jus.cjf.mineiro.service;

import java.util.List;

import br.jus.cjf.mineiro.model.Nota;
import br.jus.cjf.redmine.model.journal.IssueJournal;

public interface NotaService {

	
	void extrairNota(List<IssueJournal> issueJournalList);
	
	Nota getNota(Integer redmineJournalId);

	List<Nota> listarNota();

	Boolean notaExiste(Integer redmineJournalId);

	Boolean notaAtualizada(Integer redmineJournalId, String nota);
	
	void criarNota(Nota nota) throws Exception;

	void atualizarNota(Nota nota);
}
