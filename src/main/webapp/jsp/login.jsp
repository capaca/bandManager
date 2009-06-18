<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="org.springframework.security.ui.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.AuthenticationException" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>E-plamtax</title>
<link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<form action="<c:url value="j_spring_security_check"/>" method="post">
<div style="margin: auto; margin-top: 50px;">
	<table align="center">
		<tr align="left">
			<td>
				Usuário:
			</td>
			<td colspan="2">
				<input name="j_username" type="text"/>
			</td>
		</tr>
		<tr align="left">
			<td>
				Senha:
			</td>
			<td>
				<input name="j_password" type="password" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td align="left">
				<input type="submit" value="Entrar"/>
			</td>
		</tr>
	</table>
</div>
<c:if test="${not empty param.login_error}">
	<div style="color: red;"><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></div>
</c:if>
</form>
</body>
</html>