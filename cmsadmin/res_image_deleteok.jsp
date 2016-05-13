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
Image tex = new Image().getImage(id);
boolean delOk = false;
//Apaga arquivo fisicamente.
String caminho = (new Config().getConfig("SITE_PATH").getCfg_value()) + (new Config().getConfig("IMAGES_PATH").getCfg_value()) + tex.getImg_file();
try{
	java.io.File myFile = new java.io.File(caminho);
	delOk = myFile.delete();
} catch(Exception e) {e.printStackTrace();}
if(delOk)
   delOk = (tex!=null?tex.delete():false);
%>
<table class='listTable' width="700px">
<tr class=listBody><td>
<span class='pageLabel'>Texto:</span>
<span class='text'><b><%=(tex!=null?tex.getImg_title() + " -> " + tex.getImg_file():"Imagem não encontrada!")%></b></span>
</td></tr><tr class=listBody><td><span class='pageLabel'><b>
<%if(delOk){%>
	The item was deleted.
	</b></span><br><br>
<%} else {%>
	<img src='../img/alert.gif' width='10' height='11' align='absmiddle'>&nbsp;
	<span class='pageLabel'><b>Error! Can't delete this item!</b></span><br><br>
<%}%>
<input name='btBack' type='button' class='pageButton' value='Return to list' onClick='location.href="res_image_list.jsp?key=<%=key%>&token=<%=token%>";'>
</td></tr></table>
</body>
</html>
