package site.core;

import java.util.List;

import site.core.dao.ConfigDAO;

public class Config {

	private long cfg_id;
	private String cfg_tag = null;
	private String cfg_value = null;

	public Config getConfig(long id)
	{
		return (new ConfigDAO().getConfig(id));
	}

	public Config getConfig(String tag)
    {
		return (new ConfigDAO().getConfig(tag));
    }

	public List<Config> getList(String order)
	{
		return (new ConfigDAO().getList(order));
	}

	public long insert()
	{
		return new ConfigDAO().insert(this);
	}

	public void save()
	{
		new ConfigDAO().save(this);
	}

	public boolean delete()
	{
		return new ConfigDAO().delete(this);
	}

	public String getCfg_tag() {
		return cfg_tag;
	}

	public void setCfg_tag(String cfg_tag) {
		this.cfg_tag = cfg_tag;
	}

	public String getCfg_value() {
		return cfg_value;
	}

	public void setCfg_value(String cfg_value) {
		this.cfg_value = cfg_value;
	}

	public long getCfg_id() {
		return cfg_id;
	}

	public void setCfg_id(long cfg_id) {
		this.cfg_id = cfg_id;
	}


}
