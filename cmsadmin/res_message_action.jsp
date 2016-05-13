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
String assunto = (request.getParameter("assunto")!=null?request.getParameter("assunto"):"");
String texto = (request.getParameter("texto")!=null?request.getParameter("texto"):"");
int ativo = Integer.parseInt(request.getParameter("ativo")!=null?request.getParameter("ativo"):"");
FormMessage tex = new FormMessage();
if(id > 0)
{
	tex = new FormMessage().getFormMessage(id);
}
tex.setMsg_tipo(tipo.trim());
tex.setMsg_assunto(assunto.trim());
tex.setMsg_texto(texto.trim());
tex.setMsg_ativo(ativo);
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
<input name='btSubmit' type='button' class='pageButton' value='Back to the item' onClick='location.href="res_message.jsp?id=<%=id%>&frmID=<%=frmID%>&key=<%=key%>&token=<%=token%>";'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Back to items list' onClick='location.href="res_message_list.jsp?frmID=<%=frmID%>&key=<%=key%>&token=<%=token%>";'>
</td></tr></table>
</body>
</html>
