<%@ page language="Java"%>
<%@ page import="java.util.*, site.admin.*, site.core.*" session="true"%>
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
String filtro = (request.getParameter("filtro")!=null?request.getParameter("filtro"):"");
String lng = (request.getParameter("lng")!=null?request.getParameter("lng"):"br");
if(direction.equals("") && session.getValue("direction")!=null)
	direction = session.getValue("direction").toString();
else if(direction.equals(""))
	direction = " DESC";

String currentPage = request.getRequestURI();
currentPage = currentPage.substring(currentPage.lastIndexOf("/")+1,currentPage.length());

String parameterString = request.getQueryString();
if(parameterString==null) parameterString = "token=" + token + "&key=" + key;

String listOrder = "lng_id";
if (request.getParameter("listOrder")!=null)
	listOrder = request.getParameter("listOrder");

Language objlng = new Language();
%>
<span class="listTitle">Texts for Translation</span><br>[ <a href="res_language.jsp?id=0&token=<%=token%>&key=<%=key%>">New Text</a> ]
&nbsp;&nbsp;&nbsp;<select name="lng" id="lng"><option value="br">Portuguese - BR</option><option value="es">Spanish - ES</option></select>
<br><br>
<table class='listTable' width='90%'>
<tr class='listSubTitle'>
<td><a href='#' onClick="document._direction.direction.value=(document._direction.direction.value=='ASC')?'DESC':'ASC';self.location.href='<%=currentPage%>?listOrder=lng_from&direction=' + document._direction.direction.value + '&lng=' + document.getElementById('lng').value + '&token=<%=token%>&key=<%=key%>';">
From</a> <%=((listOrder.equals("lng_from"))?((direction.equals("ASC"))?"<img src='../img/ico_asc.gif' border=0>":"<img src='../img/ico_desc.gif' border=0>"):"")%></td>
<td><a href='#' onClick="document._direction.direction.value=(document._direction.direction.value=='ASC')?'DESC':'ASC';self.location.href='<%=currentPage%>?listOrder=lng_to&direction=' + document._direction.direction.value + '&lng=' + document.getElementById('lng').value + '&token=<%=token%>&key=<%=key%>';">
To</a> <%=((listOrder.equals("lng_to"))?((direction.equals("ASC"))?"<img src='../img/ico_asc.gif' border=0>":"<img src='../img/ico_desc.gif' border=0>"):"")%></td>
<td>Actions</td></tr>
<%
Iterator<Language> it = objlng.getList(filtro, listOrder + " " + direction, lng).iterator();
int count = 0;
while (it.hasNext()){
   count++;
   Language objItem = it.next();
   long id = objItem.getLng_id();
   String linkEdit = "<a href='res_language.jsp?id=" + id + "&" + parameterString + "'>";
   String linkEditX = "</a>";
   %>
    <tr class=listBody>
    <td><%=linkEdit%><%=(objItem.getLng_from()!=null?objItem.getLng_from():"-")%><%=linkEditX%></td>
    <td><%=linkEdit%><%=(objItem.getLng_to()!=null?objItem.getLng_to():"-")%><%=linkEditX%></td>
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
   		document.location.href = "res_language.jsp?id=" + id + "&<%=parameterString%>";
	else if(act == "excluir")
   		document.location.href = "res_language_delete.jsp?id=" + id + "&<%=parameterString%>";
}
function submitQuery()
{
	self.location.href = "res_language_list.jsp?token=<%=token%>&key=<%=key%>&lng=<%=lng%>" +
	"&filtro=" + document.getElementById("filtro").options[document.getElementById("filtro").options.selectedIndex].value;
}
</script>
