<%@ page session="true" %>
<HTML>
<HEAD>
	<title>JRocha Framework</title>
	<link rel="stylesheet" href="../css/styles.css">
    <link REL="SHORTCUT ICON" href="../img/favicon.ico">
</HEAD>
<table cellspacing="0" cellpadding="0" style="height:100%; width:100%; border-style:none; border-collapse: collapse; border-width: 0px;">
<tr style="height:75px;"><td colspan=2 border=0 style="width:100%; border-style:none; border-width: 0px;">
<IFRAME NAME="topo" FRAMEBORDER="no" SCROLLING=no SRC="res_top.jsp?token=<%=request.getParameter("token")%>&key=<%=request.getParameter("key")%>" style="height:75px; width:100%; border-style:none; border-width: 0px;"></IFRAME>
</td></tr>
<tr><td border=0 id="menuTD" style="width:120px; border-style:none; border-width: 0px;">
<IFRAME NAME="menu" id="menu" FRAMEBORDER="no" SCROLLING=auto SRC="res_menu.jsp?token=<%=request.getParameter("token")%>&key=<%=request.getParameter("key")%>" style="width:120px; height:100%; border-style:none; border-width: 0px;"></IFRAME>
</td><td border=0 style="width:700px; border-style:none; border-width: 0px;">
<IFRAME NAME="principal" FRAMEBORDER="no" SCROLLING=auto SRC="res_texto_list.jsp?token=<%=request.getParameter("token")%>&key=<%=request.getParameter("key")%>" style="width:100%; height:100%; border-style:none; border-width: 0px;"></IFRAME>
</td></tr></table>
</HTML>


