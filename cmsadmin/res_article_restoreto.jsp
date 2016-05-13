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
long cthID = (request.getParameter("cthID")!=null?Long.parseLong(request.getParameter("cthID")):0);
long id = (request.getParameter("id")!=null?Long.parseLong(request.getParameter("id")):0);
String data = (request.getParameter("date")!=null?request.getParameter("date"):"-");
Article tex = new Article().getArticle(id);
%>
<table class='listTable' width="700px">
<tr class=listBody><td>
<span class='pageLabel'>Restaurar versão:</span>
<span class='text'><b><%=tex.getArt_title() + " -> " + data%></b></span>
</td></tr><tr class=listBody><td>
	<img src='../img/alert.gif' width='10' height='11' align='absmiddle'>&nbsp;
	<span class='pageLabel'><b>WARNING! This operation is irreversible.</b></span><br><br>
	<input name='btSubmit' type='button' class='pageButton' value='Confirmar restaura&ccedil;&atilde;o' onClick='location.href="res_article_restore_action.jsp?id=<%=id%>&cthID=<%=cthID%>&key=<%=key%>&token=<%=token%>";'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Cancel and return' onClick='location.href="res_article_list.jsp?editoria=<%=tex.getEdt_id()%>&key=<%=key%>&token=<%=token%>";'>
</td></tr></table>
</body>
</html>
