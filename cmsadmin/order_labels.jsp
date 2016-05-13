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
Order obj = new Order();
long[][] quantidades = new long[100][2];
int contador = 0;
Iterator it = obj.getLabels(status).iterator();
out.println("<table width='780' class='listLabels'>");
int face = 0;
int count = 0;
while (it.hasNext()){
   Order objItem = (Order)it.next();
   Iterator<OrderItem> iti = objItem.getItemList().iterator();
   String ids = "";
   while(iti.hasNext())
   {
   	   OrderItem ord = iti.next();
   	   Product prd = new Product().getProduct(ord.getPrd_id());
   	   boolean existe = false;
   	   for(int idx=0; idx <= contador; idx++)
   	   {
   		   if(quantidades[idx][0] == ord.getPrd_id())
   		   {
   			   quantidades[idx][1] += ord.getOit_qty();
   			   existe = true;
   			   break;
   		   }
   	   }
   	   if(!existe)
   	   {
	   	   quantidades[contador][0] = ord.getPrd_id();
		   quantidades[contador++][1] = ord.getOit_qty();
   	   }
	   ids += (prd.getPrd_code() + "-" + new Util().formatZero(ord.getOit_qty(),2) + " ");
   }
   if(face == 0)
   	out.println("<tr><td>");
   else
   	out.println("</td><td>");
   	out.println("<font color='black'><b>");
   out.println(objItem.getOrd_firstname().toUpperCase() + " " + objItem.getOrd_lastname().toUpperCase() + "<br>");
   out.println(objItem.getOrd_address().toUpperCase() + ", " + objItem.getOrd_number().toUpperCase() + (objItem.getOrd_complement()!=null && !objItem.getOrd_complement().equals("")?"<br>" + objItem.getOrd_complement().toUpperCase():"") + "<br>");
   out.println(objItem.getOrd_city().toUpperCase() + " - " + objItem.getOrd_state().toUpperCase() + "<br>CEP: " + (new Util().formatZipCode(objItem.getOrd_zipcode())) + "<br>");
   out.println("<font size='-4'>[ " + ids + "]</font>");
   out.println("</b></font><br><br>");
   if(face == 0)
   	out.println("</td>");
   else
   	out.println("</td></tr>");
   face = (face == 0?1:0);
   count++;
}
out.println("</table><br><br><table border=0 cellpadding=0 cellspacing=0 width='600' class='listFooter'>");
out.println("<tr><td colspan='9'><strong>Total de etiquetas: " + count + "<br>");
for(int xx=0; xx < contador; xx++)
{
	Product prd = new Product().getProduct(quantidades[xx][0]);
	out.println(new Util().formatZero(quantidades[xx][1],2) + " - [" + prd.getPrd_code() + "] " + prd.getPrd_title() + "( " + prd.getPrd_description() + ")<br>");
}
out.println("<a href='#' onClick='self.print()'><img src='../img/ico_print.gif' alt='Imprimir esta p&aacute;gina' border='0' align='absmiddle'></a>&nbsp;<a href='#' onClick='self.print()'>Imprimir esta p&aacute;gina</a></strong></td>");
out.println("</tr></table>");
%>
</center></body></html>

