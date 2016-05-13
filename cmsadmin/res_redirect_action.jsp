<%@ page language="Java"%>
<%@ page import="site.admin.*, site.forms.*" session="true"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ include file="res_session.jsp"%>
<html>
<link rel="stylesheet" href="../css/styles.css">
<head>
<title>JRocha Framework</title>
</head><body class="listBody">
<%
long id = (request.getParameter("id")!=null?Long.parseLong(request.getParameter("id")):0);
long frmID = (request.getParameter("frmID")!=null?Long.parseLong(request.getParameter("frmID")):0);
String tipo = (request.getParameter("tipo")!=null?request.getParameter("tipo"):"");
String url = (request.getParameter("url")!=null?request.getParameter("url"):"");
int ativo = Integer.parseInt(request.getParameter("ativo")!=null?request.getParameter("ativo"):"");
FormRedirect tex = new FormRedirect();
if(id > 0)
{
	tex = new FormRedirect().getFormRedirect(id);
}
tex.setRed_tipo(tipo.trim());
tex.setRed_url(url.trim());
tex.setRed_ativo(ativo);
tex.setFrm_id(frmID);
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
<input name='btSubmit' type='button' class='pageButton' value='Back to the item' onClick='location.href="res_redirect.jsp?id=<%=id%>&frmID=<%=frmID%>&key=<%=key%>&token=<%=token%>";'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Back to items list' onClick='location.href="res_redirect_list.jsp?frmID=<%=frmID%>&key=<%=key%>&token=<%=token%>";'>
</td></tr></table>
</body>
</html>
