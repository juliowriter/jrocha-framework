<%@ page language="Java"%>
<%@ page import="java.util.*, site.admin.*, site.core.*, site.lead.*" session="true"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ include file="res_session.jsp"%>
<html>
<link rel="stylesheet" href="../css/styles.css">
<head>
<title>JRocha Framework</title>
</head>
<body class="listBody">
<%
String direction=((request.getParameter("direction")!=null && !request.getParameter("direction").equals("null"))?request.getParameter("direction"):"");
long editoria = Long.parseLong(request.getParameter("editoria")!=null?request.getParameter("editoria"):"0");
String currentPage = request.getRequestURI();
currentPage = currentPage.substring(currentPage.lastIndexOf("/")+1,currentPage.length());

String parameterString = request.getQueryString();
if(parameterString==null) parameterString = "token=" + token + "&key=" + key;

Import obj = new Import();
%>
<span class="listTitle">Lista de Importacoes de Usuarios</span><br>
<br>
<table class='listTable' width='90%'>
<tr class='listSubTitle'>
<td>Id Usuario</td>
<td>Data/Hora</td>
<td>Arquivo</td>
<td>Actions</td></tr>
<%
Iterator<Import> it = obj.getImports("P").iterator();
int count = 0;
while (it.hasNext()){
   count++;
   Import objItem = it.next();
   long id = objItem.getImp_id();
   String linkEdit = "<a href='res_import.jsp?id=" + id + "&" + parameterString + "'>";
   String linkEditX = "</a>";
   %>
    <tr class=listBody>
    <td><%=linkEdit%><%=(objItem.getPrf_id())%><%=linkEditX%></td>
    <td><%=linkEdit%><%=(objItem.getImp_datetime())%><%=linkEditX%></td>
    <td><%=linkEdit%><%=(objItem.getImp_file())%><%=linkEditX%></td>
    <td align='left'>
		<a href="#" onClick="action('editar',<%=id%>)"><img src="../img/ico_edit.gif" border=0></a>
		&nbsp;&nbsp;&nbsp;
		<a href="#" onClick="action('processar',<%=id%>)"><img src="../img/ico_checked.gif" border=0></a>
		&nbsp;&nbsp;&nbsp;
		<a href="#" onClick="action('excluir',<%=id%>)"><img src="../img/ico_delete.gif" border=0></a>
   </td></tr>
<% } %>
<tr><td colspan='5'><strong>Total: <%=count%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href='#' onClick='self.print()'><img src='../img/ico_print.gif' alt='Print this page' border='0' align='absmiddle'></a>&nbsp;<a href='#' onClick='self.print()'>Print this page</a></strong></td>
</tr></table>
<form name=_direction><input type='hidden' name='direction' value='<%=direction%>'></form>
</body>
</html>
<script type="text/javascript">
function action(act, id)
{
	if(act == "editar")
   		document.location.href = "res_import.jsp?id=" + id + "&<%=parameterString%>";
	else if(act == "processar")
   		document.location.href = "res_import_processa.jsp?id=" + id + "&<%=parameterString%>";
	else if(act == "excluir")
   		document.location.href = "res_import_delete.jsp?id=" + id + "&<%=parameterString%>";
}
</script>
