<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  <%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<form:form id="form-estado" method="post" commandName="estadoCommand">
<ul class="breadcrumb">
	<li><a href="<c:url value="/" />">Página Inicial</a> <span class="divider">/</span></li>
	<li class="active">Estados</li>
</ul>
<form:errors cssClass="error" />

<div id="div-alert">

</div>
<button type="button" class="btn btn-primary btn-submit">Atualizar Estados</button> 
	<table class="table table-estado table-striped">
				<colgroup>
					<col class="table-estado-col-estado">
					<col class="table-estado-col-contaTempoOS">
					<col class="table-estado-col-contaRecusaOS">
		
				</colgroup>
		<thead>
			<tr>
				<th class="table-estado-th-estado">Estado</th>
				<th class="table-estado-th-contaTempoOS">Conta Tempo OS</th>
				<th class="table-estado-th-contaRecusaOS">Conta Recusa OS</th>
                                <th class="table-estado-th-contaRecusaOS">Responsável</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="estado"
				items="${estadoCommand.estados}" varStatus="i">
				<tr>
					<td class="table-estado-td-estado">${estado.nome}
							<form:hidden path="estados[${i.index }].id" />
                                                        <form:hidden path="estados[${i.index }].contaIndicador" />
							<form:hidden path="estados[${i.index }].redmineStatusId" />
					</td>
					<td class="table-estado-td-contaTempoOS">
							<form:checkbox class="input-checkbox input-checkbox-contaTempoOS" path="estados[${i.index }].contaTempoOS" /> 
					</td>
					<td class="table-estado-td-contaRecusaOS">
							<form:checkbox class="input-checkbox input-checkbox-contaRecusaOS" path="estados[${i.index }].contaRecusaOS" /> 
					</td>
                                        <td class="table-estado-td-contaRecusaOS">
                                          
							
                                                        <form:select id="select-role" path="estados[${i.index }].role.id" cssErrorClass="error">
                                                            <form:option  value="" label="<<Não associado>>" />
                                                                <form:options items="${roles}" itemValue="id" itemLabel="nome" />
                                                        </form:select>
                                                        
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<button type="button" class="btn btn-primary btn-submit">Atualizar Estados</button> 
 
</form:form>