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
String action = (request.getParameter("action")!=null?request.getParameter("action"):"view");
long id = (request.getParameter("id")!=null?Long.parseLong(request.getParameter("id")):0);
String code = (request.getParameter("code")!=null?request.getParameter("code"):"");
String direction=((request.getParameter("direction")!=null && !request.getParameter("direction").equals("null"))?request.getParameter("direction"):"DESC");
String start = (request.getParameter("nextSet")!=null && !request.getParameter("nextSet").equals("null") && !request.getParameter("nextSet").equals("")?request.getParameter("nextSet"):"0");
String listOrder = (request.getParameter("listOrder")!=null?request.getParameter("listOrder"):"ord_id");
String status = (request.getParameter("status")!=null && !request.getParameter("status").equals("")?request.getParameter("status"):"W");
int day_1 = Integer.parseInt(request.getParameter("day_1")!=null && !request.getParameter("day_1").equals("")?request.getParameter("day_1"):"0");
int day_2 = Integer.parseInt(request.getParameter("day_2")!=null && !request.getParameter("day_2").equals("")?request.getParameter("day_2"):"0");
int month_1 = Integer.parseInt(request.getParameter("month_1")!=null && !request.getParameter("month_1").equals("")?request.getParameter("month_1"):"1");
int month_2 = Integer.parseInt(request.getParameter("month_2")!=null && !request.getParameter("month_2").equals("")?request.getParameter("month_2"):"1");
int year_1 = Integer.parseInt(request.getParameter("year_1")!=null && !request.getParameter("year_1").equals("")?request.getParameter("year_1"):"2009");
int year_2 = Integer.parseInt(request.getParameter("year_2")!=null && !request.getParameter("year_2").equals("")?request.getParameter("year_2"):"2009");
String parameterString = "nextSet=" + start + "&listOrder=" + listOrder + "&direction=" + direction +
						 "&day_1=" + day_1 + "&month_1=" + month_1 + "&year_1=" + year_1 +
						 "&day_2=" + day_2 + "&month_2=" + month_2 + "&year_2=" + year_2 +
						 "&status=" + status;

if(id > 0 && action.equals("reservar"))
	new Order().saveStatus("R",id);
else if(id > 0 && action.equals("confirmar"))
	new Order().saveStatus("C",id);
else if(id > 0 && action.equals("lembrar"))
	new Order().saveStatus("L",id);
else if(id > 0 && action.equals("resgatar"))
	new Order().saveStatus("I",id);
else if(id > 0 && action.equals("enviar"))
	new Order().saveStatus("H",id,code);
else if(id > 0 && action.equals("cancelar"))
	new Order().saveStatus("X",id);
else if(id > 0 && action.equals("zerar"))
	new Order().saveStatus("W",id);
else if(id > 0 && action.equals("finalizar"))
	new Order().saveStatus("F",id);

String date_begin = "";
String date_end = "";
if(day_1 > 0 && day_2 > 0)
{
	date_begin = year_1 + "-" + (month_1 < 10?"0" + month_1:month_1 + "") + "-" + (day_1 < 10?"0" + day_1:day_1 + "") + " 00:00:00";
	date_end = year_2 + "-" + (month_2 < 10?"0" + month_2:month_2 + "") + "-" + (day_2 < 10?"0" + day_2:day_2 + "") + " 23:59:59";
}
Order obj = new Order();
int max = 150;
int count = 0;
String nextStart="";
boolean moreData=false;
int startFrom = Integer.parseInt(start);
int endAt = 0;
int pages = 0;
long total = 0;
out.println("<center><span class=listTitle>Pedidos</span>");
out.println("<br><br>");
out.println("<select name=status id=status onChange='submitQuery()'>");
out.println("<option value='W'" + (status.equals("W")?" SELECTED":"") + ">Novos (em aberto)</option>");
out.println("<option value='R'" + (status.equals("R")?" SELECTED":"") + ">Aguardando pagamento</option>");
out.println("<option value='L'" + (status.equals("L")?" SELECTED":"") + ">Lembrete enviado</option>");
out.println("<option value='H'" + (status.equals("H")?" SELECTED":"") + ">Enviados</option>");
out.println("<option value='C'" + (status.equals("C")?" SELECTED":"") + ">Pagamentos confirmados</option>");
out.println("<option value='X'" + (status.equals("X")?" SELECTED":"") + ">Cancelados</option>");
out.println("<option value='F'" + (status.equals("F")?" SELECTED":"") + ">Finalizados</option>");
out.println("<option value='O'" + (status.equals("O")?" SELECTED":"") + ">Incompleto</option>");
out.println("</select>&nbsp;&nbsp;&nbsp");
out.println("De: <select name=day_1 id=day_1>");
for(int xx=0;xx <= 31;xx++)
	out.println("<option value='" + xx + "'" + (day_1 == xx?" SELECTED":"") + ">" + xx + "</option>");
out.println("</select>/<select name=month_1 id=month_1>");
for(int xx=1;xx <= 12;xx++)
	out.println("<option value='" + xx + "'" + (month_1 == xx?" SELECTED":"") + ">" + xx + "</option>");
out.println("</select>/<select name=year_1 id=year_1>");
for(int xx=2009;xx <= 2020;xx++)
	out.println("<option value='" + xx + "'" + (year_1 == xx?" SELECTED":"") + ">" + xx + "</option>");
out.println("</select>&nbsp;");
out.println("at&eacute;: <select name=day_2 id=day_2>");
for(int xx=0;xx <= 31;xx++)
	out.println("<option value='" + xx + "'" + (day_2 == xx?" SELECTED":"") + ">" + xx + "</option>");
out.println("</select>/<select name=month_2 id=month_2>");
for(int xx=1;xx <= 12;xx++)
	out.println("<option value='" + xx + "'" + (month_2 == xx?" SELECTED":"") + ">" + xx + "</option>");
out.println("</select>/<select name=year_2 id=year_2>");
for(int xx=2009;xx <= 2020;xx++)
	out.println("<option value='" + xx + "'" + (year_2 == xx?" SELECTED":"") + ">" + xx + "</option>");
out.println("</select>&nbsp;&nbsp;&nbsp");
out.println("<input type=button name=ok value='&nbsp;&nbsp;&nbsp;OK&nbsp;&nbsp;&nbsp' onClick='submitQuery()'>&nbsp;&nbsp;&nbsp");
out.println("&nbsp;&nbsp;&nbsp<a href='order_labels.jsp?token=" + token + "&key=" + key + "&status=" + status + "'>[ etiquetas ]</a>");
total = obj.getCount();
if(total > max) pages = (Integer.parseInt(String.valueOf((total/max) + 1)));
Iterator it = obj.getList(startFrom, max + 1, listOrder, direction, status, date_begin, date_end).iterator();
out.println("<br><br><table class='listTable' width='90%'>");
out.print ("<tr class='listSubTitle'>");
out.println("<td><a href='#' onClick=\"document._direction.direction.value=(document._direction.direction.value=='ASC')?'DESC':'ASC';self.location.href='order_list.jsp?nextSet=" + start + "&listOrder=ord_item&direction=' + document._direction.direction.value + '&token=" + token + "&key=" + key + "&status=" + status + "';\">");
out.println("Nome</a> " + ((listOrder.equals("ord_firstname"))?((direction.equals("ASC"))?"<img src='../img/ico_asc.gif' border=0>":"<img src='../img/ico_desc.gif' border=0>"):"") + "</td>");
out.println("<td>E-mail</td>");
out.println("<td>CEP</td>");
out.println("<td><a href='#' onClick=\"document._direction.direction.value=(document._direction.direction.value=='ASC')?'DESC':'ASC';self.location.href='order_list.jsp?nextSet=" + start + "&listOrder=ord_item&direction=' + document._direction.direction.value + '&token=" + token + "&key=" + key + "&status=" + status + "';\">");
out.println("Item/Qtd/Valor/Total</a> " + ((listOrder.equals("ord_item"))?((direction.equals("ASC"))?"<img src='../img/ico_asc.gif' border=0>":"<img src='../img/ico_desc.gif' border=0>"):"") + "</td>");
out.println("<td>Valor Final</td>");
out.println("<td><a href='#' onClick=\"document._direction.direction.value=(document._direction.direction.value=='ASC')?'DESC':'ASC';self.location.href='order_list.jsp?nextSet=" + start + "&listOrder=ord_id&direction=' + document._direction.direction.value + '&token=" + token + "&key=" + key + "&status=" + status + "';\">");
out.println("Data/Hora</a> " + ((listOrder.equals("ord_id"))?((direction.equals("ASC"))?"<img src='../img/ico_asc.gif' border=0>":"<img src='../img/ico_desc.gif' border=0>"):"") + "</td>");
out.println("<td>&Uacute;ltimo</td>");
out.println("<td>Status</td>");
out.println("<td>Actions</td>");
out.println("</tr>");
while (it.hasNext()){
   count++;
   if (count==(max + 1)) {
	 if (it.hasNext()){
		 moreData=true;
	 }
	 if (start.equals("0")) {
		nextStart= String.valueOf(max);
	 }
	 else {
		nextStart=String.valueOf(startFrom + max);
	 }
	 break;
   }
   if (!it.hasNext() && count<max) {
	   endAt = startFrom-max;
	   moreData=false; break;
   }
   Order objItem = (Order)it.next();
   long ord_id = objItem.getOrd_id();
   String label = "<b>Novo<b>";
   if(objItem.getOrd_status().equals("R"))
	   label = "<font color='orange'><b>Aguardando<b></font>";
   else if(objItem.getOrd_status().equals("L"))
	   label = "<font color='purple'><b>Cobrado</b></font>";
   else if(objItem.getOrd_status().equals("C"))
	   label = "<font color='green'><b>Confirmado</b></font>";
   else if(objItem.getOrd_status().equals("H"))
	   label = "<font color='blue'><b>Enviado</b></font>";
   else if(objItem.getOrd_status().equals("X"))
	   label = "<font color='red'><b>Cancelado</b></font>";
   else if(objItem.getOrd_status().equals("F"))
	   label = "<font color='blue'><b>Finalizado</b></font>";
   else if(objItem.getOrd_status().equals("O"))
	   label = "<font color='blue'><b>Incompleto</b></font>";

   out.println("<tr class='listBody'>");
   out.println("<td>");
   out.println("<a href='order_data.jsp?token=" + token + "&key=" + key + "&id=" + ord_id + "&" + parameterString + "' title='Ver dados completos'>" + (objItem.getOrd_firstname()!=null?objItem.getOrd_firstname():"") + " " + (objItem.getOrd_lastname()!=null?objItem.getOrd_lastname():"") + "</a>");
   out.println("</td>");
   out.println("<td>");
   out.println(objItem.getOrd_email()!=null?objItem.getOrd_email():"-");
   out.println("</td>");
   out.println("<td>");
   //out.println(objItem.getOrd_phone() + " / " + objItem.getOrd_celphone());
   out.println(objItem.getOrd_zipcode()!=null && !objItem.getOrd_zipcode().equals("")?(new Util().formatZipCode(objItem.getOrd_zipcode())):"-");
   out.println("</td>");
   out.println("<td>");
   try{
	   Iterator<OrderItem> iti = objItem.getItemList().iterator();
	   while(iti.hasNext())
	   {
		   OrderItem ord = iti.next();
		   Product prd = new Product().getProduct(ord.getPrd_id());
		   out.println("<a href='javascript:;' title='" + prd.getPrd_description() + "'>" + prd.getPrd_title() + "</a>:<br>");
		   out.println(ord.getOit_qty() + " X " + new Util().formatMoney(ord.getOit_value()) + " = ");
		   out.println(new Util().formatMoney(ord.getOit_qty() * ord.getOit_value()) + "<br><br>");
	   }
   } catch(Exception e){}
   out.println("</td>");
   out.println("<td align='right'>");
   out.println(new Util().formatMoney(objItem.getOrd_value()));
   out.println("</td>");
   out.println("<td>");
   out.println(objItem.getOrd_startdate().substring(0,16));
   out.println("</td>");
   out.println("<td>");
   out.println(objItem.getOrd_lastdate()!=null?objItem.getOrd_lastdate().substring(0,16):"-");
   out.println("</td>");
   out.println("<td>");
   out.println(label);
   out.println("</td>");
   out.println("<td align='left' NOWRAP>");
   if(objItem.getOrd_status().equals("W")) {
   		out.println("<a href='order_list.jsp?token=" + token + "&key=" + key + "&action=reservar&id=" + ord_id + "&" + parameterString + "' title='Aguardar Pagamento'><img src='../img/ico_historico.gif' border=0 title='Aguardar Pagamento' alt='Aguardar Pagamento'></a>&nbsp;");
		//out.println("<a href='order_list.jsp?token=" + token + "&key=" + key + "&action=enviar&id=" + ord_id + "&" + parameterString + "' title='Enviar Produto'><img src='../img/ico_historico.gif' border=0 title='Enviar Produto' alt='Enviar Produto'></a>&nbsp;");
		out.println("<a href='order_list.jsp?token=" + token + "&key=" + key + "&action=confirmar&id=" + ord_id + "&" + parameterString + "' title='Confirmar Pagamento'><img src='../img/ico_checked.gif' border=0 title='Confirmar Pagamento' alt='Confirmar Pagamento'></a>&nbsp;");
		out.println("<a href='javascript:excluir(" + ord_id + ",\"" + parameterString + "\");' title='Cancelar'><img src='../img/ico_excluir.gif' border=0 title='Cancelar' alt='Cancelar'></a>&nbsp;");
   } else if(objItem.getOrd_status().equals("R")) {
		out.println("<a href='order_list.jsp?token=" + token + "&key=" + key + "&action=confirmar&id=" + ord_id + "&" + parameterString + "' title='Confirmar Pagamento'><img src='../img/ico_checked.gif' border=0 title='Confirmar Pagamento' alt='Confirmar Pagamento'></a>&nbsp;");
		out.println("<a href='order_list.jsp?token=" + token + "&key=" + key + "&action=lembrar&id=" + ord_id + "&" + parameterString + "' title='Lembrar Pagamento'><img src='../img/ico_board.gif' border=0 title='Lembrar Pagamento' alt='Lembrar Pagamento'></a>&nbsp;");
		out.println("<a href='javascript:excluir(" + ord_id + ",\"" + parameterString + "\");' title='Cancelar'><img src='../img/ico_excluir.gif' border=0 title='Cancelar' alt='Cancelar'></a>&nbsp;");
		out.println("<a href='order_list.jsp?token=" + token + "&key=" + key + "&action=zerar&id=" + ord_id + "&" + parameterString + "' title='Marcar como NOVO'><img src='../img/ico_novo.gif' border=0 title='Marcar como NOVO' alt='Marcar como NOVO'></a>&nbsp;");
  } else if(objItem.getOrd_status().equals("L")) {
		out.println("<a href='order_list.jsp?token=" + token + "&key=" + key + "&action=confirmar&id=" + ord_id + "&" + parameterString + "' title='Confirmar Pagamento'><img src='../img/ico_checked.gif' border=0 title='Confirmar Pagamento' alt='Confirmar Pagamento'></a>&nbsp;");
		out.println("<a href='javascript:excluir(" + ord_id + ",\"" + parameterString + "\");' title='Cancelar'><img src='../img/ico_excluir.gif' border=0 title='Cancelar' alt='Cancelar'></a>&nbsp;");
		out.println("<a href='order_list.jsp?token=" + token + "&key=" + key + "&action=zerar&id=" + ord_id + "&" + parameterString + "' title='Marcar como NOVO'><img src='../img/ico_novo.gif' border=0 title='Marcar como NOVO' alt='Marcar como NOVO'></a>&nbsp;");
   } else if(objItem.getOrd_status().equals("C")) {
		out.println("<a href='order_data.jsp?token=" + token + "&key=" + key + "&id=" + ord_id + "&op=s&" + parameterString + "' title='Informar Envio'><img src='../img/ico_email.gif' border=0 title='Informar envio' alt='Informar envio'></a>&nbsp;");
		out.println("<a href='javascript:excluir(" + ord_id + ",\"" + parameterString + "\");' title='Cancelar'><img src='../img/ico_excluir.gif' border=0 title='Cancelar' alt='Cancelar'></a>&nbsp;");
		out.println("<a href='order_list.jsp?token=" + token + "&key=" + key + "&action=zerar&id=" + ord_id + "&" + parameterString + "' title='Marcar como NOVO'><img src='../img/ico_novo.gif' border=0 title='Marcar como NOVO' alt='Marcar como NOVO'></a>&nbsp;");
   } else if(objItem.getOrd_status().equals("H")) {
		out.println("<a href='order_list.jsp?token=" + token + "&key=" + key + "&action=finalizar&id=" + ord_id + "&" + parameterString + "' title='Finalizar'><img src='../img/ico_mover.gif' border=0 title='Finalizar' alt='Finalizar'></a>&nbsp;");
		out.println("<a href='javascript:excluir(" + ord_id + ",\"" + parameterString + "\");' title='Cancelar'><img src='../img/ico_excluir.gif' border=0 title='Cancelar' alt='Cancelar'></a>&nbsp;");
		out.println("<a href='order_list.jsp?token=" + token + "&key=" + key + "&action=zerar&id=" + ord_id + "&" + parameterString + "' title='Marcar como NOVO'><img src='../img/ico_novo.gif' border=0 title='Marcar como NOVO' alt='Marcar como NOVO'></a>&nbsp;");
   } else if(objItem.getOrd_status().equals("O")) {
		out.println("<a href='order_list.jsp?token=" + token + "&key=" + key + "&action=resgatar&id=" + ord_id + "&" + parameterString + "' title='Resgatar Desistência'><img src='../img/ico_rescue.gif' border=0 title='Resgatar Desistência' alt='Resgatar Desistência'></a>&nbsp;");
		out.println("<a href='javascript:excluir(" + ord_id + ",\"" + parameterString + "\");' title='Cancelar'><img src='../img/ico_excluir.gif' border=0 title='Cancelar' alt='Cancelar'></a>&nbsp;");
   } else {
		out.println("<a href='order_list.jsp?token=" + token + "&key=" + key + "&action=zerar&id=" + ord_id + "&" + parameterString + "' title='Marcar como NOVO'><img src='../img/ico_novo.gif' border=0 title='Marcar como NOVO' alt='Marcar como NOVO'></a>&nbsp;");
   }
   out.println("</td></tr>");
}
out.println("<tr><td colspan='9'><strong>Nesta p&aacute;gina: " + count + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
out.println("Total: " + obj.getUniqueOrders() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
if(!status.equals("A"))
	out.println("Este status: " + obj.getUniqueOrders(status) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onClick='self.print()'><img src='../img/ico_print.gif' alt='Imprimir esta p&aacute;gina' border='0' align='absmiddle'></a>&nbsp;<a href='#' onClick='self.print()'>Imprimir esta p&aacute;gina</a></strong></td>");
out.println("</tr></table>");
String lastPosition="0";
if (nextStart!="") {
   if (Integer.parseInt(nextStart)-(max + 1)>=0){
      lastPosition = String.valueOf(Integer.parseInt(nextStart)-(max + 1));
   }
}
if (endAt>0 && !moreData) {
   lastPosition = String.valueOf(endAt);
} else if (endAt==0 && !moreData) {
   lastPosition =String.valueOf(startFrom-max);
}
out.println("<table border=0 cellpadding=0 cellspacing=0 width='90%' class='listFooter'><tr><td width='30%'>");
if ( Integer.parseInt(lastPosition)>=0 && startFrom>=0 && !start.equals("0") )
{
   out.println("<a href='#' onClick=\"self.location.href=url + 'order_list.jsp?nextSet=" + lastPosition + "&listOrder=" + listOrder + "&direction=" + direction + "'\">Anterior</a>");
} else
{
  out.println("&nbsp;");
}
out.println("</td><td width='40%'>&nbsp;");
if(pages>1)
{
	out.println("<div align=center>Pages: ");
	for(int xx=0;xx<pages;xx++)
	{
		out.println("<a href='#' onClick=\"self.location.href=url + 'order_list.jsp?nextSet=" + (xx*max) + "&listOrder=" + listOrder + "&direction=" + direction + "'\">&nbsp;&nbsp;" + (xx + 1) + "&nbsp;&nbsp;</a>");
	}
	out.println("</div>");
}
out.println("</td><td width='30%'>");
if (moreData)
{
   out.println("<div align=right><a href='#' onClick=\"self.location.href=url + 'order_list.jsp?nextSet=" + nextStart + "&listOrder=" + listOrder + "&direction=" + direction + "'\">Próxima</a></div>");
} else
{
  out.println("&nbsp;");
}
out.println("</td></tr></table></center>");
out.println("<form name=_direction><input type='hidden' name='direction' value='" + direction + "'></form>");
%>
</body></html>
<script>
function submitQuery()
{
	self.location.href = "order_list.jsp?token=<%=token%>&key=<%=key%>" +
	"&status=" + document.getElementById("status").options[document.getElementById("status").options.selectedIndex].value +
	"&day_1=" + document.getElementById("day_1").options[document.getElementById("day_1").options.selectedIndex].value +
	"&month_1=" + document.getElementById("month_1").options[document.getElementById("month_1").options.selectedIndex].value +
	"&year_1=" + document.getElementById("year_1").options[document.getElementById("year_1").options.selectedIndex].value +
	"&day_2=" + document.getElementById("day_2").options[document.getElementById("day_2").options.selectedIndex].value +
	"&month_2=" + document.getElementById("month_2").options[document.getElementById("month_2").options.selectedIndex].value +
	"&year_2=" + document.getElementById("year_2").options[document.getElementById("year_2").options.selectedIndex].value;
}

function excluir(ordid, pars)
{
	if(confirm("Remover este item?"))
	{
	  window.location.href="order_list.jsp?token=<%=token%>&key=<%=key%>&action=cancelar&id=" + ordid + "&" + pars;
	}
}
</script>


