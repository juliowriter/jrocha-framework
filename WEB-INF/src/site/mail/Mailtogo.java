package site.mail;

import site.core.Util;
import site.mail.dao.MailtogoDAO;

public class Mailtogo {
	private long mtg_id;
	private String mtg_subject;
	private String mtg_address;
	private String mtg_from;
	private String mtg_replyto;
	private String mtg_text;
	private String mtg_postdate;
	private String mtg_status; //Q - Queue, E - Error, S - Sent
	private long prf_id;

	public boolean insert()
	{
		this.mtg_text = new Util().replaceSpecial(this.mtg_text);
		boolean ret = new MailtogoDAO().insert(this);
		return ret;
	}

	public void sendAll()
	{
		MailSender.start();
	}

	public boolean save()
	{
		return new MailtogoDAO().save(this);
	}

	public long getMtg_id() {
		return mtg_id;
	}

	public void setMtg_id(long mtg_id) {
		this.mtg_id = mtg_id;
	}

	public String getMtg_subject() {
		return mtg_subject;
	}

	public void setMtg_subject(String mtg_subject) {
		this.mtg_subject = mtg_subject;
	}

	public String getMtg_address() {
		return mtg_address;
	}

	public void setMtg_address(String mtg_address) {
		this.mtg_address = mtg_address;
	}

	public String getMtg_text() {
		return mtg_text;
	}

	public void setMtg_text(String mtg_text) {
		this.mtg_text = mtg_text;
	}

	public String getMtg_postdate() {
		return mtg_postdate;
	}

	public void setMtg_postdate(String mtg_postdate) {
		this.mtg_postdate = mtg_postdate;
	}

	public String getMtg_status() {
		return mtg_status;
	}

	public void setMtg_status(String mtg_status) {
		this.mtg_status = mtg_status;
	}

	public long getPrf_id() {
		return prf_id;
	}

	public void setPrf_id(long prf_id) {
		this.prf_id = prf_id;
	}

	public String getMtg_from() {
		return mtg_from;
	}

	public void setMtg_from(String mtg_from) {
		this.mtg_from = mtg_from;
	}

	public String getMtg_replyto() {
		return mtg_replyto;
	}

	public void setMtg_replyto(String mtg_replyto) {
		this.mtg_replyto = mtg_replyto;
	}


}
