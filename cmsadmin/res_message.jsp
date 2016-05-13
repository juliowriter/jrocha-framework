<%@ page language="Java"%>
<%@ page import="site.admin.*, site.forms.*" session="true"%>
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
long frmID = (request.getParameter("frmID")!=null?Long.parseLong(request.getParameter("frmID")):0);
String tipo = "";
String assunto = "";
String texto = "";
int ativo = 1;
if(id > 0) {
   FormMessage tex = new FormMessage().getFormMessage(id);
   tipo = tex.getMsg_tipo();
   assunto = tex.getMsg_assunto();
   texto = tex.getMsg_texto();
   ativo = tex.getMsg_ativo();
}
%>
<form name='frm' action='res_message_action.jsp' method='post'>
<input type='hidden' name='id' value='<%=id%>'>
<input type='hidden' name='frmID' value='<%=frmID%>'>
<input type='hidden' name='key' value='<%=key%>'>
<input type='hidden' name='token' value='<%=token%>'>
<input type='hidden' name='par' value='<%=parameterString%>'>
<table class='listTable' width="700px">
<tr class=listBody>
<td><span class='pageLabel'>TAG:</span><br>
<input name='tipo' id='tipo' type='text' class='pageText' size='40' maxlength='255' value='<%=tipo!=null?tipo:""%>'>
</td>
<td><span class='pageLabel'>Ativo:</span><br>
<input name='ativo' id='ativo' type="radio" value="1"<%=(ativo==1?" CHECKED":"")%>>Sim &nbsp;&nbsp;&nbsp;
<input name='ativo' id='ativo' type="radio" value="0"<%=(ativo==0?" CHECKED":"")%>>Não
</td>
</tr><tr class=listBody><td colspan="2">
<span class='pageLabel'>Assunto:</span><br>
<input name='assunto' id='assunto' type='text' class='pageText' size='80' maxlength='255' value='<%=assunto!=null?assunto:""%>'>
</tr><tr class=listBody><td colspan="2">
<span class='pageLabel'>Texto:</span><br>
<textarea name='texto' id='texto' rows="10" cols="100"><%=texto!=null?texto:""%></textarea>
</td></tr></table><br>
<table class='listTable' width="700px"><tr class=listBody><td>
<input name='btSubmit' type='button' class='pageButton' value='Save' onClick='checkFields();'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Cancel and Return' onClick='location.href="res_message_list.jsp?frmID=<%=frmID%>&key=<%=key%>&token=<%=token%>";'>
</td></tr></table></form>
</body>
</html>
<script>
function checkFields()
{
	var frm = document.frm;
	var error_message = "Required field!";
	if(!frm.tipo.value)
	{
		alert(error_message + " TAG.");
		frm.tipo.focus();
   		return false;
	}
	if(!frm.assunto.value)
	{
		alert(error_message + " Assunto.");
		frm.assunto.focus();
   		return false;
	}
   	frm.submit();
}
</script>
