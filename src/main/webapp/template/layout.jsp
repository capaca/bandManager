<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="org.springframework.security.ui.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.AuthenticationException" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
<link href="css/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="screen" />
<tiles:importAttribute name="titulo" scope="request"/>
<title><tiles:getAsString name="titulo"/></title>

<SCRIPT type="text/javascript" src="js/jquery-1.3.2.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/jquery.tablesorter.min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/jquery-ui-1.7.2.custom.min.js"></SCRIPT>

</head>
<body>
	<div id="principal">
		<div id="cabecalho">
			<tiles:insertAttribute name="cabecalho" />
		</div>
		<div id="page">
			<tiles:insertAttribute name="conteudo" />
		</div>
	</div>
</body>
</html>