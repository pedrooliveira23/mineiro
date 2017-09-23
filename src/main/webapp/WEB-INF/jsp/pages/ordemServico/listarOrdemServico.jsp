<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<ul class="breadcrumb">
	<li><a href="<c:url value="/" />">Página Inicial</a> <span class="divider">/</span></li>
	<li class="active">Ordens de Serviço ${breadcrumb}</li>
</ul>

<sec:authorize access="hasRole('MINEIRO - Administrador')">
	
	
	<a id="btn-atualizar" href="<c:url value="/ordemServico/atualizar" />"
		class="btn pull-right" >
		<i class="icon-repeat"></i> Atualizar Mineiro
	</a>

</sec:authorize>
<script>
    
    function hover(e) {
            
              //  alert(e.getAttribute("data-valor"));
              
              //  e.popover({
               //     title:"<strong>Solicitante</strong>",
               //     html : true,
               //     trigger : 'hover',      
                //    content :  '<div>' + e.getAttribute("data-valor")+'</div>'
                    //content : function() {
                   //     return '<img src="' + $(this).data('img') + '" />';
                    //}
               // });
            };
</script>

<form:form id="form-ordemServico-tempo" method="post" class="form-inline" commandName="filtroCommand">

	<label>Inicio</label>
	<form:input id="input-inicio" class="input-small input-data" cssErrorClass="input-small error input-data" path="inicio" />
	<label>Fim</label>
	<form:input id="input-fim" class="input-small input-data" cssErrorClass="input-small error input-data" path="fim" />
	<label>Referência</label> 
	<form:select id="select-referenciaPesquisa" cssErrorClass="error" path="referenciaPesquisa" >	
			<form:options  items="${referenciasPesquisa}" />
	</form:select>	
	<label>Classificação</label> 
	<form:select id="select-classificacao" cssErrorClass="error" path="classificacao" >	
			<form:options  items="${classificacoes}" />
	</form:select>		
	
	<button id="btn-pesquisar" class="btn" type="button"> 
		<i class="icon-search"></i> Pesquisar
	</button>
</form:form>
<c:choose>
	<c:when test="${empty ordensServico}">
		<div class="alert alert-block alert-info fade in">
			
			<h4>
				Nenhuma ordem de serviço encontrada. 
			</h4>
	
		</div>
	</c:when>
	<c:otherwise>
		<table id="table-ordemServico" class="table table-ordemServico">
			<colgroup>
                                <col class="table-ordemServico-col-index">
                                <col class="table-ordemServico-col-projeto">
				<col class="table-ordemServico-col-titulo">
                                <col class="table-ordemServico-col-data">
				
				<c:choose>
						<c:when test="${classificacao eq 'CANCELADA' || classificacao eq 'CONCLUIDA'
						|| classificacao eq 'TODAS'}">
							<col class="table-ordemServico-col-data">
							<col class="table-ordemServico-col-data">
						</c:when>
						<c:otherwise>
							<col class="table-ordemServico-col-data">
						</c:otherwise>
				</c:choose>
				<col class="table-ordemServico-col-situacao">
                                <col class="table-ordemServico-col-situacao">
                                <col class="table-ordemServico-col-tamanho">
				<col class="table-ordemServico-col-indicador-atraso">
				<col class="table-ordemServico-col-indicador-recusa">
				<col class="table-ordemServico-col-indicador-conformidade">
				<col class="table-ordemServico-col-acoes">
			</colgroup>
			<thead>
				<tr>
                                        <th class="table-ordemServico-th-indicador">#</th>
                                        <th class="table-ordemServico-th-situacao">Projeto</th>
                                        <th class="table-ordemServico-th-situacao">Ordem de Serviço</th>
                                        <!--<th class="table-ordemServico-th-data">Estimativa</th>-->
					<c:choose>
						<c:when test="${classificacao eq 'CANCELADA' || classificacao eq 'CONCLUIDA'
							|| classificacao eq 'TODAS'}">
						<th id="table-ordemServico-th-abertura" class="table-ordemServico-th-data">Abertura</th>
                                                <th id="table-ordemServico-th-estimativa" class="table-ordemServico-th-indicador">Estimativa</th>
							<th id="table-ordemServico-th-conclusao"  class="table-ordemServico-th-data">Conclusão</th>
						</c:when>
						<c:otherwise>
							<th id="table-ordemServico-th-abertura" class="table-ordemServico-th-data">Abertura</th>
                                                        <th id="table-ordemServico-th-estimativa"  class="table-ordemServico-th-indicador">Estimativa</th>
						</c:otherwise>
					</c:choose>
					<th class="table-ordemServico-th-situacao">Situação</th>
                                        <th class="table-ordemServico-th-situacao">Estado</th>
                                        <th class="table-ordemServico-th-tamanho">Tamanho</th>
					<th id="table-ordemServico-th-indicador-ac" class="table-ordemServico-th-indicador">I<sub>AC</sub></th>
					<th id="table-ordemServico-th-indicador-r" class="table-ordemServico-th-indicador">I<sub>R</sub></th>
					<th id="table-ordemServico-th-indicador-c" class="table-ordemServico-th-indicador">I<sub>C</sub></th>
					<th class="table-ordemServico-th-acoes">Ações</th>
				</tr>
			</thead>
			<tbody>
		
				<c:forEach var="ordemServico" items="${ordensServico}" varStatus="i">
                                    
					<tr class="table-ordemServico-tr-dados">
                                            <td >
                                                    ${i.index+1}
                                                </td>
                                                <td class="table-ordemServico-td-projeto" data-toggle="tooltip" title="Aberto por: ${ordemServico.demanda.autor}">
                                                    ${ordemServico.projeto.nome}
                                                </td>
						<c:choose>
							<c:when test="${ordemServico.demanda.incompleta}">
                                                            <td class="table-ordemServico-td-titulo-os-incompleta"><a href="${configuracaoMineiro.urlRedmine}/issues/${ordemServico.demanda.redmineIssueId}" target="_blank">#${ordemServico.demanda.redmineIssueId}</a> - ${ordemServico.demanda.nome}</td>
							</c:when>
							<c:otherwise>
                                                            <td class="table-ordemServico-td-titulo"><a href="${configuracaoMineiro.urlRedmine}/issues/${ordemServico.demanda.redmineIssueId}" target="_blank">#${ordemServico.demanda.redmineIssueId}</a> - ${ordemServico.demanda.nome}</td>
							</c:otherwise>
						</c:choose>
                                                                
                                                
                                                               
                                                              
                                                                
                                                      
						<c:choose>
							<c:when test="${classificacao eq 'CANCELADA' || classificacao eq 'CONCLUIDA'
								|| classificacao eq 'TODAS'}">
								
                                                                
                                                            <c:if test="${ordemServico.demanda.transicoes.size()<1}">
                                                                    <td class="table-ordemServico-td-data"><joda:format value="${ordemServico.demanda.dataCriacao}" pattern="dd/MM/yy"/></td>
                                                                </c:if>
                                                                <c:if test="${ordemServico.demanda.transicoes.size()>=1}">
                                                                    <td class="table-ordemServico-td-data"><joda:format value="${ordemServico.demanda.transicoes.get(0).getDataInicio()}" pattern="dd/MM/yy"/></td>
                                                                </c:if>
                                                                
                                                                <td class="table-ordemServico-td-data"><joda:format value="${ordemServico.demanda.dataPrevista}" pattern="dd/MM/yy"/></td>
								<td class="table-ordemServico-td-data"><joda:format value="${ordemServico.demanda.dataFinalizacao}" pattern="dd/MM/yy"/></td>
							</c:when>
							<c:otherwise>
                                                            <c:if test="${ordemServico.demanda.transicoes.size()<1}">
                                                                <td class="table-ordemServico-td-data"><joda:format value="${ordemServico.demanda.dataCriacao}" pattern="dd/MM/yy"/></td>
                                                            </c:if>
                                                            <c:if test="${ordemServico.demanda.transicoes.size()>=1}">
								<td class="table-ordemServico-td-data"><joda:format value="${ordemServico.demanda.transicoes.get(0).getDataInicio()}" pattern="dd/MM/yy"/></td>
                                                            </c:if>
                                                                <td class="table-ordemServico-td-data"><joda:format value="${ordemServico.demanda.dataPrevista}" pattern="dd/MM/yy"/></td>
                                                        </c:otherwise>
						</c:choose>
						<td class="table-ordemServico-td-situacao">${ordemServico.situacao.nome}</td>
                                                
                                                <td class="table-ordemServico-td-situacao">${ordemServico.demanda.transicoes.get(ordemServico.demanda.transicoes.size()-1).estado.nome}</td>
                                                
                                                
                                                <c:set var="detalhada" value="${ordemServico.demanda.contagemDetalhada}"/>
                                                <c:set var="estimada" value="${ordemServico.demanda.contagemEstimada}"/>
                                                <c:choose>
							<c:when test="${detalhada<=0}">
                                                            <c:choose>
                                                                    <c:when test="${estimada<=0}">
                                                                        <td class="table-ordemServico-td-tamanho">N/I</td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td class="table-ordemServico-td-tamanho">${ordemServico.demanda.contagemEstimada}</td>
                                                                   </c:otherwise>
                                                            </c:choose>
							</c:when>
							<c:otherwise>
                                                            <td class="table-ordemServico-td-tamanho">${ordemServico.demanda.contagemDetalhada}</td>
                                                       </c:otherwise>
						</c:choose>
                                                
                                                
                                                
                                                
                                                
                                                
						<td class="table-ordemServico-td-indicador"
							data-nivelServico="${ordemServico.nivelServicoAtraso}"></td>
						<td class="table-ordemServico-td-indicador"
							data-nivelServico="${ordemServico.nivelServicoRecusa}"></td>
						<td class="table-ordemServico-td-indicador"
							data-nivelServico="${ordemServico.nivelServicoConformidade}"></td>
						<td class="table-ordemServico-td-acoes"><input name="id" type="hidden"
							value="${ordemServico.id}" /> 
                                                    
                                                    <a id="table-btn-detalhar" class="btn table-btn-detalhar" href="<c:url value="/ordemServico/" />${ordemServico.demanda.redmineIssueId}/editar">
                                                        <i class="icon-align-justify"></i>
                                                    </a>
                                                        <sec:authorize access="hasAnyRole('MINEIRO - Administrador', 'MINEIRO - Gestor Técnico', 'MINEIRO - Gestor de Qualidade')">
                                                    <a id="table-btn-editar" class="btn table-btn-editar" href="<c:url value="/ordemServico/"/>${ordemServico.demanda.redmineIssueId}/transicao/listar">
                                                        <i class=" icon-edit"></i>
                                                    </a>
                                                        
                                                        </sec:authorize> 
                                                        <sec:authorize access="hasRole('MINEIRO - Gestor de Qualidade')">
                                                    <a id="table-btn-inspecionar" class="btn table-btn-inspecionar" href="<c:url value="/ordemServico/" />${ordemServico.demanda.redmineIssueId}/inspecao/listar">
                                                        <i class="icon-search"></i>
                                                    </a>   
                                                        
                                                        <a id="table-btn-atualizar" class="btn table-btn-atualizar" href="<c:url value="/ordemServico/" />${ordemServico.demanda.redmineIssueId}/atualizar">
                                                        <i class="icon-refresh"></i>
                                                    </a>
                                                     </sec:authorize> 
                                                        
                                                  <!--  <a class="btn btn-small btn-acao"
                                                                    href="<c:url value="/ordemServico/" />${ordemServico.demanda.redmineIssueId}/editar">Exibir
								Relatório</a>
                                                                <a class="btn btn-small btn-acao" href="<c:url value="/ordemServico/"/>${ordemServico.demanda.redmineIssueId}/transicao/listar">Editar
								Transições</a> 
                                                                
                                                <sec:authorize access="hasRole('MINEIRO - Gestor de Qualidade')">
								<a class="btn btn-small btn-acao"
									href="<c:url value="/ordemServico/" />${ordemServico.demanda.redmineIssueId}/inspecao/listar">Inspecionar</a>
							</sec:authorize> -->
                                                
                                                </td>
                                                
                                                 
					</tr>
				</c:forEach>
		</table>
	</c:otherwise>
</c:choose>





