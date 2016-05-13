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
Block tex = new Block();
if(id > 0)
{
	tex = new Block().getBlock(id);
	tex.savePrevious(usuario.getUsu_login());
}
tex.setBlk_name(title.trim());
tex.setBlk_tag(tag.trim());
tex.setBlk_text(text.trim().replace("_textarea_","textarea").replace("_lsaquo;","&lsaquo;").replace("_rsaquo;","&rsaquo;"));
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
<input name='btSubmit' type='button' class='pageButton' value='Back to the item' onClick='location.href="res_bloco.jsp?id=<%=id%>&key=<%=key%>&token=<%=token%>";'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Go to the items list' onClick='location.href="res_bloco_list.jsp?key=<%=key%>&token=<%=token%>";'>
</td></tr></table>
</body>
</html>
