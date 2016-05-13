package site.core;

import java.util.List;

import site.core.dao.HeaderOptionsDAO;

public class HeaderOptions {

	private long opt_id;
	private String opt_title;
	private String opt_link;
	private String opt_target;
	private int opt_order;
	private int opt_status;
	private String opt_language;

	public HeaderOptions getHeaderOptions(long id)
	{
		return (new HeaderOptionsDAO().getHeaderOptions(id));
	}

	public List<HeaderOptions> getList()
	{
		return new HeaderOptionsDAO().getList();
	}

	public List<HeaderOptions> getList(boolean withSession, String lng)
	{
		return new HeaderOptionsDAO().getList(withSession, lng);
	}

	public List<HeaderOptions> getList(String order)
	{
		return new HeaderOptionsDAO().getList(order);
	}

	public long insert()
	{
		return new HeaderOptionsDAO().insert(this);
	}

	public boolean save()
	{
		return new HeaderOptionsDAO().save(this);
	}

	public boolean delete()
	{
		return new HeaderOptionsDAO().delete(this);
	}

	public long getOpt_id() {
		return opt_id;
	}

	public void setOpt_id(long opt_id) {
		this.opt_id = opt_id;
	}

	public String getOpt_link() {
		return opt_link;
	}

	public void setOpt_link(String opt_link) {
		this.opt_link = opt_link;
	}

	public int getOpt_order() {
		return opt_order;
	}

	public void setOpt_order(int opt_order) {
		this.opt_order = opt_order;
	}

	public int getOpt_status() {
		return opt_status;
	}

	public void setOpt_status(int opt_status) {
		this.opt_status = opt_status;
	}

	public String getOpt_target() {
		return opt_target;
	}

	public void setOpt_target(String opt_target) {
		this.opt_target = opt_target;
	}

	public String getOpt_title() {
		return opt_title;
	}

	public void setOpt_title(String opt_title) {
		this.opt_title = opt_title;
	}

	public String getOpt_language() {
		return opt_language;
	}

	public void setOpt_language(String opt_language) {
		this.opt_language = opt_language;
	}



}
