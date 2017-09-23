<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<ul class="breadcrumb">
	<li><a href="<c:url value="/" />">Página Inicial</a> <span class="divider">/</span></li>
	<li><a href="<c:url value="/contrato/listar" />">Contrato</a> <span class="divider">/</span></li>
        <li class="active">Editar Valor Vigência</li>
</ul>

<div id="div-alert">

</div>

<form:form id="form-verificacao-adicionar" method="post" class="form-horizontal" commandName="valor">

	<form:hidden path="id" />
        <form:hidden path="contrato.id" />
	<h4>Editar Valor</h4>


	<fieldset>
		<label>Quantidade P.F</label>
		<form:input id="input-percentualAprovacao" class="input-small bigint" cssErrorClass="input-small error bigint"
			path="quantitativo" />
		<span id="span-percentualAprovacao"> </span> <br />
		<form:errors path="quantitativo" cssClass="error" />
	</fieldset>
        
        <fieldset>
		<label>Valor P.F</label>
		<form:input id="input-percentualAprovacao" class="input-small bigint" cssErrorClass="input-small error bigint"
			path="valorUnitario" />
		<span id="span-percentualAprovacao"> </span> <br />
		<form:errors path="valorUnitario" cssClass="error" />
	</fieldset>
        
        <fieldset>
		<label>Inicio Vigência</label>
		<form:input id="input-iniExpediente" class="input-small input-data" cssErrorClass="input-small error input-data"
			path="dataInicioVigencia" format="prop:dateFieldFormat" validate="required"/>
		<span id="span-percentualAprovacao"> </span> <br />
		<form:errors path="dataInicioVigencia" cssClass="error" />
	</fieldset>
        
        



			<button type="submit" class="btn btn-submit btn-primary">Salvar</button>

		
                        <a href="<c:url value="/admin/contrato/"/>${valor.contrato.id}/editar" class="btn">Cancelar</a>


</form:form>
