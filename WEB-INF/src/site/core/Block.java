package site.core;

import java.util.Iterator;
import java.util.List;

import site.core.dao.BlockDAO;

public class Block {

	private long blk_id;
	private String blk_tag;
	private String blk_name;
	private String blk_text;

	public Block getBlock(long id)
	{
		return (new BlockDAO().getBlock(id));
	}

	public Block getBlock(String tag)
	{
		return (new BlockDAO().getBlock(tag));
	}

	public String[] getTags()
	{
		return (new BlockDAO().getTags());
	}

	public String parseBlock(String text, String lng) {
		return this.parseBlock(text, lng, 0);
	}

	public String parseBlock(String text, String lng, String tag) {
		this.blk_tag = tag;
		return this.parseBlock(text, lng, 0);
	}

	public String parseBlock(String text, String lng, String tag, long userID) {
		this.blk_tag = tag;
		return this.parseBlock(text, lng, userID);
	}

	public String parseBlock(String text, String lng, long userID) {
		  String txt = text;
		  if(txt != null)
		  {
			  int posIni = 0;
			  while(posIni >= 0)
			  {
				  posIni = txt.indexOf("_BLOCK-");
				  if(posIni >= 0)
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
						  txt = txt.replace(frmTag, frmTxt);
					  } else {
						  break;
					  }
				  } else {
					  break;
				  }
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
		  }
		  if(!lng.equalsIgnoreCase("en"))
		  {
			  int posIni = txt.indexOf("_TB_");
			  while(posIni >= 0)
			  {
				  int posFim = txt.indexOf("_TE_", posIni + 4);
				  if(posFim > posIni)
				  {
					  String frmTag = txt.substring(posIni + 4, posFim);
					  Language lang = new Language().getLanguage(frmTag, lng, this.blk_tag);
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
		  String dateserver = new Util().sysDateTime();
		  txt = txt.replace("_TB_", "").replace("_TE_", "").replace("_SITE_URL_", new Config().getConfig("SITE_URL").getCfg_value()).replace("_EMAIL_FROM_", new Config().getConfig("EMAIL_FROM").getCfg_value()).replace("_USER_ID_", userID + "").replace("_SERVER_DATE_TIME_", dateserver);
		return txt;
	}

	public List<Block> getList(String filtro, String order)
	{
		return new BlockDAO().getList(filtro, order);
	}

	public String[] getVersions()
	{
		return (new BlockDAO().getVersions(this));
	}

	public void restorePrevious(long hid)
	{
		new BlockDAO().restorePrevious(this, hid);
	}

	public void savePrevious(String user)
	{
		new BlockDAO().savePrevious(this, user);
	}

	public long insert()
	{
		return new BlockDAO().insert(this);
	}

	public boolean save()
	{
		return new BlockDAO().save(this);
	}

	public boolean delete()
	{
		return new BlockDAO().delete(this);
	}

	public long getBlk_id() {
		return blk_id;
	}

	public void setBlk_id(long blk_id) {
		this.blk_id = blk_id;
	}

	public String getBlk_name() {
		return blk_name;
	}

	public void setBlk_name(String blk_name) {
		this.blk_name = blk_name;
	}

	public String getBlk_tag() {
		return blk_tag;
	}

	public void setBlk_tag(String blk_tag) {
		this.blk_tag = blk_tag;
	}

	public String getBlk_text() {
		return blk_text;
	}

	public void setBlk_text(String blk_text) {
		this.blk_text = blk_text;
	}
}
