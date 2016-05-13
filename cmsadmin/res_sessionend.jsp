<%@ page language="java" isErrorPage="true" %>
<%@ page import='java.util.*' session='true'%>
<% String key = request.getParameter("key"); %>
<html><head><title>reservaflex</title>
</head><body><br>
<span style='font-size: 13px;font-weight:bold;font-family:Tahoma;'>Your session expired</span>
<p><img src='../img/ico_nav.gif' width='16' height='16' align='absmiddle'>&nbsp;
<span style='font-size: 11px;font-weight:bold;font-family:Tahoma;'>
<b>For security reasons, your session ended after some inactivity.</b></span></p><p>
<span style='font-size: 11px;font-weight:bold;font-family:Tahoma;'>
<a href='javascript:login();'><b>Please, login again to keep working</b></a></span></p></body></html>
<script>function login(){ top.location.href='res_login.jsp?key=<%=key%>'; }</script>
