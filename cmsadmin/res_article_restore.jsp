<%@ page language="Java"%>
<%@ page import="java.util.*, site.admin.*, site.core.*" session="true"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ include file="res_session.jsp"%>
<html>
<link rel="stylesheet" href="../css/styles.css">
<head>
<title>JRocha Framework</title>
</head>
<body class="listBody">
<%
String currentPage = request.getRequestURI();
currentPage = currentPage.substring(currentPage.lastIndexOf("/")+1,currentPage.length());

String parameterString = request.getQueryString();
if(parameterString==null) parameterString = "token=" + token + "&key=" + key;

long id = (request.getParameter("id")!=null?Long.parseLong(request.getParameter("id")):0);
Article obj = new Article().getArticle(id);
%>
<span class="listTitle">Lista de Vers&otilde;es para o Artigo <%=obj.getArt_title()%></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="res_article_list.jsp?editoria=<%=obj.getEdt_id()%>&token=<%=token%>&key=<%=key%>"><img src='../img/ico_up.gif'></a><br>
<table class='listTable' width='90%'>
<tr class='listSubTitle'>
<td>Data/Hora</td>
<td>Usu&aacute;rio</td>
<td>Actions</td></tr>
<%
String[] versoes = obj.getVersions();
int count = 0;
if(versoes!=null && versoes.length > 0)
{
	for(int xx=0; xx < versoes.length;xx++)
	{
	   count++;
	   String[] linha = versoes[xx].split(";");
	   String linkEdit = "<a href='res_article_restoreto.jsp?cthID=" + linha[0] + "&date=" + linha[1] + "&" + parameterString + "'>";
	   String linkEditX = "</a>";
	   %>
	    <tr class=listBody>
	    <td>
	    <%=linkEdit%><b><%=linha[1]%></b><%=linkEditX%></td>
	    <td><%=linkEdit%><%=linha[2]%><%=linkEditX%></td>
	    <td align='left'>
			<%=linkEdit%><img src="../img/ico_restoreto.gif" border=0></a>
	   </td></tr>
	<% }
}%>
<tr><td colspan='5'><strong>Total: <%=count%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href='#' onClick='self.print()'><img src='../img/ico_print.gif' alt='Print this page' border='0' align='absmiddle'></a>&nbsp;<a href='#' onClick='self.print()'>Print this page</a></strong></td>
</tr></table>
</body>
</html>
