<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />

<tiles:importAttribute name="titulo" scope="request"/>
<title><tiles:getAsString name="titulo"/></title>

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