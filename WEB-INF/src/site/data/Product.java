package site.data;

import java.util.List;

import site.data.dao.ProductDAO;

public class Product {

	private long prd_id = 0;
	private String prd_title = null;
	private String prd_description = null;
	private String prd_image = null;
	private int prd_price = 0;
	private int prd_price_1 = 0;
	private int prd_price_2 = 0;
	private int prd_price_3 = 0;
	private int prd_order = 0;
	private String prd_code = null;
	private String prd_status = null;

	public Product getProduct(long id)
    {
		return (new ProductDAO().getProduct(id));
    }

	public List<Product> getProducts(String status)
    {
		return (new ProductDAO().getProducts(status));
    }

	public List<Product> getProducts()
    {
		return (new ProductDAO().getProducts());
    }

	public boolean insert()
	{
		return new ProductDAO().insert(this);
	}

	public boolean save()
	{
		return new ProductDAO().save(this);
	}

	public String getPrd_description() {
		return prd_description;
	}

	public void setPrd_description(String prd_description) {
		this.prd_description = prd_description;
	}

	public String getPrd_image() {
		return prd_image;
	}

	public void setPrd_image(String prd_image) {
		this.prd_image = prd_image;
	}

	public int getPrd_price() {
		return prd_price;
	}

	public void setPrd_price(int prd_price) {
		this.prd_price = prd_price;
	}

	public int getPrd_price_1() {
		return prd_price_1;
	}

	public void setPrd_price_1(int prd_price_1) {
		this.prd_price_1 = prd_price_1;
	}

	public int getPrd_price_2() {
		return prd_price_2;
	}

	public void setPrd_price_2(int prd_price_2) {
		this.prd_price_2 = prd_price_2;
	}

	public int getPrd_price_3() {
		return prd_price_3;
	}

	public void setPrd_price_3(int prd_price_3) {
		this.prd_price_3 = prd_price_3;
	}

	public String getPrd_status() {
		return prd_status;
	}

	public void setPrd_status(String prd_status) {
		this.prd_status = prd_status;
	}

	public String getPrd_title() {
		return prd_title;
	}

	public void setPrd_title(String prd_title) {
		this.prd_title = prd_title;
	}

	public long getPrd_id() {
		return prd_id;
	}

	public void setPrd_id(long prd_id) {
		this.prd_id = prd_id;
	}

	public int getPrd_order() {
		return prd_order;
	}

	public void setPrd_order(int prd_order) {
		this.prd_order = prd_order;
	}

	public String getPrd_code() {
		return prd_code;
	}

	public void setPrd_code(String prd_code) {
		this.prd_code = prd_code;
	}


}
