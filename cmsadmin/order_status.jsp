<%@ page language="Java"%>
<%@ page import="site.subscription.*" session="true"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<html>
<link rel="stylesheet" href="../css/styles.css">
<head>
<title>MySelf English</title>
</head>
<body class="listBody">
<%
String token = request.getParameter("token");
if(token == null || !token.equals("Z2JLMX89IEPSHD78A3DQ"))
{
	System.out.println("token invalido. redirecionou para myselfenglish.com.br");
	response.sendRedirect("http://www.myselfenglish.com.br");
}
String tke = (request.getParameter("id_transacao")!=null?request.getParameter("id_transacao"):"");
String status =((request.getParameter("status_pagamento")!=null && !request.getParameter("status_pagamento").equals("null"))?request.getParameter("status_pagamento"):"X");
System.out.println("id_transacao: " + tke);
System.out.println("status_pagamento: " + status);
if(!tke.equals("") && status.trim().equals("1"))
{
	boolean order = (new SubscriptionOrder().saveStatusMoIP(tke));
	if(!order)
	{
		System.out.println("nao encontrou pedidos com este id");
	} else {
		System.out.println("encontrou pedido e atualizou o status");
	}
} else {
	System.out.println("nao entrou no if da atualizacao");
}
out.println("200");
%>
</body></html>