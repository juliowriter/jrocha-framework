<%
String token = request.getParameter("token");
String key = request.getParameter("key");
String redir = null;
if(session==null || token==null || key==null || session.getValue("IDSESSAO")==null || !session.getValue("IDSESSAO").equals(token)){
	redir = "<html><head><title>JRocha Framework</title>" +
	"</head><body><br><span style='font-size: 13px;font-weight:\"bold\";font-family: \"Tahoma\";'>Your session expired</span>" +
	"<p><img src='../img/alert.gif' width='10' height='11' align='absmiddle'>&nbsp;" +
	"<span style='font-size: 11px;font-weight:\"bold\";font-family: \"Tahoma\";'><b>For security reasons, your session ended after some inactivity." +
	"</b></span></p><p><span style='font-size: 11px;font-weight:\"bold\";font-family: \"Tahoma\";'>" +
	"<a href='javascript:login();'><b>Please, login again to keep working</b></a></span></p></body></html>" +
	"<script>function login(){ top.location.href='res_login.jsp?key=" + key + "'; }</script>";
	out.println(redir);
	out.flush();
	return;
}
User usuario = null;
String profile = "";
try{
	usuario = (User)session.getAttribute("USUARIO");
	profile = usuario.getPrf_id();
}
catch (Exception eUsu) {
	redir = "<html><head><title>Thate News</title>" +
	"</head><body><br><span style='font-size: 13px;font-weight:\"bold\";font-family: \"Tahoma\";'>Your session expired</span>" +
	"<p><img src='../img/alert.gif' width='10' height='11' align='absmiddle'>&nbsp;" +
	"<span style='font-size: 11px;font-weight:\"bold\";font-family: \"Tahoma\";'><b>For security reasons, your session ended after some inactivity." +
	"</b></span></p><p><span style='font-size: 11px;font-weight:\"bold\";font-family: \"Tahoma\";'>" +
	"<a href='javascript:login();'><b>Please, login again to keep working</b></a></span></p></body></html>" +
	"<script>function login(){ top.location.href='res_login.jsp?key=" + key + "'; }</script>";
	out.println(redir);
	out.flush();
	return;
}
%>
