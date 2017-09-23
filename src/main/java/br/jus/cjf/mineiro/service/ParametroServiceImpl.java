/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.cjf.mineiro.service;

import br.jus.cjf.mineiro.dao.ParametroDao;
import br.jus.cjf.mineiro.model.Parametro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author diogo.araujo
 */
@Service
public class ParametroServiceImpl implements ParametroService {
    
    
    private final ParametroDao parametroDao;
    
    private static Logger logger = LoggerFactory
	.getLogger(ParametroServiceImpl.class);
	
	@Autowired
	private ParametroServiceImpl(ParametroDao parametroDao) {
		super();
		this.parametroDao = parametroDao;
	}
        
        @Override
	@Transactional("mineiroTransactionManager")
        public Parametro getParametro(String nome){
            return parametroDao.getParametro(nome);
        }
}
