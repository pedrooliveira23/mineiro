<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<c:if test="${not empty error}">
		<div class="alert alert-error">
			<strong><spring:message code="cadastro.invalido"/></strong>
		</div>
</c:if>
<c:if test="${not empty sucesso}">
		<div class="alert alert-success">
			<strong><spring:message code="cadastro.sucesso"/></strong>
		</div>
</c:if>

<c:if test="${not empty matricula}">
	<div class="alert alert-success">
		<strong>Número de Matrícula: ${matricula}</strong>
	</div>
</c:if>

<div id="div_cadadstro" class="row-fluid">
	<form:form id="form-verificacao-adicionar" method="post" class="form-horizontal" action="cadastroUsuario" commandName="usuario">
	<form:errors cssClass="error" />
	<fieldset>
		<label for="input_nome">Nome</label>
		<input type="text" id="input_nome" class="input-large" name='nome'/>
	</fieldset>
	<fieldset>
	<label for="input_senha">Senha</label>
	<input type="password" id="input_senha" class="input-large" name='senha' />
	</fieldset>
     
	<button type="submit" class="btn btn-submit btn-primary">Cadastrar</button>
	<a href="<c:url value="/autenticacao"/>" class="btn">Voltar</a>
</form:form>
</div>


