<%@ page language="Java"%>
<%@ page import="java.util.*, site.admin.*, site.core.*" session="true"%>
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
String title = "";
String tag = "";
String text = "";
if(id > 0) {
   Block tex = new Block().getBlock(id);
   title = tex.getBlk_name();
   tag = tex.getBlk_tag();
   text = tex.getBlk_text().replace("textarea","_textarea_").replace("&lsaquo;","_lsaquo;").replace("&rsaquo;","_rsaquo;");
}
%>
<form name='frm' action='res_bloco_action.jsp' method='post'>
<input type='hidden' name='id' value='<%=id%>'>
<input type='hidden' name='key' value='<%=key%>'>
<input type='hidden' name='token' value='<%=token%>'>
<input type='hidden' name='par' value='<%=parameterString%>'>
<table class='listTable' width="700px"><tr class=listBody><td><span class='pageLabel'>Tag:</span><br>
<input name='tag' id='tag' type='text' class='pageText' size='40' maxlength='255' value='<%=tag!=null?tag:""%>'>
</td></tr><tr class=listBody><td colspan='5'>
<span class='pageLabel'>Name:</span><br>
<input name='title' id='title' type='text' class='pageText' size='127' maxlength='255' value='<%=title!=null?title:""%>'>
</td></tr><tr class=listBody><td colspan='5'>
<span class='pageLabel'>Content:</span><br>
	<textarea rows=40 cols=180 name="texto"><%=text%></textarea>
</td></tr></table><br>
<table class='listTable' width="700px"><tr class=listBody><td>
<input name='btSubmit' type='button' class='pageButton' value='Save' onClick='checkFields();'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Cancel and Return' onClick='location.href="res_bloco_list.jsp?key=<%=key%>&token=<%=token%>";'>
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
