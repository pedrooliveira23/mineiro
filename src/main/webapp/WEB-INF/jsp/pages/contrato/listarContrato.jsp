<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<ul class="breadcrumb">
	<li><a href="<c:url value="/" />">Página Inicial</a> <span class="divider">/</span></li>
	<li class="active">Contrato</li>
</ul>

<div id="div-alert alert-error" style="color: black; background-color: rgb(255, 194, 194);  background-image: aliceblue;">
${erro}
</div>

	<%@include file="../../templates/ordemServico/ordemServicoPrecificadaTemplate.jsp" %> 
					

		<a href="<c:url value="/admin/contrato/" />criar"
			class="btn btn-primary">Adicionar Contrato</a>
	



<table class="table table-inspecao">
	<colgroup>
		<col class="col-contrato">
		<col class="col-contratada">
		<col class="col-quantidade">
                <col class="col-valor">
		<col class="col-acoes">
	</colgroup>
	<thead>
		<tr>
			<th>Contrato</th>
			<th>Contratada</th>
			<th>Quantidade P.F</th>
			<th>Valor P.F. Atual</th>
                        <th>Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="contrato" items="${contratos}" varStatus="i">
			<tr>
				<td>${contrato.numero}</td>
				<td>${contrato.contratada}</td>
				<td><fmt:formatNumber type="number" minFractionDigits="0"
						value="${contrato.quantitativo}" /></td>
                                <td><fmt:formatNumber type="number" minFractionDigits="2"
						value="${contrato.valorUnitario}" /></td>
				<td>
                                    
                                  <c:set var="cid" value="${contrato.id}"/>  
                                  <c:set var="caid" value="${sessionScope.contratada.id}"/>
                                     <c:choose>
                                        <c:when test="${cid ==caid }">
                                                
                                                
                                        
                                                    
                                                    
                                                    <a          
								href="<c:url value="/admin/contrato/" />${contrato.id}/editar"
								class="btn btn-small btn-acao">Editar</a>
							<a      disabled="disabled" style="pointer-events: none; cursor: default;"
								href="<c:url value="/admin/contrato/${contrato.id}/excluir" />"
								class="btn btn-small btn-acao">Excluir</a>

                                        </c:when>
                                        <c:otherwise>
                                                
                                                <a
								href="<c:url value="/admin/contrato/" />${contrato.id}/editar"
								class="btn btn-small btn-acao">Editar</a>
							<a
								href="<c:url value="/admin/contrato/${contrato.id}/excluir" />"
								class="btn btn-small btn-acao">Excluir</a>

                                        </c:otherwise>

                                </c:choose>
                                    
                                    
                                    
                                    
  

						
				</td>
			</tr>
		</c:forEach>
</table>








