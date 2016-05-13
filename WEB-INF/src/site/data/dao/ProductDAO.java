package site.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site.core.ConnectionPool;
import site.data.Product;


public class ProductDAO extends ConnectionPool {

	public Product getProduct(long id)
    {
		Product ret = null;
    	String sql = "SELECT * FROM site_products WHERE prd_id = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, id);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getProduct(rs);
	       cleanUp(pstmt, rs);
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      closeConnection(con);
	    }
	    return ret;
    }

	public Product getProduct(ResultSet rs) throws SQLException
    {
         Product ret = new Product();
    	 ret.setPrd_id(rs.getLong("prd_id"));
         ret.setPrd_description(rs.getString("prd_description"));
         ret.setPrd_image(rs.getString("prd_image"));
         ret.setPrd_price(rs.getInt("prd_price"));
         ret.setPrd_price_1(rs.getInt("prd_price_1"));
         ret.setPrd_price_2(rs.getInt("prd_price_2"));
         ret.setPrd_price_3(rs.getInt("prd_price_3"));
         ret.setPrd_order(rs.getInt("prd_order"));
         ret.setPrd_status(rs.getString("prd_status"));
         ret.setPrd_title(rs.getString("prd_title"));
         ret.setPrd_code(rs.getString("prd_code"));
	     return ret;
    }

    public boolean insert(Product product)
    {
    	String sql = "INSERT INTO site_products (prd_description,prd_image,prd_title,prd_status," +
    			"prd_price,prd_price_1,prd_price_2,prd_price_3,prd_order,prd_code) VALUES " +
    			"(?,?,?,?,?,?,?,?,?)";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, product.getPrd_description());
	       pstmt.setString(2, product.getPrd_image());
	       pstmt.setString(3, product.getPrd_title());
	       pstmt.setString(4, product.getPrd_status());
	       pstmt.setInt(5, product.getPrd_price());
	       pstmt.setInt(6, product.getPrd_price_1());
	       pstmt.setInt(7, product.getPrd_price_2());
	       pstmt.setInt(8, product.getPrd_price_3());
	       pstmt.setInt(9, product.getPrd_order());
	       pstmt.setString(10, product.getPrd_code());
	       ret = pstmt.executeUpdate() == 1;
	       cleanUp(pstmt);
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      closeConnection(con);
	    }
	    return ret;
    }

    public boolean save(Product product)
    {
    	String sql = "UPDATE site_products SET prd_description = ?, prd_image = ?, prd_title = ?, prd_status = ?," +
    			"prd_price = ?, prd_price_1 = ?, prd_price_2 = ?, prd_price_3 = ?, prd_order = ?, prd_code = ? WHERE prd_id = ? LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, product.getPrd_description());
	       pstmt.setString(2, product.getPrd_image());
	       pstmt.setString(3, product.getPrd_title());
	       pstmt.setString(4, product.getPrd_status());
	       pstmt.setInt(5, product.getPrd_price());
	       pstmt.setInt(6, product.getPrd_price_1());
	       pstmt.setInt(7, product.getPrd_price_2());
	       pstmt.setInt(8, product.getPrd_price_3());
	       pstmt.setInt(9, product.getPrd_order());
	       pstmt.setString(10, product.getPrd_code());
	       pstmt.setLong(11, product.getPrd_id());
	       ret = pstmt.executeUpdate() == 1;
	       cleanUp(pstmt);
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      closeConnection(con);
	    }
	    return ret;
    }

	public List<Product> getProducts(String status)
	{
		List<Product> ret = new ArrayList<Product>();
    	String sql = "SELECT * FROM site_products WHERE prd_status = ? ORDER BY prd_order";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, status);
	       ResultSet rs = pstmt.executeQuery();
	       while(rs.next())
	         ret.add(this.getProduct(rs));
	       cleanUp(pstmt, rs);
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      closeConnection(con);
	    }
	    return ret;
	}

	public List<Product> getProducts()
	{
		List<Product> ret = new ArrayList<Product>();
    	String sql = "SELECT * FROM site_products ORDER BY prd_order";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       ResultSet rs = pstmt.executeQuery();
	       while(rs.next())
	         ret.add(this.getProduct(rs));
	       cleanUp(pstmt, rs);
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      closeConnection(con);
	    }
	    return ret;
	}
}
