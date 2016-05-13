package site.data;

import site.data.dao.OptinDAO;

public class Optin {

	private long opt_id;
	private String opt_name;
	private String opt_zipcode;
	private String opt_email;
	private String opt_lists;
	private String opt_token;
	private String opt_optinstamp;

	public Optin getOptinToken(String token)
    {
		return (new OptinDAO().getOptinToken(token));
    }

	public Optin getOptin(String email)
    {
		return (new OptinDAO().getOptin(email));
    }

	public Optin getOptin(long id)
    {
		return (new OptinDAO().getOptin(id));
    }

	public boolean insert()
	{
		return new OptinDAO().insert(this);
	}

	public boolean save()
	{
		return new OptinDAO().save(this);
	}

	public long getOpt_id() {
		return opt_id;
	}

	public void setOpt_id(long opt_id) {
		this.opt_id = opt_id;
	}

	public String getOpt_name() {
		return opt_name;
	}

	public void setOpt_name(String opt_name) {
		this.opt_name = opt_name;
	}

	public String getOpt_zipcode() {
		return opt_zipcode;
	}

	public void setOpt_zipcode(String opt_zipcode) {
		this.opt_zipcode = opt_zipcode;
	}

	public String getOpt_email() {
		return opt_email;
	}

	public void setOpt_email(String opt_email) {
		this.opt_email = opt_email;
	}

	public String getOpt_lists() {
		return opt_lists;
	}

	public void setOpt_lists(String opt_lists) {
		this.opt_lists = opt_lists;
	}

	public String getOpt_token() {
		return opt_token;
	}

	public void setOpt_token(String opt_token) {
		this.opt_token = opt_token;
	}

	public String getOpt_optinstamp() {
		return opt_optinstamp;
	}

	public void setOpt_optinstamp(String opt_optinstamp) {
		this.opt_optinstamp = opt_optinstamp;
	}

}
