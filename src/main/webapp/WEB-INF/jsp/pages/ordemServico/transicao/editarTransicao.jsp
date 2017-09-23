<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

 
<ul class="breadcrumb">
	<li><a href="<c:url value="/" />">Página Inicial</a> <span class="divider">/</span>
	</li>
	<li><a href="<c:url value="/ordemServico/listar" />">Ordens de Serviço</a> <span
		class="divider">/</span>
	</li>
	<li><a
		href="<c:url value="/ordemServico/" />${ordemServico.demanda.redmineIssueId}/transicao/listar">Transições</a>
		<span class="divider">/</span>
	</li>
	<li class="active">${transicao.estado.nome} (<joda:format value="${transicao.dataInicio}"
			pattern="dd.MM.yyyy" /> - <joda:format value="${transicao.dataFim}" pattern="dd/MM/yyyy" />)</li>
</ul>
<form:form id="form-transicao-editar" method="post" class="form-horizontal" commandName="transicao">
	<form:hidden path="id" />
	<form:hidden path="estado.nome" />
	<form:hidden path="dataInicio" />
	<form:hidden path="dataFim" />

	<%@include file="../../../templates/ordemServico/ordemServicoPrecificadaTemplate.jsp" %> 


	<h4 id="h4-transicao-editar"><a href="${configuracaoMineiro.urlRedmine}/issues/${ordemServico.demanda.redmineIssueId}">#${ordemServico.demanda.redmineIssueId} -
		${ordemServico.demanda.nome}</a></h4>
	<h5 id="h4-transicao-editar">
		${transicao.estado.nome} (
		<joda:format value="${transicao.dataInicio}" pattern="dd/MM/yyyy" />
		-
		<joda:format value="${transicao.dataFim}" pattern="dd/MM/yyyy" />
		)
	</h5>


	<fieldset>
		<label>Conta Tempo</label>
		<form:checkbox id="input-checkbox-contaTempoOS" class="input-checkbox" path="contaTempoOS" />
		<br />
		<form:errors path="contaTempoOS" cssClass="error" />
	</fieldset>

	<fieldset>
		<label>Conta Recusa</label>
		<form:checkbox id="input-checkbox-contaRecusaOS" class="input-checkbox"
			path="contaRecusaOS" />
		<br />
		<form:errors path="contaRecusaOS" cssClass="error" />
	</fieldset>

	<fieldset id="fieldset-justificativa">
		<label>Justificativa</label>
		<form:textarea id="textarea-justificativa" rows="3" cssErrorClass="error" path="justificativa.descricao" />
		<br />
		<form:errors path="justificativa.descricao" cssClass="error" />
	</fieldset>
	<input type="hidden" name="justificativa.autor" value="${usuario.nome}"/>

	<c:choose>
		<c:when test="${ordemServico.situacao eq 'PRECIFICADA'}">
			<button disabled="disabled" type="submit" class="btn btn-submit btn-primary disabled">Salvar</button>

		</c:when>
		<c:otherwise>
			<button type="submit" class="btn btn-submit btn-primary">Salvar</button>

		</c:otherwise>

	</c:choose>
	<a href="<c:url value="/ordemServico/" />${ordemServico.demanda.redmineIssueId}/transicao/listar"
		class="btn">Cancelar</a>


</form:form>
