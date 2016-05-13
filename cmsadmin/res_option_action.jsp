<%@ page language="Java"%>
<%@ page import="site.admin.*, site.core.*" session="true"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ include file="res_session.jsp"%>
<html>
<link rel="stylesheet" href="../css/styles.css">
<head>
<title>JRocha Framework</title>
</head><body class="listBody">
<%
long id = (request.getParameter("id")!=null?Long.parseLong(request.getParameter("id")):0);
String title = (request.getParameter("title")!=null?request.getParameter("title"):"");
String link = (request.getParameter("link")!=null?request.getParameter("link"):"");
String destino = (request.getParameter("destino")!=null?request.getParameter("destino"):"");
String lang = (request.getParameter("language")!=null?request.getParameter("language"):"br");
int order = Integer.parseInt(request.getParameter("order")!=null?request.getParameter("order"):"0");
int type = Integer.parseInt(request.getParameter("type")!=null?request.getParameter("type"):"0");
HeaderOptions tex = new HeaderOptions();
if(id > 0)
{
   tex = new HeaderOptions().getHeaderOptions(id);
}
tex.setOpt_title(title);
tex.setOpt_link(link);
tex.setOpt_target(destino);
tex.setOpt_status(type);
tex.setOpt_order(order);
tex.setOpt_language(lang);
if(id > 0)
	tex.save();
else
	id = tex.insert();
%>
<table class='listTable' width="700px"><tr class=listBody><td>
<span class='pageLabel'><b>
Item saved!
</b></span>
<br><br>
<input name='btSubmit' type='button' class='pageButton' value='Back to the item' onClick='location.href="res_option.jsp?id=<%=id%>&key=<%=key%>&token=<%=token%>";'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Back to items list' onClick='location.href="res_option_list.jsp?key=<%=key%>&token=<%=token%>";'>
</td></tr></table>
</body>
</html>
