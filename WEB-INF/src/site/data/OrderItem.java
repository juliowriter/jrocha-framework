package site.data;

import site.data.dao.OrderDAO;

public class OrderItem {

	private long oit_id;
	private long ord_id;
	private long prd_id;
	private int oit_qty;
	private int oit_value;

	public OrderItem getOrderItem(long id)
    {
		return (new OrderDAO().getOrderItem(id));
    }

	public long getOit_id() {
		return oit_id;
	}

	public void setOit_id(long oit_id) {
		this.oit_id = oit_id;
	}

	public int getOit_qty() {
		return oit_qty;
	}

	public void setOit_qty(int oit_qty) {
		this.oit_qty = oit_qty;
	}

	public int getOit_value() {
		return oit_value;
	}

	public void setOit_value(int oit_value) {
		this.oit_value = oit_value;
	}

	public long getOrd_id() {
		return ord_id;
	}

	public void setOrd_id(long ord_id) {
		this.ord_id = ord_id;
	}

	public long getPrd_id() {
		return prd_id;
	}

	public void setPrd_id(long prd_id) {
		this.prd_id = prd_id;
	}


}
