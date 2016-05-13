<%@ page language="Java"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ page import="myapp.*"%>
<%
long id = Long.parseLong(request.getParameter("id")!=null && !request.getParameter("id").equals("_id_")?request.getParameter("id"):"0");
MyHelloWorld obj = new MyHelloWorld().getWelcomeText(id);
if(obj!=null)
{	
	out.println(obj.getId() + "|||,|||" + obj.getWelcome_text());
} else {
	out.println(0 + "|||,|||");
}	
%>