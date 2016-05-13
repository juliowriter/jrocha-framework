package site.subscription;

import java.util.List;
import site.core.Util;
import site.subscription.dao.SubscriptionPlanDAO;

public class SubscriptionPlan {

	private long prc_id = 0;
	private int prc_credits = 0;
	private int prc_price = 0;
	private int prc_months = 1;
	private String prc_status = "A";
	private String prc_name = "Free";
	private String prc_description = "Free";

	public SubscriptionPlan getPlan(long id)
    {
		return (new SubscriptionPlanDAO().getPlan(id));
    }

	public SubscriptionPlan getPlanByPrice(int price)
    {
		return (new SubscriptionPlanDAO().getPlanByPrice(price));
    }

	public SubscriptionPlan getPlanByName(String plan)
    {
		return (new SubscriptionPlanDAO().getPlanByName(plan));
    }

	public SubscriptionPlan getPlanByCredits(int credits)
    {
		return (new SubscriptionPlanDAO().getPlanByCredits(credits));
    }

	public List<SubscriptionPlan> getSubscriptionPlan(int min)
    {
		return (new SubscriptionPlanDAO().getSubscriptionPlan(min));
    }

	public boolean insert()
	{
		return new SubscriptionPlanDAO().insert(this);
	}

	public boolean save()
	{
		return new SubscriptionPlanDAO().save(this);
	}

	public String getPriceByCredits(int credits)
	{
		return "R$ " + new Util().formatMoney(new SubscriptionPlanDAO().getPriceByCredits(credits));
	}

	public int getPrc_credits() {
		return prc_credits;
	}

	public void setPrc_credits(int prc_credits) {
		this.prc_credits = prc_credits;
	}

	public long getPrc_id() {
		return prc_id;
	}

	public void setPrc_id(long prc_id) {
		this.prc_id = prc_id;
	}

	public int getPrc_price() {
		return prc_price;
	}

	public void setPrc_price(int prc_price) {
		this.prc_price = prc_price;
	}

	public String getPrc_status() {
		return prc_status;
	}

	public void setPrc_status(String prc_status) {
		this.prc_status = prc_status;
	}

	public String getPrc_name() {
		return prc_name;
	}

	public void setPrc_name(String prc_name) {
		this.prc_name = prc_name;
	}

	public String getPrc_description() {
		return prc_description;
	}

	public void setPrc_description(String prc_description) {
		this.prc_description = prc_description;
	}

	public int getPrc_months() {
		return prc_months;
	}

	public void setPrc_months(int prc_months) {
		this.prc_months = prc_months;
	}
	
}
