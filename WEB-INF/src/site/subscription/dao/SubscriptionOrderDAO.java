package site.subscription.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site.core.Block;
import site.core.Config;
import site.core.ConnectionPool;
import site.subscription.Subscription;
import site.subscription.SubscriptionOrder;
import site.mail.Mailtogo;

public class SubscriptionOrderDAO extends ConnectionPool {

	public SubscriptionOrder getSubscriptionOrder(long id)
    {
		SubscriptionOrder ret = null;
    	String sql = "SELECT * FROM site_subscription_order WHERE ord_id = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, id);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getSubscriptionOrder(rs);
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

	public SubscriptionOrder getSubscriptionOrder(String email)
    {
		SubscriptionOrder ret = null;
    	String sql = "SELECT * FROM site_subscription_order WHERE ord_email = ? ORDER BY ord_id DESC LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, email);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getSubscriptionOrder(rs);
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

	public int getTotal(String status)
    {
		int ret = 0;
    	String sql = "SELECT COUNT(*) AS TOTAL FROM site_subscription_order WHERE ord_status = ?";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, status);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = rs.getInt("TOTAL");
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

	public int getTotalToday(String status)
    {
		int ret = 0;
    	String sql = "SELECT COUNT(*) AS TOTAL FROM site_subscription_order WHERE ord_status = ? AND ord_datetime >= DATE(SYSDATE())";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, status);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = rs.getInt("TOTAL");
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

	public SubscriptionOrder getSubscriptionOrderToken(String token)
    {
		SubscriptionOrder ret = null;
    	String sql = "SELECT * FROM site_subscription_order WHERE ord_token = ? ORDER BY ord_id DESC LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, token);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next()){
	    	   ret = new SubscriptionOrderDAO().getSubscriptionOrder(rs);
	       } else {
			   sql = "SELECT * FROM site_subscription_order WHERE ord_oldtoken LIKE '%" + token + ",%' LIMIT 1";
		       pstmt = con.prepareStatement(sql);
			   rs = pstmt.executeQuery();
			   if(rs.next())
				   ret = new SubscriptionOrderDAO().getSubscriptionOrder(rs);
	       }
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

	public SubscriptionOrder getSubscriptionOrder(ResultSet rs) throws SQLException
    {
		 SubscriptionOrder ret = new SubscriptionOrder();
    	 ret.setOrd_id(rs.getLong("ord_id"));
    	 ret.setAcc_id(rs.getLong("acc_id"));
    	 ret.setPrc_id(rs.getLong("prc_id"));
         ret.setOrd_email(rs.getString("ord_email"));
         ret.setOrd_token(rs.getString("ord_token"));
         ret.setOrd_status(rs.getString("ord_status"));
         ret.setOrd_value(rs.getInt("ord_value"));
         ret.setOrd_credits(rs.getInt("ord_credits"));
         ret.setOrd_oldtoken(rs.getString("ord_oldtoken"));
         ret.setOrd_datetime(rs.getString("ord_datetime"));
         ret.setOrd_lastdatetime(rs.getString("ord_lastdatetime"));
	     return ret;
    }

	public boolean exists(String email)
    {
		boolean ret = false;
    	String sql = "SELECT 1 FROM site_subscription_order WHERE ord_email = ?  AND ord_status != 'X' LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, email);
	       ResultSet rs = pstmt.executeQuery();
	       ret = rs.next();
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

    public long getCount()
    {
		long ret = 0;
    	String sql = "SELECT COUNT(*) AS TOTAL FROM site_subscription_order";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       ResultSet rs = pstmt.executeQuery();
	       rs.next();
	       ret = rs.getLong("TOTAL");
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

    public long getUniqueSubscriptionOrders(String status, long item)
    {
		long ret = 0;
    	String sql = "SELECT COUNT(*) AS TOTAL FROM site_subscription_order WHERE ord_status <> 'X'";
    	if(status!=null && item == 0)
    		sql += " AND ord_status = ? GROUP BY ord_status";
    	else if(status!=null && item > 0)
    		sql += " AND ord_status = ? AND prc_id = ? GROUP BY ord_status + prc_id";
    	else if(item > 0)
    		sql += " AND prc_id = ? GROUP BY prc_id";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       if(status!=null && item == 0)
	       {
	    		pstmt.setString(1, status);
	       } else if(status!=null && item > 0)
	       {
	    		pstmt.setString(1, status);
	    		pstmt.setLong(2, item);
	       } else if(item > 0) {
	    		pstmt.setLong(1, item);
	       }
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	    	   ret = rs.getLong("TOTAL");
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

    public List<SubscriptionOrder> getList(long arg0, long arg1, String arg2, String arg3, String arg4, String arg5, String arg6){
        List<SubscriptionOrder> coll = new ArrayList<SubscriptionOrder>();
        String sql = "SELECT * FROM site_subscription_order ";
        boolean hasWhere = false;
        if(!arg5.equals("A")) {
        	if(hasWhere)
        		sql += " AND ord_status = '" + arg4 + "' ";
        	else
        		sql += " WHERE ord_status = '" + arg4 + "' ";
        	hasWhere = true;
        }
        if(!arg4.equals("F") && !arg4.equals("X")) {
	    	if(hasWhere)
	    		sql += " AND ord_status <> 'F' AND ord_status <> 'X' ";
	    	else
	    		sql += " WHERE ord_status <> 'F' AND ord_status <> 'X' ";
        	hasWhere = true;
        }
        if(!arg5.equals("") && !arg6.equals("")) {
	    	if(hasWhere)
	    		sql += " AND ord_startdate >= '" + arg5 + "' AND ord_startdate <= '" + arg6 + "' ";
	    	else
	    		sql += " WHERE ord_startdate >= '" + arg5 + "' AND ord_startdate <= '" + arg6 + "' ";
        }
        sql += "ORDER BY " + arg2 + " " + arg3 + " LIMIT " + arg0 + "," + arg1;
        Connection con = null;
        try{
           con = getConnection();
           PreparedStatement pstmt = con.prepareStatement(sql);
           ResultSet rs = pstmt.executeQuery();
           while(rs.next()) coll.add( new SubscriptionOrderDAO().getSubscriptionOrder(rs) );
           cleanUp(pstmt, rs);
        } catch (SQLException e) {
          e.printStackTrace();
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          closeConnection(con);
        }
        return coll;
    }

    public long insert(SubscriptionOrder order)
    {
    	String sql = "INSERT INTO site_subscription_order (ord_email,ord_status,ord_token,ord_value,ord_credits,acc_id,prc_id,ord_datetime,ord_lastdatetime) VALUES " +
    			"(?,?,?,?,?,?,?,SYSDATE(),SYSDATE())";
	    Connection con = null;
	    long ret = 0;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, order.getOrd_email());
	       pstmt.setString(2, order.getOrd_status());
	       pstmt.setString(3, order.getOrd_token());
	       pstmt.setInt(4, order.getOrd_value());
	       pstmt.setInt(5, order.getOrd_credits());
	       pstmt.setLong(6, order.getAcc_id());
	       pstmt.setLong(7, order.getPrc_id());
	       pstmt.executeUpdate();
	       cleanUp(pstmt);
	       sql = "SELECT ord_id FROM site_subscription_order WHERE acc_id = ? ORDER BY ord_id DESC LIMIT 1";
	       pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, order.getAcc_id());
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	    	   ret = rs.getLong("ord_id");
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      closeConnection(con);
	    }
	    return ret;
    }

    public boolean saveStatus(String status, long id)
    {
    	String sql = "UPDATE site_subscription_order SET ord_status = ?, ord_lastdatetime = SYSDATE()";
    	sql += " WHERE ord_id = ? LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, status);
	       pstmt.setLong(2, id);
	       ret = pstmt.executeUpdate() == 1;
	       cleanUp(pstmt);
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      closeConnection(con);
	    }
	    if(status.equals("C") || status.equals("U"))
	    {
	    	SubscriptionOrder od = new SubscriptionOrder().getSubscriptionOrder(id);
			if(od.getPrc_id() > 0)
			{
				Subscription sb = new Subscription();
				sb.setAcc_id(od.getAcc_id());
				sb.setOrd_id(od.getOrd_id());
				sb.setPrc_id(od.getPrc_id());
			    if(status.equals("C"))
			    	sb.setSub_status("A");
			    else
			    	sb.setSub_status("U");
				sb.insert();
			}
	    }
	    return ret;
    }

    public boolean saveStatusMoIP(String token)
    {
    	String sql = "UPDATE site_subscription_order SET ord_status = ?, ord_lastdatetime = SYSDATE()";
    	sql += " WHERE ord_token = ? AND ord_status <> 'C' LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, "C");
	       pstmt.setString(2, token);
	       ret = pstmt.executeUpdate() == 1;
		    if(ret)
		    {
		    	SubscriptionOrder od = new SubscriptionOrder().getSubscriptionOrderToken(token);
				if(od.getPrc_id() > 0)
				{
					Subscription sb = new Subscription();
					sb.setAcc_id(od.getAcc_id());
					sb.setOrd_id(od.getOrd_id());
					sb.setPrc_id(od.getPrc_id());
					sb.setSub_status("A");
					sb.insert();
				}
		    	Mailtogo mtg = new Mailtogo();
				mtg.setMtg_address(od.getOrd_email());
				mtg.setMtg_from(new Config().getConfig("EMAIL_FROM").getCfg_value());
				mtg.setMtg_replyto(new Config().getConfig("EMAIL_FROM").getCfg_value());
				mtg.setMtg_status("Q");
				mtg.setMtg_subject(new Block().parseBlock(new Config().getConfig("EMAIL_PAYMENT_SUBJECT").getCfg_value(),"br"));
				mtg.setMtg_text(new Block().parseBlock(new Config().getConfig("EMAIL_PAYMENT_BODY").getCfg_value(),"br"));
				mtg.insert();
				mtg.sendAll();
		    }
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

    public boolean saveStatusPayPal(String token, String lng)
    {
    	String sql = "UPDATE site_subscription_order SET ord_status = ?, ord_lastdatetime = SYSDATE()";
    	sql += " WHERE ord_token = ? AND ord_status <> 'C' LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    System.out.println(sql);
	    System.out.println(token);
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, "C");
	       pstmt.setString(2, token);
	       ret = pstmt.executeUpdate() == 1;
		    if(ret)
		    {
		    	SubscriptionOrder od = new SubscriptionOrder().getSubscriptionOrderToken(token);
				if(od.getPrc_id() > 0)
				{
					Subscription sb = new Subscription();
					sb.setAcc_id(od.getAcc_id());
					sb.setOrd_id(od.getOrd_id());
					sb.setPrc_id(od.getPrc_id());
					sb.setSub_status("A");
					sb.insert();
				}
		    	Mailtogo mtg = new Mailtogo();
				mtg.setMtg_address(od.getOrd_email());
				mtg.setMtg_from("Bukk.Me <" + new Config().getConfig("EMAIL_FROM").getCfg_value() + ">");
				mtg.setMtg_replyto(new Config().getConfig("EMAIL_FROM").getCfg_value());
				mtg.setMtg_status("Q");
				mtg.setMtg_subject(new Block().parseBlock(new Config().getConfig("EMAIL_PAYMENT_SUBJECT").getCfg_value(),lng));
				mtg.setMtg_text(new Block().parseBlock(new Config().getConfig("EMAIL_PAYMENT_BODY").getCfg_value(),lng));
				mtg.insert();
				mtg.sendAll();
		    }
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

    public boolean save(SubscriptionOrder order)
    {
    	String sql = "UPDATE site_subscription_order SET ord_email = ?, ord_status = ?, ord_token = ?, " +
    				 "ord_value = ?, ord_oldtoken = ?, " +
		"ord_lastdatetime = SYSDATE() WHERE ord_id = ? LIMIT 1";
		Connection con = null;
		boolean ret = false;
		try{
		   con = getConnection();
		   PreparedStatement pstmt = con.prepareStatement(sql);
		   pstmt.setString(1, order.getOrd_email());
		   pstmt.setString(2, order.getOrd_status());
		   pstmt.setString(3, order.getOrd_token());
		   pstmt.setInt(4, order.getOrd_value());
	       pstmt.setString(5, order.getOrd_oldtoken());
		   pstmt.setLong(6, order.getOrd_id());
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
}
