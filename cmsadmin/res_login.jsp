<%@ page language="Java"%>
<%@ page import="java.util.*, site.admin.*" session="true"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ page errorPage="res_err.jsp"%>
<script language="JavaScript1.2">
function valida(){
	var f= document.frm;
    if (!f.usuario.value){
		window.alert("Please, inform your username");
		f.usuario.focus();
		return;
    } else {
		if (!f.senha.value){
			window.alert("Please, inform your password");
			f.senha.focus();
			return;
		}

	}
	f.submit();
}
</script>
<%
String key = (request.getParameter("key")!=null?request.getParameter("key"):"0");
session.setAttribute("USUARIO",null);
session.putValue("accessOptions","");
long lngUsuarioId=0;
boolean entrada = true;
if(!key.equals("0"))
{%>
	<script>
	function foco()
	{
		document.frm.usuario.focus();
	}
	</script>
<%
		//autenticamos usuario
		if (request.getParameter("usuario")!=null && request.getParameter("senha")!=null) {
			String usuario=request.getParameter("usuario");
			String senha=request.getParameter("senha");
			lngUsuarioId = -1;
			try {
		User login = new User();
		lngUsuarioId = login.validaLoginSenha(usuario, senha, Long.parseLong(key));
			}
			catch(NullPointerException npe ) {
		npe.printStackTrace();
			}
			catch(Exception _e){
		_e.printStackTrace();
			}
			//se obteve sucesso
			if (lngUsuarioId > 0){
		User u = new User(lngUsuarioId);
		if(!u.getUsu_status().equals("I")){
			entrada = false;
			String token = u.getIdSessao();
			session.setAttribute("USUARIO",u);
			session.putValue("IDSESSAO", token);
			String profile = u.getPrf_id();
			Menu func = new Menu();
			List<Menu> collPai = func.getFuncionalidadePerfil(profile);//pega todas as funcionalidades sem pai
			List<Menu> collFilho;
			Iterator<Menu> itPai = collPai.iterator();
			String accessOptions = "";
			long arrIndices[]=new long[100];
			int totalPai=0;
			long indicePai= -1;
			long indiceFilho=-1;
			while(itPai.hasNext()){
				Menu pai = itPai.next();
				indicePai = pai.getTar_id();
				boolean temFilhas = pai.temFilhas(indicePai);
				String rotinaPai = pai.getTar_rotina();
				accessOptions += (rotinaPai + "|");
				collFilho = func.getFuncionalidade(indicePai, profile);//pegou todas as funcionalidades filhas
				Iterator<Menu> itFilho = collFilho.iterator();
				if (itFilho.hasNext()){
					arrIndices[totalPai] = indicePai;
					//para cada pai encontrado iteramos os filhos
					while (itFilho.hasNext()){
						Menu filho = (Menu)itFilho.next();
						indiceFilho = filho.getTar_id();
						String rotinaFilho = filho.getTar_rotina();
						accessOptions += (rotinaFilho + "|");
					}
				}
			}
			session.putValue("accessOptions", accessOptions);
			response.sendRedirect("res_main.jsp?token=" + token + "&key=" + key);
		} else {
			try {
		session.invalidate();
			} catch(Exception invalida) {
		throw new Exception("This user is inactive or blocked! Please contact the system administrator.");
			}
		}
			} else {
		try {
			session.invalidate();
		} catch(Exception invalida) {
			throw new Exception("This user is inactive or blocked! Please contact the system administrator.");
		}
			}
		}
} else {%>
	<script>
	function foco()
	{
		document.frm.key.focus();
	}
	</script>
<%}
String imagem = "../img/adm_logo.png";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  	<title>JRocha Framework</title>
	<meta http-equiv='Content-Language' content='pt-br'>
	<meta http-equiv='Content-Type' content='text/html; charset=windows-1252'>
	<link rel="stylesheet" href="../css/styles.css">
	<link rel="icon" href="../img/favicon.png" type="image/png">
	<link rel="shortcut icon" href="../img/favicon.png" type="image/png">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="foco();">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
	<td align="center"><span class="texto"><br>
	  <br>
	  <img src="<%=imagem%>"><br>
	  &nbsp; <br>
	  <%if (lngUsuarioId<0){%>
	  <font color="#CC0000"><strong>
		Invalid user and/or password! Please, try again.
	  </strong></font><br>
	  <%}%>
	  </span>
	  <table width="26%" align="center" class="lista">
	  <form name="frm" method="post" action="res_login.jsp">
  		<input name="key" type="hidden" id="key" value="1">
	  <tr>
	  <td width="31%"><span class="nomecampo">
		User
	  </span></td>
	  <td width="69%"> <input name="usuario" type="text" id="usuario" maxlength="64" class="text"> </td>
	  </tr>
	  <tr>
	  <td><span class="nomecampo">
	 	Password
	  </span></td>
	  <td nowrap><p>
		  <input name="senha" type="password" id="senha" maxlength="64" class="text">
	  </p>
	  </td>
	  </tr>
	  <tr>
	  <td>&nbsp;</td>
	  <td><input name="btEnviar" type="submit" id="btEnviar"
			value="Login"
	     class="submit" onClick="valida()">
      </td>
	  </tr>
      </form>
      </table><span class="texto">
	  <div align="center">
	  <br>
				<a href="http://www.pixconn.com">www.pixconn.com</a></div>
	  </span></td>
  </tr>
</table>
</body>
</html>
