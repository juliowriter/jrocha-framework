<%@ page language="Java"%>
<%@ page import="java.util.*, site.admin.*, site.data.*, site.core.*" session="true"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ include file="res_session.jsp"%>
<html>
<link rel="stylesheet" href="../css/styles.css">
<head>
<title>Julio Rocha</title>
</head>
<body class="listBody">
<%
long id = (request.getParameter("id")!=null?Long.parseLong(request.getParameter("id")):0);
Order objItem = new Order().getOrder(id);

String direction=((request.getParameter("direction")!=null && !request.getParameter("direction").equals("null"))?request.getParameter("direction"):"DESC");
String start = (request.getParameter("nextSet")!=null && !request.getParameter("nextSet").equals("null") && !request.getParameter("nextSet").equals("")?request.getParameter("nextSet"):"0");
String listOrder = (request.getParameter("listOrder")!=null?request.getParameter("listOrder"):"ord_id");
String item = (request.getParameter("item")!=null && !request.getParameter("item").equals("")?request.getParameter("item"):"xxx");
String status = (request.getParameter("status")!=null && !request.getParameter("status").equals("")?request.getParameter("status"):"A");
int day_1 = Integer.parseInt(request.getParameter("day_1")!=null && !request.getParameter("day_1").equals("")?request.getParameter("day_1"):"0");
int day_2 = Integer.parseInt(request.getParameter("day_2")!=null && !request.getParameter("day_2").equals("")?request.getParameter("day_2"):"0");
int month_1 = Integer.parseInt(request.getParameter("month_1")!=null && !request.getParameter("month_1").equals("")?request.getParameter("month_1"):"1");
int month_2 = Integer.parseInt(request.getParameter("month_2")!=null && !request.getParameter("month_2").equals("")?request.getParameter("month_2"):"1");
int year_1 = Integer.parseInt(request.getParameter("year_1")!=null && !request.getParameter("year_1").equals("")?request.getParameter("year_1"):"2009");
int year_2 = Integer.parseInt(request.getParameter("year_2")!=null && !request.getParameter("year_2").equals("")?request.getParameter("year_2"):"2009");
String parameterString = "nextSet=" + start + "&listOrder=" + listOrder + "&direction=" + direction +
						 "&day_1=" + day_1 + "&month_1=" + month_1 + "&year_1=" + year_1 +
						 "&day_2=" + day_2 + "&month_2=" + month_2 + "&year_1=" + year_2 +
						 "&status=" + status + "&item=" + item;

String label = "<b>Novo<b>";
   if(objItem.getOrd_status().equals("R"))
	   label = "<font color='orange'><b>Aguardando Pagamento<b></font>";
   else if(objItem.getOrd_status().equals("C"))
	   label = "<font color='green'><b>Pagamento Confirmado</b></font>";
   else if(objItem.getOrd_status().equals("H"))
	   label = "<font color='blue'><b>Enviado</b></font>";
   else if(objItem.getOrd_status().equals("X"))
	   label = "<font color='blue'><b>Cancelado</b></font>";
   else if(objItem.getOrd_status().equals("F"))
	   label = "<font color='blue'><b>Finalizado</b></font>";
out.println("<center><span class=listTitle>Dados do Pedido</span>");
out.println("<br><br><table class='listTable'>");
out.println("<tr class=listBody>");
out.println("<td class=loginTd><span class=pageLabel>");
out.println("<b>Data e Hora da Inscri&ccedil;&atilde;o: <b>" + objItem.getOrd_startdate().substring(0,16) + "<br>");
out.println("<b>Desconto: <b>" + (objItem.getOrd_discount()!=null?objItem.getOrd_discount():"-") + "<br>");
out.println("<b>Valor Total: <b>R$ " + (new Util().formatMoney(objItem.getOrd_value())) + "<br>");
out.println("<b>Token MoIP: <b>" + objItem.getOrd_token() + "<br><br>");
out.println("<b>Status do Pedido: <b>" + label + "<br>");
out.println("<b>Código Rastreamento: <b>" + (objItem.getOrd_mailcode()!=null?objItem.getOrd_mailcode():"-") + "<br><br>");
out.println("<b>Nome: <b>" + objItem.getOrd_firstname() + " " + objItem.getOrd_lastname() + "<br>");
out.println("<b>E-mail: <b>" + objItem.getOrd_email() + "<br>");
out.println("<b>Telefones: <b>" + objItem.getOrd_phone() + " / " + objItem.getOrd_celphone() + "<br>");
out.println("<b>E-mail: <b>" + objItem.getOrd_email() + "<br>");
out.println("<b>E-mail alternativo: <b>" + objItem.getOrd_email2() + "<br>");
out.println("<b>Endere&ccedil;o: <b>" + objItem.getOrd_address() + "<br>");
out.println("<b>N&uacute;mero: <b>" + objItem.getOrd_number() + "<br>");
out.println("<b>Cidade: <b>" + objItem.getOrd_city() + "<br>");
out.println("<b>Estado: <b>" + objItem.getOrd_state() + "<br>");
out.println("<b>CEP: <b>" + objItem.getOrd_zipcode() + "<br>");
out.println("<b>Produtos: <b><blockquote>");
out.println("<table celspacing='0' celpadding='0' border='none' class='insideTable'>");
Iterator<OrderItem> iti = objItem.getItemList().iterator();
String ids = "";
while(iti.hasNext())
{
	   OrderItem ord = iti.next();
	   Product prd = new Product().getProduct(ord.getPrd_id());
	   out.println("<tr><td><a href='javascript:;' title='" + prd.getPrd_description() + "'>" + prd.getPrd_title() + "</a>:</td>");
	   out.println("<td>" + ord.getOit_qty() + " X</td><td>" + new Util().formatMoney(ord.getOit_value()) + " =</td>");
	   out.println("<td>" + new Util().formatMoney(ord.getOit_qty() * ord.getOit_value()) + "</td></tr>");
	   ids += (new Util().formatZero(ord.getPrd_id(),4) + "-" + new Util().formatZero(ord.getOit_qty(),2) + " ");
}
out.println("</table>");
out.println("</td>");
out.println("</tr></table><br><br>");
if(request.getParameter("op")!=null && request.getParameter("op").equals("s"))
{
	out.println("<center><span class=listTitle>Informação de Envio</span>");
	out.println("<br><br><table class='listTable'>");
	out.println("<tr class=listBody>");
	out.println("<td class=loginTd><span class=pageLabel><left>");
	out.println("Código para rastreamento:<br>");
	out.println("<input type=text name=codigo id=codigo value=''><br>");
	out.println("<input type=button name=ok value=Enviar onClick='submitCode();'><br>");
	out.println("</left></span></td>");
	out.println("</tr></table><br><br>");
} else {
	out.println("<center><span class=listTitle>Etiqueta</span>");
	out.println("<br><br><table class='listTable'>");
	out.println("<tr class=listBody>");
	out.println("<td class=loginTd><span class=pageLabel><left>");
	out.println(objItem.getOrd_firstname().toUpperCase() + " " + objItem.getOrd_lastname().toUpperCase() + "<br>");
	out.println(objItem.getOrd_address().toUpperCase() + ", " + objItem.getOrd_number().toUpperCase() + " - " + objItem.getOrd_complement().toUpperCase() + "<br>");
	out.println(objItem.getOrd_city().toUpperCase() + " - " + objItem.getOrd_state().toUpperCase() + " - CEP: " + (objItem.getOrd_zipcode()!=null && !objItem.getOrd_zipcode().equals("")?(new Util().formatZipCode(objItem.getOrd_zipcode())):"-") + "<br>");
	out.println("<font size='-4'>[ " + ids + "]</font></left></span></td>");
	out.println("</tr></table><br><br>");
}
out.println("<input type='button' name='btnBack' value='Return to list' onClick='submitQuery();' class='pageButton'></center>");
%>
</body></html>
<script>
function submitQuery()
{
	self.location.href = "order_list.jsp?token=<%=token%>&key=<%=key%>&<%=parameterString%>";
}
function submitCode()
{
	self.location.href = "order_list.jsp?token=<%=token%>&key=<%=key%>&action=enviar&code=" +
	document.getElementById("codigo").value + "&id=<%=id%>&<%=parameterString%>";
}
</script>


