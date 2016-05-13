<%@ page language="Java"%>
<%@ page import="java.util.*, site.admin.*, site.core.*" session="true"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ include file="res_session.jsp"%>
<html>
<head>
<title>JRocha Framework</title>
	<script type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>
	<link rel="icon" href="../img/favicon.png" type="image/png">
	<link rel="shortcut icon" href="../img/favicon.png" type="image/png">
	<link href="../bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="../bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<script type="text/javascript" src="../scp/jquery-1.8.2.min.js"></script>

	<script type="text/javascript">
	$(document).ready(
		function()
		{
			//$('#redactor').redactor({ minHeight: 300, fixed: true, convertDivs: false});
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
String tag = "";
String text = "";
int order = 0;
int published = 0;
int comments = 0;
long parent = 0;
int haschat = 0;
int withsession = 0;
if(id > 0) {
   Content tex = new Content().getContent(id);
   title = tex.getCts_title();
   tag = tex.getCts_tag();
   text = tex.getCts_text();
   order = tex.getCts_order();
   comments = tex.getCts_comments();
   published = tex.getCts_published();
   parent = tex.getCts_parent();
   haschat = tex.getCts_haschat();
   withsession = tex.getCts_session();
}
%>
<form name='frm' action='res_texto_action.jsp' method='post'>
<input type='hidden' name='id' value='<%=id%>'>
<input type='hidden' name='key' value='<%=key%>'>
<input type='hidden' name='token' value='<%=token%>'>
<input type='hidden' name='par' value='<%=parameterString%>'>
<table class='listTable' width="90%"><tr class=listBody><td>
</td><td><span class='pageLabel'>Tag:</span><br>
<input name='tag' id='tag' type='text' class='pageText' size='40' maxlength='255' value='<%=tag!=null?tag:""%>'>
</td><td><span class='pageLabel'>Published:</span><br>
<select name="published"><option <%=(published==0?"SELECTED ":"")%>value="0">No</option><option <%=(published==1?"SELECTED ":"")%>value="1">Yes</option></select>
</td><td><span class='pageLabel'>Order:</span><br>
<select name="order">
<%for(int xx=1;xx<999;xx++){%>
	<option <%=(order==xx?"SELECTED ":"")%>value="<%=xx%>"><%=xx%></option>
<%}%>
</select>
</td><td><span class='pageLabel'>Session:</span><br>
<select name="withsession"><option <%=(withsession==0?"SELECTED ":"")%>value="0">No</option><option <%=(withsession==1?"SELECTED ":"")%>value="1">Yes</option></select>
</td></tr>
<tr class=listBody><td colspan='5'>
<span class='pageLabel'>Name:</span><br>
<input name='title' id='title' type='text' maxlength='255' value='<%=title!=null?title:""%>'>
</td></tr><tr class=listBody><td colspan='5'>
<span class='pageLabel'>Content:</span><br>
	<textarea id="redactor" name="texto" style="width: 100%; height: 600px;"><%=text%></textarea>
</td></tr></table><br>
<table class='listTable' width="700px"><tr class=listBody><td>
<input name='btSubmit' type='button' class='pageButton' value='Save' onClick='checkFields();'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Cancel and Return' onClick='location.href="res_texto_list.jsp?key=<%=key%>&token=<%=token%>";'>
</td></tr></table></form>
</body>
</html>
<script>
function checkFields()
{
	var frm = document.frm;
	var error_message = "Required field!";
	if(!frm.tag.value)
	{
		alert(error_message + " Tag.");
		frm.tag.focus();
   		return false;
	}
	if(!frm.title.value)
	{
		alert(error_message + " Name.");
		frm.title.focus();
   		return false;
	}
   	frm.submit();
}
</script>
