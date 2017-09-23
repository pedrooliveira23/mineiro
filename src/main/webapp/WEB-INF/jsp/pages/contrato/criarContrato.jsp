<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<ul class="breadcrumb">
	<li><a href="<c:url value="/" />">Página Inicial</a> <span class="divider">/</span></li>
	<li><a href="<c:url value="/admin/contrato/listar" />">Contrato</a> <span class="divider">/</span></li>
        <li class="active">Novo Contrato</li>
</ul>

<div id="div-alert">

</div>
        <%@include file="../../templates/ordemServico/ordemServicoPrecificadaTemplate.jsp" %> 
        
<form:form id="form-verificacao-adicionar" method="post" class="form-horizontal" commandName="contrato">

	<form:hidden path="id" />

	<h4>Novo Contrato</h4>

	
	<fieldset>
		<label>Número do Contrato</label>
		<form:input id="input-artefatoInspecionado" class="input-xxlarge"
			cssErrorClass="input-xxlarge error" path="numero" />
		<br />
		<form:errors path="numero" cssClass="error" />
                
	</fieldset>
	<fieldset>
		<label>Empresa</label>
		<form:input id="input-artefatoInspecionado" class="input-xxlarge"
			cssErrorClass="input-xxlarge error" path="contratada" />
		<br />
		<form:errors path="contratada" cssClass="error" />
	</fieldset>

	<fieldset>
		<label>Quantidade P.F</label>
		<form:input id="input-percentualAprovacao" class="input-small bigint" cssErrorClass="input-small error bigint"
			path="quantitativo" />
		<span id="span-percentualAprovacao"> </span> <br />
		<form:errors path="quantitativo" cssClass="error" />
	</fieldset>
        
        <fieldset>
		<label>Valor P.F Atual</label>
		<form:input id="input-percentualAprovacao" class="input-small bigint" cssErrorClass="input-small error bigint"
			path="valorUnitario" />
		<span id="span-percentualAprovacao"> </span> <br />
		<form:errors path="valorUnitario" cssClass="error" />
	</fieldset>
        
        <fieldset>
		<label>Inicio Expediente</label>
		<form:input id="input-iniExpediente" class="input-small  time" cssErrorClass="input-small error time"
			path="inicioExpediente1" format="prop:dateFieldFormat" validate="required"/>
		<span id="span-percentualAprovacao"> </span> <br />
		<form:errors path="inicioExpediente1" cssClass="error" />
	</fieldset>
        
        <fieldset>
		<label>Fim Expediente</label>
		<form:input id="input-fimExpediente" class="input-small  time" cssErrorClass="input-small error time"
			path="fimExpediente1" format="prop:dateFieldFormat" validate="required" disabled="disabled"/>
		<span id="span-percentualAprovacao"> </span> <br />
		<form:errors path="fimExpediente1" cssClass="error" />
	</fieldset>
        



			<button type="submit" class="btn btn-submit btn-primary">Salvar</button>

		
                        <a href="<c:url value="/admin/contrato/listar"/>" class="btn">Cancelar</a>


</form:form>
