package site.core;

import site.core.dao.ActivityLogDAO;

public class ActivityLog {
	private long log_id;
	private long cli_id;
	private String log_datetime;
	private String log_function;
	private String log_ip;
	private String log_description;
	private String log_type;

	public void insert()
	{
		new ActivityLogDAO().insert(this);
	}

	public void insert(long cliID, String function, String type, String ip, String description)
	{
		ActivityLog alg = new ActivityLog();
		alg.setCli_id(cliID);
		alg.setLog_description(description);
		alg.setLog_ip(ip);
		alg.setLog_function(function);
		alg.setLog_type(type);
		new ActivityLogDAO().insert(alg);
	}

	public long getCli_id() {
		return cli_id;
	}
	public void setCli_id(long cli_id) {
		this.cli_id = cli_id;
	}
	public String getLog_datetime() {
		return log_datetime;
	}
	public void setLog_datetime(String log_datetime) {
		this.log_datetime = log_datetime;
	}
	public String getLog_description() {
		return log_description;
	}
	public void setLog_description(String log_description) {
		this.log_description = log_description;
	}
	public String getLog_function() {
		return log_function;
	}
	public void setLog_function(String log_function) {
		this.log_function = log_function;
	}
	public long getLog_id() {
		return log_id;
	}
	public void setLog_id(long log_id) {
		this.log_id = log_id;
	}
	public String getLog_ip() {
		return log_ip;
	}
	public void setLog_ip(String log_ip) {
		this.log_ip = log_ip;
	}
	public String getLog_type() {
		return log_type;
	}
	public void setLog_type(String log_type) {
		this.log_type = log_type;
	}



}