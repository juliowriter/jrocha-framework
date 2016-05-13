package site.subscription;

import java.util.List;

import site.subscription.dao.SubscriptionOrderDAO;

public class SubscriptionOrder {

	private long ord_id;
	private String ord_email;
	private String ord_status;
	private String ord_datetime;
	private String ord_lastdatetime;
	private String ord_token;
	private long acc_id;
	private long prc_id;
	private int ord_value;
	private int ord_credits;
	private String ord_oldtoken;

	public SubscriptionOrder getSubscriptionOrder(String email)
    {
		return (new SubscriptionOrderDAO().getSubscriptionOrder(email));
    }

	public SubscriptionOrder getSubscriptionOrderToken(String token)
    {
		return (new SubscriptionOrderDAO().getSubscriptionOrderToken(token));
    }

	public SubscriptionOrder getSubscriptionOrder(long id)
    {
		return (new SubscriptionOrderDAO().getSubscriptionOrder(id));
    }

	public long insert()
	{
		return new SubscriptionOrderDAO().insert(this);
	}

	public int getTotal(String status)
	{
		return new SubscriptionOrderDAO().getTotal(status);
	}

	public int getTotalToday(String status)
	{
		return new SubscriptionOrderDAO().getTotalToday(status);
	}

	public boolean update()
	{
		return new SubscriptionOrderDAO().save(this);
	}

	public boolean saveStatus(String status, long id)
	{
		return new SubscriptionOrderDAO().saveStatus(status, id);
	}

	public boolean saveStatusMoIP(String token)
	{
		return new SubscriptionOrderDAO().saveStatusMoIP(token);
	}

	public boolean saveStatusPaypal(String token, String lng)
	{
		return new SubscriptionOrderDAO().saveStatusPayPal(token, lng);
	}

	public boolean save()
	{
		return new SubscriptionOrderDAO().save(this);
	}

	public boolean exists(String email)
	{
		return new SubscriptionOrderDAO().exists(email);
	}

	public long getCount()
	{
		return new SubscriptionOrderDAO().getCount();
	}

	public List<SubscriptionOrder> getList(long arg0, long arg1, String arg2, String arg3, String arg4, String arg5, String arg6){
    	return new SubscriptionOrderDAO().getList(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
    }

	public int getOrd_credits() {
		return ord_credits;
	}

	public void setOrd_credits(int ord_credits) {
		this.ord_credits = ord_credits;
	}

	public String getOrd_datetime() {
		return ord_datetime;
	}

	public void setOrd_datetime(String ord_datetime) {
		this.ord_datetime = ord_datetime;
	}

	public String getOrd_email() {
		return ord_email;
	}

	public void setOrd_email(String ord_email) {
		this.ord_email = ord_email;
	}

	public long getOrd_id() {
		return ord_id;
	}

	public void setOrd_id(long ord_id) {
		this.ord_id = ord_id;
	}

	public String getOrd_lastdatetime() {
		return ord_lastdatetime;
	}

	public void setOrd_lastdatetime(String ord_lastdatetime) {
		this.ord_lastdatetime = ord_lastdatetime;
	}

	public String getOrd_status() {
		return ord_status;
	}

	public void setOrd_status(String ord_status) {
		this.ord_status = ord_status;
	}

	public String getOrd_token() {
		return ord_token;
	}

	public void setOrd_token(String ord_token) {
		this.ord_token = ord_token;
	}

	public int getOrd_value() {
		return ord_value;
	}

	public void setOrd_value(int ord_value) {
		this.ord_value = ord_value;
	}

	public long getAcc_id() {
		return acc_id;
	}

	public void setAcc_id(long acc_id) {
		this.acc_id = acc_id;
	}

	public String getOrd_oldtoken() {
		return ord_oldtoken;
	}

	public void setOrd_oldtoken(String ord_oldtoken) {
		this.ord_oldtoken = ord_oldtoken;
	}

	public long getPrc_id() {
		return prc_id;
	}

	public void setPrc_id(long prc_id) {
		this.prc_id = prc_id;
	}


}
