package br.jus.cjf.mineiro.service;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import br.jus.cjf.mineiro.dao.DiaNaoUtilDao;

import org.hibernate.SessionFactory;

import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;


@RunWith(MockitoJUnitRunner.class)
    @Transactional
public class DiasNaoUteisServiceImplTest {

	private DiaNaoUtilServiceImpl diasNaoUteisService;
	@Mock
	DiaNaoUtilDao diaNaoUtilDao;
	
	LocalDate diaUtil = new LocalDate(2012, 6, 9);
	LocalDate carnaval = new LocalDate(2012, 2, 2);
    LocalDate natal = new LocalDate(2012, 12, 25);
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
            	
            
            //DiaNaoUtilDao diaNaoUtilDao =mock(DiaNaoUtilDao.class);
            when(diaNaoUtilDao.ehDiaUtil(carnaval)).thenReturn(false);
            when(diaNaoUtilDao.ehDiaUtil(natal)).thenReturn(false);
            when(diaNaoUtilDao.ehDiaUtil(diaUtil)).thenReturn(true);
            diasNaoUteisService = new DiaNaoUtilServiceImpl(diaNaoUtilDao);
	}
	
	@Test
	public void testaDiasNaoUteis() {
            Assert.assertFalse("Carnaval deveria ser não útil", diasNaoUteisService.ehDiaUtil(new DateTime(2012, 2, 2, 12, 0)));
            Assert.assertFalse("Natal deveria ser não útil", diasNaoUteisService.ehDiaUtil(new DateTime(2015, 6, 9, 12, 0,0,0)));
	}
	
        @Test
	public void testaDiasUteis() {
            	Assert.assertTrue("Dia 26/11/2012 deveria ser útil", diaNaoUtilDao.ehDiaUtil(diaUtil));
	}
	
}
