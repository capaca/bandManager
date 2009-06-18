<%@ page import="org.springframework.security.ui.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.AuthenticationException" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="header-wrapper">
	<div id="header">
		<div id="menu">

			<ul>
				<li class="current_page_item"><a href="#">Home</a></li>
				<li><a href="#">Blog</a></li>
				<li><a href="#">Photos</a></li>
				<li><a href="#">About</a></li>
				<li><a href="#">Links</a></li>
				<li><a href="#">Contact</a></li>
			</ul>
		</div>
		<!-- end #menu -->
		
		<div id="user">
			<s:url id="linkEditarPerfil" action="editarPerfil" />
			<s:a href="%{#linkEditarPerfil}">Editar perfil</s:a> | 
			<a href="<c:url value="/j_spring_security_logout"/>">Sair</a>
		</div>
	</div>
	<!-- end #header -->
</div>
<!-- end #header-wrapper -->
