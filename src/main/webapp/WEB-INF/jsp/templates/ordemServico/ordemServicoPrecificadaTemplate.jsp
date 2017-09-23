<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	<c:if test="${ordemServico.situacao eq 'PRECIFICADA'}">
		<div class="alert alert-info noprint">
			<strong>Ordem de Serviço Precificada!</strong> Essa ordem de serviço não pode mais ser alterada.
		</div>
	</c:if>





