<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<ul class="breadcrumb">
	<li><a href="<c:url value="/" />">Página Inicial</a> <span class="divider">/</span></li>
	<li><a href="<c:url value="/ordemServico/listar" />">Ordens de Serviço</a> <span
		class="divider">/</span></li>
	<li class="active">Inspeções</li>
</ul>

	<%@include file="../../templates/ordemServico/ordemServicoPrecificadaTemplate.jsp" %> 
					
	<c:if test="${ordemServico.situacao ne 'PRECIFICADA'}">

		<a href="<c:url value="/ordemServico/" />${ordemServico.demanda.redmineIssueId}/inspecao/criar"
			class="btn btn-primary">Adicionar Inspeção</a>
	</c:if>



<table class="table table-inspecao">
	<colgroup>
		<col class="col-data">
		<col class="col-tipoInspecao">
		<col class="col-percentual">
		<col class="col-acoes">
	</colgroup>
	<thead>
		<tr>
			<th>Data</th>
			<th>Tipo de Inspeção</th>
			<th>Percentual de Aprovação</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="inspecao" items="${inspecoes}" varStatus="i">
			<tr>
				<td><joda:format value="${inspecao.dia}" pattern="dd/MM/yyyy" />
				</td>
				<td>${inspecao.tipoInspecao.nome}</td>
				<td><fmt:formatNumber type="number" minFractionDigits="0"
						value="${inspecao.percentualAprovacao}" />%</td>
				<td><c:choose>
						<c:when test="${ordemServico.situacao eq 'PRECIFICADA'}">
			

						</c:when>
						<c:otherwise>
							<a
								href="<c:url value="/ordemServico/" />${ordemServico.demanda.redmineIssueId}/inspecao/${inspecao.id}/editar"
								class="btn btn-small btn-acao">Editar</a>
							<a
								href="<c:url value="/ordemServico/" />${ordemServico.demanda.redmineIssueId}/inspecao/${inspecao.id}/excluir"
								class="btn btn-small btn-acao">Excluir</a>

						</c:otherwise>

					</c:choose>
				</td>
			</tr>
		</c:forEach>
</table>








