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
Article tex = new Article().getArticle(id);
tex.savePrevious(usuario.getUsu_login());
tex.restorePrevious(cthID);
%>
<table class='listTable' width="700px"><tr class=listBody><td>
<span class='pageLabel'><b>
Item restaurado com sucesso!
</b></span>
<br><br>
<input name='btBack' type='button' class='pageButton' value='Back to items list' onClick='location.href="res_article_list.jsp?editoria=<%=tex.getEdt_id()%>&key=<%=key%>&token=<%=token%>";'>
</td></tr></table>
</body>
</html>
