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
</head><body class="listBody">
<%
String parameterString = request.getQueryString();
long id = (request.getParameter("id")!=null?Long.parseLong(request.getParameter("id")):0);
String title = "";
String link = "";
String destino = "";
String lang = "";
int order = 0;
int type = 0;
if(id > 0) {
   HeaderOptions tex = new HeaderOptions().getHeaderOptions(id);
   title = tex.getOpt_title();
   link = tex.getOpt_link();
   destino = tex.getOpt_target();
   order = tex.getOpt_order();
   type = tex.getOpt_status();
   lang = tex.getOpt_language();
}
%>
<form name='frm' action='res_option_action.jsp' method='post'>
<input type='hidden' name='id' value='<%=id%>'>
<input type='hidden' name='key' value='<%=key%>'>
<input type='hidden' name='token' value='<%=token%>'>
<input type='hidden' name='par' value='<%=parameterString%>'>
<table class='listTable' width="700px"><tr class=listBody><td><span class='pageLabel'>Title:</span><br>
<input name='title' id='title' type='text' class='pageText' size='40' maxlength='255' value='<%=title!=null?title:""%>'>
</td><td><span class='pageLabel'>Language:</span><br>
<select name="language">
<option <%=(lang.equals("en")?"SELECTED ":"")%>value="en">English</option>
<option <%=(lang.equals("br")?"SELECTED ":"")%>value="br">Portuguese</option>
<option <%=(lang.equals("es")?"SELECTED ":"")%>value="es">Spanish</option>
</select>
</td><td><span class='pageLabel'>Target:</span><br>
<select name="destino">
<option <%=(destino.equals("_top")?"SELECTED ":"")%>value="_top">Same Window</option>
<option <%=(destino.equals("_blank")?"SELECTED ":"")%>value="_blank">New Window</option>
</select>
</td></tr>
<tr><td><span class='pageLabel'>Link:</span><br>
<select name="link">
<%
String[] tags = new Content().getTags();
for(int xx=0;xx<tags.length;xx++)
{%>
	<option <%=(link.equals(tags[xx])?"SELECTED ":"")%>value="<%=tags[xx]%>"><%=tags[xx]%></option>
<%}%>
</select>
</td><td><span class='pageLabel'>Type:</span><br>
<select name="type">
<option <%=(type==0?"SELECTED ":"")%>value="0">Not Visible</option>
<option <%=(type==1?"SELECTED ":"")%>value="1">Default</option>
<option <%=(type==2?"SELECTED ":"")%>value="2">Session Only</option></select>
</td><td><span class='pageLabel'>Order:</span><br>
<select name="order">
<%for(int xx=1;xx<100;xx++){%>
	<option <%=(order==xx?"SELECTED ":"")%>value="<%=xx%>"><%=xx%></option>
<%}%>
</select>
</td></tr></table>
<table class='listTable' width="700px"><tr class=listBody><td>
<input name='btSubmit' type='button' class='pageButton' value='Save' onClick='checkFields();'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Cancel and Return' onClick='location.href="res_option_list.jsp?key=<%=key%>&token=<%=token%>";'>
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
		alert(error_message + " Title.");
		frm.title.focus();
   		return false;
 	}
   	frm.submit();
}
</script>
