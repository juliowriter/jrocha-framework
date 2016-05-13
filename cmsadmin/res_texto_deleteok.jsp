<%@ page language="Java"%>
<%@ page import="java.util.*, site.admin.*, site.core.*" session="true"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ include file="res_session.jsp"%>
<html>
<link rel="stylesheet" href="../css/styles.css">
<head>
<title>reservaflex</title>
</head><body class="listBody">
<%
long id = (request.getParameter("id")!=null?Long.parseLong(request.getParameter("id")):0);
Content tex = new Content().getContent(id);
tex.savePrevious(usuario.getUsu_login());
boolean delOk = (tex!=null?tex.delete():false);
%>
<table class='listTable' width="700px">
<tr class=listBody><td>
<span class='pageLabel'>Page:</span>
<span class='text'><b><%=(tex!=null?tex.getCts_tag() + " -> " + tex.getCts_title():"Not found!")%></b></span>
</td></tr><tr class=listBody><td><span class='pageLabel'><b>
<%if(delOk){%>
	The item was deleted.
	</b></span><br><br>
<%} else {%>
	<img src='../img/alert.gif' width='10' height='11' align='absmiddle'>&nbsp;
	<span class='pageLabel'><b>Error! Can't delete this page!</b></span><br><br>
<%}%>
<input name='btBack' type='button' class='pageButton' value='Return to list' onClick='location.href="res_texto_list.jsp?key=<%=key%>&token=<%=token%>";'>
</td></tr></table>
</body>
</html>
