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
long parent = (request.getParameter("parent")!=null?Long.parseLong(request.getParameter("parent")):0);
long editoria = (request.getParameter("editoria")!=null?Long.parseLong(request.getParameter("editoria")):0);
String title = (request.getParameter("title")!=null?request.getParameter("title"):"");
String tag = (request.getParameter("tag")!=null?request.getParameter("tag"):"");
String text = (request.getParameter("article")!=null?request.getParameter("article"):"");
String resume = (request.getParameter("resume")!=null?request.getParameter("resume"):"");
int order = Integer.parseInt(request.getParameter("order")!=null?request.getParameter("order"):"0");
int published = Integer.parseInt(request.getParameter("published")!=null?request.getParameter("published"):"0");
int comments = Integer.parseInt(request.getParameter("comments")!=null?request.getParameter("comments"):"0");
int withsession = Integer.parseInt(request.getParameter("withsession")!=null?request.getParameter("withsession"):"0");
Article tex = new Article();
if(id > 0)
{
   tex = new Article().getArticle(id);
   tex.savePrevious(usuario.getUsu_login());
}
tex.setArt_title(title);
tex.setArt_comments(comments);
tex.setArt_order(order);
tex.setArt_published(published);
tex.setArt_session(withsession);
tex.setArt_tag(tag);
tex.setArt_text(text);
tex.setArt_resume(resume);
tex.setCts_id(parent);
tex.setEdt_id(editoria);
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
<input name='btSubmit' type='button' class='pageButton' value='Back to the item' onClick='location.href="res_article.jsp?id=<%=id%>&key=<%=key%>&token=<%=token%>";'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Back to items list' onClick='location.href="res_article_list.jsp?editoria=<%=editoria%>&key=<%=key%>&token=<%=token%>";'>
</td></tr></table>
</body>
</html>
