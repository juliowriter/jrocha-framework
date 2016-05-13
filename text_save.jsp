<%@ page language="Java"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ page import="myapp.*" session="true"%>
<%
String urlRedirect = "index?pag=ERROR";
try {
	long id = Long.parseLong(request.getParameter("id")!=null && !request.getParameter("id").equals("_id_")?request.getParameter("id"):"0");
	String welcome_text = (request.getParameter("welcome_text")!=null?request.getParameter("welcome_text"):"");
	MyHelloWorld obj = new MyHelloWorld().getWelcomeText(id);
    if(obj == null)
       obj = new MyHelloWorld();

    obj.setWelcome_text(welcome_text);
	if(id > 0)
	   obj.save();
	else
	   obj.insert();
	urlRedirect="index?pag=MYTEXTLIST";
} catch (Exception e)
{
	e.printStackTrace();
}
response.sendRedirect(urlRedirect);
%>
