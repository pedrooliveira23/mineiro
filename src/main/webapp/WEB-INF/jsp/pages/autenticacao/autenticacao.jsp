<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<c:if test="${not empty error}">
		<div class="alert alert-error">
			<strong><spring:message code="autenticao.invalida"/></strong>
		</div>
</c:if>

<div id="div_autenticacao" class="row-fluid">
<form id="form_autenticacao" method="post" action="<c:url value='/login' />"  >
	<form:errors cssClass="error" />
	<fieldset>
	<label for="input_matricula">Matricula</label>
	<input type="text" id="input_matricula" class="input-large" name='j_username'/>
	</fieldset>
	<fieldset>
	<label for="input_senha">Senha</label>
	<input type="password" id="input_senha" class="input-large" name='j_password' />
	</fieldset>	
     
         <fieldset>
         
            
           <fieldset>
		<label>Contrato</label>
		<form:select id="nContrato" path="contrato" name="nContrato"   >
			<form:options items="${contratos}" itemValue="id" itemLabel="contratada" />
		</form:select>
		
		
	</fieldset>
     </fieldset>	
     
	<button type="submit" class="btn btn-submit btn-primary">Autenticar</button> 
</form>

	<form method="get" action="<c:url value='/cadastro'/>">
		<label for="btn_cadastro">Não tem conta? Crie uma agora.</label>
		<button id="btn_cadastro" type="submit" class="btn btn-submit btn-primary">Cadastrar-se</button>
	</form>

</div>


