/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*- 
* Classname:             CustomWebAuthenticationDetailsSource.java 
* 
* Version information:   (versão) 
* 
* Date:                  14/11/2014 - 14:08:33 
* 
* author:                luis 
* Copyright notice:       
*/ 

package br.jus.cjf.spring.util;

/**
 * Descrição
 * @see
 * @author luis
 */
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class CustomWebAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {
    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new CustomWebAuthenticationDetails(context);
    }
} //fim da classe CustomWebAuthenticationDetailsSource 
