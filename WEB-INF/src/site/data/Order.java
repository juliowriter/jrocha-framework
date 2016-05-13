package site.data;

import java.util.List;

import site.data.dao.OrderDAO;

public class Order {

	private long ord_id;
	private String ord_firstname;
	private String ord_lastname;
	private String ord_zipcode;
	private String ord_email;
	private String ord_email2;
	private String ord_status;
	private String ord_phone;
	private String ord_celphone;
	private String ord_address;
	private String ord_number;
	private String ord_complement;
	private String ord_neighborhood;
	private String ord_city;
	private String ord_state;
	private String ord_startdate;
	private String ord_lastdate;
	private String ord_token;
	//private String ord_item;
	//private int ord_qty;
	private String ord_discount;
	private String ord_mailcode;
	private int cou_id;
	private int ord_value;
	private String ord_oldtoken;

	public Order getOrder(String email)
    {
		return (new OrderDAO().getOrder(email));
    }

	public Order getOrderToken(String token)
    {
		return (new OrderDAO().getOrderToken(token));
    }

	public Order getOrder(long id)
    {
		return (new OrderDAO().getOrder(id));
    }

	public long insert()
	{
		return new OrderDAO().insert(this);
	}

	public int getTotal(String status)
	{
		return new OrderDAO().getTotal(status);
	}

	public boolean update()
	{
		return new OrderDAO().save(this);
	}

	public boolean insertItens(String itens, long oid)
	{
		return new OrderDAO().insertItens(itens, oid);
	}

	public List<OrderItem> getItemList(){
    	return new OrderDAO().getItemList(this.getOrd_id());
    }

	public int getItem_qty(long prd_id){
    	return new OrderDAO().getItem_qty(this.getOrd_id(), prd_id);
    }

	public boolean saveStatus(String status, long id)
	{
		return new OrderDAO().saveStatus(status, id, null);
	}

	public boolean saveStatusMoIP(String token)
	{
		return new OrderDAO().saveStatusMoIP(token);
	}

	public boolean saveStatus(String status, long id, String mailcode)
	{
		return new OrderDAO().saveStatus(status, id, mailcode);
	}

	public boolean save()
	{
		return new OrderDAO().save(this);
	}

	public void clearItens()
	{
		new OrderDAO().clearItens(this.getOrd_id());
	}

	public boolean exists(String email)
	{
		return new OrderDAO().exists(email);
	}

	public long getCount()
	{
		return new OrderDAO().getCount();
	}

	public long getUniqueOrders()
	{
		return new OrderDAO().getUniqueOrders(null,null);
	}

	public long getUniqueOrders(String status)
	{
		return new OrderDAO().getUniqueOrders(status,null);
	}

	public long getUniqueOrders(String status, String item)
	{
		return new OrderDAO().getUniqueOrders(status,item);
	}

	public List<Order> getList(long arg0, long arg1, String arg2, String arg3, String arg4, String arg5, String arg6){
    	return new OrderDAO().getList(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
    }

	public List<Order> getLabels(String status){
    	return new OrderDAO().getLabels(status);
    }

	public long getOrd_id() {
		return ord_id;
	}
	public void setOrd_id(long ord_id) {
		this.ord_id = ord_id;
	}
	public String getOrd_firstname() {
		return ord_firstname;
	}
	public void setOrd_firstname(String ord_firstname) {
		this.ord_firstname = ord_firstname;
	}
	public String getOrd_lastname() {
		return ord_lastname;
	}
	public void setOrd_lastname(String ord_lastname) {
		this.ord_lastname = ord_lastname;
	}
	public String getOrd_zipcode() {
		return ord_zipcode;
	}
	public void setOrd_zipcode(String ord_zipcode) {
		this.ord_zipcode = ord_zipcode;
	}
	public String getOrd_email() {
		return ord_email;
	}
	public void setOrd_email(String ord_email) {
		this.ord_email = ord_email;
	}
	public String getOrd_email2() {
		return ord_email2;
	}
	public void setOrd_email2(String ord_email2) {
		this.ord_email2 = ord_email2;
	}
	public String getOrd_status() {
		return ord_status;
	}
	public void setOrd_status(String ord_status) {
		this.ord_status = ord_status;
	}
	public String getOrd_startdate() {
		return ord_startdate;
	}
	public void setOrd_startdate(String ord_startdate) {
		this.ord_startdate = ord_startdate;
	}
	public String getOrd_token() {
		return ord_token;
	}

	public void setOrd_token(String ord_token) {
		this.ord_token = ord_token;
	}

	public String getOrd_phone() {
		return ord_phone;
	}

	public void setOrd_phone(String ord_phone) {
		this.ord_phone = ord_phone;
	}

	public String getOrd_celphone() {
		return ord_celphone;
	}

	public void setOrd_celphone(String ord_celphone) {
		this.ord_celphone = ord_celphone;
	}

	public String getOrd_address() {
		return ord_address;
	}

	public void setOrd_address(String ord_address) {
		this.ord_address = ord_address;
	}

	public String getOrd_number() {
		return ord_number;
	}

	public void setOrd_number(String ord_number) {
		this.ord_number = ord_number;
	}

	public String getOrd_city() {
		return ord_city;
	}

	public void setOrd_city(String ord_city) {
		this.ord_city = ord_city;
	}

	public String getOrd_state() {
		return ord_state;
	}

	public void setOrd_state(String ord_state) {
		this.ord_state = ord_state;
	}

	public int getCou_id() {
		return cou_id;
	}

	public void setCou_id(int cou_id) {
		this.cou_id = cou_id;
	}

	public String getOrd_discount() {
		return ord_discount;
	}

	public void setOrd_discount(String ord_discount) {
		this.ord_discount = ord_discount;
	}

	public int getDiscount()
	{
		int ret = 0;
		if(!this.ord_discount.equals(""))
			ret = new OrderDAO().getDiscount(this.ord_discount);
		return ret;
	}

	public int getOrd_value() {
		return ord_value;
	}

	public void setOrd_value(int ordValue) {
		ord_value = ordValue;
	}

	public String getOrd_complement() {
		return ord_complement;
	}

	public void setOrd_complement(String ordComplement) {
		ord_complement = ordComplement;
	}

	public String getOrd_lastdate() {
		return ord_lastdate;
	}

	public void setOrd_lastdate(String ord_lastdate) {
		this.ord_lastdate = ord_lastdate;
	}

	public String getOrd_mailcode() {
		return ord_mailcode;
	}

	public void setOrd_mailcode(String ord_mailcode) {
		this.ord_mailcode = ord_mailcode;
	}

	public String getOrd_neighborhood() {
		return ord_neighborhood;
	}

	public void setOrd_neighborhood(String ord_neighborhood) {
		this.ord_neighborhood = ord_neighborhood;
	}

	public String getOrd_oldtoken() {
		return ord_oldtoken;
	}

	public void setOrd_oldtoken(String ord_oldtoken) {
		this.ord_oldtoken = ord_oldtoken;
	}


}
