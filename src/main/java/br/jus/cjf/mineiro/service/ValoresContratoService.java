package br.jus.cjf.mineiro.service;

import java.math.BigDecimal;
import java.util.List;

import br.jus.cjf.mineiro.model.CategoriaNivelServico;
import br.jus.cjf.mineiro.model.Contrato;
import br.jus.cjf.mineiro.model.Indicador;
import br.jus.cjf.mineiro.model.NivelServico;
import br.jus.cjf.mineiro.model.Penalidade;
import br.jus.cjf.mineiro.model.TipoIndicador;
import br.jus.cjf.mineiro.model.ValoresContrato;
import org.joda.time.DateTime;

public interface ValoresContratoService {


        void excluirValoresContrato(ValoresContrato valor);
	    
        ValoresContrato getValoresContratoPorVigencia(DateTime de, DateTime ate);
        
        List<ValoresContrato> getValoresContrato(Contrato contrato);
        
        ValoresContrato getValoresContratoPorId(Integer id);

	void atualizarValoresContrato(ValoresContrato valorescontrato);

	void criarValoresContrato(ValoresContrato valorescontrato);
	
}
