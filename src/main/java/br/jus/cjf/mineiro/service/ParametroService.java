/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.cjf.mineiro.service;

import br.jus.cjf.mineiro.model.Parametro;

/**
 *
 * @author diogo.araujo
 */
public interface ParametroService {
    
    Parametro getParametro(String nome);
    
}
