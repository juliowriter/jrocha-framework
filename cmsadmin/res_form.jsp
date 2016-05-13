<%@ page language="Java"%>
<%@ page import="site.admin.*,site.forms.*" session="true"%>
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
String nome = "";
String origem = "";
int confirmacao = 1;
int agradecimento = 1;
int copia = 1;
int urlok = 1;
int urlerro = 1;
if(id > 0) {
   Form tex = new Form().getForm(id);
   nome = tex.getFrm_nome();
   origem = tex.getFrm_origem();
   confirmacao = tex.getFrm_msgconfirmacao();
   agradecimento = tex.getFrm_msgagradecimento();
   copia = tex.getFrm_msgcopia();
   urlok = tex.getFrm_urlok();
   urlerro = tex.getFrm_urlerro();
}
%>
<form name='frm' action='res_form_action.jsp' method='post'>
<input type='hidden' name='id' value='<%=id%>'>
<input type='hidden' name='key' value='<%=key%>'>
<input type='hidden' name='token' value='<%=token%>'>
<input type='hidden' name='par' value='<%=parameterString%>'>
<table class='listTable' width="700px">
<tr class=listBody>
<td colspan="3"><span class='pageLabel'>Nome:</span><br>
<input name='nome' id='nome' type='text' class='pageText' size='60' maxlength='255' value='<%=nome!=null?nome:""%>'>
</td>
<td colspan="3"><span class='pageLabel'>Origem:</span><br>
<input name='origem' id='origem' type='text' class='pageText' size='70' maxlength='255' value='<%=origem!=null?origem:""%>'>
</td>
</tr><tr class=listBody>
<td>
<span class='pageLabel'>Mensagens -></span><br>
</td>
<td>
<span class='pageLabel'>Confirmação:</span><br>
<input type="radio" name="confirmacao" value="1"<%=(confirmacao == 1?" CHECKED":"")%>>Sim &nbsp;&nbsp;&nbsp; <input type="radio" name="confirmacao" value="0"<%=(confirmacao == 0?" CHECKED":"")%>>Não
</td>
<td>
<span class='pageLabel'>Agradecimento:</span><br>
<input type="radio" name="agradecimento" value="1"<%=(agradecimento == 1?" CHECKED":"")%>>Sim &nbsp;&nbsp;&nbsp; <input type="radio" name="agradecimento" value="0"<%=(agradecimento == 0?" CHECKED":"")%>>Não
</td>
<td>
<span class='pageLabel'>Cópia:</span><br>
<input type="radio" name="copia" value="1"<%=(copia== 1?" CHECKED":"")%>>Sim &nbsp;&nbsp;&nbsp; <input type="radio" name="copia" value="0"<%=(copia == 0?" CHECKED":"")%>>Não
</td>
<td>
<span class='pageLabel'>URL OK:</span><br>
<input type="radio" name="urlok" value="1"<%=(urlok == 1?" CHECKED":"")%>>Sim &nbsp;&nbsp;&nbsp; <input type="radio" name="urlok" value="0"<%=(urlok == 0?" CHECKED":"")%>>Não
</td>
<td>
<span class='pageLabel'>URL Erro:</span><br>
<input type="radio" name="urlerro" value="1"<%=(urlerro == 1?" CHECKED":"")%>>Sim &nbsp;&nbsp;&nbsp; <input type="radio" name="urlerro" value="0"<%=(urlerro == 0?" CHECKED":"")%>>Não
</td>
</tr></table><br>
<table class='listTable' width="700px"><tr class=listBody><td>
<input name='btSubmit' type='button' class='pageButton' value='Save' onClick='checkFields();'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Cancel and Return' onClick='location.href="res_form_list.jsp?key=<%=key%>&token=<%=token%>";'>
</td></tr></table></form>
</body>
</html>
<script>
function checkFields()
{
	var frm = document.frm;
	var error_message = "Required field!";
	if(!frm.nome.value)
	{
		alert(error_message + " Nome.");
		frm.nome.focus();
   		return false;
	}
	if(!frm.origem.value)
	{
		alert(error_message + " Origem.");
		frm.origem.focus();
   		return false;
	}
   	frm.submit();
}
</script>
