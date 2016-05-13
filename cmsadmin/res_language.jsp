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
String lng = (request.getParameter("lng")!=null?request.getParameter("lng"):"br");
String from = "";
String to = "";
if(id > 0) {
   Language tex = new Language().getLanguage(id, lng);
   from = tex.getLng_from();
   to = tex.getLng_to();
}
%>
<form name='frm' action='res_language_action.jsp' method='post'>
<input type='hidden' name='id' value='<%=id%>'>
<input type='hidden' name='key' value='<%=key%>'>
<input type='hidden' name='token' value='<%=token%>'>
<input type='hidden' name='lng' value='<%=lng%>'>
<input type='hidden' name='par' value='<%=parameterString%>'>
<table class='listTable' width="700px"><tr class=listBody><td>
<tr class=listBody><td colspan='5'>
<span class='pageLabel'>From:</span><br>
	<textarea name="from" style="width: 80%; height: 200px;"><%=from%></textarea>
</td></tr>
<tr class=listBody><td colspan='5'>
<span class='pageLabel'>To:</span><br>
	<textarea name="to" style="width: 80%; height: 200px;"><%=to%></textarea>
</td></tr>
</table><br>
<table class='listTable' width="700px"><tr class=listBody><td>
<input name='btSubmit' type='button' class='pageButton' value='Save' onClick='checkFields();'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Cancel and Return' onClick='location.href="res_language_list.jsp?lng=<%=lng%>&key=<%=key%>&token=<%=token%>";'>
</td></tr></table></form>
</body>
</html>
<script>
function checkFields()
{
	var frm = document.frm;
	var error_message = "Required field!";
	if(!frm.from.value)
	{
		alert(error_message + " From.");
		frm.from.focus();
   		return false;
	}
	if(!frm.to.value)
	{
		alert(error_message + " To.");
		frm.to.focus();
   		return false;
	}
   	frm.submit();
}
</script>
