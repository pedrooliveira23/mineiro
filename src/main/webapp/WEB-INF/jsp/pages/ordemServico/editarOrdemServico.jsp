<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<ul class="breadcrumb noprint">
    <li><a href="<c:url value="/" />">Página Inicial</a> <span class="divider">/</span></li>
    <li><a href="<c:url value="/ordemServico/listar" />">Ordens de Serviço</a> <span class="divider">/</span></li>
    <li class="active"><span class="span-tipoRelatorio">Relatório  Gerencial</span> <span class="divider">/</span>#${ordemServico.demanda.redmineIssueId} - ${ordemServico.demanda.nome}</li>

</ul>

<c:choose>

    <c:when test="${not ordemServico.demanda.incompleta}">
        <form:form id="form-ordemServico-relatorio" method="post" commandName="ordemServico">


            <form:hidden path="id"/>
            <form:hidden path="demanda.redmineIssueId"/>
            <input type="hidden" name="autorFinalizacao" value="${usuario.nome}"/>

            <%@include file="../../templates/ordemServico/ordemServicoPrecificadaTemplate.jsp" %> 


            <h4 class="h4-ordemServico-relatorio">Relatório da Ordem de Serviço ${ordemServico.demanda.redmineIssueId}</h4>

            <table class="table table-dadosOrdemServico table-striped">
                <caption>
                    Dados da Ordem de Serviço ${ordemServico.demanda.redmineIssueId}
                </caption>
                <colgroup>
                    <col class="table-dadosOrdemServico-col-titulo">
                    <col class="table-dadosOrdemServico-col-valor">

                </colgroup>
                <tbody>
                    <tr>
                        <td class="table-dadosOrdemServico-td-titulo">Empresa</td>
                        <td>${ordemServico.contrato.contratada}</td>
                    </tr>
                    <tr>
                        <td class="table-dadosOrdemServico-td-titulo">Contrato</td>
                        <td>${ordemServico.contrato.numero}</td>
                    </tr>
                    <tr>
                        <td class="table-dadosOrdemServico-td-titulo">Ordem de Serviço</td>
                        <td>${ordemServico.demanda.redmineIssueId} - ${ordemServico.demanda.nome}</td>
                    </tr>
                    <tr>
                        <td class="table-dadosOrdemServico-td-titulo">Data Abertura</td>
                        <c:if test="${ordemServico.demanda.transicoes.size()<1}">
                            <td ><joda:format value="${ordemServico.demanda.dataCriacao}" pattern="dd/MM/yy"/></td>
                        </c:if>
                        <c:if test="${ordemServico.demanda.transicoes.size()>=1}">
                            <td ><joda:format value="${ordemServico.demanda.transicoes.get(0).getDataInicio()}" pattern="dd/MM/yy"/></td>
                        </c:if>
                    </tr>
                    <tr>
                        <td class="table-dadosOrdemServico-td-titulo">Data Estimada de Conclusão</td>
                        <td ><joda:format value="${ordemServico.demanda.dataPrevista}" pattern="dd/MM/yy"/></td>
                    </tr>
                    <tr>
                        <td class="table-dadosOrdemServico-td-titulo">Data de Conclusão</td>
                        <td ><joda:format value="${ordemServico.demanda.dataFinalizacao}" pattern="dd/MM/yy"/></td>
                    </tr>
                    
                    
                    
                    
                    
                    <tr>
                        <td class="table-dadosOrdemServico-td-titulo">Tipo de Ordem de Serviço</td>
                        <td>${ordemServico.demanda.tipoDemanda.nome}</td>
                    </tr>
                    <tr>
                        <td class="table-dadosOrdemServico-td-titulo">Descrição</td>
                        <td>${ordemServico.demanda.descricao}</td>
                    </tr>
                    <c:if test="${ordemServico.demanda.tipoSistema ne 'NAO_SE_APLICA'}">
                        <tr>
                            <td class="table-dadosOrdemServico-td-titulo">Tipo de Sistema</td>
                            <td>${ordemServico.demanda.tipoSistema.nome}</td>
                        </tr>
                    </c:if>

                    <tr>
                        <td class="table-dadosOrdemServico-td-titulo">Projeto</td>
                        <td>${ordemServico.projeto.nome}</td>
                    </tr>
                    <tr>
                        <td class="table-dadosOrdemServico-td-titulo">Linguagem</td>
                        <td>${ordemServico.projeto.linguagem.nome}</td>
                    </tr>
                    <tr>
                        <td class="table-dadosOrdemServico-td-titulo">Deflator de Tipo de Demanda</td>
                        <td><fmt:formatNumber value="${ordemServico.demanda.tipoDemanda.deflator}"/></td>
                    </tr>
                    <tr>
                        <td class="table-dadosOrdemServico-td-titulo">Deflator da Linguagem</td>
                        <td>
                            <!-- 
                             // alterado   (já era hard code no enum Linguagem)
                            // para atender vigencia de contrato de forma mais rápida // não deveria ser feito dessa forma! isso mesmo!
                            -->

                            <c:choose>
                                <c:when test="${fn:contains(ordemServico.contrato.contratada, 'Indra')}">
                                    <fmt:formatNumber value="${ordemServico.projeto.linguagem.fatorPonderacao}"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:formatNumber value="1"/>
                                </c:otherwise>
                            </c:choose>

                        </td>





                    </tr>
                    <c:if test="${not fn:contains(ordemServico.demanda.tipoDemanda, 'Manutenção Corretiva')}">
                        <tr>
                            <td class="table-dadosOrdemServico-td-titulo">Contagem Estimada</td>
                            <td><fmt:formatNumber value="${ordemServico.demanda.contagemEstimada}"/></td>
                        </tr>
                    </c:if>
                    <tr>
                        <td class="table-dadosOrdemServico-td-titulo">Contagem Detalhada</td>
                        <td><fmt:formatNumber value="${ordemServico.demanda.contagemDetalhada}"/></td>
                    </tr>
                </tbody>
                
                <tfoot>
                    <tr>
                        <td class="table-cronometroOrdemServico-td-duracaoTotal table-dadosOrdemServico-td-titulo" colspan="1">Duração
                            Total</td>
                        <td colspan="1"><spring:eval expression="ordemServico.demanda.duracaoTotal" />
                        </td>
                    </tr>
                    <tr>
                        <td class="table-cronometroOrdemServico-td-duracaoContandoTempoOS table-dadosOrdemServico-td-titulo" colspan="1">Duração Contando Tempo OS</td>
                        <td colspan="1"><spring:eval expression="ordemServico.demanda.duracaoContandoTempoOS" />
                        </td>
                    </tr>
                    <tr>
                        <td class="table-cronometroOrdemServico-td-prazoMaximo table-dadosOrdemServico-td-titulo" colspan="1">Prazo Máximo</td>
                        <td colspan="1"><spring:eval expression="ordemServico.demanda.duracaoPrazoMaximo" />
                        </td>
                    </tr>
                    <tr>
                        <td class="table-cronometroOrdemServico-td-quantidadeDiasAtraso table-dadosOrdemServico-td-titulo" colspan="1">Quantidade de Dias de Atraso</td>
                        <td colspan="1"><spring:eval expression="ordemServico.demanda.duracaoAtraso" />
                        </td>
                    </tr>
                </tfoot>
            </table>

            <table class="table table-nivelServicoOrdemServico table-striped">
                <caption>
                    Tempo por Estado
                </caption>
            </table>
                        
            <div class="span4" style="margin-bottom: 50px">
		<div id="div_tempo_situacao"></div>
            </div>
                        
                        
            <table class="table table-nivelServicoOrdemServico table-striped">
                <caption>
                    Tempo por Responsável
                </caption>
            </table>
                        
            <div class="span4" style="margin-bottom: 50px">
		<div id="div_tempo_area"></div>
            </div>

            <table class="table table-nivelServicoOrdemServico table-striped" >
                <caption>
                    Níveis de Serviço da Ordem de Serviço ${ordemServico.demanda.redmineIssueId} <sup>1</sup>
                </caption>
                <colgroup>
                    <col class="table-nivelServicoOrdemServico-col-indicador">
                    <col class="table-nivelServicoOrdemServico-col-valor">
                    <col class="table-nivelServicoOrdemServico-col-classificacao">

                </colgroup>
                <tbody>
                <thead>
                    <tr>

                        <th>Indicador de Nível de Serviço</th>

                        <th>Valor do Indicador</th>

                        <th>Classificação </th>

                    </tr>
                </thead>
                <tr>
                    <td class="table-nivelServicoOrdemServico-td-indicador">Atraso</td>
                    <td class="table-nivelServicoOrdemServico-td-valor"> 
                        <fmt:formatNumber type="number" minFractionDigits="0" value="${ordemServico.demanda.percentualAtraso}" />%

                    </td>
                    <td class="table-nivelServicoOrdemServico-td-classificacao">${ordemServico.nivelServicoAtraso.nome}</td>
                </tr>
                <tr>
                    <td class="table-nivelServicoOrdemServico-td-indicador">Conformidade</td>
                    <td class="table-nivelServicoOrdemServico-td-valor"> 
                        <fmt:formatNumber type="number" minFractionDigits="0" value="${ordemServico.demanda.conformidade}" />%
                    </td>
                    <td class="table-nivelServicoOrdemServico-td-classificacao">${ordemServico.nivelServicoConformidade.nome}</td>
                </tr>
                <tr>
                    <td class="table-nivelServicoOrdemServico-td-indicador">Recusa</td>
                    <td class="table-nivelServicoOrdemServico-td-valor"> ${ordemServico.demanda.quantidadeRecusas} </td>
                    <td class="table-nivelServicoOrdemServico-td-classificacao">${ordemServico.nivelServicoRecusa.nome}</td>
                </tr>
            </tbody>
        </table>
        



        <table class="table table-valorOrdemServico table-striped">
            <caption>
                Valores da Ordem de Serviço ${ordemServico.demanda.redmineIssueId} 
            </caption>
            <colgroup>
                <col class="table-valorOrdemServico-col-calculo">
                <col class="table-valorOrdemServico-col-valor">

            </colgroup>
            <thead>
                <tr>

                    <th>Cálculo</th>

                    <th>Valor (R$)</th>

                </tr>
            </thead>
            <tbody>
                <tr>
                    <td >Valor Corrente do Ponto de Função</td>
                    <td ><fmt:formatNumber value="${ordemServico.contrato.valorUnitario}" 
                                      type="currency"/></td>
                </tr>
                <tr>
                    <td >Valor Bruto (<c:choose>
                                            <c:when test="${ ordemServico.demanda.contagemDetalhada le 0 or empty ordemServico.demanda.contagemDetalhada }">Contagem Estimada: <fmt:formatNumber value="${ordemServico.demanda.contagemEstimada}" type="number"/>
                                            </c:when>
                                            <c:otherwise>Contagem Detalhada: <fmt:formatNumber value="${ordemServico.demanda.contagemDetalhada}" type="number"/>
                                            </c:otherwise>
                                       </c:choose> PF)  
                    <c:if test="${ordemServico.situacao eq 'PRECIFICADA' && ordemServico.demanda.concluida eq true}">
                        <sup>10</sup>
                    </c:if>
                    
                    
                    
                    </td>
                    <td ><fmt:formatNumber value="${ordemServico.valorBruto}" type="currency"/></td>
                </tr>
               
                    <tr>
                        <td>Valor Bruto Deflacionado <sup>2</sup></td>
                        <td>
                            <fmt:formatNumber value="${ordemServico.valorBrutoDeflacionado}" type="currency"/>
                        </td>
                    </tr>
    
                <tr>
                    <td>Glosa Atraso (<fmt:formatNumber value="${ordemServico.glosaAtraso}" type="number" minFractionDigits="0" />%) <sup>3</sup>
                    </td>
                    <td class="table-valorOrdemServico-td-valorGlosaMulta">
                        <fmt:formatNumber value="${ordemServico.valorGlosaAtraso}" type="currency"/>	
                    </td>
                </tr>
                <c:if test="${fn:contains(ordemServico.contrato.contratada, 'Indra')}">
                    <tr>
                        <td>Glosa Conformidade (<fmt:formatNumber value="${ordemServico.glosaConformidade}" type="number" minFractionDigits="0" />%) <sup>4</sup></td>
                        <td class="table-valorOrdemServico-td-valorGlosaMulta">
                            <fmt:formatNumber value="${ordemServico.valorGlosaConformidade}" type="currency"/>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td>Glosa Recusa (<fmt:formatNumber value="${ordemServico.glosaRecusa}" type="number" minFractionDigits="0" />%) <sup>5</sup></td>
                    <td class="table-valorOrdemServico-td-valorGlosaMulta">
                        <fmt:formatNumber value="${ordemServico.valorGlosaRecusa}" type="currency"/>
                    </td>
                </tr>

                <c:choose>
                    <c:when test="${fn:contains(ordemServico.contrato.contratada, 'Indra')}">
                        <tr>
                            <td>Multa Atraso (<fmt:formatNumber value="${ordemServico.multaAtraso}" type="number" minFractionDigits="0" />%) <sup>6</sup></td>
                            <td class="table-valorOrdemServico-td-valorGlosaMulta">
                                <fmt:formatNumber value="${ordemServico.valorMultaAtraso}" type="currency"/>
                            </td>
                        </tr>

                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td>Multa Atraso Cont. (<fmt:formatNumber value="${ordemServico.multaAtraso}" type="number" minFractionDigits="0" />%) <sup>6</sup></td>
                            <td class="table-valorOrdemServico-td-valorGlosaMulta">
                                <fmt:formatNumber value="${ordemServico.valorMultaAtraso}" type="currency"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Multa Atraso Demanda (<fmt:formatNumber value="${ordemServico.multaAtrasoDemanda}" type="number" minFractionDigits="0" />%) <sup>7</sup></td>
                            <td class="table-valorOrdemServico-td-valorGlosaMulta">
                                <fmt:formatNumber value="${ordemServico.multaAtrasoDemanda}" type="currency"/>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>

            </tbody>
            <tfoot>


                <c:choose>
                    <c:when test="${fn:contains(ordemServico.contrato.contratada, 'Indra')}">
                        <tr>		
                            <td class="table-valorOrdemServico-td-GlosaMulta">Total Glosa (<fmt:formatNumber 
                                    value="${ordemServico.glosaRecusa+ordemServico.glosaAtraso+ordemServico.glosaConformidade}" 
                                    type="number" minFractionDigits="0" 
                                    />%)
                            </td>

                            <td class="table-valorOrdemServico-td-valorGlosaMulta">
                                <fmt:formatNumber 
                                    value="${ordemServico.valorGlosaRecusa+ordemServico.valorGlosaAtraso+ordemServico.valorGlosaConformidade}" 
                                    type="currency"
                                    />
                            </td>
                        </tr>
                        <tr>		
                            <td class="table-valorOrdemServico-td-GlosaMulta">Total Multa (<fmt:formatNumber 
                                    value="${ordemServico.multaAtraso}" 
                                    type="number" minFractionDigits="0" 
                                    />%)
                            </td>

                            <td class="table-valorOrdemServico-td-valorGlosaMulta">
                                <fmt:formatNumber 
                                    value="${ordemServico.valorMultaAtraso}" 
                                    type="currency"
                                    />
                            </td>
                        </tr>

                    </c:when>
                    <c:otherwise>
                        <tr>
                            <c:if test="${ordemServico.glosaRecusa+ordemServico.glosaAtraso >30}">
                                <td class="table-valorOrdemServico-td-GlosaMulta">Total Glosas (<fmt:formatNumber value="30" type="number" minFractionDigits="0" />%) <sup>8</sup></td>
                                <td class="table-valorOrdemServico-td-valorGlosaMulta">
                                    <fmt:formatNumber value="${ordemServico.totalGlosas}" type="currency" />
                                    
                                </td>
                            </c:if>
                            <c:if test="${ordemServico.glosaRecusa+ordemServico.glosaAtraso <=30}">
                                <td class="table-valorOrdemServico-td-GlosaMulta">Total Glosas (
                                    <fmt:formatNumber 
                                        value="${ordemServico.glosaRecusa+ordemServico.glosaAtraso+ordemServico.glosaConformidade}" 
                                        type="number" minFractionDigits="0" 
                                        />%) <sup>8</sup>
                                </td>
                                <td class="table-valorOrdemServico-td-valorGlosaMulta">
                                    <fmt:formatNumber 
                                        value="${ordemServico.valorGlosaRecusa+ordemServico.valorGlosaAtraso+ordemServico.valorGlosaConformidade}" 
                                        type="currency"
                                        />
                                </td>
                            </c:if>    
                        </tr>


                        <tr>		
                            <td class="table-valorOrdemServico-td-GlosaMulta">Total Multas
                            </td>

                            <td class="table-valorOrdemServico-td-valorGlosaMulta">
                                <fmt:formatNumber 
                                    value="${ordemServico.valorMultaAtraso+ordemServico.valorMultaAtrasoDemanda}" 
                                    type="currency"
                                    />
                            </td>
                        </tr>



                    </c:otherwise>
                </c:choose>


                <tr>
                    <td class="table-valorOrdemServico-td-valorTotalFinal">Valor Total<sup>9</sup></td>
                    <td>
                        <fmt:formatNumber value="${ordemServico.valorTotal}" type="currency"/>
                    </td>
                </tr>
            </tfoot>
        </table>
                    
                    





        <div class="div-cronometroOrdemServico hide">
            <table class="table table-cronometroOrdemServico">
                <caption>
                    <c:if test="${fn:contains(ordemServico.contrato.contratada, 'Indra')}">
                    Histórico da Ordem de Serviço ${ordemServico.demanda.redmineIssueId} <sup>7</sup>
                    </c:if>
                    <c:if  test ="${not fn: contains(ordemServico.contrato.contratada, 'Indra')}">
                       Histórico da Ordem de Serviço ${ordemServico.demanda.redmineIssueId} 
                    </c:if>
                    
                    
                </caption>
                <colgroup>
                    <col class="table-cronometroOrdemServico-col-trasicao">
                    <col class="table-cronometroOrdemServico-col-contaTempoOS">
                    <col class="table-cronometroOrdemServico-col-contaRecusaOS">
                    <col class="table-cronometroOrdemServico-col-tempo">
                    <col class="table-cronometroOrdemServico-col-tempoUtil">
                    <col class="table-cronometroOrdemServico-col-periodo">
                </colgroup>
                <thead>
                    <tr>


                        <th>Estado</th>

                        <th class="table-cronometroOrdemServico-th-contaTempoOS">Conta Tempo</th>

                        <th class="table-cronometroOrdemServico-th-contaRecusaOS">Conta Recusa</th>

                        <th>Tempo</th>

                        <th>Tempo Útil</th>

                        <th>Período</th>



                    </tr>
                </thead>
                <tbody>
                    <c:set var="trCor" value="cinza" />
                    <c:set var="trGlosa" value="glosa" />
                    <c:forEach var="transicao" items="${ordemServico.demanda.transicoes}"
                               varStatus="i">
                        <c:choose>
                            <c:when test="${trCor eq 'branca'}">
                                <c:set var="trCor" value="cinza" />
                            </c:when>
                            <c:otherwise>
                                <c:set var="trCor" value="branca" />
                            </c:otherwise>
                        </c:choose> 
                        
                        <c:choose>
                            <c:when test="${transicao.contaRecusaOS eq true}">
                                <c:set var="trGlosa" value="glosa" />
                            </c:when>
                            <c:otherwise>
                                <c:set var="trGlosa" value="normal" />
                            </c:otherwise>
                        </c:choose> 
                        
                        <tr class="tr-${trCor} tr-${trGlosa}">
                            <td>${transicao.estado.nome}</td>
                            <td class="table-cronometroOrdemServico-td-contaTempoOS"><form:checkbox  disabled="true" class="input-checkbox"
                                            path="demanda.transicoes[${i.index }].contaTempoOS" /> 	
                            </td>
                            <td class="table-cronometroOrdemServico-td-contaRecusaOS"><form:checkbox  disabled="true" class="input-checkbox"
                                            path="demanda.transicoes[${i.index }].contaRecusaOS" /> 	
                            </td>
                            <td>
                                <spring:eval expression="transicao.duracaoTotal" />
                            </td>
                            <td>
                                <spring:eval expression="transicao.duracaoContandoTempo" />
                            </td>
                            <td><span><joda:format value="${transicao.dataInicio}" pattern="dd/MM/yy - H:mm:ss"/>
                                    <br/><joda:format value="${transicao.dataFim}" pattern="dd/MM/yy - H:mm:ss"/>
                                </span></td>
                        </tr>
                        <c:if test="${not empty transicao.justificativa.descricao}">
                            <tr class="tr-${trCor} table-cronometroOrdemServico-tr-justificativa">

                                <td colspan="6">
                                    <span class="span-justificativa"><strong>Justificativa de Alteração: </strong>${transicao.justificativa.descricao}</span>
                                    <span class="span-autor"><strong>Gestor Técnico: </strong>${transicao.justificativa.autor}</span>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
                
                
                
                
            </table>
            <c:if test="${fn:contains(ordemServico.contrato.contratada, 'Indra')}">
                <small>7 - Item 8.9.1</small>
            </c:if>
        </div>

        <div class="div-inspecaoOrdemServico hide">
            <table class="table table-inspecaoOrdemServico table-striped">
                <caption>
                    <c:if test="${fn:contains(ordemServico.contrato.contratada, 'Indra')}">
                    Inspeções da Ordem de Serviço ${ordemServico.demanda.redmineIssueId} <sup>8</sup>
                    </c:if>
                    <c:if  test ="${not fn: contains(ordemServico.contrato.contratada, 'Indra')}">
                       Inspeções da Ordem de Serviço ${ordemServico.demanda.redmineIssueId} 
                    </c:if>
                </caption>
                <colgroup>
                    <col class="table-inspecaoOrdemServico-col-inspecao">
                    <col class="table-inspecaoOrdemServico-col-percentual">

                </colgroup>
                <thead>
                    <tr>

                        <th class="table-inspecaoOrdemServico-th-inspecao">Inspeção</th>

                        <th class="table-inspecaoOrdemServico-th-percentual">Percentual</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="inspecao" items="${ordemServico.demanda.inspecoes}" varStatus="i">
                        <tr>
                            <td class="table-inspecaoOrdemServico-th-inspecao">${inspecao.tipoInspecao.nome}</td>
                            <td class="table-inspecaoOrdemServico-td-percentual">${inspecao.percentualAprovacao}</td>
                        </tr>
                    </c:forEach>

                </tbody>
                <tfoot>

                    <tr>
                        <td class="table-inspecaoOrdemServico-td-conformidadeDemanda">Conformidade da Demanda</td>
                        <td class="table-inspecaoOrdemServico-td-percentual">

                            <fmt:formatNumber type="number" minFractionDigits="0" value="${ordemServico.demanda.conformidade}" />%
                        </td>
                    </tr>
                </tfoot>
            </table>
                        
                        
                        
        <c:if test="${fn:contains(ordemServico.contrato.contratada, 'Indra')}">
            <small>8 - Subitem 2 do Item 8.6.1</small> 
        </c:if>
        </div>
                        <div class="rlt-referencias ">
                        <c:choose>
                            <c:when test="${fn:contains(ordemServico.contrato.contratada, 'Indra')}">
                                <small>2 - Item 8.11.1</small>
                                <small>3 - Subitem 18 do Item 11.1</small>
                                <small>4 - Subitem 20 do Item 11.1</small>
                                <small>5 - Subitem 21 do Item 11.1</small>
                                <small>1 - Item 8.6.1</small>
                                <c:choose>
                                    <c:when test="${fn:contains(ordemServico.demanda.tipoDemanda, 'Garantia')}">
                                        <small>6 - Subitem 17 do Item 11.1</small>
                                    </c:when>
                                    <c:otherwise>
                                        <small>6 - Subitem 19 do Item 11.1</small>
                                    </c:otherwise>
                                </c:choose>
                                <small>9 - Subitem 22 do Item 11.1</small>
                            </c:when>
                            <c:otherwise>
                                <small>1 - Item 6.6</small>
                                <small>3 - Subitem 4 do Item 6.10</small>
                                <small>5 - Subitem 4 do Item 6.10</small>
                                <small>6 - Inciso XIV, parágrafo 2º, item f</small>
                                <small>7 - Inciso XIV, parágrafo 2º, item b,c</small>
                                <small>8 - Subitem 4 do Item 6.10</small>
                                <small>9 - Subitem 1 do Item 6.10</small>
                            </c:otherwise>
                        </c:choose>
                                
                                <c:if test="${ordemServico.situacao eq 'PRECIFICADA' && ordemServico.demanda.concluida eq true}">
                                    <small>10 - Ordem de Serviço Precificada com o valor de Ponto de Função: 
                                        <fmt:formatNumber value="${ordemServico.valorBruto/ordemServico.demanda.contagemDetalhada}" type="currency"/>
                                    
                                    </small>
                                </c:if>
                    </div>
                        

                           
        <c:if test="${ordemServico.situacao ne 'PRECIFICADA' && ordemServico.demanda.concluida eq true}">
            <button id="btn-finalizar" type="button" class="btn btn-primary noprint hide">Precificar Ordem de Serviço</button>
        </c:if>
        <button id="btn-tipoRelatorio" data-estado="gerencial" type="button" class="btn btn-primary noprint">Exibir Relatório Detalhado</button>
        <!--<button id="btn-refShow" data-estado="hide" type="button" class="btn btn-primary noprint">Exibir Referências</button> -->
    </form:form>
        
    <input type="hidden" id="jsonRedmineIssueId" value='${ordemServico.demanda.redmineIssueId}'/>

    <div id="modal-alerta" class="modal hide fade">

        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">&times;</button>
            <h4>Confirmação da Precificação da Ordem de Serviço ${ordemServico.demanda.redmineIssueId}</h4>
        </div>
        <div class="modal-body">
            <h5 class="text-error">A ordem de serviço não poderá mais ser alterada após ser precificada. Você deseja precificar a ordem de serviço ${ordemServico.demanda.redmineIssueId}?</h5>

        </div>
        <div class="modal-footer">
            <button id="btn-modal-confirmar" type="button" name="salvar" class="btn btn-submit btn-primary">Confirmar</button> 
            <button type="button" data-dismiss="modal" class="btn">Cancelar</button> 

        </div>
    </div>
</c:when>

<c:otherwise>

    <div class="alert alert-error noprint">
        <strong>${ordemServico.demanda.contagemDetalhada} Ordem de serviço sem contagem detalhada! </strong> Somente após a inserção da contagem detalhada no redmine será possível gerar o relatório.
    </div>

</c:otherwise>


</c:choose>