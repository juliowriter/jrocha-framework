<%@ page language="Java"%>
<%@ page import="java.util.*, site.admin.*, site.core.*, com.jspsmart.upload.*" session="true"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<jsp:useBean id="myUpload" scope="page" class="com.jspsmart.upload.SmartUpload" />
<html>
<link rel="stylesheet" href="../css/styles.css">
<head>
<%
out.println("<title>Save imagem</title>");
%>
</head><body class="listBody">
<table class='listTable' width="700px"><tr class=listBody><td>
<span class='pageLabel'><b>
<%
long id = 0;
boolean ok = true;
String errcode = "000";
String key = "";
String token = "";
try {
	myUpload.initialize(pageContext);
	myUpload.setAllowedFilesList("jpg,JPG,gif,GIF,png,PNG");
	myUpload.setMaxFileSize(5242880);
	myUpload.upload();
} catch(Exception e) {
	e.printStackTrace();
	ok = false;
	errcode = "001";
}
if(ok)
{
	String title = (myUpload.getRequest().getParameter("title")!=null?myUpload.getRequest().getParameter("title"):"-");
	String tag = (myUpload.getRequest().getParameter("tag")!=null?myUpload.getRequest().getParameter("tag"):"-");
	String lang = (myUpload.getRequest().getParameter("language")!=null?myUpload.getRequest().getParameter("language"):"br");
	String carousel_caption = (myUpload.getRequest().getParameter("carousel_caption")!=null?myUpload.getRequest().getParameter("carousel_caption"):"-");
	String carousel_description = (myUpload.getRequest().getParameter("carousel_description")!=null?myUpload.getRequest().getParameter("carousel_description"):"-");
	key = (myUpload.getRequest().getParameter("key")!=null?myUpload.getRequest().getParameter("key"):"-");
	token = (myUpload.getRequest().getParameter("token")!=null?myUpload.getRequest().getParameter("token"):"-");
	try {
		//Verifica se existe a pasta, se não existe, cria.
		String caminho = (new Config().getConfig("SITE_PATH").getCfg_value()) + (new Config().getConfig("IMAGES_PATH").getCfg_value());
		try{
			java.io.File myDirectory = new java.io.File(caminho);
			if( !myDirectory.isDirectory() ){
				myDirectory.mkdir();
			}
		} catch(Exception e) {e.printStackTrace();}

		Image img = new Image();
		img.setImg_title(title);
		img.setImg_tag(tag);
		img.setImg_language(lang);
		img.setImg_carousel_caption(carousel_caption);
		img.setImg_carousel_description(carousel_description);
		//Identifica o arquivo e faz o upload
		if(myUpload.getFiles().getCount() > 0)
		{
			com.jspsmart.upload.File myFile = myUpload.getFiles().getFile(0);
			String nomeArquivo = myFile.getFileName();
			img.setImg_file(nomeArquivo);
			if(!myUpload.getFiles().getFile(0).isMissing()) {
				myFile.saveAs(caminho+""+nomeArquivo);
				img.setImg_size(myFile.getSize());
			}
		}
		id = img.insert();
	} catch(IllegalArgumentException iae) {
		iae.printStackTrace();
		ok = false;
		errcode = "002";
	} catch(Exception e) {
		e.printStackTrace();
		ok = false;
		errcode = "003";
	}
}
if(ok)
{
   out.println("Imagem enviada com sucesso!");
} else {
   out.println("Erro ao enviar imagem! - Código do erro: " + errcode);
}
%>
</b></span>
<br><br>
<input name='btNew' type='button' class='pageButton' value='Novo item' onClick='location.href="res_image.jsp?id=0&key=<%=key%>&token=<%=token%>";'>&nbsp;&nbsp;&nbsp;
<input name='btSubmit' type='button' class='pageButton' value='Back to the item' onClick='location.href="res_image.jsp?id=<%=id%>&key=<%=key%>&token=<%=token%>";'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Back to items list' onClick='location.href="res_image_list.jsp?key=<%=key%>&token=<%=token%>";'>
</td></tr></table>
</body>
</html>


