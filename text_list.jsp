<%@ page language="Java"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ page import="myapp.*, site.core.*"%>
<%
out.println(new Block().parseBlock(new MyHelloWorld().getMyTextList(),"en"));
%>