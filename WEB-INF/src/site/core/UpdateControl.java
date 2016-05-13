package site.core;

import site.core.dao.UpdateControlDAO;

public class UpdateControl {

	private String upd_last = null;
	private int upd_running = 0;

	public UpdateControl getUpdateControl()
	{
		return (new UpdateControlDAO().getUpdateControl());
	}

	public void save()
	{
		new UpdateControlDAO().save(this);
	}

	public String getUpd_last() {
		return upd_last;
	}

	public void setUpd_last(String upd_last) {
		this.upd_last = upd_last;
	}

	public int getUpd_running() {
		return upd_running;
	}

	public void setUpd_running(int upd_running) {
		this.upd_running = upd_running;
	}

}
