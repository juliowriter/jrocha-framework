<%@ page import="java.util.*, site.admin.*" session="true"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ include file="res_session.jsp"%>
<html>
<head>
<title>JRocha Framework</title>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<link rel="stylesheet" href="../css/styles.css">
<script>
function menu(indice){
	document.location.href = "res_menu.jsp?token=<%=token%>&key=<%=key%>&idFunc="+indice;
}
</script>
</head>
<body>
<%
long idFunc = 0;
if (request.getParameter("idFunc")!=null){
	idFunc = Long.parseLong(request.getParameter("idFunc"));
	//ao trocar de opcoes, marcamos o novo funcionalidade Id, para compor as tabs (abas) dinamicamente em outros jsps
	try {
		session.setAttribute("FUNCIONALIDADE_ID", request.getParameter("idFunc"));
	}
	catch(Exception eFunc){
		out.print("<script>var caminho=self.location.href;caminho = caminho.substring( caminho.lastIndexOf('/')+1, caminho.lastIndexOf('/') -caminho.length;top.location.href= caminho + 'icrm_sessionend.jsp';</script>");
	}
}
Menu func = new Menu();
List<Menu> collPai = func.getFuncionalidadePerfil(profile);//pega todas as funcionalidades sem pai
List<Menu> collFilho;
Iterator<Menu> itPai = collPai.iterator();

//variaveis usadas para escrita na pagina
String textoPai = "";
String textoFilho;
String funcionalidadePai = "";
String funcionalidadeFilho;
String rotinaPai = "";
String rotinaFilho= "";
String tipoFramePai = "frm";
String tipoFrameFilho = "frm";
//String accessOptions = "";
long indicePai= -1;
long indiceFilho=-1;
//iteramos cada opcao pai
String linkPai, link;
int totalPai=0;
long arrIndices[]=new long[100];
long abrirPai=0;
while(itPai.hasNext()){
	Menu pai = (Menu)itPai.next();
	funcionalidadePai = pai.getTar_descricao();
	textoPai = pai.getTar_texto();
	indicePai = pai.getTar_id();
	boolean temFilhas = pai.temFilhas(indicePai);
	rotinaPai = pai.getTar_rotina();
	tipoFramePai = pai.getTar_tipo().trim();
	//accessOptions += (rotinaPai + "|");
	if (tipoFramePai.equals("frm")) {
		linkPai = " href=javascript:menu(" + indicePai + ");parent.principal.location.href='" + rotinaPai + (rotinaPai.indexOf("?") > 0?"&":"?") + "token=" + token + "&key=" + key + "'; ";
	}
	else if (tipoFramePai.equals("full")) {
		linkPai = " href=javascript:menu(" + indicePai + ");parent.location.href='" + rotinaPai + (rotinaPai.indexOf("?") > 0?"&":"?") + "token=" + token + "&key=" + key + "'; ";
	}
	else if (tipoFramePai.equals("new")) {
		linkPai = " href=javascript:menu(" + indicePai + ");window.open('" + rotinaPai + (rotinaPai.indexOf("?") > 0?"&":"?") + "token=" + token + "&key=" + key + "'); ";
	}
	else {
		linkPai = " href=javascript:menu(" + indicePai + "); ";
	}
	collFilho = func.getFuncionalidade(indicePai, profile);//pegou todas as funcionalidades filhas
	Iterator<Menu> itFilho = collFilho.iterator();
	if (itFilho.hasNext()){
		arrIndices[totalPai] = indicePai;
		out.print("<table width='100%' border='0' cellpadding='0' cellspacing='0' class=menu><tr>");
		out.print("<td width='1%'><div align='right'><a " + linkPai + " title='" + textoPai + "'><img id='img" + indicePai + "' src='../img/ico_mais.gif' width='11' height='11' border=0></a></div></td>");
		out.print("<td width='1%'><div align='left'><img src='../img/img_traco_h.gif' width='12' height='1' align='absmiddle'></div></td>");
		out.print("<td width='98%'><span class='texto'>");
		if (temFilhas) out.print("<b>");
		out.print("<a " + linkPai + " title='" + textoPai + "'>" + funcionalidadePai + "</a>");
		if (temFilhas) out.print("<b>");
		out.print("</span></td></tr>");
		out.print("<tr>");
		out.print("<td align='center'><img src='../img/img_traco_v.gif' width='1' height='4'></td>");
		out.print("<td colspan='2'><img src='../img/px.gif' height=1 width='1'></td>");
		out.print("</tr>");

		indiceFilho = -1;
		funcionalidadeFilho = "";
		textoFilho="";
		rotinaFilho = "";
		tipoFrameFilho="frm";
		out.print("<tr><td colspan='3'><table width='100%' border='0' cellpadding='0' cellspacing='0' id='subopcao" + indicePai + "' style='display: none'>");
		link = "";

		//para cada pai encontrado iteramos os filhos
		while (itFilho.hasNext()){

			Menu filho = (Menu)itFilho.next();
			indiceFilho = filho.getTar_id();
			funcionalidadeFilho= filho.getTar_descricao();
			textoFilho = filho.getTar_texto();
			rotinaFilho = filho.getTar_rotina();
			//accessOptions += (rotinaFilho + "|");
			tipoFrameFilho = filho.getTar_tipo().trim();
			boolean temNetas = filho.temFilhas(indiceFilho);

			if (tipoFrameFilho.equals("frm")) {
				link = " href='" + rotinaFilho + (rotinaFilho.indexOf("?") > 0?"&":"?") + "token=" + token + "&key=" + key + "' target='principal'";
			}
			else if (tipoFrameFilho.equals("full")) {
				link = " href='" + rotinaFilho + (rotinaFilho.indexOf("?") > 0?"&":"?") + "token=" + token + "&key=" + key + "'  target='_top'";
			}
			else if (tipoFrameFilho.equals("new")) {
				link = " href='#' onClick='window.open(" + rotinaFilho + (rotinaFilho.indexOf("?") > 0?"&":"?") + "token=" + token + "&key=" + key + ");'";
			}
			else {
				link = " href='#'";
			}
			if(indiceFilho == idFunc) abrirPai = indicePai;
			out.print("<tr>");
			out.print("<td width='1%'><div align='right'><img src='../img/ico_traco_t.gif' width='11' height='11'></div></td>");
			out.print("<td width='1%'><img src='../img/img_traco_h.gif' width='12' height='1' align='absmiddle'></td>");
			out.print("<td width='98%'><span class='texto'><a " + link + " title='" + textoFilho +"'>" + funcionalidadeFilho + "</a></span></td>");
			out.print("</tr>");
			out.print("<tr>");
			out.print("<td align='center'><img src='../img/img_traco_v.gif' width='1' height='4'></td>");
			out.print("<td colspan='2'><img src='../img/px.gif' height=1 width='1'></td>");
			out.print("</tr>");

		} //while
		out.print("</table>");
		out.print("</td></tr>");
	}// fim do if que testa se existe filhos
	else {

		if (tipoFramePai.equals("frm")) {
			linkPai = " href=javascript:menu(" + indicePai + ");parent.principal.location.href='" + rotinaPai + (rotinaPai.indexOf("?") > 0?"&":"?") + "token=" + token + "&key=" + key + "'; ";
		}
		else if (rotinaPai.equals("res_login.jsp")) {
			linkPai = " href=javascript:parent.location.href='res_login.jsp?key=" + key + "'; ";
		}
		else if (tipoFramePai.equals("full")) {
			linkPai = " href=javascript:parent.location.href='" + rotinaPai + (rotinaPai.indexOf("?") > 0?"&":"?") + "token=" + token + "&key=" + key + "'; ";
		}
		else if (tipoFramePai.equals("new")) {
			linkPai = " href=javascript:menu(" + indicePai + ");window.open('" + rotinaPai + (rotinaPai.indexOf("?") > 0?"&":"?") + "token=" + token + "&key=" + key + "'); ";
		}
		else {
			linkPai = " href=javascript:menu(" + indicePai + "); ";
		}
		out.print("<table width='100%' border='0' cellpadding='0' cellspacing='0' class=menu><tr>");
		out.print("<td width='1%'><div align='right'><img id='img" + indicePai + "' src='../img/ico_toc.gif' width='11' height='11'></div></td>");
		out.print("<td width='1%'><div align='left'><img src='../img/img_traco_h.gif' width='12' height='1' align='absmiddle'></div></td>");
		out.print("<td width='98%'><div class='menu'><a " + linkPai + " title='" + textoPai + "'>" + funcionalidadePai + "</a></div></td></tr>");
		if (itPai.hasNext()) {
			out.print("<tr>");
			out.print("<td align='center'><img src='../img/img_traco_v.gif' width='1' height='4'></td>");
			out.print("<td colspan='2'><img src='../img/px.gif' height=1 width='1'></td>");
			out.print("</tr>");
		}

	}//if existe registro


} //fim do pai
%>
</table>
</body>
</html>
<script>
<%
for (int i=0; i<totalPai;i++){
	if (arrIndices[i] > 0){	%>
		eval("document.all.img<%=arrIndices[i]%>").src= "../img/ico_menos.gif";
		eval("document.all.subopcao<%=arrIndices[i]%>").style.display="none";
	<%
	}
}
if(idFunc > 0){
	if (abrirPai > 0) {
	%>
		eval("document.all.img<%=abrirPai%>").src= "../img/ico_mais.gif";
		eval("document.all.subopcao<%=abrirPai%>").style.display="block";
<%} else{%>
	var img= eval("document.all.img<%=request.getParameter("idFunc")%>.src");
	if ( img.indexOf('ico_mais.gif') > 0 ) {
		eval("document.all.img<%=request.getParameter("idFunc")%>").src="../img/ico_menos.gif";
		eval("document.all.subopcao<%=request.getParameter("idFunc")%>").style.display="block";
	}
<%}
}
//session.putValue("accessOptions", accessOptions);
%>
</script>
