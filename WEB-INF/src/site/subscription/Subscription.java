package site.subscription;

import site.subscription.dao.SubscriptionDAO;

public class Subscription {

	private long sub_id = 0;
	private long acc_id = 0;
	private long prc_id = 0;
	private long ord_id = 0;
	private String sub_startdate = null;
	private String sub_enddate = null;
	private String sub_status = "A";

	public Subscription getSubscription(long id)
    {
		return (new SubscriptionDAO().getSubscription(id));
    }

	public Subscription getSubscriptionByAccount(long id)
    {
		return (new SubscriptionDAO().getSubscriptionByAccount(id));
    }

	public boolean insert()
	{
		return new SubscriptionDAO().insert(this);
	}

	public boolean save()
	{
		return new SubscriptionDAO().save(this);
	}

	public long getAcc_id() {
		return acc_id;
	}

	public void setAcc_id(long acc_id) {
		this.acc_id = acc_id;
	}

	public long getOrd_id() {
		return ord_id;
	}

	public void setOrd_id(long ord_id) {
		this.ord_id = ord_id;
	}

	public long getPrc_id() {
		return prc_id;
	}

	public void setPrc_id(long prc_id) {
		this.prc_id = prc_id;
	}

	public String getSub_enddate() {
		return sub_enddate;
	}

	public void setSub_enddate(String sub_enddate) {
		this.sub_enddate = sub_enddate;
	}

	public long getSub_id() {
		return sub_id;
	}

	public void setSub_id(long sub_id) {
		this.sub_id = sub_id;
	}

	public String getSub_startdate() {
		return sub_startdate;
	}

	public void setSub_startdate(String sub_startdate) {
		this.sub_startdate = sub_startdate;
	}

	public String getSub_status() {
		return sub_status;
	}

	public void setSub_status(String sub_status) {
		this.sub_status = sub_status;
	}

}
