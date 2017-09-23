<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda" %>
<ul class="breadcrumb">
	<li><a href="<c:url value="/" />">Página Inicial</a> <span class="divider">/</span></li>
	<li class="active">Dia Não Útil</li>
</ul>


	<a id="btn-adicionar-diaNaoUtil" href="<c:url value="/admin/diaNaoUtil/criar" />" class="btn btn-primary ">Adicionar Dia</a>
	

	<table class="table table-diaNaoUtil">
		<colgroup>
			<col class="table-diaNaoUtil-col-data">
			<col class="table-diaNaoUtil-col-titulo">
			<col class="table-diaNaoUtil-col-repete">
			<col class="table-diaNaoUtil-col-ativo">
			<col class="table-diaNaoUtil-col-acoes">
		</colgroup>
		<thead>
			<tr>
				<th>Data</th>
				<th>Titulo</th>
				<th>Repete Anualmente</th>
				<th>Ativo</th>
				<th>Ações</th>
			</tr>
		</thead>
		<tbody>

				<c:forEach var="diaNaoUtil" items="${diaNaoUtilCommand.diasNaoUteis}" varStatus="i">
				<tr>
					<td><joda:format value="${diaNaoUtil.dia}" pattern="dd/MM/yyyy"/></td>
					<td>${diaNaoUtil.descricao}</td>
					<td>
					<c:choose>
					<c:when test="${diaNaoUtil.anual}">
					Sim
					</c:when>
					<c:otherwise>
					Não
					</c:otherwise>
					</c:choose>
					</td>
					<td>
					<c:choose>
					<c:when test="${diaNaoUtil.ativo}">
					Sim
					</c:when>
					<c:otherwise>
					Não
					</c:otherwise>
					</c:choose>
					</td>
					<td>
					<c:choose>
					<c:when test="${diaNaoUtil.ativo}">
					<a href="<c:url value="/admin/diaNaoUtil/${diaNaoUtil.id}/editar/bloquear" />" type="submit" name="bloquear" class="btn btn-small btn-acao">Bloquear</a>
					</c:when>
					<c:otherwise>
					<a href="<c:url value="/admin/diaNaoUtil/${diaNaoUtil.id}/editar/desbloquear" />" type="submit" name="desbloquear" class="btn btn-small btn-acao">Desbloquear</a>
					</c:otherwise>
					</c:choose>
					<a href="<c:url value="/admin/diaNaoUtil/${diaNaoUtil.id}/excluir" />" type="submit" name="excluir" class="btn btn-small btn-acao">Excluir</a>
					</td>
				</tr>
			</c:forEach>
	</table>








