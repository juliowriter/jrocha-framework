package site.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import site.core.Config;
import site.core.ConnectionPool;
import site.core.Content;
import site.core.Util;
import site.data.Order;
import site.data.OrderItem;
import site.data.Product;
import site.mail.Mailtogo;

public class OrderDAO extends ConnectionPool {

	public Order getOrder(long id)
    {
		Order ret = null;
    	String sql = "SELECT * FROM site_order WHERE ord_id = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, id);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getOrder(rs);
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

	public Order getOrder(String email)
    {
		Order ret = null;
    	String sql = "SELECT * FROM site_order WHERE ord_email = ? ORDER BY ord_id DESC LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, email);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getOrder(rs);
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
    	String sql = "SELECT COUNT(*) AS TOTAL FROM site_order WHERE ord_status = ?";
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

	public Order getOrderToken(String token)
    {
		Order ret = null;
    	String sql = "SELECT * FROM site_order WHERE ord_token = ? ORDER BY ord_id DESC LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, token);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next()){
	    	   ret = new OrderDAO().getOrder(rs);
	       } else {
			   sql = "SELECT * FROM site_order WHERE ord_oldtoken LIKE '%" + token + ",%' LIMIT 1";
		       pstmt = con.prepareStatement(sql);
			   rs = pstmt.executeQuery();
			   if(rs.next())
				   ret = new OrderDAO().getOrder(rs);
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

	public Order getOrder(ResultSet rs) throws SQLException
    {
		 Order ret = new Order();
    	 ret.setOrd_id(rs.getLong("ord_id"));
         ret.setOrd_email(rs.getString("ord_email"));
         ret.setOrd_email2(rs.getString("ord_email2"));
         ret.setOrd_firstname(rs.getString("ord_firstname"));
         ret.setOrd_lastname(rs.getString("ord_lastname"));
         ret.setOrd_startdate(rs.getString("ord_startdate"));
         ret.setOrd_lastdate(rs.getString("ord_lastdate"));
         ret.setOrd_token(rs.getString("ord_token"));
         ret.setOrd_status(rs.getString("ord_status"));
         ret.setOrd_phone(rs.getString("ord_phone"));
         ret.setOrd_celphone(rs.getString("ord_celphone"));
         ret.setOrd_address(rs.getString("ord_address"));
         ret.setOrd_number(rs.getString("ord_number"));
         ret.setOrd_complement(rs.getString("ord_complement"));
         ret.setOrd_neighborhood(rs.getString("ord_neighborhood"));
         ret.setOrd_city(rs.getString("ord_city"));
         ret.setOrd_state(rs.getString("ord_state"));
         ret.setOrd_zipcode(rs.getString("ord_zipcode"));
         ret.setOrd_discount(rs.getString("ord_discount"));
         ret.setCou_id(rs.getInt("cou_id"));
         ret.setOrd_value(rs.getInt("ord_value"));
         ret.setOrd_mailcode(rs.getString("ord_mailcode"));
         ret.setOrd_oldtoken(rs.getString("ord_oldtoken"));
	     return ret;
    }

	public OrderItem getOrderItem(long id)
    {
		OrderItem ret = null;
    	String sql = "SELECT * FROM site_order_item WHERE oit_id = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, id);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getOrderItem(rs);
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

	public OrderItem getOrderItem(ResultSet rs) throws SQLException
    {
		 OrderItem ret = new OrderItem();
    	 ret.setOrd_id(rs.getLong("ord_id"));
    	 ret.setOit_id(rs.getLong("oit_id"));
         ret.setOit_qty(rs.getInt("oit_qty"));
         ret.setOit_value(rs.getInt("oit_value"));
         ret.setPrd_id(rs.getLong("prd_id"));
	     return ret;
    }

	public boolean exists(String email)
    {
		boolean ret = false;
    	String sql = "SELECT 1 FROM site_order WHERE ord_email = ?  AND ord_status != 'X' LIMIT 1";
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
    	String sql = "SELECT COUNT(*) AS TOTAL FROM site_order";
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

    public long getUniqueOrders(String status, String item)
    {
		long ret = 0;
    	String sql = "SELECT COUNT(*) AS TOTAL FROM site_order WHERE ord_status <> 'X'";
    	if(status!=null && item==null)
    		sql += " AND ord_status = ? GROUP BY ord_status";
    	else if(status!=null && item!=null)
    		sql += " AND ord_status = ? AND ord_item = ? GROUP BY ord_status + ord_item";
    	else if(item!=null)
    		sql += " AND ord_item = ? GROUP BY ord_item";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       if(status!=null && item==null)
	       {
	    		pstmt.setString(1, status);
	       } else if(status!=null && item!=null)
	       {
	    		pstmt.setString(1, status);
	    		pstmt.setString(2, item);
	       } else if(item!=null) {
	    		pstmt.setString(1, item);
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

    public List<Order> getList(long arg0, long arg1, String arg2, String arg3, String arg4, String arg5, String arg6){
        List<Order> coll = new ArrayList<Order>();
        String sql = "SELECT * FROM site_order ";
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
           while(rs.next()) coll.add( new OrderDAO().getOrder(rs) );
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

    public List<Order> getLabels(String status){
        List<Order> coll = new ArrayList<Order>();
        String sql = "SELECT * FROM site_order WHERE ord_status = ? ORDER BY ord_firstname, ord_lastname";
        Connection con = null;
        try{
           con = getConnection();
           PreparedStatement pstmt = con.prepareStatement(sql);
           pstmt.setString(1, status);
           ResultSet rs = pstmt.executeQuery();
           while(rs.next()) coll.add( new OrderDAO().getOrder(rs) );
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

    public List<OrderItem> getItemList(long id){
        List<OrderItem> coll = new ArrayList<OrderItem>();
        String sql = "SELECT * FROM site_order_item WHERE ord_id = ? ORDER BY oit_id";
        Connection con = null;
        try{
           con = getConnection();
           PreparedStatement pstmt = con.prepareStatement(sql);
           pstmt.setLong(1, id);
           ResultSet rs = pstmt.executeQuery();
           while(rs.next()) coll.add( new OrderDAO().getOrderItem(rs) );
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

    public int getItem_qty(long ord_id, long prd_id){
        int ret = 0;
        String sql = "SELECT oit_qty FROM site_order_item WHERE ord_id = ? AND prd_id = ? LIMIT 1";
        Connection con = null;
        try{
           con = getConnection();
           PreparedStatement pstmt = con.prepareStatement(sql);
           pstmt.setLong(1, ord_id);
           pstmt.setLong(2, prd_id);
           ResultSet rs = pstmt.executeQuery();
           if(rs.next()) ret = rs.getInt("oit_qty");
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

    public long insert(Order order)
    {
    	String sql = "INSERT INTO site_order (ord_firstname,ord_lastname," +
    			"ord_zipcode,ord_email,ord_email2,ord_status,ord_token,ord_phone,ord_celphone,ord_address," +
    			"ord_number,ord_complement,ord_city,ord_state,ord_discount,ord_value,ord_neighborhood,ord_startdate) VALUES " +
    			"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE())";
	    Connection con = null;
	    long ret = 0;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, order.getOrd_firstname());
	       pstmt.setString(2, order.getOrd_lastname());
	       pstmt.setString(3, order.getOrd_zipcode()!=null?order.getOrd_zipcode():"00000000");
	       pstmt.setString(4, order.getOrd_email());
	       pstmt.setString(5, order.getOrd_email2());
	       pstmt.setString(6, order.getOrd_status());
	       pstmt.setString(7, order.getOrd_token());
	       pstmt.setString(8, order.getOrd_phone());
	       pstmt.setString(9, order.getOrd_celphone());
	       pstmt.setString(10, order.getOrd_address());
	       pstmt.setString(11, order.getOrd_number());
	       pstmt.setString(12, order.getOrd_complement());
	       pstmt.setString(13, order.getOrd_city());
	       pstmt.setString(14, order.getOrd_state());
	       pstmt.setString(15, order.getOrd_discount());
	       pstmt.setInt(16, order.getOrd_value());
	       pstmt.setString(17, order.getOrd_neighborhood());
	       pstmt.executeUpdate();
	       cleanUp(pstmt);
	       sql = "SELECT ord_id FROM site_order WHERE ord_email = ? ORDER BY ord_id DESC LIMIT 1";
	       pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, order.getOrd_email());
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

    public boolean insertItens(String itens, long oid)
    {
    	String sql = "INSERT INTO site_order_item (ord_id, prd_id, oit_qty, oit_value) VALUES (?,?,?,?)";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       String[] its = itens.split(",");
	       for(int xx=0;xx<its.length;xx++)
	       {
	    	   String[] item = its[xx].split(":");
		       pstmt.setLong(1, oid);
		       pstmt.setLong(2, Long.parseLong(item[0]));
		       pstmt.setInt(3, Integer.parseInt(item[1]));
		       pstmt.setInt(4, Integer.parseInt(item[2]));
		       ret = pstmt.executeUpdate() == 1;
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

    public void clearItens(long id)
    {
    	String sql = "DELETE FROM site_order_item WHERE ord_id = ?";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, id);
	       pstmt.executeUpdate();
	       cleanUp(pstmt);
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      closeConnection(con);
	    }
    }

    public boolean saveStatus(String status, long id, String mailcode)
    {
    	String sql = "UPDATE site_order SET ord_status = ?, ord_lastdate = SYSDATE()";
    	if(mailcode!=null && !mailcode.equals(""))
    		sql += ", ord_mailcode = '" + mailcode + "'";
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
	    if(status.equals("C"))
	    {
	    	Order od = new Order().getOrder(id);
			Mailtogo mtg = new Mailtogo();
			mtg.setMtg_address(od.getOrd_email());
			mtg.setMtg_from(new Config().getConfig("EMAIL_FROM").getCfg_value());
			mtg.setMtg_replyto(new Config().getConfig("EMAIL_FROM").getCfg_value());
			mtg.setMtg_status("Q");
		    Content ct = new Content().getContent("ORDER_PAYMENT_EMAIL_SUBJECT");
			mtg.setMtg_subject(ct.getCts_text());
			ct = new Content().getContent("ORDER_PAYMENT_EMAIL_BODY");
			mtg.setMtg_text(ct.getCts_text());
			mtg.insert();
			mtg.sendAll();
		} else if(status.equals("L"))
		{
	    	Order od = new Order().getOrder(id);
			Mailtogo mtg = new Mailtogo();
			mtg.setMtg_address(od.getOrd_email());
			mtg.setMtg_from(new Config().getConfig("EMAIL_FROM").getCfg_value());
			mtg.setMtg_replyto(new Config().getConfig("EMAIL_FROM").getCfg_value());
			mtg.setMtg_status("Q");
		    Content ct = new Content().getContent("ORDER_PAYMENT_EMAIL_REMINDER_SUBJECT");
			mtg.setMtg_subject(ct.getCts_text());
			ct = new Content().getContent("ORDER_PAYMENT_EMAIL_REMINDER_BODY");

			String order_data = "";
			Iterator<OrderItem> it = od.getItemList().iterator();
			while(it.hasNext())
			{
				OrderItem oi = it.next();
				Product prd = new Product().getProduct(oi.getPrd_id());
				order_data += prd.getPrd_title() + "(" + prd.getPrd_description() + ")" + " x " + oi.getOit_qty() +
							" x R$ " + (new Util().formatMoney(oi.getOit_value())) + " = R$ " + (new Util().formatMoney(oi.getOit_value() * oi.getOit_qty())) + "<br>";
			}
			order_data += "Valor Total (com frete): R$ " + (new Util().formatMoney(od.getOrd_value()));
			mtg.setMtg_text(ct.getCts_text().replace("_order_data_", order_data).replace("_ord_token_", od.getOrd_token() + ""));
			mtg.insert();
			mtg.sendAll();
		} else if(status.equals("I"))
		{
	    	Order od = new Order().getOrder(id);
			Mailtogo mtg = new Mailtogo();
			mtg.setMtg_address(od.getOrd_email());
			mtg.setMtg_from(new Config().getConfig("EMAIL_FROM").getCfg_value());
			mtg.setMtg_replyto(new Config().getConfig("EMAIL_FROM").getCfg_value());
			mtg.setMtg_status("Q");
		    Content ct = new Content().getContent("ORDER_EMAIL_RESCUE_SUBJECT");
			mtg.setMtg_subject(ct.getCts_text());
			ct = new Content().getContent("ORDER_EMAIL_RESCUE_BODY");
			mtg.setMtg_text(ct.getCts_text());
			mtg.insert();
			mtg.sendAll();
		} else if(status.equals("H") && mailcode!=null && !mailcode.equals(""))
		{
	    	Order od = new Order().getOrder(id);
			Mailtogo mtg = new Mailtogo();
			mtg.setMtg_address(od.getOrd_email());
			mtg.setMtg_from(new Config().getConfig("EMAIL_FROM").getCfg_value());
			mtg.setMtg_replyto(new Config().getConfig("EMAIL_FROM").getCfg_value());
			mtg.setMtg_status("Q");
		    Content ct = new Content().getContent("ORDER_DELIVERY_EMAIL_SUBJECT");
			mtg.setMtg_subject(ct.getCts_text());
			ct = new Content().getContent("ORDER_DELIVERY_EMAIL_BODY");
			mtg.setMtg_text(ct.getCts_text().replace("_code_",mailcode));
			mtg.insert();
			mtg.sendAll();
	    }
	    return ret;
    }

    public boolean saveStatusMoIP(String token)
    {
    	String sql = "UPDATE site_order SET ord_status = ?, ord_lastdate = SYSDATE()";
    	sql += " WHERE ord_token = ? LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, "C");
	       pstmt.setString(2, token);
	       ret = pstmt.executeUpdate() == 1;
	       cleanUp(pstmt);
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      closeConnection(con);
	    }
	    if(ret)
	    {
	    	Order od = new Order().getOrderToken(token);
			Mailtogo mtg = new Mailtogo();
			mtg.setMtg_address(od.getOrd_email());
			mtg.setMtg_from(new Config().getConfig("EMAIL_FROM").getCfg_value());
			mtg.setMtg_replyto(new Config().getConfig("EMAIL_FROM").getCfg_value());
			mtg.setMtg_status("Q");
		    Content ct = new Content().getContent("ORDER_PAYMENT_EMAIL_SUBJECT");
			mtg.setMtg_subject(ct.getCts_text());
			ct = new Content().getContent("ORDER_PAYMENT_EMAIL_BODY");
			mtg.setMtg_text(ct.getCts_text());
			mtg.insert();
			mtg.sendAll();
	    }
		return ret;
    }

    public boolean save(Order order)
    {
    	String sql = "UPDATE site_order SET ord_firstname = ?, ord_lastname = ?, " +
		"ord_zipcode = ?, ord_email = ?, ord_email2 = ?, ord_status = ?, ord_token = ?, " +
		"ord_phone = ?, ord_celphone = ?, ord_address = ?, " +
		"ord_number = ?, ord_complement = ?, ord_city = ?, ord_state = ?, " +
		"ord_discount = ?, ord_value = ?, ord_neighborhood = ?, ord_oldtoken = ?, " +
		"ord_startdate = SYSDATE() WHERE ord_id = ? LIMIT 1";
		Connection con = null;
		boolean ret = false;
		try{
		   con = getConnection();
		   PreparedStatement pstmt = con.prepareStatement(sql);
		   pstmt.setString(1, order.getOrd_firstname());
		   pstmt.setString(2, order.getOrd_lastname());
		   pstmt.setString(3, order.getOrd_zipcode());
		   pstmt.setString(4, order.getOrd_email());
		   pstmt.setString(5, order.getOrd_email2());
		   pstmt.setString(6, order.getOrd_status());
		   pstmt.setString(7, order.getOrd_token());
		   pstmt.setString(8, order.getOrd_phone());
		   pstmt.setString(9, order.getOrd_celphone());
		   pstmt.setString(10, order.getOrd_address());
		   pstmt.setString(11, order.getOrd_number());
		   pstmt.setString(12, order.getOrd_complement());
		   pstmt.setString(13, order.getOrd_city());
		   pstmt.setString(14, order.getOrd_state());
		   pstmt.setString(15, order.getOrd_discount());
		   pstmt.setInt(16, order.getOrd_value());
	       pstmt.setString(17, order.getOrd_neighborhood());
	       pstmt.setString(18, order.getOrd_oldtoken());
		   pstmt.setLong(19, order.getOrd_id());
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

    public String[] search(String name)
    {
		String ret[] = null;
    	String sql = "SELECT p.ord_id, p.ord_firstname, p.ord_lastname FROM site_order p WHERE p.ord_firstname = ? OR p.ord_lastname = ? OR p.ord_email = ? ORDER BY p.ord_firstname";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, name);
	       pstmt.setString(2, name);
	       pstmt.setString(3, name);
	       ResultSet rs = pstmt.executeQuery();
	       String ctts = "";
	       while(rs.next())
	         ctts += (rs.getString("ord_id") + ";" + rs.getString("ord_firstname") + ";" + rs.getString("ord_lastname") + ":");
	       cleanUp(pstmt, rs);
	       if(ctts.length() > 1)
	       {
	    	   ctts = ctts.substring(0, ctts.length()-1);
	    	   ret = ctts.split(":");
	       }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      closeConnection(con);
	    }
	    return ret;
    }

    public int getDiscount(String code)
    {
    	int ret = 0;
    	String sql = "SELECT dis_value FROM site_discount WHERE dis_code = ? AND dis_type = 1 AND dis_expire >= SYSDATE() LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, code);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = rs.getInt("dis_value");
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
