package site.core;

import java.util.Iterator;
import java.util.List;

import site.core.dao.ArticleDAO;

public class Article {

	private long art_id;
	private String art_tag;
	private String art_title;
	private String art_resume;
	private String art_text;
	private int art_order;
	private int art_published;
	private int art_comments;
	private long cts_id;
	private int art_haschat;
	private int art_session;
	private long edt_id;

	public Article getArticle(long id)
	{
		return (new ArticleDAO().getArticle(id));
	}

	public Article getArticle(String tag)
	{
		return (new ArticleDAO().getArticle(tag));
	}

	public String[] getTags()
	{
		return (new ArticleDAO().getTags());
	}

	public void restorePrevious(long hid)
	{
		new ArticleDAO().restorePrevious(this, hid);
	}

	public String[] getVersions()
	{
		return (new ArticleDAO().getVersions(this));
	}

	public List<Article> getArticles(String tag)
	{
		return (new ArticleDAO().getArticles(tag));
	}

	public List<Article> getArticles(long editoria)
	{
		return (new ArticleDAO().getArticles(editoria));
	}

	public List<Article> getArticles(long parent, long editoria)
	{
		return (new ArticleDAO().getArticles(parent, editoria));
	}


	public List<Article> getList(String order)
	{
		return new ArticleDAO().getList(order);
	}

	public List<Article> getList(String filtro, String order)
	{
		return new ArticleDAO().getList(filtro, order);
	}

	public long insert()
	{
		return new ArticleDAO().insert(this);
	}

	public void savePrevious(String user)
	{
		new ArticleDAO().savePrevious(this, user);
	}

	public boolean save()
	{
		return new ArticleDAO().save(this);
	}

	public boolean delete()
	{
		return new ArticleDAO().delete(this);
	}

	public long getArt_id() {
		return art_id;
	}
	public void setArt_id(long art_id) {
		this.art_id = art_id;
	}
	public int getArt_order() {
		return art_order;
	}
	public void setArt_order(int art_order) {
		this.art_order = art_order;
	}
	public int getArt_published() {
		return art_published;
	}
	public void setArt_published(int art_published) {
		this.art_published = art_published;
	}
	public String getArt_tag() {
		return art_tag;
	}
	public void setArt_tag(String art_tag) {
		this.art_tag = art_tag;
	}
	public String getArt_text() {
		return art_text;
	}
	public String getArt_text(String[] pars, boolean full) {
		  String txt = (full?art_text:art_resume);
		  if(txt != null)
		  {
			  int posIni = txt.indexOf("_BLOCK-");
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
						  Iterator<Image> it = new Image().getListByTag(frmTag).iterator();
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
			  if(pars != null)
			  {
				  for(int xx=0; xx<pars.length; xx++)
				  {
					  String parName = pars[xx].substring(0,pars[xx].indexOf(":"));
					  String parValue = pars[xx].substring(pars[xx].indexOf(":")+1);
					  txt = txt.replace("_" + parName + "_", parValue);
				  }
			  }
			  if(!full)
			  {
				  Block blk = new Block().getBlock("_BLOCK_ARTICLE_LINK_");
				  if(blk!=null)
				  {
					  txt += blk.getBlk_text().replace("_ID_", this.getArt_id() + "");
				  }
			  }
		  }
		return txt;
	}
	public void setArt_text(String art_text) {
		this.art_text = art_text;
	}
	public String getArt_title() {
		return art_title;
	}
	public void setArt_title(String art_title) {
		this.art_title = art_title;
	}

	public int getArt_comments() {
		return art_comments;
	}

	public void setArt_comments(int art_comments) {
		this.art_comments = art_comments;
	}

	public int getArt_haschat() {
		return art_haschat;
	}

	public void setArt_haschat(int art_haschat) {
		this.art_haschat = art_haschat;
	}

	public int getArt_session() {
		return art_session;
	}

	public void setArt_session(int art_session) {
		this.art_session = art_session;
	}

	public long getCts_id() {
		return cts_id;
	}

	public void setCts_id(long cts_id) {
		this.cts_id = cts_id;
	}

	public long getEdt_id() {
		return edt_id;
	}

	public void setEdt_id(long edt_id) {
		this.edt_id = edt_id;
	}

	public String getArt_resume() {
		return art_resume;
	}

	public void setArt_resume(String art_resume) {
		this.art_resume = art_resume;
	}


}
