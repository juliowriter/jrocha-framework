package site.subscription.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site.core.ConnectionPool;
import site.subscription.Subscription;
import site.subscription.SubscriptionPlan;


public class SubscriptionDAO extends ConnectionPool {

	public Subscription getSubscription(long id)
    {
		Subscription ret = null;
    	String sql = "SELECT * FROM site_subscription WHERE sub_id = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, id);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getSubscription(rs);
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

	public Subscription getSubscriptionByAccount(long id)
    {
		Subscription ret = null;
    	String sql = "SELECT * FROM site_subscription WHERE acc_id = ? AND (sub_status = 'A' OR sub_status = 'U') AND sub_enddate >= SYSDATE() ORDER BY prc_id DESC LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, id);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getSubscription(rs);
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

	public Subscription getSubscription(ResultSet rs) throws SQLException
    {
         Subscription ret = new Subscription();
    	 ret.setSub_id(rs.getLong("sub_id"));
    	 ret.setPrc_id(rs.getLong("prc_id"));
    	 ret.setAcc_id(rs.getLong("acc_id"));
    	 ret.setOrd_id(rs.getLong("ord_id"));
    	 ret.setSub_startdate(rs.getString("sub_startdate"));
         ret.setSub_enddate(rs.getString("sub_enddate"));
         ret.setSub_status(rs.getString("sub_status"));
	     return ret;
    }

    public boolean insert(Subscription item)
    {
    	SubscriptionPlan sp = new SubscriptionPlan().getPlan(item.getPrc_id());
    	String sql = "INSERT INTO site_subscription (prc_id,acc_id,ord_id,sub_enddate,sub_startdate,sub_status) VALUES (?,?,?,DATE_ADD(SYSDATE(),INTERVAL 50 YEAR),SYSDATE(),'A')";
        if(sp.getPrc_price() > 0 && sp.getPrc_months() > 0)
    		sql = "INSERT INTO site_subscription (prc_id,acc_id,ord_id,sub_enddate,sub_startdate,sub_status) VALUES (?,?,?,DATE_ADD(SYSDATE(),INTERVAL " + (sp.getPrc_months() + 1) + " MONTH),SYSDATE(),'A')";
    	Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, item.getPrc_id());
	       pstmt.setLong(2, item.getAcc_id());
	       pstmt.setLong(3, item.getOrd_id());
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

    public boolean insertUpdate(Subscription item)
    {
   		Subscription sb = this.getSubscriptionByAccount(item.getAcc_id());
    	String sql = "INSERT INTO site_subscription (prc_id,acc_id,ord_id,sub_enddate,sub_startdate,sub_status) VALUES (?,?,?,?,SYSDATE(),'U')";
    	Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, item.getPrc_id());
	       pstmt.setLong(2, item.getAcc_id());
	       pstmt.setLong(3, item.getOrd_id());
	       pstmt.setString(4, sb.getSub_enddate());
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

    public boolean save(Subscription item)
    {
    	String sql = "UPDATE site_subscription SET sub_status = ?, sub_enddate = ? " +
    			"WHERE sub_id = ? LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, item.getSub_status());
	       pstmt.setString(2, item.getSub_enddate());
	       pstmt.setLong(3, item.getSub_id());
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

	public List<Subscription> getSubscription()
	{
		List<Subscription> ret = new ArrayList<Subscription>();
    	String sql = "SELECT * FROM site_subscription ORDER BY sub_id DESC";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       ResultSet rs = pstmt.executeQuery();
	       while(rs.next())
	         ret.add(this.getSubscription(rs));
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
