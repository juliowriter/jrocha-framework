<%@ page language="Java"%>
<%@ page import="java.util.*, site.admin.*, site.core.*" session="true"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ include file="res_session.jsp"%>
<html>
<head>
<link rel="stylesheet" href="../css/styles.css">
<title>JRocha Framework</title>
	<script type="text/javascript" src="../scp/jquery-1.8.2.min.js"></script>
</head><body class="listBody">
<%
String parameterString = request.getQueryString();
long id = (request.getParameter("id")!=null?Long.parseLong(request.getParameter("id")):0);
String title = "";
String arquivo = "";
String tag = "";
String carousel_caption = "";
String carousel_description = "";
String lang = "";
if(id > 0) {
   Image tex = new Image().getImage(id);
   title = tex.getImg_title();
   arquivo = tex.getImg_file();
   tag = tex.getImg_tag();
   lang = tex.getImg_language();
   carousel_caption = tex.getImg_carousel_caption();
   carousel_description = tex.getImg_carousel_description();
}
%>
<form name='frm' action='res_image_action<%=(id == 0?"_file":"")%>.jsp' method='post' <%=(id == 0?"enctype='multipart/form-data'":"")%>>
<input type='hidden' name='id' value='<%=id%>'>
<input type='hidden' name='key' value='<%=key%>'>
<input type='hidden' name='token' value='<%=token%>'>
<input type='hidden' name='par' value='<%=parameterString%>'>
<table class='listTable' width="700px"><tr class=listBody><td><span class='pageLabel'>Name:</span><br>
<input name='title' id='title' type='text' class='pageText' size='127' maxlength='255' value='<%=title!=null?title:""%>'>
</td></tr>
<%if(id == 0){%>
<tr class=listBody><td>
<span class='pageLabel'>Arquivo:</span><br>
	<input name='arquivo' type='file' size='50' maxlength='255' class='pageText'>
</td></tr>
<%} else {%>
<tr class=listBody><td>
<span class='pageLabel'>URL:</span><br>
	<%=((new Config().getConfig("IMAGES_URL").getCfg_value()) + arquivo)%>
</td></tr>
<%}%>
<tr class=listBody><td>
<span class='pageLabel'>TAG:</span><br>
<select name="tag" id="tag">
<option value=""> Nenhuma </option>
<%
String[] tags = new Block().getTags();
for(int xx=0;xx<tags.length;xx++)
{
    if(tags[xx].indexOf("-CAROUSEL-") > 0){%>
	      <option <%=(tag.trim().equals(tags[xx].trim())?"SELECTED ":"")%>value="<%=tags[xx].trim()%>"><%=tags[xx].trim()%></option>
<%  }
}%>
</select><br>
</td></tr>
<tr class=listBody><td>
<span class='pageLabel'>Idioma:</span><br>
<select name="language" id="language">
  <option <%=(lang.equals("br")?"SELECTED ":"")%>value="br">Português - Brasil</option>
  <option <%=(lang.equals("en")?"SELECTED ":"")%>value="en">Inglês</option>
  <option <%=(lang.equals("es")?"SELECTED ":"")%>value="es">Espanhol</option>
</select><br>
</td></tr>
<tr class=listBody><td><span class='pageLabel'>Name Carrossel (apenas para carrossel de imagens):</span><br>
<input name='carousel_caption' id='carousel_caption' type='text' class='pageText' size='127' maxlength='255' value='<%=carousel_caption!=null?carousel_caption:""%>'>
</td></tr>
<tr class=listBody><td><span class='pageLabel'>Texto Carrossel (apenas para carrossel de imagens):</span><br>
<textarea name='carousel_description' id='carousel_description' rows='5' cols='100'><%=carousel_description!=null?carousel_description:""%></textarea>
</td></tr>
</table><br>
<table class='listTable' width="700px"><tr class=listBody><td>
<input name='btSubmit' type='button' class='pageButton' value='Save' onClick='checkFields();'>&nbsp;&nbsp;&nbsp;
<input name='btBack' type='button' class='pageButton' value='Cancel and Return' onClick='location.href="res_image_list.jsp?key=<%=key%>&token=<%=token%>";'>
</td></tr></table></form>
</body>
</html>
<script>
function checkFields()
{
	var frm = document.frm;
	var error_message = "Required field!";
	if(!frm.title.value)
	{
		alert(error_message + " Name.");
		frm.title.focus();
   		return false;
	}
	if(frm.carousel_description.value.lenght > 2000)
	{
		alert("O tamanho máximo para o campo descrição é de 2000 caracteres. Seu texto tem " + frm.carousel_description.value.lenght + "!");
		frm.carousel_description.focus();
   		return false;
	}
<%if(id == 0){%>
	if(!frm.arquivo.value)
	{
		alert(error_message + " Arquivo.");
		frm.arquivo.focus();
   		return false;
	}
<%}%>
   	frm.submit();
}
</script>
