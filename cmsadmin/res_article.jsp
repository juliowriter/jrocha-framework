<%@ page language="Java"%>
<%@ page import="java.util.*, site.admin.*, site.core.*" session="true"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ include file="res_session.jsp"%>
<html>
<head>
<title>JRocha Framework</title>
	<link rel="stylesheet" href="../redactor/redactor.css" />
	<script type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>
	<link rel="icon" href="../img/favicon.png" type="image/png">
	<link rel="shortcut icon" href="../img/favicon.png" type="image/png">
	<link href="../bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="../bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<script type="text/javascript" src="../scp/jquery-1.8.2.min.js"></script>
	<script src="../redactor/redactor.min.js"></script>

	<script type="text/javascript">
	$(document).ready(
		function()
		{
			$('#redactor').redactor({ minHeight: 300, fixed: true, convertDivs: false});
			$('#redactor2').redactor({ minHeight: 150, fixed: true, convertDivs: false});
		}
	);
	</script>
	<style>
		.redactor_editor, .redactor_editor:focus {
			background: <%=new Config().getConfig("SITE_BGCOLOR").getCfg_value()%> !important;
		}
        .redactor_editor a {
	       color: #ffffff !important;
	       text-decoration: none !important;
        }
	</style>
</head><body class="listBody">
<%
String parameterString = request.getQueryString();
long id = (request.getParameter("id")!=null?Long.parseLong(request.getParameter("id")):0);
String title = "";
long parent = 0;
String text = "";
String resume = "";
String tag = "";
long editoria = Long.parseLong(request.getParameter("editoria")!=null?request.getParameter("editoria"):"0");
int order = 0;
int published = 0;
int comments = 0;
int haschat = 0;
int withsession = 0;
if(id > 0) {
   Article tex = new Article().getArticle(id);
   title = tex.getArt_title();
   text = tex.getArt_text();
   resume = tex.getArt_resume();
   tag = tex.getArt_tag();
   editoria = tex.getEdt_id();
   order = tex.getArt_order();
   comments = tex.getArt_comments();
   published = tex.getArt_published();
   parent = tex.getCts_id();
   haschat = tex.getArt_haschat();
   withsession = tex.getArt_session();
}
%>
<form name='frm' action='res_article_action.jsp' method='post'>
<input type='hidden' name='id' value='<%=id%>'>
<input type='hidden' name='key' value='<%=key%>'>
<input type='hidden' name='token' value='<%=token%>'>
<input type='hidden' name='par' value='<%=parameterString%>'>
<table class='listTable' width="700px"><tr class=listBody><td><span class='pageLabel'>Editoria:</span><br>
<select name="editoria">
<%
Iterator<Editoria> ite = new Editoria().getList().iterator();
while(ite.hasNext())
  {
      Editoria edt = ite.next();
  %>
	<option <%=(editoria==edt.getEdt_id()?"SELECTED ":"")%>value="<%=edt.getEdt_id()%>"><%=edt.getEdt_titulo()%></option>
<%}%>
</select>
</td><td><span class='pageLabel'>Publicado:</span><br>
<select name="published"><option <%=(published==0?"SELECTED ":"")%>value="0">Não</option><option <%=(published==1?"SELECTED ":"")%>value="1">Sim</option></select>
</td><td><span class='pageLabel'>Comentários:</span><br>
<select name="comments"><option <%=(comments==0?"SELECTED ":"")%>value="0">Não</option><option <%=(comments==0?"SELECTED ":"")%>value="1">Sim</option></select>
</td><td><span class='pageLabel'>Ordem:</span><br>
<select name="order">
<%for(int xx=1;xx<100;xx++){%>
	<option <%=(order==xx?"SELECTED ":"")%>value="<%=xx%>"><%=xx%></option>
<%}%>
</select>
</td></tr>
<tr class=listBody><td>
<span class='pageLabel'>T&iacute;tulo:</span><br>
<input name='title' id='title' type='text' maxlength='255' value='<%=title!=null?title:""%>'>
</td>
<td>
<span class='pageLabel'>TAG:</span><br>
<input name='tag' id='tag' type='text' maxlength='255' value='<%=tag!=null?tag:""%>'>
</td>
<td><span class='pageLabel'>P&aacute;gina destino:</span><br>
<select name="parent">
<%
Iterator<Content> itt = new Content().getList("cts_tag ASC").iterator();
while(itt.hasNext())
  {
      Content cts = itt.next();
  %>
	<option <%=(parent==cts.getCts_id()?"SELECTED ":"")%>value="<%=cts.getCts_id()%>"><%=cts.getCts_tag()%></option>
<%}%>
</select>
</td><td><span class='pageLabel'>Sess&atilde;o:</span><br>
<select name="withsession"><option <%=(withsession==0?"SELECTED ":"")%>value="0">Não</option><option <%=(withsession==1?"SELECTED ":"")%>value="1">Sim</option></select>
</td>
</tr><tr class=listBody><td colspan='4'>
<span class='pageLabel'>Conte&uacute;do:</span><br>
	<textarea id="redactor" name="article"><%=text%></textarea>
</td>
</tr><tr class=listBody><td colspan='4'>&nbsp;</td>
</tr><tr class=listBody><td colspan='4'>
<span class='pageLabel'>Resumo:</span><br>
	<textarea id="redactor2" name="resume"><%=resume%></textarea>
</td>
</tr></table><br>
<table class='listTable' width="700px"><tr class=listBody><td>
<input name='btSubmit' type='button' class='pageButton' value='Save' onClick='checkFields();'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Cancel and Return' onClick='location.href="res_article_list.jsp?editoria=<%=editoria%>&key=<%=key%>&token=<%=token%>";'>
</td></tr></table></form>
</body>
</html>
<script>
function checkFields()
{
	var frm = document.frm;
	var error_message = "Required field!";
	if(!frm.title.value)
	{
		alert(error_message + " Name.");
		frm.title.focus();
   		return false;
	}
   	frm.submit();
}
</script>
