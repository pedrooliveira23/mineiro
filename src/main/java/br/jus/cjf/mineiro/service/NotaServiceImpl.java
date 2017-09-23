package br.jus.cjf.mineiro.service;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cjf.mineiro.dao.NotaDao;
import br.jus.cjf.mineiro.model.Nota;
import br.jus.cjf.mineiro.model.Transicao;
import br.jus.cjf.redmine.model.journal.IssueJournal;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.logging.Level;

@Service
public class NotaServiceImpl implements NotaService {

    private final NotaDao notaDao;

    private final TransicaoService transicaoService;

    private static Logger logger = LoggerFactory.getLogger(NotaServiceImpl.class);

    public static byte[] encode(byte[] arr, String fromCharsetName) {
        return encode(arr, Charset.forName(fromCharsetName), Charset.forName("UTF-8"));
    }

    public static byte[] encode(byte[] arr, String fromCharsetName, String targetCharsetName) {
        return encode(arr, Charset.forName(fromCharsetName), Charset.forName(targetCharsetName));
    }

    public static byte[] encode(byte[] arr, Charset sourceCharset, Charset targetCharset) {

        ByteBuffer inputBuffer = ByteBuffer.wrap(arr);

        CharBuffer data = sourceCharset.decode(inputBuffer);

        ByteBuffer outputBuffer = targetCharset.encode(data);
        byte[] outputData = outputBuffer.array();

        return outputData;
    }

    @Autowired
    public NotaServiceImpl(NotaDao notaDao, TransicaoService transicaoService) {
        super();
        this.notaDao = notaDao;
        this.transicaoService = transicaoService;
    }

    @Override
    @Transactional("mineiroTransactionManager")
    public void extrairNota(List<IssueJournal> issueJournalList) {

        for (IssueJournal issueJournal : issueJournalList) {
            //System.out.println(" Teste issues:"+issueJournal.getIssue().getId());
            if (issueJournal.getNotes() != null) {
                if (!issueJournal.getNotes().isEmpty()) {
                    DateTime dataCriacaoNota = new DateTime(issueJournal.getCreatedOnDate());
                    Transicao transicao = transicaoService.getTransicao(issueJournal.getIssue().getId(), dataCriacaoNota);
                    if (!notaExiste(issueJournal.getId())) {
                        Nota nota = new Nota();
                        String STR = new String(encode(issueJournal.getNotes().getBytes(Charset.forName("UTF-8")), "UTF-8", "ISO-8859-1"), Charset.forName("ISO-8859-1"));
                        nota.setNota(STR);
                        nota.setDataCriacao(dataCriacaoNota);
                        nota.setTransicao(transicao);
                        nota.setAutor(issueJournal.getUser().getFirstName() + " " + issueJournal.getUser().getLastName());
                        nota.setRedmineJournalId(issueJournal.getId());
                        try {
                            notaDao.criarNota(nota);
                            logger.debug("#" + issueJournal.getIssue().getId() + " - Transição - Nota Inserida:" + issueJournal.getNotes());
                        } catch (Exception eex) {
                            nota.setNota("Erro");
                            try {
                                notaDao.criarNota(nota);
                            } catch (Exception ex) {
                                java.util.logging.Logger.getLogger(NotaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            logger.debug("#" + issueJournal.getIssue().getId() + " - Transição - Nota Inserida com erro:" + issueJournal.getNotes());
                        }

                    } else {
                        Nota nota = notaDao.getNota(issueJournal.getId());
                        String STR = new String(encode(issueJournal.getNotes().getBytes(Charset.forName("UTF-8")), "UTF-8", "ISO-8859-1"), Charset.forName("ISO-8859-1"));
                        nota.setNota(STR);
                        nota.setAutor(issueJournal.getUser().getFirstName() + " " + issueJournal.getUser().getLastName());

                        notaDao.atualizarNota(nota);
                        logger.debug("#" + issueJournal.getIssue().getId() + " - Transição - Nota Atualizada:" + issueJournal.getNotes());

                    }
                }

            }

        }

    }

    @Override
    @Transactional("mineiroTransactionManager")
    public Nota getNota(Integer redmineJournalId) {

        return notaDao.getNota(redmineJournalId);
    }

    @Override
    @Transactional("mineiroTransactionManager")
    public List<Nota> listarNota() {

        return notaDao.listarNota();
    }

    @Override
    @Transactional("mineiroTransactionManager")
    public Boolean notaExiste(Integer redmineJournalId) {

        return notaDao.notaExiste(redmineJournalId);
    }

    @Override
    @Transactional("mineiroTransactionManager")
    public Boolean notaAtualizada(Integer redmineJournalId, String nota) {

        return notaDao.notaAtualizada(redmineJournalId, nota);
    }

    @Override
    @Transactional("mineiroTransactionManager")
    public void criarNota(Nota nota) throws Exception {

        notaDao.criarNota(nota);

    }

    @Override
    @Transactional("mineiroTransactionManager")
    public void atualizarNota(Nota nota) {

        notaDao.atualizarNota(nota);

    }

}
