<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<ul class="breadcrumb">
	<li><a href="<c:url value="/" />">Página Inicial</a> <span class="divider">/</span></li>
	<li><a href="<c:url value="/ordemServico/listar" />">Ordens de Serviço</a> <span
		class="divider">/</span></li>
	<li class="active">Transições</li>

</ul>

<form:form method="post" commandName="transicaoCommand">

	<%@include file="../../../templates/ordemServico/ordemServicoPrecificadaTemplate.jsp" %> 
	
	<table class="table table-transicao">
		<caption>
			<a href="${configuracaoMineiro.urlRedmine}/issues/${ordemServico.demanda.redmineIssueId}">#${ordemServico.demanda.redmineIssueId} -
		${ordemServico.demanda.nome}</a>
		</caption>
		<colgroup>
			<col class="table-transicao-col-nota">
			<col class="table-transicao-col-trasicao">
			<col class="table-transicao-col-contaTempoOS">
			<col class="table-transicao-col-contaRecusaOS">
			<col class="table-transicao-col-tempo">
			<col class="table-transicao-col-tempoUtil">
			<col class="table-transicao-col-periodo">
			<col class="table-transicao-col-acoes">

		</colgroup>
		<thead>
			<tr>
			
				<th>						
					
						<a class="button-expandir-trs"><i class="icon-plus"></i></a> 
					
				</th>
			
				<th>Transição de Estado</th>

				<th class="table-transicao-th-contaTempoOS">Conta Tempo</th>

				<th class="table-transicao-th-contaRecusaOS">Conta Recusa</th>

				<th>Tempo</th>
				
				<th>Tempo Útil</th>

				<th>Período</th>
				<th class="table-transicao-th-acoes">Ações</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="trCor" value="cinza" />
			<c:forEach var="transicao" items="${transicaoCommand.transicoes}" varStatus="i">
				<c:choose>
			        <c:when test="${trCor eq 'branca'}">
			            <c:set var="trCor" value="cinza" />
			        </c:when>
			        <c:otherwise>
			            <c:set var="trCor" value="branca" />
			        </c:otherwise>
			    </c:choose> 
				<tr class="tr-${trCor}" data-nota-class="table-transicao-tr${i.index }-nota">
					<td>
						<c:if test="${not empty transicao.notas}">
							<a class="button-expandir-tr"><i class="icon-plus"></i></a> 
						</c:if>
					
					</td>
					<td>

					${transicao.estado.nome}
				
					</td>
					<td class="table-transicao-td-contaTempoOS"><form:checkbox disabled="true"
							class="input-checkbox" path="transicoes[${i.index }].contaTempoOS" />
					</td>
					<td class="table-transicao-td-contaRecusaOS"><form:checkbox disabled="true"
							class="input-checkbox" path="transicoes[${i.index }].contaRecusaOS" />
					</td>
					<td><spring:eval expression="transicao.duracaoTotal" />
					</td>
					<td><spring:eval expression="transicao.duracaoContandoTempo" />
					</td>
					<td><span><joda:format value="${transicao.dataInicio}" pattern="dd/MM/yy - H:mm:ss"/>
					<br/><joda:format value="${transicao.dataFim}" pattern="dd/MM/yy - H:mm:ss"/></span>
					
					</td>
					<td class="table-transicao-td-acoes"><c:choose>
							<c:when test="${ordemServico.situacao eq 'PRECIFICADA'}">
	
							</c:when>
							<c:otherwise>

								<a class="btn btn-small btn-acao"
									href="<c:url value="/ordemServico/" />
						${ordemServico.demanda.redmineIssueId}/transicao/${transicao.id}/editar">Editar</a>


							</c:otherwise>

						</c:choose>
					</td>
				</tr>
				<c:forEach var="nota" items="${transicao.notas}" varStatus="j">
					<tr class="tr-${trCor} table-transicao-tr${i.index }-nota table-transicao-tr-nota invisivel">
						<td colspan="8">
							<span class="span-nota"><strong>Nota: </strong>${nota.nota}</span>
							<span class="span-autor"><strong>Autor: </strong>${nota.autor}</span>
						</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td class="table-transicao-td-duracaoTotal" colspan="4">Duração Total</td>
				<td colspan="4"><spring:eval expression="ordemServico.demanda.duracaoTotal" />
				</td>
			</tr>
			<tr>
				<td class="table-transicao-td-duracaoContandoTempoOS" colspan="4">Duração Contando Tempo OS</td>
				<td colspan="4"><spring:eval expression="ordemServico.demanda.duracaoContandoTempoOS" />
				</td>
			</tr>
			<tr>
				<td class="table-transicao-td-duracaoContandoTempoCJF" colspan="4">Tempo Gasto pelo CJF</td>
				<td colspan="4"><spring:eval expression="ordemServico.demanda.duracaoContandoTempoCJF" />
				</td>
			</tr>
			<tr>
				<td class="table-transicao-td-prazoMaximo" colspan="4">Prazo Máximo</td>
				<td colspan="4"><spring:eval expression="ordemServico.demanda.duracaoPrazoMaximo" />
				</td>
			</tr>
			<tr>
				<td class="table-transicao-td-quantidadeDiasAtraso" colspan="4">Quantidade de Dias de Atraso</td>
				<td colspan="4"><spring:eval expression="ordemServico.demanda.duracaoAtraso" />
				</td>
			</tr>
		</tfoot>
	</table>

</form:form>