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
String title = (request.getParameter("title")!=null?request.getParameter("title"):"");
String tag = (request.getParameter("tag")!=null?request.getParameter("tag"):"");
String text = (request.getParameter("texto")!=null?request.getParameter("texto"):"");
int order = Integer.parseInt(request.getParameter("order")!=null?request.getParameter("order"):"0");
int published = Integer.parseInt(request.getParameter("published")!=null?request.getParameter("published"):"0");
int comments = Integer.parseInt(request.getParameter("comments")!=null?request.getParameter("comments"):"0");
int withsession = Integer.parseInt(request.getParameter("withsession")!=null?request.getParameter("withsession"):"0");
Content tex = new Content();
if(id > 0)
{
   tex = new Content().getContent(id);
   tex.savePrevious(usuario.getUsu_login());
}
tex.setCts_title(title);
tex.setCts_comments(comments);
tex.setCts_order(order);
tex.setCts_published(published);
tex.setCts_session(withsession);
tex.setCts_tag(tag);
tex.setCts_text(text);
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
<input name='btSubmit' type='button' class='pageButton' value='Back to the item' onClick='location.href="res_texto.jsp?id=<%=id%>&key=<%=key%>&token=<%=token%>";'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Back to items list' onClick='location.href="res_texto_list.jsp?key=<%=key%>&token=<%=token%>";'>
</td></tr></table>
</body>
</html>
