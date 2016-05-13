package site.drm;

import java.util.List;

import site.drm.dao.LinkDAO;

public class Link {

	private long lnk_id;
	private String lnk_name;
	private String lnk_file;
	private String lnk_path;
	private String lnk_pathplay;
	private String lnk_urlplay;
	private int lnk_status;
	private int lnk_group;

	public Link getLink(long id)
	{
		return new LinkDAO().getLink(id);
	}

	public String insert()
	{
		return new LinkDAO().insert(this);
	}

	public List<Link> getLinkList()
	{
		return new LinkDAO().getLinkList();
	}

	public String getLnk_file() {
		return lnk_file;
	}

	public void setLnk_file(String lnk_file) {
		this.lnk_file = lnk_file;
	}

	public long getLnk_id() {
		return lnk_id;
	}

	public void setLnk_id(long lnk_id) {
		this.lnk_id = lnk_id;
	}

	public String getLnk_name() {
		return lnk_name;
	}

	public void setLnk_name(String lnk_name) {
		this.lnk_name = lnk_name;
	}

	public String getLnk_path() {
		return lnk_path;
	}

	public void setLnk_path(String lnk_path) {
		this.lnk_path = lnk_path;
	}

	public String getLnk_pathplay() {
		return lnk_pathplay;
	}

	public void setLnk_pathplay(String lnk_pathplay) {
		this.lnk_pathplay = lnk_pathplay;
	}

	public int getLnk_status() {
		return lnk_status;
	}

	public void setLnk_status(int lnk_status) {
		this.lnk_status = lnk_status;
	}

	public String getLnk_urlplay() {
		return lnk_urlplay;
	}

	public void setLnk_urlplay(String lnk_urlplay) {
		this.lnk_urlplay = lnk_urlplay;
	}

	public int getLnk_group() {
		return lnk_group;
	}

	public void setLnk_group(int lnk_group) {
		this.lnk_group = lnk_group;
	}


}
