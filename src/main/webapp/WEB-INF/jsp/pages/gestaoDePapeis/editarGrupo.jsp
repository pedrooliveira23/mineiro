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
	<li><a href="<c:url value="/listarUsuarios" />">Usuários</a> <span class="divider">/</span></li>
	<li class="active"><span class="span-tipoRelatorio">Gestão de Papeis</span> <span class="divider">/</span>${usuario.nome}</li>
</ul>


		<table id="table-ordemServico" class="table table-ordemServico">
			<colgroup>
				<col class="col-half2">
			</colgroup>

			<thead>
			<tr>
				<th class="col-half2">Papel já atribuído</th>
			</tr>
			</thead>

			<tbody>

			<c:forEach var="grupo" items="${usuario.grupos}" varStatus="i">
			<tr>
				<td class="col-half2">${grupo.nome}</td>
				<td class="col-half3"><a href="<c:url value="/admin/grupo/usuario/"/>${usuario.id}/removerGrupo/${grupo.id}"	class="btn btn-small btn-acao">Remover</a></td>
			</tr>
			</c:forEach>

		</table>

		<table id="table-ordemServico2" class="table table-ordemServico">
			<colgroup>
				<col class="col-half2">
			</colgroup>

			<thead>
			<tr>
				<th class="col-half2">Papel Disponivel</th>
			</tr>
			</thead>

			<tbody>

			<c:forEach var="grupo1" items="${grupos}" varStatus="i">
			<tr>
				<td class="col-half2">${grupo1.nome}</td>
				<td class="col-half3"><a href="<c:url value="/admin/grupo/usuario/"/>${usuario.id}/adicionarGrupo/${grupo1.id}"	class="btn btn-small btn-acao">Adicionar</a></td>
			</tr>
			</c:forEach>

		</table>
