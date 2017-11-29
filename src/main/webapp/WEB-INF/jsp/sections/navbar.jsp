<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse"
			   data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</a>
			<a class="brand" href="<c:url value="/" />">
				<img style="padding-bottom: 5px;" src="<c:url value="/img/helmet_mine.png" />"/>
				<span>Mineiro</span> </a>

			<sec:authorize access="hasAnyRole('MINEIRO - Administrador', 'MINEIRO - Gestor T�cnico', 'MINEIRO - Gestor de Qualidade', 'MINEIRO - Observador')">


				<div class="nav-collapse collapse">
					<ul class="nav">
						<sec:authorize access="hasAnyRole('MINEIRO - Administrador', 'MINEIRO - Gestor T�cnico', 'MINEIRO - Gestor de Qualidade')">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
													data-toggle="dropdown">Contrato<b class="caret"></b> </a>
								<ul class="dropdown-menu">
									<c:forEach items="${contratos}" var="item">

										<li><a tabindex="${item.id}" href="<c:url value="/trocarContrato/"/>${item.id}">${item.contratada}</a>

										</li>

									</c:forEach>

								</ul>
							</li>
							<li id="li-nav-fabrica"><strong>(${sessionScope.contratada.contratada})</strong></li>
						</sec:authorize>

						<li class="dropdown" style="margin-left: 30px;"><a href="#" class="dropdown-toggle"
																		   data-toggle="dropdown">A��es<b class="caret"></b> </a>
							<ul class="dropdown-menu">
								<li class="dropdown-submenu"><a tabindex="-1" href="#">Ordens de Servi�o</a>
									<ul class="dropdown-menu">
										<li><a href="<c:url value="/ordemServico/listar"/>">Visualizar</a></li>

									</ul>
								</li>
								<sec:authorize access="hasRole('MINEIRO - Administrador')">
									<li class="divider"></li>
									<li class="dropdown-submenu"><a tabindex="-1" href="#">Diretrizes Globais</a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value="/admin/diaNaoUtil/listar"/>">Editar Dias N�o �teis</a></li>
											<li><a href="<c:url value="/admin/estado/listar"/>">Editar Estados</a></li>
										</ul>
									</li>
									<li class="dropdown-submenu"><a tabindex="-1" href="#">Configura��es</a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value="/admin/configuracao"/>">Visualizar</a></li>
										</ul>
									</li>
									<li class="dropdown-submenu"><a tabindex="-1" href="#">Contratos</a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value="/admin/contrato/listar"/>">Visualizar</a></li>
										</ul>
									</li>
								</sec:authorize>


							</ul>
						</li>
						<sec:authorize access="hasRole('MINEIRO - Administrador')">
							<li id="li-nav-papeis"><a href="<c:url value="/listarUsuarios"/>">Papeis</a></li>
						</sec:authorize>


						<!--<li id="li-nav-sair"><a href="<c:url value="/logout"/>">Sair</a></li>-->


					</ul>


				</div>

			</sec:authorize>

			<ul class="nav" style="float:right;">
				<sec:authorize access="hasAnyRole('MINEIRO - Administrador', 'MINEIRO - Gestor T�cnico', 'MINEIRO - Gestor de Qualidade','MINEIRO - Observador')">
					<li id="li-nav-sair"><a href="<c:url value="/logout"/>">Sair</a></li>
				</sec:authorize>
				<li><a/><span>Vers�o: ${sessionScope.versao} </span> </a></li>
			</ul>


			<!--  <a class="versao"  /><span>Vers�o: ${sessionScope.versao} </span> </a>
                -->
			<sec:authorize access="hasAnyRole('MINEIRO - Administrador', 'MINEIRO - Gestor T�cnico', 'MINEIRO - Gestor de Qualidade')">
				<!-- <a class="versao" style="font-size: 15px" href="<c:url value="/logout"/>"><span>Sair </span></a>
				-->
			</sec:authorize>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>