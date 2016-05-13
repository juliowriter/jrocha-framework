<%@ page language="Java"%>
<%@ page import="site.admin.*, site.core.*" session="true"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ include file="res_session.jsp"%>
<html>
<head>
<link rel="stylesheet" href="../css/styles.css">
<title>JRocha Framework</title>
	<script type="text/javascript" src="../scp/jquery-1.8.2.min.js"></script>
</head><body class="listBody">
<%
String parameterString = request.getQueryString();
long id = (request.getParameter("id")!=null?Long.parseLong(request.getParameter("id")):0);
String tag = "";
String valor = "";
if(id > 0) {
   Config tex = new Config().getConfig(id);
   tag = tex.getCfg_tag();
   valor = tex.getCfg_value();
}
%>
<form name='frm' action='res_config_action.jsp' method='post'>
<input type='hidden' name='id' value='<%=id%>'>
<input type='hidden' name='key' value='<%=key%>'>
<input type='hidden' name='token' value='<%=token%>'>
<input type='hidden' name='par' value='<%=parameterString%>'>
<table class='listTable' width="700px">
<tr class=listBody>
<td><span class='pageLabel'>TAG:</span><br>
<input name='tag' id='tag' type='text' class='pageText' size='40' maxlength='255' value='<%=tag!=null?tag:""%>'>
</td>
</tr><tr class=listBody><td>
<span class='pageLabel'>Value:</span><br>
<textarea name='valor' id='value' rows="10" cols="100"><%=valor!=null?valor:""%></textarea>
</td></tr></table><br>
<table class='listTable' width="700px"><tr class=listBody><td>
<input name='btSubmit' type='button' class='pageButton' value='Save' onClick='checkFields();'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Cancel and Return' onClick='location.href="res_config_list.jsp?key=<%=key%>&token=<%=token%>";'>
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
		alert(error_message + " TAG.");
		frm.tag.focus();
   		return false;
	}
	if(!frm.valor.value)
	{
		alert(error_message + " Value.");
		frm.valor.focus();
   		return false;
	}
   	frm.submit();
}
</script>
