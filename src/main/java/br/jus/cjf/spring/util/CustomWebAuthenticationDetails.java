/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*- 
* Classname:             CustomWebAuthenticationDetails.java 
* 
* Version information:   (versão) 
* 
* Date:                  14/11/2014 - 14:11:04 
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
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import javax.servlet.http.HttpServletRequest;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

    private String nContrato;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        this.nContrato = request.getParameter("nContrato");
    }

    public String getnContrato() {
        return nContrato;
    }
    
    public void setContrato(String contrato){
        this.nContrato = contrato;
    }
    
} //fim da classe CustomWebAuthenticationDetails 
