<%@ page language="Java"%>
<%@ page import="java.util.*, site.admin.*, site.core.*" session="true"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ include file="res_session.jsp"%>
<html>
<link rel="stylesheet" href="../css/styles.css">
<head>
<title>JRocha Framework</title>
</head><body class="listBody">
<%
long id = (request.getParameter("id")!=null?Long.parseLong(request.getParameter("id")):0);
String lng = (request.getParameter("lng")!=null?request.getParameter("lng"):"br");
Language tex = new Language().getLanguage(id, lng);
%>
<table class='listTable' width="700px">
<tr class=listBody><td>
<span class='pageLabel'>Text:</span>
<span class='text'><b><%=tex.getLng_from() + " -> " + tex.getLng_to()%></b></span>
</td></tr><tr class=listBody><td>
	<img src='../img/alert.gif' width='10' height='11' align='absmiddle'>&nbsp;
	<span class='pageLabel'><b>WARNING! This operation is irreversible.</b></span><br><br>
	<input name='btSubmit' type='button' class='pageButton' value='Proceed and delete' onClick='location.href="res_language_deleteok.jsp?id=<%=id%>&lng=<%=lng%>&key=<%=key%>&token=<%=token%>";'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Cancel and return' onClick='location.href="res_language_list.jsp?key=<%=key%>&lng=<%=lng%>&token=<%=token%>";'>
</td></tr></table>
</body>
</html>
