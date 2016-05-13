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
long editoria = Long.parseLong(request.getParameter("editoria")!=null?request.getParameter("editoria"):"0");
String filtro = "edt_id = " + editoria;
if(direction.equals("") && session.getValue("direction")!=null)
	direction = session.getValue("direction").toString();
else if(direction.equals(""))
	direction = " DESC";

String currentPage = request.getRequestURI();
currentPage = currentPage.substring(currentPage.lastIndexOf("/")+1,currentPage.length());

String parameterString = request.getQueryString();
if(parameterString==null) parameterString = "token=" + token + "&key=" + key;

String listOrder = "art_id";
if (request.getParameter("listOrder")!=null)
	listOrder = request.getParameter("listOrder");

Article obj = new Article();
%>
<span class="listTitle">Articles</span><br>
[ <a href="res_article.jsp?id=0&editoria=<%=editoria%>&token=<%=token%>&key=<%=key%>">New Article</a> ]
<br><br>
<select name="editoria" id="editoria" onChange="submitQuery()">
<option value="0"> Select the category</option>
<%
Iterator<Editoria> itt = new Editoria().getList().iterator();
while(itt.hasNext())
  {
      Editoria edt = itt.next();
  %>
	<option <%=(editoria==edt.getEdt_id()?"SELECTED ":"")%>value="<%=edt.getEdt_id()%>"><%=edt.getEdt_titulo()%></option>
<%}%>
</select>
<br>
<table class='listTable' width='90%'>
<tr class='listSubTitle'>
<td><a href='#' onClick="document._direction.direction.value=(document._direction.direction.value=='ASC')?'DESC':'ASC';self.location.href='<%=currentPage%>?listOrder=descricao&direction=' + document._direction.direction.value + '&token=<%=token%>&key=<%=key%>';">
T&iacute;tulo</a> <%=((listOrder.equals("art_title"))?((direction.equals("ASC"))?"<img src='../img/ico_asc.gif' border=0>":"<img src='../img/ico_desc.gif' border=0>"):"")%></td>
<td>Publicado</td>
<td>Actions</td></tr>
<%
Iterator<Article> it = obj.getList(filtro, listOrder + " " + direction).iterator();
int count = 0;
while (it.hasNext()){
   count++;
   Article objItem = it.next();
   long id = objItem.getArt_id();
   String linkEdit = "<a href='res_article.jsp?id=" + id + "&" + parameterString + "'>";
   String linkEditX = "</a>";
   %>
    <tr class=listBody>
    <td><%=linkEdit%><%=(objItem.getArt_title()!=null?objItem.getArt_title():"-")%><%=linkEditX%></td>
    <td><%=linkEdit%><%=(objItem.getArt_published() == 1?"Sim":"N&atilde;o")%><%=linkEditX%></td>
    <td align='left'>
		<a href="#" onClick="action('editar',<%=id%>)"><img src="../img/ico_edit.gif" border=0></a>
		&nbsp;&nbsp;&nbsp;
		<a href="#" onClick="action('restore',<%=id%>)"><img src="../img/ico_restore.gif" border=0></a>
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
   		document.location.href = "res_article.jsp?id=" + id + "&<%=parameterString%>";
	else if(act == "restore")
   		document.location.href = "res_article_restore.jsp?id=" + id + "&<%=parameterString%>";
	else if(act == "excluir")
   		document.location.href = "res_article_delete.jsp?id=" + id + "&<%=parameterString%>";
}
function submitQuery()
{
	self.location.href = "res_article_list.jsp?token=<%=token%>&key=<%=key%>" +
	"&editoria=" + document.getElementById("editoria").options[document.getElementById("editoria").options.selectedIndex].value;
}
</script>
