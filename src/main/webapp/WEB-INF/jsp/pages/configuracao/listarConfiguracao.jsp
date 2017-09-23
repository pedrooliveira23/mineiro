<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<ul class="breadcrumb">
	<li><a href="<c:url value="/" />">Página Inicial</a> <span class="divider">/</span></li>
	<li class="active">Configuração</li>
</ul>

<div id="div-alert">

</div>

		<table id="table-configuracao-mineiro-properties" class="table table-configuracao table-striped">
			<caption>
				Propriedades do mineiro.properties
			</caption>
			<colgroup>
				<col class="table-configuracao-col-titulo">
				<col class="table-configuracao-col-valor">
	
			</colgroup>
			<tbody>
				<tr>
					<td class="table-configuracao-td-titulo">redmine.url</td>
					<td>${configuracaoMineiro.urlRedmine}</td>
				</tr>
				<tr>
					<td class="table-configuracao-td-titulo">email.destinatario</td>
					<td>${configuracaoMineiro.destinatarioEmail}</td>
				</tr>
				<tr>
					<td class="table-configuracao-td-titulo">email.destinatario.teste</td>
					<td>${configuracaoMineiro.destinatarioEmailTeste}</td>
				</tr>
				<tr>
					<td class="table-configuracao-td-titulo">email.remetente</td>
					<td>${configuracaoMineiro.remetenteEmail}</td>
				</tr>
				<tr>
					<td class="table-configuracao-td-titulo">email.titulo</td>
					<td>${configuracaoMineiro.tituloEmail}</td>
				</tr>
				<tr>
					<td class="table-configuracao-td-titulo">email.titulo.teste</td>
					<td>${configuracaoMineiro.tituloEmailTeste}</td>
				</tr>
				<tr>
					<td class="table-configuracao-td-titulo">mineiro.ambiente</td>
					<td>${configuracaoMineiro.ambienteMineiro}</td>
				</tr>
			</tbody>
		</table>
		
		<button type="button" id="btn-enviar-email" data-url="<c:url value="/admin/configuracao/email/teste" />" class="btn btn-primary btn-submit">Enviar Email de Teste</button> 
		
		
		<table id="table-configuracao-bancos" class="table table-configuracao table-striped">
			<caption>
				Configurações dos Bancos (Mineiro, Redmine e Simus)
			</caption>
			<colgroup>
				<col class="table-configuracao-col-titulo">
				<col class="table-configuracao-col-valor">
	
			</colgroup>
			<tbody>
				<tr>
					<td class="table-configuracao-td-titulo">connection.url do Mineiro</td>
					<td>${configuracaoMineiro.urlBancoMineiro}</td>
				</tr>
				<tr>
					<td class="table-configuracao-td-titulo">connection.url do Redmine</td>
					<td>${configuracaoMineiro.urlBancoRedmine}</td>
				</tr>
				<tr>
					<td class="table-configuracao-td-titulo">connection.url do Simus</td>
					<td>${configuracaoMineiro.urlBancoSimus}</td>
				</tr>
			</tbody>
		</table>
		
		
			