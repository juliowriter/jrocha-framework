package site.core;

import java.util.Iterator;
import java.util.List;

import site.core.dao.ContentDAO;

public class Content {

	private long cts_id;
	private String cts_tag;
	private String cts_title;
	private String cts_text;
	private int cts_order;
	private int cts_published;
	private int cts_comments;
	private long cts_parent;
	private int cts_haschat;
	private int cts_session;

	public Content getContent(long id)
	{
		return (new ContentDAO().getContent(id));
	}

	public Content getContent(String tag)
	{
		return (new ContentDAO().getContent(tag));
	}

	public String[] getTags()
	{
		return (new ContentDAO().getTags());
	}

	public void restorePrevious(long hid)
	{
		new ContentDAO().restorePrevious(this, hid);
	}

	public String[] getVersions()
	{
		return (new ContentDAO().getVersions(this));
	}

	public List<Content> getContents(String tag)
	{
		return (new ContentDAO().getContents(tag));
	}

	public List<Content> getContents(long parent)
	{
		return (new ContentDAO().getContents(parent));
	}

	public List<Content> getList(String order)
	{
		return new ContentDAO().getList(order);
	}

	public List<Content> getList(String filtro, String order)
	{
		return new ContentDAO().getList(filtro, order);
	}

	public long insert()
	{
		return new ContentDAO().insert(this);
	}

	public void savePrevious(String user)
	{
		new ContentDAO().savePrevious(this, user);
	}

	public boolean save()
	{
		return new ContentDAO().save(this);
	}

	public boolean delete()
	{
		return new ContentDAO().delete(this);
	}

	public long getCts_id() {
		return cts_id;
	}
	public void setCts_id(long cts_id) {
		this.cts_id = cts_id;
	}
	public int getCts_order() {
		return cts_order;
	}
	public void setCts_order(int cts_order) {
		this.cts_order = cts_order;
	}
	public int getCts_published() {
		return cts_published;
	}
	public void setCts_published(int cts_published) {
		this.cts_published = cts_published;
	}
	public String getCts_tag() {
		return cts_tag;
	}
	public void setCts_tag(String cts_tag) {
		this.cts_tag = cts_tag;
	}
	public String getCts_text() {
		return cts_text;
	}
	public String getCts_text(String[] pars) {
		return getCts_text(pars, null, 0, "br");
	}
	public String getCts_text(String[] pars, String edt, long artID, String lng) {
		  String txt = cts_text;
		  if(txt != null)
		  {
			  if(edt!=null && !edt.equals(""))
              {
                   Editoria ed = new Editoria().getEditoria(edt);
                   if(ed!=null)
                   {
                	   Iterator<Article> ita = new Article().getArticles(ed.getEdt_id()).iterator();
            		   String articles = "<ul class='articles'>";
            		   String titles = "<ul class='articlelist'>";
                	   while(ita.hasNext())
                	   {
                		   Article atc = ita.next();
                		   Content cta = new Content().getContent(atc.getCts_id());
                		   articles += ("<li>" + atc.getArt_text(pars, false) + "</li>");
                		   titles += ("<li><a href='index?pag=" + cta.getCts_tag() + "&art=" + atc.getArt_id() + "'>" + atc.getArt_title() + "</a></li>");
                	   }
            		   articles += "</ul>";
            		   titles += "</ul>";
                       txt = txt.replace("_ARTICLES_", articles).replace("_ARTICLE-LIST_", titles).replace("_TITLE_", ed.getEdt_titulo());
                   }
              }
			  if(artID > 0)
			  {
				  Article ate = new Article().getArticle(artID);
				  String artc = ate.getArt_text(pars, true);
                  Editoria ed = new Editoria().getEditoria(ate.getEdt_id());
				  txt = txt.replace("_ARTICLE-FULL_", artc).replace("_TITLE_", ed.getEdt_titulo()).replace("_EDITORIA_", ed.getEdt_tag() + "");
			  } else {
				  txt = txt.replace("_ARTICLE-FULL_","Erro! Conte&uacute;do indisponivel!");
			  }
     		  int posIni = txt.indexOf("_ARTICLE-");
			  while(posIni >= 0)
			  {
				  int posFim = txt.indexOf("_", posIni + 9);
				  if(posFim > posIni)
				  {
					  String fullTag = txt.substring(posIni, posFim + 1);
					  String artTag = "";
					  boolean full = (fullTag.indexOf("FULL") > 0);
					  if(full)
					      artTag = fullTag.substring(14, fullTag.length()-1);
					  else
					      artTag = fullTag.substring(9, fullTag.length()-1);
					  Article art = new Article().getArticle(artTag);
					  String artTxt = "Erro! Conte&uacute;do indisponivel!";
					  if(art != null)
					  {
						  artTxt = art.getArt_text(pars, full);
					  }
					  txt = txt.replace(fullTag, (artTxt!=null?artTxt:"Erro! Conte&uacute;do indisponivel!"));
				  }
				  posIni = txt.indexOf("_ARTICLE-");
			  }
			  posIni = txt.indexOf("_BLOCK-");
			  while(posIni >= 0)
			  {
				  int posFim = txt.indexOf("_", posIni + 7);
				  if(posFim > posIni)
				  {
					  String frmTag = txt.substring(posIni, posFim + 1);
					  Block frm = new Block().getBlock(frmTag);
					  String frmTxt = "Erro! Conte&uacute;do indisponivel!";
					  if(frm != null)
					  {
						  frmTxt = (frm.getBlk_text() != null?frm.getBlk_text():frmTxt).trim();
					  }
					  int posCarousel = frmTxt.indexOf("_CAROUSEL-IMAGES_");
					  if(posCarousel >= 0)
					  {
						  Iterator<Image> it = new Image().getListByTag(frmTag, lng).iterator();
						  String carouselOptions = "<div class=\"carousel-inner\">\n";
						  int count = 1;
						  while(it.hasNext())
						  {
							  Image img = it.next();
							  carouselOptions += "<div class=\"item" + (count == 1?" active":"") + "\">";
							  carouselOptions += "<img src=\"" +
							                      (new Config().getConfig("IMAGES_URL").getCfg_value()) + img.getImg_file() +
							                      "\" alt=\"" + img.getImg_title() + "\" class=\"slides\" style=\"\">\n";
							  if((img.getImg_carousel_caption() != null && !img.getImg_carousel_caption().equals("")) ||
									  (img.getImg_carousel_description() != null && !img.getImg_carousel_description().equals("")))
									  {
								  		  carouselOptions += "<div class=\"container\"><div class=\"carousel-caption\">\n";
										  if(img.getImg_carousel_caption() != null && !img.getImg_carousel_caption().equals(""))
										  {
											  carouselOptions += "<h1>" + img.getImg_carousel_caption() + "</h1>\n";
										  }
										  if(img.getImg_carousel_description() != null && !img.getImg_carousel_description().equals(""))
										  {
											  carouselOptions += img.getImg_carousel_description();
										  }
										  carouselOptions += "</div></div>\n";
									  }
							  carouselOptions += "</div>\n";
							  count++;
						  }
						  carouselOptions += "</div><ol class=\"carousel-indicators\">\n";
						  for(int xx = 0;xx < (count-1); xx++)
						  {
							  carouselOptions += "<li data-target=\"#myCarousel\" data-slide-to=\"" + xx + "\"" + (xx == 0?" class=\"active\"":"") + "></li>\n";
						  }
						  carouselOptions += "</ol>\n";
						  frmTxt = frmTxt.replace("_CAROUSEL-IMAGES_", carouselOptions);
					  }
					  txt = txt.replace(frmTag, frmTxt);
				  }
				  posIni = txt.indexOf("_BLOCK-");
			  }
     		  posIni = txt.indexOf("_EDITORIAS_");
			  if(posIni >= 0)
			  {
				  int posFim = txt.indexOf("_", posIni + 9);
				  if(posFim > posIni)
				  {
					  String fullTag = txt.substring(posIni, posFim + 1);
					  Iterator<Editoria> ite = new Editoria().getList().iterator();
					  String editorias = "<ul class='editorias'>";
					  while(ite.hasNext())
					  {
						  Editoria ed = ite.next();
               		      editorias += ("<li><a href='index?pag=EDITORIA&edt=" + ed.getEdt_tag() + "'>" + ed.getEdt_titulo() + "</a></li>");
					  }
					  editorias += "</ul>";
					  txt = txt.replace(fullTag, editorias);
				  }
			  }
			  if(pars != null)
			  {
				  for(int xx=0; xx<pars.length; xx++)
				  {
					  String parName = pars[xx].substring(0,pars[xx].indexOf(":"));
					  String parValue = pars[xx].substring(pars[xx].indexOf(":")+1);
					  txt = txt.replace("_" + parName + "_", parValue);
				  }
			  }
			  if(!lng.equalsIgnoreCase("en"))
			  {
				  posIni = txt.indexOf("_TB_");
				  while(posIni >= 0)
				  {
					  int posFim = txt.indexOf("_TE_", posIni + 4);
					  if(posFim > posIni)
					  {
						  String frmTag = txt.substring(posIni + 4, posFim);
						  Language lang = new Language().getLanguage(frmTag, lng, this.getCts_tag());
						  if(lang!=null)
						  {
							  txt = txt.substring(0, posIni) + lang.getLng_to() + txt.substring(posFim + 4);
						  } else {
							  txt = txt.substring(0, posIni) + txt.substring(posIni + 4, posFim) + txt.substring(posFim + 4);
						  }
					  } else {
						  break;
					  }
					  posIni = txt.indexOf("_TB_");
				  }
		      }
			  txt = txt.replace("_TB_", "").replace("_TE_", "").replace("_SITE_URL_", new Config().getConfig("SITE_URL").getCfg_value()).replace("_EMAIL_FROM_", new Config().getConfig("EMAIL_FROM").getCfg_value());
		  }
		return txt;
	}
	public void setCts_text(String cts_text) {
		this.cts_text = cts_text;
	}
	public String getCts_title() {
		return cts_title;
	}
	public void setCts_title(String cts_title) {
		this.cts_title = cts_title;
	}

	public int getCts_comments() {
		return cts_comments;
	}

	public void setCts_comments(int cts_comments) {
		this.cts_comments = cts_comments;
	}

	public long getCts_parent() {
		return cts_parent;
	}

	public void setCts_parent(long cts_parent) {
		this.cts_parent = cts_parent;
	}

	public int getCts_haschat() {
		return cts_haschat;
	}

	public void setCts_haschat(int cts_haschat) {
		this.cts_haschat = cts_haschat;
	}

	public int getCts_session() {
		return cts_session;
	}

	public void setCts_session(int cts_session) {
		this.cts_session = cts_session;
	}

}
