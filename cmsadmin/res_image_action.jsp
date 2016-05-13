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
String carousel_caption = (request.getParameter("carousel_caption")!=null?request.getParameter("carousel_caption"):"");
String carousel_description = (request.getParameter("carousel_description")!=null?request.getParameter("carousel_description"):"");
String lang = (request.getParameter("language")!=null?request.getParameter("language"):"br");
Image tex = new Image();
tex = new Image().getImage(id);
tex.setImg_title(title.trim());
tex.setImg_tag(tag.trim());
tex.setImg_language(lang);
tex.setImg_carousel_caption(carousel_caption.trim());
tex.setImg_carousel_description(carousel_description.trim());
tex.save();
%>
<table class='listTable' width="700px"><tr class=listBody><td>
<span class='pageLabel'><b>
Item alterado com sucesso!
</b></span>
<br><br>
<input name='btNew' type='button' class='pageButton' value='Novo item' onClick='location.href="res_image.jsp?id=0&key=<%=key%>&token=<%=token%>";'>&nbsp;&nbsp;&nbsp;
<input name='btSubmit' type='button' class='pageButton' value='Back to the item' onClick='location.href="res_image.jsp?id=<%=id%>&key=<%=key%>&token=<%=token%>";'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Back to items list' onClick='location.href="res_image_list.jsp?key=<%=key%>&token=<%=token%>";'>
</td></tr></table>
</body>
</html>
