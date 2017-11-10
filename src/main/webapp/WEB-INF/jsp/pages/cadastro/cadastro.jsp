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

<div id="div_cadadstro" class="row-fluid">
<form id="form_cadastro" method="post" action="<c:url value='/cadastro' />" commandName='novoUsuario'  >
	<form:errors cssClass="error" />
	<fieldset>
	<label for="input_email">E-mail</label>
	<input type="email" id="input_email" class="input-large" name='email'/>
	</fieldset>
	<fieldset>
		<label for="input_nome">Nome</label>
		<input type="text" id="input_nome" class="input-large" name='nome'/>
	</fieldset>
	<fieldset>
	<label for="input_senha">Senha</label>
	<input type="password" id="input_senha" class="input-large" name='senha' />
	</fieldset>
     
	<button type="submit" class="btn btn-submit btn-primary">Cadastrar</button>
</form>
</div>


