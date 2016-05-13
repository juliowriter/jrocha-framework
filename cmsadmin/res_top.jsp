<%@ page import="java.util.*, site.admin.*, site.core.*" session="true"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ include file="res_session.jsp"%>
<html>
<head>
<title>JRocha Framework</title>
<link rel="stylesheet" href="../css/styles.css">
<script>
var show = true;
function showHide(){
	if(show){
		//parent.menu.style.width = 10;
		parent.document.getElementById("menuTD").style.width = "0px";
		parent.document.getElementById("menu").style.width = "0px";
		show = false;
	} else {
		//parent.menu.style.width = 150;
		parent.document.getElementById("menuTD").style.width = "150px";
		parent.document.getElementById("menu").style.width = "150px";
		show = true;
	}
}
</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form name="frm" action="res_busca.jsp?token=<%=token%>&key<%=key%>" method="post" target="principal">
<input type='hidden' name='key' value='<%=key%>'>
<input type='hidden' name='token' value='<%=token%>'>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
     <tr>
      <td width="150px" align="left" valign="middle"><a href="res_main.jsp?token=<%=token%>&key=<%=key%>" target="_top">JRocha Framework</a>
      <br><a href='#' onClick='showHide();'><img src='../img/ico_showhide.gif' border='0'></a><br>
      </td>
      <td align="center">
      <%
      String nomeUsuario=usuario.getUsu_nome();
      if (nomeUsuario!=null && !nomeUsuario.equals("") && nomeUsuario.indexOf(" ")!=-1){
      	nomeUsuario = nomeUsuario.substring(0,nomeUsuario.indexOf(" "));
      }
      Calendar c = Calendar.getInstance();
      String data = (new Util().formatZero(c.get(Calendar.DAY_OF_MONTH),2)) + "/" + (new Util().formatZero((c.get(Calendar.MONTH)+1),2)) + "/" + c.get(Calendar.YEAR);
      out.print("<span class='nomecampo'>User: <b>" + nomeUsuario + "</b></span><br>");
      out.print("<span class='texto'>" + data + "</span>");
      %>
      </td>
      <td class="busca" align="left" width="220px"> &nbsp;
	  </td>
    </tr>
</table><img src="../img/img_green_line.gif" width="100%" height="2">
</form>
</body>
</html>
