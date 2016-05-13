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
%>
<table class='listTable' width="700px">
<tr class=listBody><td>
<span class='pageLabel'>Imagem:</span>
<span class='text'><b><%=tex.getImg_title() + " -> " + tex.getImg_file()%></b></span>
</td></tr><tr class=listBody><td>
	<img src='../img/alert.gif' width='10' height='11' align='absmiddle'>&nbsp;
	<span class='pageLabel'><b>WARNING! This operation is irreversible.</b></span><br><br>
	<input name='btSubmit' type='button' class='pageButton' value='Proceed and delete' onClick='location.href="res_image_deleteok.jsp?id=<%=id%>&key=<%=key%>&token=<%=token%>";'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Cancel and return' onClick='location.href="res_image_list.jsp?key=<%=key%>&token=<%=token%>";'>
</td></tr></table>
</body>
</html>
