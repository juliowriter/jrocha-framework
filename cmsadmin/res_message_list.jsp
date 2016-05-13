<%@ page language="Java"%>
<%@ page import="java.util.*, site.admin.*, site.forms.*" session="true"%>
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
long frmID = Long.parseLong(request.getParameter("frmID")!=null?request.getParameter("frmID"):"0");
if(direction.equals("") && session.getValue("direction")!=null)
	direction = session.getValue("direction").toString();

String currentPage = request.getRequestURI();
currentPage = currentPage.substring(currentPage.lastIndexOf("/")+1,currentPage.length());

String parameterString = request.getQueryString();
if(parameterString==null) parameterString = "token=" + token + "&key=" + key;

String listOrder = "msg_tipo";
if (request.getParameter("listOrder")!=null)
	listOrder = request.getParameter("listOrder");

FormMessage obj = new FormMessage();
%>
<span class="listTitle">Lista de Mensagens</span><br>[ <a href="res_message.jsp?id=0&frmID=<%=frmID%>&token=<%=token%>&key=<%=key%>">Cadastrar nova mensagem</a> ]
<br><br>
<table class='listTable' width='90%'>
<tr class='listSubTitle'>
<td><a href='#' onClick="document._direction.direction.value=(document._direction.direction.value=='ASC')?'DESC':'ASC';self.location.href='<%=currentPage%>?listOrder=msg_tipo&direction=' + document._direction.direction.value + '&token=<%=token%>&key=<%=key%>';">
TAG</a> <%=((listOrder.equals("msg_tipo"))?((direction.equals("ASC"))?"<img src='../img/ico_asc.gif' border=0>":"<img src='../img/ico_desc.gif' border=0>"):"")%></td>
<td><a href='#' onClick="document._direction.direction.value=(document._direction.direction.value=='ASC')?'DESC':'ASC';self.location.href='<%=currentPage%>?listOrder=msg_assunto&direction=' + document._direction.direction.value + '&token=<%=token%>&key=<%=key%>';">
Assunto</a> <%=((listOrder.equals("msg_assunto"))?((direction.equals("ASC"))?"<img src='../img/ico_asc.gif' border=0>":"<img src='../img/ico_desc.gif' border=0>"):"")%></td>
<td>Ativo</td>
<td>Actions</td></tr>
<%
Iterator<FormMessage> it = obj.getList(frmID, listOrder + " " + direction).iterator();
int count = 0;
while (it.hasNext()){
   count++;
   FormMessage objItem = it.next();
   long id = objItem.getMsg_id();
   String linkEdit = "<a href='res_message.jsp?id=" + id + "&" + parameterString + "'>";
   String linkEditX = "</a>";
   %>
    <tr class=listBody>
    <td>
    <%=linkEdit%><b><%=objItem.getMsg_tipo()%></b><%=linkEditX%></td>
    <td><%=linkEdit%><%=(objItem.getMsg_assunto()!=null?objItem.getMsg_assunto():"-")%><%=linkEditX%></td>
    <td><%=linkEdit%><%=(objItem.getMsg_ativo()==1?"Sim":"Não")%><%=linkEditX%></td>
    <td align='left'>
		<a href="#" onClick="action('editar',<%=id%>)"><img src="../img/ico_edit.gif" border=0></a>
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
   		document.location.href = "res_message.jsp?id=" + id + "&<%=parameterString%>";
	else if(act == "excluir")
   		document.location.href = "res_message_delete.jsp?id=" + id + "&<%=parameterString%>";
}
function submitQuery()
{
	self.location.href = "res_message_list.jsp?frmID=<%=frmID%>&token=<%=token%>&key=<%=key%>" +
	"&filtro=" + document.getElementById("filtro").options[document.getElementById("filtro").options.selectedIndex].value;
}
</script>
