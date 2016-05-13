<%@ page language="Java"%>
<%@ page import="site.admin.*,site.forms.*" session="true"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ include file="res_session.jsp"%>
<html>
<link rel="stylesheet" href="../css/styles.css">
<head>
<title>JRocha Framework</title>
</head><body class="listBody">
<%
long id = (request.getParameter("id")!=null?Long.parseLong(request.getParameter("id")):0);
String nome = (request.getParameter("nome")!=null?request.getParameter("nome"):"");
String origem = (request.getParameter("origem")!=null?request.getParameter("origem"):"");
int confirmacao = Integer.parseInt(request.getParameter("confirmacao")!=null?request.getParameter("confirmacao"):"0");
int agradecimento = Integer.parseInt(request.getParameter("agradecimento")!=null?request.getParameter("agradecimento"):"0");
int copia = Integer.parseInt(request.getParameter("copia")!=null?request.getParameter("copia"):"0");
int urlok = Integer.parseInt(request.getParameter("urlok")!=null?request.getParameter("urlok"):"0");
int urlerro = Integer.parseInt(request.getParameter("urlerro")!=null?request.getParameter("urlerro"):"0");
Form tex = new Form();
if(id > 0)
{
	tex = new Form().getForm(id);
}
tex.setFrm_nome(nome.trim());
tex.setFrm_origem(origem.trim());
tex.setFrm_msgagradecimento(agradecimento);
tex.setFrm_msgconfirmacao(confirmacao);
tex.setFrm_msgcopia(copia);
tex.setFrm_urlok(urlok);
tex.setFrm_urlerro(urlerro);
if(id > 0)
	tex.save();
else
	id = tex.insert();
%>
<table class='listTable' width="700px"><tr class=listBody><td>
<span class='pageLabel'><b>
Item saved!
</b></span>
<br><br>
<input name='btSubmit' type='button' class='pageButton' value='Back to the item' onClick='location.href="res_form.jsp?id=<%=id%>&key=<%=key%>&token=<%=token%>";'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Back to items list' onClick='location.href="res_form_list.jsp?key=<%=key%>&token=<%=token%>";'>
</td></tr></table>
</body>
</html>
