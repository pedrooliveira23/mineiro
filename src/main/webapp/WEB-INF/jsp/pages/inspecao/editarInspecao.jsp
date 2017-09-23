<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<ul class="breadcrumb">
	<li><a href="<c:url value="/" />">Página Inicial</a> <span class="divider">/</span></li>
	<li><a href="<c:url value="/ordemServico/listar" />">Ordens de Serviço</a> <span
		class="divider">/</span></li>
	<li><a
		href="<c:url value="/ordemServico/" />${ordemServico.demanda.redmineIssueId}/inspecao/listar">Inspeções</a>
		<span class="divider">/</span></li>
	<li class="active">${inspecao.tipoInspecao.nome} - ${inspecao.artefatoInspecionado} - <joda:format
			value="${inspecao.dia}" pattern="dd/MM/yyyy" /></li>
</ul>
<form:form id="form-verificacao-adicionar" method="post" class="form-horizontal"
	commandName="inspecao">
	<form:hidden path="demanda.id" />
	<form:hidden path="demanda.redmineIssueId" />
	<form:hidden path="id" />

	<%@include file="../../templates/ordemServico/ordemServicoPrecificadaTemplate.jsp" %> 
	
	<h4>
		${inspecao.tipoInspecao.nome} - ${inspecao.artefatoInspecionado} -
		<joda:format value="${inspecao.dia}" pattern="dd/MM/yyyy" />
	</h4>

	<fieldset>
		<label>Tipo de Inspeção</label>
		<form:select id="select-tipoInspecao" path="tipoInspecao" cssErrorClass="error">
			<form:options items="${tiposInspecao}" itemValue="id" itemLabel="nome" />
		</form:select>
		<br />
		<form:errors path="tipoInspecao" cssClass="error" />
	</fieldset>
	<fieldset>
		<label>Artefato Inspecionado</label>
		<form:input id="input-artefatoInspecionado" class="input-xxlarge"
			cssErrorClass="input-xxlarge error" path="artefatoInspecionado" />
		<br />
		<form:errors path="artefatoInspecionado" cssClass="error" />
	</fieldset>
	<fieldset>
		<label>Data</label>
		<form:input id="input-dia" class="input-small input-data" cssErrorClass="input-small error input-data" path="dia" />
		<br />
		<form:errors path="dia" cssClass="error" />
	</fieldset>

	<fieldset>
		<label>Percentual de Aprovação</label>
		<form:input id="input-percentualAprovacao" class="input-small" cssErrorClass="input-small error"
			path="percentualAprovacao" />
		<span id="span-percentualAprovacao"> % </span> <br />
		<form:errors path="percentualAprovacao" cssClass="error" />
	</fieldset>



	<c:choose>
		<c:when test="${ordemServico.situacao eq 'PRECIFICADA'}">
			<button disabled="disabled" type="submit" name="salvar" class="btn btn-submit btn-primary disabled">Salvar</button>

		</c:when>
		<c:otherwise>
			<button type="submit" name="salvar" class="btn btn-submit btn-primary">Salvar</button>

		</c:otherwise>

	</c:choose>

	<a href="<c:url value="/ordemServico/" />${ordemServico.demanda.redmineIssueId}/inspecao/listar"
		class="btn">Cancelar</a>


</form:form>
