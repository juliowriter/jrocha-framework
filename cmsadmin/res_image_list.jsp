<%@ page language="Java"%>
<%@ page import="java.util.*, java.text.*, site.admin.*, site.core.*" session="true"%>
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

String listOrder = "img_title";
if (request.getParameter("listOrder")!=null)
	listOrder = request.getParameter("listOrder");

Image obj = new Image();
%>
<span class="listTitle">Lista de Imagens</span><br>[ <a href="res_image.jsp?id=0&token=<%=token%>&key=<%=key%>">Cadastrar nova imagem</a> ]
<br><br>
<table class='listTable' width='90%'>
<tr class='listSubTitle'>
<td><a href='#' onClick="document._direction.direction.value=(document._direction.direction.value=='ASC')?'DESC':'ASC';self.location.href='<%=currentPage%>?listOrder=img_title&direction=' + document._direction.direction.value + '&token=<%=token%>&key=<%=key%>';">
T&iacute;tulo</a> <%=((listOrder.equals("img_title"))?((direction.equals("ASC"))?"<img src='../img/ico_asc.gif' border=0>":"<img src='../img/ico_desc.gif' border=0>"):"")%></td>
<td><a href='#' onClick="document._direction.direction.value=(document._direction.direction.value=='ASC')?'DESC':'ASC';self.location.href='<%=currentPage%>?listOrder=img_file&direction=' + document._direction.direction.value + '&token=<%=token%>&key=<%=key%>';">
URL</a> <%=((listOrder.equals("img_file"))?((direction.equals("ASC"))?"<img src='../img/ico_asc.gif' border=0>":"<img src='../img/ico_desc.gif' border=0>"):"")%></td>
<td><a href='#' onClick="document._direction.direction.value=(document._direction.direction.value=='ASC')?'DESC':'ASC';self.location.href='<%=currentPage%>?listOrder=img_tag&direction=' + document._direction.direction.value + '&token=<%=token%>&key=<%=key%>';">
TAG</a> <%=((listOrder.equals("img_tag"))?((direction.equals("ASC"))?"<img src='../img/ico_asc.gif' border=0>":"<img src='../img/ico_desc.gif' border=0>"):"")%></td>
<td>Tamanho</td>
<td>Idioma</td>
<td>Actions</td></tr>
<%
Iterator<Image> it = obj.getList(listOrder + " " + direction).iterator();
int count = 0;
while (it.hasNext()){
   count++;
   Image objItem = it.next();
   long id = objItem.getImg_id();
   String linkEdit = "<a href='res_image.jsp?id=" + id + "&" + parameterString + "'>";
   String linkEditX = "</a>";
   String url = (new Config().getConfig("IMAGES_URL").getCfg_value()) + (objItem.getImg_file()!=null?objItem.getImg_file():"-");

   double tamanho = objItem.getImg_size();
   String unidade = " bytes";
   if(objItem.getImg_size() >= 1048576)
   {
	   tamanho = ((double)objItem.getImg_size() / (double)1048576);
	   unidade = " MBytes";
   } else if(objItem.getImg_size() >= 1024)
   {
	   tamanho = ((double)objItem.getImg_size() / (double)1024);
	   unidade = " KBytes";
   }
   DecimalFormat format = new DecimalFormat();
   format.setMaximumFractionDigits(2);
   String fileSize = format.format(tamanho) + unidade;
   %>
    <tr class=listBody>
    <td>
    <%=linkEdit%><b><%=objItem.getImg_title()%></b><%=linkEditX%></td>
    <td><%=linkEdit%><%=url%><%=linkEditX%></td>
    <td><%=linkEdit%><%=objItem.getImg_tag()%><%=linkEditX%></td>
    <td><%=linkEdit%><%=fileSize%><%=linkEditX%></td>
    <td><%=linkEdit%><%=objItem.getImg_language()%><%=linkEditX%></td>
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
   		document.location.href = "res_image.jsp?id=" + id + "&<%=parameterString%>";
	else if(act == "excluir")
   		document.location.href = "res_image_delete.jsp?id=" + id + "&<%=parameterString%>";
}
function submitQuery()
{
	self.location.href = "res_image_list.jsp?token=<%=token%>&key=<%=key%>" +
	"&filtro=" + document.getElementById("filtro").options[document.getElementById("filtro").options.selectedIndex].value;
}
</script>
