<%@ page language="Java"%>
<%@ page import="site.admin.*, site.core.Config" session="true"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ include file="res_session.jsp"%>
<html>
<link rel="stylesheet" href="../css/styles.css">
<head>
<title>JRocha Framework</title>
</head><body class="listBody">
<%
long id = (request.getParameter("id")!=null?Long.parseLong(request.getParameter("id")):0);
Config tex = new Config().getConfig(id);
boolean delOk = (tex!=null?tex.delete():false);
%>
<table class='listTable' width="700px">
<tr class=listBody><td>
<span class='pageLabel'>TAG:</span>
<span class='text'><b><%=(tex!=null?tex.getCfg_tag():"Item not found!")%></b></span>
</td></tr><tr class=listBody><td><span class='pageLabel'><b>
<%if(delOk){%>
	The item was deleted.
	</b></span><br><br>
<%} else {%>
	<img src='../img/alert.gif' width='10' height='11' align='absmiddle'>&nbsp;
	<span class='pageLabel'><b>Error! Can't delete this item!</b></span><br><br>
<%}%>
<input name='btBack' type='button' class='pageButton' value='Return to list' onClick='location.href="res_config_list.jsp?key=<%=key%>&token=<%=token%>";'>
</td></tr></table>
</body>
</html>
