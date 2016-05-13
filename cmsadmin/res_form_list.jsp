<%@ page language="Java"%>
<%@ page import="java.util.*, site.admin.*,site.forms.*" session="true"%>
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
if(direction.equals("") && session.getValue("direction")!=null)
	direction = session.getValue("direction").toString();

String currentPage = request.getRequestURI();
currentPage = currentPage.substring(currentPage.lastIndexOf("/")+1,currentPage.length());

String parameterString = request.getQueryString();
if(parameterString==null) parameterString = "token=" + token + "&key=" + key;

String listOrder = "frm_nome";
if (request.getParameter("listOrder")!=null)
	listOrder = request.getParameter("listOrder");

Form obj = new Form();
%>
<span class="listTitle">Lista de Formulários</span><br>[ <a href="res_form.jsp?id=0&token=<%=token%>&key=<%=key%>">Cadastrar novo formulário</a> ]
<br><br>
<table class='listTable' width='90%'>
<tr class='listSubTitle'>
<td><a href='#' onClick="document._direction.direction.value=(document._direction.direction.value=='ASC')?'DESC':'ASC';self.location.href='<%=currentPage%>?listOrder=frm_nome&direction=' + document._direction.direction.value + '&token=<%=token%>&key=<%=key%>';">
Nome</a> <%=((listOrder.equals("frm_nome"))?((direction.equals("ASC"))?"<img src='../img/ico_asc.gif' border=0>":"<img src='../img/ico_desc.gif' border=0>"):"")%></td>
<td><a href='#' onClick="document._direction.direction.value=(document._direction.direction.value=='ASC')?'DESC':'ASC';self.location.href='<%=currentPage%>?listOrder=frm_origem&direction=' + document._direction.direction.value + '&token=<%=token%>&key=<%=key%>';">
Origem</a> <%=((listOrder.equals("frm_origem"))?((direction.equals("ASC"))?"<img src='../img/ico_asc.gif' border=0>":"<img src='../img/ico_desc.gif' border=0>"):"")%></td>
<td>Actions</td></tr>
<%
Iterator<Form> it = obj.getList(listOrder + " " + direction).iterator();
int count = 0;
while (it.hasNext()){
   count++;
   Form objItem = it.next();
   long id = objItem.getFrm_id();
   String linkEdit = "<a href='res_form.jsp?id=" + id + "&" + parameterString + "'>";
   String linkEditX = "</a>";
   %>
    <tr class=listBody>
    <td>
    <%=linkEdit%><b><%=objItem.getFrm_nome()%></b><%=linkEditX%></td>
    <td><%=linkEdit%><%=(objItem.getFrm_origem()!=null?objItem.getFrm_origem():"-")%><%=linkEditX%></td>
    <td align='left'>
		<a href="#" onClick="action('editar',<%=id%>)"><img src="../img/ico_edit.gif" border=0></a>
		&nbsp;&nbsp;&nbsp;
		<a href="#" onClick="action('redirect',<%=id%>)"><img src="../img/ico_redirect.gif" border=0></a>
		&nbsp;&nbsp;&nbsp;
		<a href="#" onClick="action('message',<%=id%>)"><img src="../img/ico_message.gif" border=0></a>
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
   		document.location.href = "res_form.jsp?id=" + id + "&<%=parameterString%>";
	else if(act == "redirect")
   		document.location.href = "res_redirect_list.jsp?frmID=" + id + "&<%=parameterString%>";
	else if(act == "message")
   		document.location.href = "res_message_list.jsp?frmID=" + id + "&<%=parameterString%>";
	else if(act == "excluir")
   		document.location.href = "res_form_delete.jsp?id=" + id + "&<%=parameterString%>";
}
function submitQuery()
{
	self.location.href = "res_form_list.jsp?token=<%=token%>&key=<%=key%>" +
	"&filtro=" + document.getElementById("filtro").options[document.getElementById("filtro").options.selectedIndex].value;
}
</script>
