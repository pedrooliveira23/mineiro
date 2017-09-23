<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda" %>
    



<form:form method="post" commandName="diaNaoUtil">
	
		<ul class="breadcrumb">
			<li><a href="<c:url value="/" />">Página Inicial</a> <span class="divider">/</span></li>
			<li class="active"><a href="<c:url value="/admin/diaNaoUtil/listar" />">Dias Não Úteis</a><span class="divider">/</span></li>
			<li class="active">Editar</li>
		</ul>

			<h3>Cadastro de Dia</h3>
		
			<fieldset>
				<label>Titulo</label> 
				<form:input id="input-titulo" class="input-xxlarge" cssErrorClass="input-xxlarge error" path="descricao" />	
					
				<br/>
				<form:errors path="descricao" cssClass="error" />
			</fieldset>
			
			<fieldset>
				<label>Dia</label>
				<form:input id="input-dia" class="input-small input-data" cssErrorClass="input-small error input-data" path="dia" />
				
				<br/>
				<form:errors  cssClass="error" path="dia" />
			</fieldset>
			
			<fieldset>
				<label> Repete anualmente no mesmo dia? </label> <label
					class="radio inline"> 
					<form:radiobutton path="anual" value="true"  />
					Sim </label> <label class="radio inline"> 
					<form:radiobutton  path="anual" value="false" />
					Não </label>

				<br/>
				<form:errors path="anual" cssClass="error" />
			</fieldset>
			

	
			<button type="submit" class="btn btn-submit btn-primary">Salvar</button> 
			<a class="btn" href="<c:url value="/admin/diaNaoUtil/listar" />">Cancelar</a> 


</form:form>
