<%@ page language="Java"%>
<%@ page import="site.core.*, java.util.*, site.account.Account" %>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%
	String lng = (request.getParameter("lng")!=null && !request.getParameter("lng").equals("")?request.getParameter("lng"):(request.getServerName().indexOf("com.br") > 0?"br":"en"));
	String muda = (request.getParameter("mda")!=null && !request.getParameter("mda").equals("")?request.getParameter("mda"):"false");
	String pag = (request.getParameter("pag")!=null && !request.getParameter("pag").equals("")?request.getParameter("pag"):"");
	String edt = (request.getParameter("edt")!=null && !request.getParameter("edt").equals("")?request.getParameter("edt"):"");
	String par = (request.getParameter("par")!=null && !request.getParameter("par").equals("")?request.getParameter("par"):"");
	long art = Long.parseLong(request.getParameter("art")!=null && !request.getParameter("art").equals("")?request.getParameter("art"):"0");
	if(pag.equals(""))
	{	
		pag = (session.getAttribute("profile") == null?"HOME":"HOME-SESSION");
	} 	
	Cookie cookies [] = request.getCookies();
	Cookie lnCookie = null;
	Cookie lpCookie = null;
	Cookie lrCookie = null;
	if (cookies != null)
	{
		for (int i = 0; i < cookies.length; i++)
		{
			if (cookies [i].getName().equals("jrocha_language"))
				lnCookie = cookies[i];
			else if (cookies [i].getName().equals("jrocha_lastpage"))
				lpCookie = cookies[i];
			else if (cookies [i].getName().equals("jrocha_lastpar"))
				lrCookie = cookies[i];
		}
	}
	if(muda.equals("false") && lnCookie != null)
		lng = lnCookie.getValue();
	Cookie cook = new Cookie("jrocha_language", lng);
	cook.setMaxAge(365 * 24 * 60 * 60);
	response.addCookie(cook);

	if(pag.equals("_LAST_") && lpCookie != null)
	{
		pag = lpCookie.getValue();
		if(lrCookie != null)
			par = lrCookie.getValue();
	}

	cook = new Cookie("jrocha_lastpage", pag);
	cook.setMaxAge(24 * 60 * 60);
	response.addCookie(cook);

	cook = new Cookie("jrocha_lastpar", par);
	cook.setMaxAge(24 * 60 * 60);
	response.addCookie(cook);

	Content ctd = new Content().getContent(pag);
	if(ctd == null)
		ctd = new Content().getContent("HOME");

	boolean withSession = (ctd.getCts_session() == 1);
	if(withSession && (session == null || session.getAttribute("profile") == null))
	{
		session.setAttribute("last_page", pag);
		pag = "SESSION-END";
		ctd = new Content().getContent(pag);
		withSession = false;
	} 
	long userID = 0;
	if(withSession && session.getAttribute("profile") != null)
	{
		Account acc = (Account)session.getAttribute("profile");
		userID = acc.getPrf_id();
	}
	//insert a jsp file before the HTML
	String top = (request.getParameter("top")!=null && !request.getParameter("top").equals("")?request.getParameter("top"):"none");
	//insert a jsp file before the content
	String bef = (request.getParameter("bef")!=null && !request.getParameter("bef").equals("")?request.getParameter("bef"):"none");
	//insert a jsp file after the content
	String pos = (request.getParameter("pos")!=null && !request.getParameter("pos").equals("")?request.getParameter("pos"):"none");
	String[] pars = null;
	if(par.length() > 0)
		pars = par.split(";");

	//Includes the global CSS block
	Block frm = new Block().getBlock("_BLOCK-CSS-ALL_");
    String css_block = (frm != null && frm.getBlk_text() != null?frm.getBlk_text():"").trim();
    
    //Includes the Page CSS Block
    frm = new Block().getBlock("_BLOCK-CSS-" + pag.toUpperCase() + "_");
    css_block += (frm != null && frm.getBlk_text() != null?frm.getBlk_text():"").trim();

    //Includes the Menu Block
   	frm = new Block().getBlock("_BLOCK-MENU-" + pag.toUpperCase() + "_");
	if(frm == null)
	{
		if(withSession)
			frm = new Block().getBlock("_BLOCK-MENU-SESSION_");
		if(frm == null)
			frm = new Block().getBlock("_BLOCK-MENU_");
	}
	String menu_block = (frm != null && frm.getBlk_text() != null?frm.getBlk_text():"").trim();
	if(!menu_block.equals(""))
	{
		Iterator<HeaderOptions> it = new HeaderOptions().getList(withSession, lng).iterator();
		String menu_options = "";
		while(it.hasNext()){
			HeaderOptions ho = it.next();
			boolean active = pag.equals(ho.getOpt_link());
			if(!active && ho.getOpt_link().indexOf("edt") > 0)
			{
				active = ho.getOpt_link().substring(ho.getOpt_link().indexOf("edt") + 4, ho.getOpt_link().length()).equals(edt);
			}
			if(!active && art > 0)
			{
                Article act = new Article().getArticle(art);
                Editoria eda = new Editoria().getEditoria(act.getEdt_id());
				active = ho.getOpt_link().substring(ho.getOpt_link().indexOf("edt") + 4, ho.getOpt_link().length()).equals(eda.getEdt_tag());
			}
			if(ho.getOpt_link().equals("HOME"))
				menu_options += "<li" +  (active?" class='active'":"") + "><a href='/'>" + ho.getOpt_title() + "</a></li>\n";
			else
				menu_options += "<li" +  (active?" class='active'":"") + "><a href='index?pag=" + ho.getOpt_link() + "'>" + ho.getOpt_title() + "</a></li>\n";
		}
		menu_block = menu_block.replace("_MENU-OPTIONS_", menu_options).trim();
	}
	if(withSession)
	{	
		Block accmenu = new Block().getBlock("_BLOCK-ACCOUNT-MENU_");
		if(accmenu!=null)
		   menu_block = menu_block.replace("_MENU-ACCOUNT_", accmenu.getBlk_text()).trim();
	} else {
			menu_block = menu_block.replace("_MENU-ACCOUNT_", "").trim();
	}
	boolean menu_first = (new Config().getConfig("MENU_POSITION").getCfg_value()).equals("TOP");

	//Includes the footer block
	frm = new Block().getBlock("_BLOCK-FOOTER-" + pag.toUpperCase() + "_");
	if(frm == null)
	{
		if(withSession)
			frm = new Block().getBlock("_BLOCK-FOOTER-SESSION_");
		if(frm == null)
			frm = new Block().getBlock("_BLOCK-FOOTER_");
	}
	String footer_block = (frm != null && frm.getBlk_text() != null?frm.getBlk_text():"").trim();

	//Includes the Wrapper Block (Open)
	frm = new Block().getBlock("_BLOCK-PAGE-WRAPPER-START-" + pag.toUpperCase() + "_");
	if(frm == null)
	{
		if(withSession)
			frm = new Block().getBlock("_BLOCK-PAGE-WRAPPER-START-SESSION_");
		if(frm == null)
			frm = new Block().getBlock("_BLOCK-PAGE-WRAPPER-START_");
	}
	String wrapper_start = (frm != null && frm.getBlk_text() != null?frm.getBlk_text():"").trim();

	//Includes the Wrapper Block (Close)
	frm = new Block().getBlock("_BLOCK-PAGE-WRAPPER-END-" + pag.toUpperCase() + "_");
	if(frm == null)
	{
		if(withSession)
			frm = new Block().getBlock("_BLOCK-PAGE-WRAPPER-END-SESSION_");
		if(frm == null)
			frm = new Block().getBlock("_BLOCK-PAGE-WRAPPER-END_");
	}
	String wrapper_end = (frm != null && frm.getBlk_text() != null?frm.getBlk_text():"").trim();

	//Includes the global javascript block
	frm = new Block().getBlock("_BLOCK-JS-ALL_");
	String js_block = (frm != null && frm.getBlk_text() != null?frm.getBlk_text():"").trim();

	//Includes the page javascript block
	frm = new Block().getBlock("_BLOCK-JS-" + pag.toUpperCase() + "_");
    js_block += (frm != null && frm.getBlk_text() != null?frm.getBlk_text():"").trim();

    frm = new Block();

    String contentfinal = ctd.getCts_text(pars, edt, art, lng);
%>
<%if(!top.equals("none")){
	String pagTop = "top_" + pag.toLowerCase() + ".jsp";
	%>
	<jsp:include flush="true" page="<%=pagTop%>" />
<%}%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="Description" content="<%=(new Config().getConfig("SITE_DESCRIPTION").getCfg_value())%>"/>
<meta name="Keywords" content="<%=(new Config().getConfig("SITE_KEYWORDS").getCfg_value())%>"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><%=(new Config().getConfig("APP_NAME").getCfg_value())%> - <%=ctd.getCts_title()%></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link rel="icon" href="img/favicon.png" type="image/png">
<link rel="shortcut icon" href="img/favicon.png" type="image/png">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
   <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<%=frm.parseBlock(css_block,lng)%>
<%=frm.parseBlock(js_block,lng, userID)%>
</head>
<body>
	<div class="container-fluid">
	  <%=frm.parseBlock(wrapper_start,lng)%>
	  <%=(menu_first?frm.parseBlock(menu_block,lng):"")%>
	  <%=(contentfinal != null?contentfinal:frm.parseBlock("<div style='margin-top: 200px; margin-left: 300px; height: 400px;'>_TB_SORRY. PAGE NOT FOUND!_TE_</div>",lng))%>
	  <%=(!menu_first?frm.parseBlock(menu_block,lng):"")%>
	  <%=frm.parseBlock(footer_block,lng)%>
	  <%=frm.parseBlock(wrapper_end,lng)%>
	</div> <!-- container-fluid -->
</body>
</html>
