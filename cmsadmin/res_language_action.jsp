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
String from = (request.getParameter("from")!=null?request.getParameter("from"):"");
String to = (request.getParameter("to")!=null?request.getParameter("to"):"");
String lng = (request.getParameter("lng")!=null?request.getParameter("lng"):"br");
Language tex = new Language();
if(id > 0)
{
   tex = new Language().getLanguage(id, lng);
}
tex.setLng_from(from);
tex.setLng_to(to);
if(id > 0)
	tex.save(lng);
else
	id = tex.insert(lng);
%>
<table class='listTable' width="700px"><tr class=listBody><td>
<span class='pageLabel'><b>
Item saved!
</b></span>
<br><br>
<input name='btSubmit' type='button' class='pageButton' value='Back to the item' onClick='location.href="res_language.jsp?id=<%=id%>&lng=<%=lng%>&key=<%=key%>&token=<%=token%>";'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Back to items list' onClick='location.href="res_language_list.jsp?lng=<%=lng%>&key=<%=key%>&token=<%=token%>";'>
</td></tr></table>
</body>
</html>
