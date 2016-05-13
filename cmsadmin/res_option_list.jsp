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
if(direction.equals("") && session.getValue("direction")!=null)
	direction = session.getValue("direction").toString();
else if(direction.equals(""))
	direction = " DESC";

String currentPage = request.getRequestURI();
currentPage = currentPage.substring(currentPage.lastIndexOf("/")+1,currentPage.length());

String parameterString = request.getQueryString();
if(parameterString==null) parameterString = "token=" + token + "&key=" + key;

String listOrder = "opt_title";
if (request.getParameter("listOrder")!=null)
	listOrder = request.getParameter("listOrder");

HeaderOptions obj = new HeaderOptions();
%>
<span class="listTitle">Menu Options</span><br>[ <a href="res_option.jsp?id=0&token=<%=token%>&key=<%=key%>">New Menu Option</a> ]
<br><br>
<table class='listTable' width='90%'>
<tr class='listSubTitle'>
<td><a href='#' onClick="document._direction.direction.value=(document._direction.direction.value=='ASC')?'DESC':'ASC';self.location.href='<%=currentPage%>?listOrder=opt_title&direction=' + document._direction.direction.value + '&token=<%=token%>&key=<%=key%>';">
Title</a> <%=((listOrder.equals("opt_title"))?((direction.equals("ASC"))?"<img src='../img/ico_asc.gif' border=0>":"<img src='../img/ico_desc.gif' border=0>"):"")%></td>
<td><a href='#' onClick="document._direction.direction.value=(document._direction.direction.value=='ASC')?'DESC':'ASC';self.location.href='<%=currentPage%>?listOrder=opt_link&direction=' + document._direction.direction.value + '&token=<%=token%>&key=<%=key%>';">
Link</a> <%=((listOrder.equals("opt_link"))?((direction.equals("ASC"))?"<img src='../img/ico_asc.gif' border=0>":"<img src='../img/ico_desc.gif' border=0>"):"")%></td>
<td>Order</td>
<td>Type</td>
<td>Language</td>
<td>Actions</td></tr>
<%
Iterator<HeaderOptions> it = obj.getList(listOrder + " " + direction).iterator();
int count = 0;
while (it.hasNext()){
   count++;
   HeaderOptions objItem = it.next();
   long id = objItem.getOpt_id();
   String linkEdit = "<a href='res_option.jsp?id=" + id + "&" + parameterString + "'>";
   String linkEditX = "</a>";
   %>
    <tr class=listBody>
    <td>
    <%=linkEdit%><b><%=objItem.getOpt_title()%></b><%=linkEditX%></td>
    <td><%=linkEdit%><%=(objItem.getOpt_link()!=null?objItem.getOpt_link():"-")%><%=linkEditX%></td>
    <td><%=linkEdit%><%=objItem.getOpt_order()%><%=linkEditX%></td>
    <td><%=linkEdit%><%=(objItem.getOpt_status() == 1?"Default":(objItem.getOpt_status() == 2?"Session Only":"Not Visible"))%><%=linkEditX%></td>
    <td><%=linkEdit%><%=objItem.getOpt_language()%><%=linkEditX%></td>
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
   		document.location.href = "res_option.jsp?id=" + id + "&<%=parameterString%>";
	else if(act == "excluir")
   		document.location.href = "res_option_delete.jsp?id=" + id + "&<%=parameterString%>";
}
function submitQuery()
{
	self.location.href = "res_option_list.jsp?token=<%=token%>&key=<%=key%>" +
	"&filtro=" + document.getElementById("filtro").options[document.getElementById("filtro").options.selectedIndex].value;
}
</script>
