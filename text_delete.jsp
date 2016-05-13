<%@ page language="Java"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ page import="myapp.*"%>
<%
long id = Long.parseLong(request.getParameter("id")!=null?request.getParameter("id"):"0");
MyHelloWorld obj = new MyHelloWorld().getWelcomeText(id);
obj.delete();
%>