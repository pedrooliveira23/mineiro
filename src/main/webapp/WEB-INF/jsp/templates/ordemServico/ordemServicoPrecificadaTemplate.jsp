<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	<c:if test="${ordemServico.situacao eq 'PRECIFICADA'}">
		<div class="alert alert-info noprint">
			<strong>Ordem de Servi�o Precificada!</strong> Essa ordem de servi�o n�o pode mais ser alterada.
		</div>
	</c:if>





