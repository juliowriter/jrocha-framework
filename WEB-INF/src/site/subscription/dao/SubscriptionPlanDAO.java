package site.subscription.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site.core.ConnectionPool;
import site.subscription.SubscriptionPlan;


public class SubscriptionPlanDAO extends ConnectionPool {

	public SubscriptionPlan getPlan(long id)
    {
		SubscriptionPlan ret = null;
    	String sql = "SELECT * FROM site_subscriptionplan WHERE prc_id = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, id);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getPlan(rs);
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

	public SubscriptionPlan getPlanByPrice(int price)
    {
		SubscriptionPlan ret = null;
    	String sql = "SELECT * FROM site_subscriptionplan WHERE prc_price = ? AND prc_status = 'A' LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setInt(1, price);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getPlan(rs);
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

	public SubscriptionPlan getPlanByName(String plan)
    {
		SubscriptionPlan ret = null;
    	String sql = "SELECT * FROM site_subscriptionplan WHERE prc_name = ? AND prc_status = 'A' LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, plan);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getPlan(rs);
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

	public SubscriptionPlan getPlanByCredits(int credits)
    {
		SubscriptionPlan ret = null;
    	String sql = "SELECT * FROM site_subscriptionplan WHERE prc_credits = ? AND prc_status = 'A' LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setInt(1, credits);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getPlan(rs);
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

	public SubscriptionPlan getPlan(ResultSet rs) throws SQLException
    {
         SubscriptionPlan ret = new SubscriptionPlan();
    	 ret.setPrc_id(rs.getLong("prc_id"));
    	 ret.setPrc_credits(rs.getInt("prc_credits"));
         ret.setPrc_price(rs.getInt("prc_price"));
         ret.setPrc_months(rs.getInt("prc_months"));
         ret.setPrc_status(rs.getString("prc_status"));
         ret.setPrc_name(rs.getString("prc_name"));
         ret.setPrc_description(rs.getString("prc_description"));
	     return ret;
    }

    public boolean insert(SubscriptionPlan item)
    {
    	String sql = "INSERT INTO site_subscriptionplan (prc_price,prc_status,prc_credits,prc_name,prc_description,prc_months) VALUES (?,?,?,?,?,?)";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setInt(1, item.getPrc_price());
	       pstmt.setString(2, item.getPrc_status());
	       pstmt.setInt(3, item.getPrc_credits());
	       pstmt.setString(4, item.getPrc_name());
	       pstmt.setString(5, item.getPrc_description());
	       pstmt.setInt(6, item.getPrc_months());
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

    public boolean save(SubscriptionPlan item)
    {
    	String sql = "UPDATE site_subscriptionplan SET prc_price = ?,prc_status = ?,prc_credits = ?,prc_name=?,prc_description=?,prc_months=? " +
    			"WHERE prc_id = ? LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setInt(1, item.getPrc_price());
	       pstmt.setString(2, item.getPrc_status());
	       pstmt.setInt(3, item.getPrc_credits());
	       pstmt.setString(4, item.getPrc_name());
	       pstmt.setString(5, item.getPrc_description());
	       pstmt.setInt(6, item.getPrc_months());
	       pstmt.setLong(7, item.getPrc_id());
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

	public List<SubscriptionPlan> getSubscriptionPlan()
	{
		List<SubscriptionPlan> ret = new ArrayList<SubscriptionPlan>();
    	String sql = "SELECT * FROM site_subscriptionplan ORDER BY prc_credits";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       ResultSet rs = pstmt.executeQuery();
	       while(rs.next())
	         ret.add(this.getPlan(rs));
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

	public List<SubscriptionPlan> getSubscriptionPlan(int min)
	{
		List<SubscriptionPlan> ret = new ArrayList<SubscriptionPlan>();
    	String sql = "SELECT * FROM site_subscriptionplan WHERE prc_credits >= ? AND prc_status = 'A' ORDER BY prc_credits";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setInt(1, min);
	       ResultSet rs = pstmt.executeQuery();
	       while(rs.next())
	         ret.add(this.getPlan(rs));
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

	public int getPriceByCredits(int credits)
	{
    	int ret = credits;
		String sql = "SELECT prc_price FROM site_subscriptionplan WHERE prc_credits = ? AND prc_status = 'A' LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setInt(1, credits);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = rs.getInt("prc_price");
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
