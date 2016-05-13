<%@ page language="Java"%>
<%@ page import="java.util.*, site.admin.*, site.core.*" session="true"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ include file="res_session.jsp"%>
<html>
<link rel="stylesheet" href="../css/styles.css">
<head>
<title>amor a vista</title>
</head><body class="listBody">
<%
long id = (request.getParameter("id")!=null?Long.parseLong(request.getParameter("id")):0);
Content tex = new Content().getContent(id);
%>
<table class='listTable' width="700px">
<tr class=listBody><td>
<span class='pageLabel'>Page:</span>
<span class='text'><b><%=tex.getCts_tag() + " -> " + tex.getCts_title()%></b></span>
</td></tr><tr class=listBody><td>
	<img src='../img/alert.gif' width='10' height='11' align='absmiddle'>&nbsp;
	<span class='pageLabel'><b>WARNING! This operation is irreversible.</b></span><br><br>
	<input name='btSubmit' type='button' class='pageButton' value='Proceed and delete' onClick='location.href="res_texto_deleteok.jsp?id=<%=id%>&key=<%=key%>&token=<%=token%>";'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Cancel and return' onClick='location.href="res_texto_list.jsp?key=<%=key%>&token=<%=token%>";'>
</td></tr></table>
</body>
</html>
