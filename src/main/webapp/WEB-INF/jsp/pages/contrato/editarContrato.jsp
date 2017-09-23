<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<ul class="breadcrumb">
	<li><a href="<c:url value="/" />">Página Inicial</a> <span class="divider">/</span></li>
	<li><a href="<c:url value="/admin/contrato/listar" />">Contrato</a> <span class="divider">/</span></li>
        <li class="active">Editar Contrato</li>
</ul>

<div id="div-alert alert-error" style="color: black; background-color: rgb(255, 194, 194);  background-image: aliceblue;">
${mensagem}
</div>
        
        <form:form id="form-verificacao-adicionar" method="post" class="form-horizontal" commandName="contrato">

	<form:hidden path="id" />

	<h4>Novo Contrato</h4>

        <table>
            <tr>
                <td colspan="2">
	<fieldset>
		<label>Número do Contrato</label>
		<form:input id="input-artefatoInspecionado" class="input-xxlarge"
			cssErrorClass="input-xxlarge error" path="numero" />
		<br />
		<form:errors path="numero" cssClass="error" />
	</fieldset>
        </td>
        <td colspan="2">
	<fieldset>
		<label>Empresa</label>
		<form:input id="input-artefatoInspecionado" class="input-xxlarge"
			cssErrorClass="input-xxlarge error" path="contratada" />
		<br />
		<form:errors path="contratada" cssClass="error" />
	</fieldset>
        </td>
        </tr>
        
        
        
        
        
        
        
        <tr>
        <td>

	<fieldset>
		<label>Quantidade P.F</label>
		<form:input id="input-percentualAprovacao" class="input-small bigint" cssErrorClass="input-small error"
			path="quantitativo" />
		<span id="span-percentualAprovacao"> </span> <br />
		<form:errors path="quantitativo" cssClass="error" />
	</fieldset>
        </td>
        <td>
        
        <fieldset>
		<label>Valor P.F Atual</label>
		<form:input id="input-percentualAprovacao" class="input-small bigint" cssErrorClass="input-small error"
			path="valorUnitario" />
		<span id="span-percentualAprovacao"> </span> <br />
		<form:errors path="valorUnitario" cssClass="error" />
	</fieldset>
        </td>
        <td>
        <fieldset>
		<label>Inicio Expediente</label>
		<form:input id="input-iniExpediente" class="input-small input-data time" cssErrorClass="input-small error"
			path="inicioExpediente1" format="prop:dateFieldFormat" validate="required"/>
		<span id="span-percentualAprovacao"> </span> <br />
		<form:errors path="inicioExpediente1" cssClass="error" />
	</fieldset>
        </td>
        <td>
        <fieldset>
		<label>Fim Expediente</label>
		<form:input id="input-fimExpediente" class="input-small input-data time" cssErrorClass="input-small error"
			path="fimExpediente1" format="prop:dateFieldFormat" validate="required" disabled="disabled"
                        
                        />
		<span id="span-percentualAprovacao"> </span> <br />
		<form:errors path="fimExpediente1" cssClass="error" />
                
	</fieldset>
        
         </td>
         
        </tr>       
        </table>
        
                <hr style="margin-top: 0px">
        
       
        <a href="<c:url value="/admin/contrato/" />${contrato.id}/valores/criar"
			class="btn btn-primary" style="margin-top: 40px;">Adicionar Valores Vigência</a> 
                <div style="margin-top: 10px;border: 0px solid #DDD">
               
    <table class="table table-inspecao" >
	<colgroup>
		<col class="col-contrato">
		<col class="col-acoes">
	</colgroup>
	<thead>
		<tr>
			<th>Valor P.F</th>
                        <th>Inicio Vigência</th>
                        <th>Fim Vigência</th>
                        <th>Ações</th>

		</tr>
	</thead>
	<tbody>
		<c:forEach var="valor" items="${valores}" varStatus="i">
			<tr>
                                <td>${valor.valorUnitario}</td>
				<td><joda:format value="${valor.dataInicioVigencia}" pattern="dd/MM/yyyy" /></td>
                                <td><joda:format value="${valor.dataFimVigencia}" pattern="dd/MM/yyyy" /></td>
                                
                                <td>
                                
                                <c:choose>
                                        <c:when test="${not empty valor.dataFimVigencia }">
                                                
                                                
                                                <a disabled="disabled"  
                                                   style="pointer-events: none; cursor: default;"
                                                   href="<c:url value="/admin/contrato/${contrato.id}/valores/" />${valor.id}/editar"
                                                    class="btn btn-small btn-acao">Editar</a>

                                                <a disabled="disabled" style="pointer-events: none; cursor: default;"
                                                    href="<c:url value="/admin/contrato/${contrato.id}/valores/${valor.id}/excluir" />"
                                                    class="btn btn-small btn-acao">Excluir</a>

                                        </c:when>
                                        <c:otherwise>
                                                
                                                
                                                <a  href="<c:url value="/admin/contrato/${contrato.id}/valores/" />${valor.id}/editar"
                                                    class="btn btn-small btn-acao">Editar</a>
                                                                
                                                <a  href="<c:url value="/admin/contrato/${contrato.id}/valores/${valor.id}/excluir" />"
                                                    class="btn btn-small btn-acao">Excluir</a>

                                        </c:otherwise>

                                </c:choose>
                                
                                
                                
                                

				
							
							

						
				</td>
			</tr>
		</c:forEach>
</table>
        
                </div>
        
        
        
        
        
        
        
        
        
        
                        <hr style="margin-top: 80px;">
        <button type="submit" class="btn btn-submit btn-primary" >Salvar</button>

		
                        <a href="<c:url value="/admin/contrato/listar"/>" class="btn" >Cancelar</a>
        
    </form:form>
        
                        
                
