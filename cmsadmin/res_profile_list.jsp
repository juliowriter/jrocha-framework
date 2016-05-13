<%@ page language="Java"%>
<%@ page import="java.util.*, site.admin.*, site.core.*, site.account.*" session="true"%>
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
String plan = (request.getParameter("plan")!=null?request.getParameter("plan"):"ALL");
if(direction.equals("") && session.getValue("direction")!=null)
	direction = session.getValue("direction").toString();
else if(direction.equals(""))
	direction = " DESC";

String currentPage = request.getRequestURI();
currentPage = currentPage.substring(currentPage.lastIndexOf("/")+1,currentPage.length());

String parameterString = request.getQueryString();
if(parameterString==null) parameterString = "token=" + token + "&key=" + key;

String listOrder = "prf_id";
if (request.getParameter("listOrder")!=null)
	listOrder = request.getParameter("listOrder");

Account objacc = new Account();
%>
<span class="listTitle">Accounts List</span><br>[ <a href="res_account.jsp?id=0&token=<%=token%>&key=<%=key%>">New Account</a> ]
&nbsp;&nbsp;&nbsp;<select name="plan" id="plan"><option value="ALL">ALL</option><option value="BASIC">BASIC</option><option value="ADVANCED">ADVANCED</option><option value="PREMIUM">PREMIUM</option><option value="AGENCY">AGENCY</option></select>
&nbsp;&nbsp;&nbsp;<input type='text' value='<%=filtro%>' name='filtro' id='filtro'><br><br>
<table class='listTable' width='90%'>
<tr class='listSubTitle'>
<td><a href='#' onClick="document._direction.direction.value=(document._direction.direction.value=='ASC')?'DESC':'ASC';self.location.href='<%=currentPage%>?listOrder=prf_firstname&direction=' + document._direction.direction.value + '&plan=' + document.getElementById('plan').value + '&token=<%=token%>&key=<%=key%>';">
Name</a> <%=((listOrder.equals("acc_firstname"))?((direction.equals("ASC"))?"<img src='../img/ico_asc.gif' border=0>":"<img src='../img/ico_desc.gif' border=0>"):"")%></td>
<td><a href='#' onClick="document._direction.direction.value=(document._direction.direction.value=='ASC')?'DESC':'ASC';self.location.href='<%=currentPage%>?listOrder=prf_datetime&direction=' + document._direction.direction.value + '&plan=' + document.getElementById('plan').value + '&token=<%=token%>&key=<%=key%>';">
Date</a> <%=((listOrder.equals("prf_datetime"))?((direction.equals("ASC"))?"<img src='../img/ico_asc.gif' border=0>":"<img src='../img/ico_desc.gif' border=0>"):"")%></td>
<td>Actions</td></tr>
<%
Iterator<Account> it = objacc.getList(filtro, listOrder + " " + direction, plan).iterator();
int count = 0;
while (it.hasNext()){
   count++;
   Account objItem = it.next();
   long id = objItem.getPrf_id();
   String linkEdit = "<a href='res_language.jsp?id=" + id + "&" + parameterString + "'>";
   String linkEditX = "</a>";
   %>
    <tr class=listBody>
    <td><%=linkEdit%><%=(objItem.getPrf_firstname()!=null?objItem.getPrf_firstname():"-")%><%=linkEditX%></td>
    <td><%=linkEdit%><%=(objItem.getPrf_datetime()!=null?objItem.getPrf_datetime():"-")%><%=linkEditX%></td>
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
   		document.location.href = "res_profile.jsp?id=" + id + "&<%=parameterString%>";
	else if(act == "excluir")
   		document.location.href = "res_profile_delete.jsp?id=" + id + "&<%=parameterString%>";
}
function submitQuery()
{
	self.location.href = "res_profile_list.jsp?token=<%=token%>&key=<%=key%>&plan=<%=plan%>" +
	"&filtro=" + document.getElementById("filtro").value;
}
</script>
