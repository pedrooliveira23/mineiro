<%@page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<ul class="breadcrumb">
	<li><a href="<c:url value="/" />">Página Inicial</a> <span class="divider">/</span></li>
	<li class="active">Usuários ${breadcrumb}</li>
</ul>


<c:choose>
	<c:when test="${empty usuarios}">
		<div class="alert alert-block alert-info fade in">

			<h4>
				Nenhum usuário encontrado.
			</h4>

		</div>
	</c:when>
	<c:otherwise>
		<table id="table-ordemServico" class="table table-ordemServico">
			<colgroup>
				<col class="col-half">
				<col class="col-half">
				<col class="col-half2">
				<col class="col-half3">
			</colgroup>

			<thead>
			<tr>
				<th class="col-half">Matrícula</th>
				<th class="col-half">Nome</th>
				<th class="col-half2">Papeis</th>
				<th class="col-half3"></th>
			</tr>
			</thead>

			<tbody>

			<c:forEach var="usuario" items="${usuarios}" varStatus="i">
			<tr>
				<td class="col-half">${usuario.matricula}</td>
				<td class="col-half">${usuario.nome}</td>
				<td class="col-half2">${usuario.grupos}</td>
				<td class="col-half3"><a href="<c:url value="/admin/grupo/" />${usuario.id}/editar"	class="btn btn-small btn-acao">Editar</a></td>
			</tr>
			</c:forEach>
		</table>
	</c:otherwise>
</c:choose>