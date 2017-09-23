package br.jus.cjf.mineiro.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.jus.cjf.mineiro.dao.TransicaoDao;
import br.jus.cjf.mineiro.model.Contrato;

public class TransicaoServiceImplTest {
	
	private TransicaoService transicaoService;

	
	
	@Before
	public void setup() {
	
            /*TransicaoDao transicaoDao = mock(TransicaoDao.class);
            DiaNaoUtilService diaNaoUtilService = mock(DiaNaoUtilService.class);
            ContratoService contratoService = mock(ContratoService.class);
            
            Contrato contrato = new Contrato();
            contrato.setContratada("Indra");
            contrato.setNumero("005/2012-CJF");
            contrato.setQuantitativo(new BigDecimal("3000"));
            contrato.setValorUnitario(new BigDecimal("470.33"));
            contrato.setValorTotal(new BigDecimal("1410990"));
            contrato.setInicioExpediente(new LocalTime(9,0));
            contrato.setFimExpediente(new LocalTime(19,0));
            when(contratoService.getContratoPorNumero("005/2012-CJF")).thenReturn(contrato);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 11, 25, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(true);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 11, 26, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(true);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 11, 27, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(true);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 11, 28, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(true);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 12, 14, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(true);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 12, 15, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(false);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 12, 16, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(false);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 12, 17, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(true);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 12, 18, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(true);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 12, 19, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(true);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 12, 20, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(false);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 12, 21, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(false);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 12, 23, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(false);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 12, 24, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(false);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 12, 25, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(false);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 12, 26, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(false);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 12, 27, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(false);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 12, 28, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(false);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 12, 29, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(false);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 12, 30, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(false);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2012, 12, 31, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(false);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2013, 1, 1, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(false);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2013, 1, 2, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(false);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2013, 1, 3, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(false);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2013, 1, 4, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(false);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2013, 1, 5, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(false);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2013, 1, 6, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(false);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2013, 1, 7, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(true);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2013, 1, 8, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(true);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2013, 1, 9, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(true);
            when(diaNaoUtilService.ehDiaUtil(new DateTime(2013, 1, 10, 0, 0).withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))).thenReturn(true);
            transicaoService = new TransicaoServiceImpl(transicaoDao,diaNaoUtilService, contratoService);*/
		
	}




	@Test
	public void testaTransicaoInicioFimIguaisInicioFimAntesInicioExpediente() {
		
            /*
            Assert.assertTrue("Inicio e Fim Iguais - Inicio e Fim antes inicio do Expediente",
        transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 25, 7, 0), new DateTime(2012, 11, 25, 8, 0))==Duration.standardHours(0).getMillis());*/	}
	@Test
	public void testaTransicaoInicioFimIguaisInicioFimDepoisFimExpediente() {
            /*		Assert.assertTrue("Inicio e Fim Iguais - Inicio e Fim depois do fim do Expediente",
        transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 25, 20, 0), new DateTime(2012, 11, 25, 21, 0))==Duration.standardHours(0).getMillis());*/	}
	@Test
	public void testaTransicaoInicioFimIguaisInicioFimDentroExpediente() {
            /*		Assert.assertTrue("Inicio e Fim Iguais - Inicio e Fim dentro do Expediente",
        transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 25, 10, 0), new DateTime(2012, 11, 25, 12, 0))==Duration.standardHours(2).getMillis());*/	}
	@Test
	public void testaTransicaoInicioFimIguaisInicioIgualInicioExpedienteFimIgualFimExpediente() {
	
            /*	Assert.assertTrue("Inicio e Fim Iguais - Inicio igual inicio do expediente e Fim igual fim do expediente",
        transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 25, 9, 0), new DateTime(2012, 11, 25, 19, 0))==Duration.standardHours(10).getMillis());*/	}
	@Test
	public void testaTransicaoInicioFimIguaisInicioAntesInicioExpedienteFimIgualFimExpediente() {
	
            /*		Assert.assertTrue("Inicio e Fim Iguais - Inicio antes do inicio expediente e Fim igual fim do expediente",
        transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 25, 7, 0), new DateTime(2012, 11, 25, 19, 0))==Duration.standardHours(10).getMillis());*/	}	
		
	@Test
	public void testaTransicaoInicioFimIguaisInicioIgualInicioExpedienteFimDepoisFimExpediente() {
	
            /*		Assert.assertTrue("Inicio e Fim Iguais - Inicio igual inicio do expediente e Fim depois do fim expediente",
            transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 25, 9, 0), new DateTime(2012, 11, 25, 22, 0))==Duration.standardHours(10).getMillis());*/		
	}	
	@Test
	public void testaTransicaoInicioFimIguaisInicioAntesInicioExpedienteFimDepoisFimExpediente() {
	
            /*		Assert.assertTrue("Inicio e Fim Iguais - Inicio antes do inicio do expediente e Fim depois do fim do expediente",
            transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 25, 7, 0), new DateTime(2012, 11, 25, 22, 0))==Duration.standardHours(10).getMillis());*/		
	}	
	@Test
	public void testaTransicaoInicioFimIguaisInicioAntesInicioExpedienteFimAntesFimExpediente() {
	
            /*		Assert.assertTrue("Inicio e Fim Iguais - Inicio antes do inicio do expediente e Fim antes do fim do expediente",
            transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 25, 7, 0), new DateTime(2012, 11, 25, 12, 0))==Duration.standardHours(3).getMillis());*/		
	}		
	@Test
	public void testaTransicaoInicioFimIguaisInicioIgualInicioExpedienteFimAntesFimExpediente() {
	
            /*		Assert.assertTrue("Inicio e Fim Iguais - Inicio igual inicio do expediente e Fim antes do fim do expediente",
            transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 25, 9, 0), new DateTime(2012, 11, 25, 12, 0))==Duration.standardHours(3).getMillis());*/		
	}				
	@Test
	public void testaTransicaoInicioFimIguaisInicioAntesFimExpedienteFimIgualFimExpediente() {
	
            /*		Assert.assertTrue("Inicio e Fim Iguais - Inicio antes do fim do expediente e Fim igual fim do expediente",
            transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 25, 10, 0), new DateTime(2012, 11, 25, 19, 0))==Duration.standardHours(9).getMillis());*/		
	}	
	
	@Test
	public void testaTransicaoInicioFimIguaisInicioAntesFimExpedienteFimDepoisFimExpediente() {
	
            /*		Assert.assertTrue("Inicio e Fim Iguais - Inicio antes do fim do expediente e Fim depois do fim do expediente",
            transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 25, 10, 0), new DateTime(2012, 11, 25, 22, 0))==Duration.standardHours(9).getMillis());*/		
	}	
	
	@Test
	public void testaTransicaoInicioFimIguaisInicioAntesFimExpedienteFimDepoisFimExpedienteComFeriado() {
	
            /*		Assert.assertTrue("Inicio e Fim Iguais - Inicio antes do fim do expediente e Fim depois do fim do expediente",
            transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 12, 25, 10, 0), new DateTime(2012, 12, 25, 22, 0))==Duration.standardHours(0).getMillis());*/		
	}	
	
	
	
	@Test
	public void testaTransicaoInicioFimDiferentesInicioFimAntesInicioExpediente() {
		
            /*		Assert.assertTrue("Inicio e Fim Diferentes - Inicio e Fim antes inicio do Expediente",
            transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 26, 7, 0), new DateTime(2012, 11, 28, 8, 0))==Duration.standardHours(20).getMillis());*/		
	}
	@Test
	public void testaTransicaoInicioFimDiferentesInicioFimDepoisFimExpediente() {
		
            /*		Assert.assertTrue("Inicio e Fim Diferentes - Inicio e Fim depois do fim do Expediente",
        transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 26, 20, 0), new DateTime(2012, 11, 28, 21, 0))==Duration.standardHours(20).getMillis());*/	}
	@Test
	public void testaTransicaoInicioFimDiferentesInicioFimDentroExpediente() {
		
            /*		Assert.assertTrue("Inicio e Fim Diferentes - Inicio e Fim dentro do Expediente",
            transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 26, 10, 0), new DateTime(2012, 11, 28, 12, 0))==Duration.standardHours(22).getMillis());*/		
	}
	@Test
	public void testaTransicaoInicioFimDiferentesInicioIgualInicioExpedienteFimIgualFimExpediente() {
	
            /*		Assert.assertTrue("Inicio e Fim Diferentes - Inicio igual inicio do expediente e Fim igual fim do expediente",
        transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 26, 9, 0), new DateTime(2012, 11, 28, 19, 0))==Duration.standardHours(30).getMillis());*/	}
	@Test
	public void testaTransicaoInicioFimDiferentesInicioAntesInicioExpedienteFimIgualFimExpediente() {
	
            /*		Assert.assertTrue("Inicio e Fim Diferentes - Inicio antes do inicio expediente e Fim igual fim do expediente",
            transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 26, 7, 0), new DateTime(2012, 11, 28, 19, 0))==Duration.standardHours(30).getMillis());*/		
	}	
		
	@Test
	public void testaTransicaoInicioFimDiferentesInicioIgualInicioExpedienteFimDepoisFimExpediente() {
	
            /*		Assert.assertTrue("Inicio e Fim Diferentes - Inicio igual inicio do expediente e Fim depois do fim expediente",
            transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 26, 9, 0), new DateTime(2012, 11, 28, 22, 0,0,0))==Duration.standardHours(30).getMillis());*/		
	}	
	@Test
	public void testaTransicaoInicioFimDiferentesInicioAntesInicioExpedienteFimDepoisFimExpediente() {
	
            /*		Assert.assertTrue("Inicio e Fim Diferentes - Inicio antes do inicio do expediente e Fim depois do fim do expediente",
            transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 26, 7, 0), new DateTime(2012, 11, 28, 22, 0))==Duration.standardHours(30).getMillis());*/		
	}	
	@Test
	public void testaTransicaoInicioFimDiferentesInicioAntesInicioExpedienteFimAntesFimExpediente() {
	
            /*		Assert.assertTrue("Inicio e Fim Diferentes - Inicio antes do inicio do expediente e Fim antes do fim do expediente",
            transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 26, 7, 0), new DateTime(2012, 11, 28, 12, 0))==Duration.standardHours(23).getMillis());*/			
	}		
	@Test
	public void testaTransicaoInicioFimDiferentesInicioIgualInicioExpedienteFimAntesFimExpediente() {
	
            /*		Assert.assertTrue("Inicio e Fim Diferentes - Inicio igual inicio do expediente e Fim antes do fim do expediente",
            transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 26, 9, 0), new DateTime(2012, 11, 28, 12, 0,0,0))==Duration.standardHours(23).getMillis());*/		
	}		
		
	@Test
	public void testaTransicaoInicioFimDiferentesInicioantesFimExpedienteFimIgualFimExpediente() {
	
            /*		Assert.assertTrue("Inicio e Fim Diferentes - Inicio antes do fim do expediente e Fim igual fim do expediente",
            transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 26, 10, 0), new DateTime(2012, 11, 28, 19, 0))==Duration.standardHours(29).getMillis());*/		
	}	
	
	@Test
	public void testaTransicaoInicioFimDiferentesInicioAntesFimExpedienteFimDepoisFimExpediente() {
	
            /*		Assert.assertTrue("Inicio e Fim Diferentes - Inicio antes do fim do expediente e Fim depois do fim do expediente",
            transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 11, 26, 10, 0), new DateTime(2012, 11, 28, 22, 0))==Duration.standardHours(29).getMillis());*/		
	}	

	@Test
	public void testaTransicaoInicioFimDiferentesInicioAntesFimExpedienteFimDepoisFimExpedienteComFimDeSemana() {
	
            /*		Assert.assertTrue("Inicio e Fim Diferentes - Inicio antes do fim do expediente e Fim depois do fim do expediente com fim de semana",
            transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 12, 14, 10, 0), new DateTime(2012, 12, 18, 22, 0))==Duration.standardHours(29).getMillis());*/		
	}
	
	@Test
	public void testaTransicaoInicioFimDiferentesInicioAntesFimExpedienteFimDepoisFimExpedienteComFeriados() {
	
            /*		Assert.assertTrue("Inicio e Fim Diferentes - Inicio antes do fim do expediente e Fim depois do fim do expediente com feriados",
            transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(new DateTime(2012, 12, 14, 10, 0), new DateTime(2013, 1, 10, 22, 0))==Duration.standardHours(79).getMillis());*/		
	}	




}
