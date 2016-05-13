package site.account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import site.core.ConnectionPool;
import site.core.Encription;
import site.core.Security;
import site.subscription.Subscription;
import site.account.Account;


public class AccountDAO extends ConnectionPool {

	public Account getAccount(long id)
    {
		Account ret = null;
    	String sql = "SELECT * FROM site_profile WHERE prf_id = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, id);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getAccount(rs);
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

	public Account getAccount(String email)
    {
		Account ret = null;
    	String sql = "SELECT * FROM site_profile WHERE prf_email = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, email);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getAccount(rs);
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

	public Account getAccountToken(String token)
    {
		Account ret = null;
    	String sql = "SELECT * FROM site_profile WHERE prf_token = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, token);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getAccount(rs);
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

	public Account getAccount(ResultSet rs) throws SQLException
    {
         Account ret = new Account();
    	 ret.setPrf_id(rs.getLong("prf_id"));
         ret.setPrf_email(rs.getString("prf_email"));
         ret.setPrf_token(rs.getString("prf_token"));
         ret.setPrf_zipcode(rs.getString("prf_zipcode"));
         ret.setPrf_address(rs.getString("prf_address"));
         ret.setPrf_number(rs.getString("prf_number"));
         ret.setPrf_complement(rs.getString("prf_complement"));
         ret.setPrf_neighborhood(rs.getString("prf_neighborhood"));
         ret.setPrf_city(rs.getString("prf_city"));
         ret.setPrf_state(rs.getString("prf_state"));
         ret.setPrf_phone(rs.getString("prf_phone"));
         ret.setPrf_datetime(rs.getString("prf_datetime"));
         ret.setPrf_lastname(rs.getString("prf_lastname"));
         ret.setPrf_firstname(rs.getString("prf_firstname"));
         ret.setPrf_password(new Encription().decode(rs.getString("prf_password")));
         ret.setPrf_status(rs.getString("prf_status"));
         ret.setPrf_birth_date(rs.getString("prf_birth_date"));
         ret.setPrf_gender(rs.getString("prf_gender"));
         ret.setPrf_nickname(rs.getString("prf_nickname"));
    	 ret.setCou_id(rs.getLong("cou_id"));
    	 ret.setPrf_level(rs.getInt("prf_level"));
    	 ret.setPrf_language(rs.getString("prf_language"));
    	 ret.setPrf_country(rs.getString("prf_country"));
    	 ret.setPrf_company(rs.getString("prf_company"));
    	 ret.setPrf_website(rs.getString("prf_website"));
    	 ret.setPrf_credit(rs.getLong("prf_credit"));
    	 ret.setPrf_billingday(rs.getString("prf_billingday"));
    	 ret.setPrf_wizard(rs.getInt("prf_wizard"));
	     return ret;
    }

    public boolean insert(Account item)
    {
    	String sql = "INSERT INTO site_profile (prf_email,prf_token,prf_zipcode,prf_lastname,prf_firstname," +
    			"prf_password,prf_birth_date,prf_gender,prf_nickname,cou_id,prf_language,prf_ip,prf_country," +
    			"prf_status,prf_address,prf_number,prf_complement,prf_neighborhood,prf_city,prf_state,prf_phone," +
    			"prf_company,prf_website,prf_level,prf_wizard,prf_datetime) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE())";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, item.getPrf_email());
	       pstmt.setString(2, item.getPrf_token());
	       pstmt.setString(3, item.getPrf_zipcode());
	       pstmt.setString(4, item.getPrf_lastname());
	       pstmt.setString(5, item.getPrf_firstname());
	       pstmt.setString(6, new Encription().encode(item.getPrf_password()));
	       pstmt.setString(7, item.getPrf_birth_date());
	       pstmt.setString(8, item.getPrf_gender());
	       pstmt.setString(9, item.getPrf_nickname());
	       pstmt.setLong(10, item.getCou_id());
	       pstmt.setString(11, item.getPrf_language());
	       pstmt.setString(12, item.getPrf_ip());
	       pstmt.setString(13, item.getPrf_country());
	       pstmt.setString(14, item.getPrf_status());
	       pstmt.setString(15, item.getPrf_address());
	       pstmt.setString(16, item.getPrf_number());
	       pstmt.setString(17, item.getPrf_complement());
	       pstmt.setString(18, item.getPrf_neighborhood());
	       pstmt.setString(19, item.getPrf_city());
	       pstmt.setString(20, item.getPrf_state());
	       pstmt.setString(21, item.getPrf_phone());
	       pstmt.setString(22, item.getPrf_company());
	       pstmt.setString(23, item.getPrf_website());
	       pstmt.setInt(24, item.getPrf_level());
	       pstmt.setInt(25, item.getPrf_wizard());
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

    public boolean save(Account item)
    {
    	String sql = "UPDATE site_profile SET prf_email = ?, prf_zipcode = ?," +
    			"prf_lastname = ?, prf_firstname = ?, prf_password = ?, prf_birth_date = ?, prf_gender = ?," +
    			"prf_nickname = ?, cou_id = ?, prf_language = ?, prf_address = ?, prf_number = ?, prf_complement = ?," +
    			"prf_neighborhood = ?, prf_city = ?, prf_state = ?, prf_phone = ? , prf_company = ? , prf_website = ?, prf_wizard = ? " +
    			"WHERE prf_id = ? LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, item.getPrf_email());
	       pstmt.setString(2, item.getPrf_zipcode());
	       pstmt.setString(3, item.getPrf_lastname());
	       pstmt.setString(4, item.getPrf_firstname());
	       pstmt.setString(5, new Encription().encode(item.getPrf_password()));
	       pstmt.setString(6, item.getPrf_birth_date());
	       pstmt.setString(7, item.getPrf_gender());
	       pstmt.setString(8, item.getPrf_nickname());
	       pstmt.setLong(9, item.getCou_id());
	       pstmt.setString(10, item.getPrf_language());
	       pstmt.setString(11, item.getPrf_address());
	       pstmt.setString(12, item.getPrf_number());
	       pstmt.setString(13, item.getPrf_complement());
	       pstmt.setString(14, item.getPrf_neighborhood());
	       pstmt.setString(15, item.getPrf_city());
	       pstmt.setString(16, item.getPrf_state());
	       pstmt.setString(17, item.getPrf_phone());
	       pstmt.setString(18, item.getPrf_company());
	       pstmt.setString(19, item.getPrf_website());
	       pstmt.setInt(20, item.getPrf_wizard());
	       pstmt.setLong(21, item.getPrf_id());
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

    public boolean setBillingDay(Account item)
    {
    	String sql = "UPDATE site_profile SET prf_billingday = LPAD(IF(DAYOFMONTH(SYSDATE()) = 31,30,DAYOFMONTH(SYSDATE())),2,'0') WHERE prf_id = ? LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, item.getPrf_id());
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

    public boolean linkAccount(Account item, long id)
    {
    	String sql = "UPDATE site_profile SET cou_id = ? WHERE prf_id = ? LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, item.getPrf_id());
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
	    return ret;
    }

    public boolean confirm(long id, String stamp)
    {
    	String sql = "UPDATE site_profile SET prf_confirm = ?, prf_status = 'A' WHERE prf_id = ? LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, stamp);
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
	    return ret;
    }

    public boolean hasOrderToday(long id)
    {
    	String sql = "SELECT 1 FROM site_subscription_order WHERE acc_id = ? AND DATE(ord_lastdatetime) = DATE(SYSDATE()) LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, id);
	       ResultSet rs = pstmt.executeQuery();
	       ret = rs.next();
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

    public boolean unlink(long id)
    {
    	String sql = "UPDATE site_profile SET cou_id = 0 WHERE prf_id = ? LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, id);
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

    public int getMyCredits(long id)
    {
    	String sql = "SELECT prf_credit FROM site_profile WHERE prf_id = ? LIMIT 1";
	    Connection con = null;
	    int ret = 0;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, id);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	    	   ret = rs.getInt("prf_credit");
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

    public int getTotalToday()
    {
    	String sql = "SELECT COUNT(*) AS TOTAL FROM site_profile WHERE prf_datetime >= DATE(SYSDATE())";
	    Connection con = null;
	    int ret = 0;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	    	   ret = rs.getInt("TOTAL");
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

    public int getTotal()
    {
    	String sql = "SELECT COUNT(*) AS TOTAL FROM site_profile";
	    Connection con = null;
	    int ret = 0;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	    	   ret = rs.getInt("TOTAL");
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

    public String resetPasswordToken(String email)
    {
    	String sql = "INSERT INTO site_resetpassword (rst_token, rst_email, rst_datetime) VALUES (?,?,SYSDATE())";
	    Connection con = null;
	    String ret = new Security().getToken();
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, ret);
	       pstmt.setString(2, email);
	       pstmt.executeUpdate();
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

    public void removeToken(String token)
    {
    	String sql = "DELETE FROM site_resetpassword WHERE rst_token = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, token);
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

    public String validateToken(String token)
    {
    	String sql = "SELECT rst_email FROM site_resetpassword WHERE rst_token = ? AND rst_datetime <= DATE_ADD(SYSDATE(), INTERVAL 1 DAY) LIMIT 1";
	    Connection con = null;
	    String ret = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, token);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	    	   ret = rs.getString("rst_email");
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

    public boolean drawCredits(long id, int credits)
    {
    	String sql = "UPDATE site_profile SET prf_credit = (prf_credit - ?) WHERE prf_id = ? LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setInt(1, credits);
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
	    return ret;
    }

    public boolean putCredits(long id, int credits)
    {
    	String sql = "UPDATE site_profile SET prf_credit = (prf_credit + ?) WHERE prf_id = ? LIMIT 1";
    	System.out.println("put: " + credits);
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setInt(1, credits);
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
	    return ret;
    }

    public Account login(String email, String senha)
    {
		Account ret = null;
    	String sql = "SELECT * FROM site_profile WHERE (prf_email = ? OR prf_nickname = ?) AND prf_password = ? AND prf_status = 'A' LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, email);
	       pstmt.setString(2, email);
	       pstmt.setString(3, new Encription().encode(senha));
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getAccount(rs);
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

	public Account login(String token)
    {
		Account ret = null;
		Date now = new Date();
		SimpleDateFormat dateformated = new SimpleDateFormat("yyyyMMdd");
		String dataToken = new Encription().decode(token).substring(0,8);
		String dataToday = dateformated.format(now);
    	String login = new Encription().decode(token).substring(8);
		String sql = "SELECT * FROM site_profile WHERE prf_nickname = ? AND prf_status = 'A' LIMIT 1";
	    Connection con = null;
	    try{
	    	if(dataToken.equals(dataToday))
	    	{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, login);
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		         ret = this.getAccount(rs);
		       cleanUp(pstmt, rs);
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

	public boolean find(String email)
    {
		boolean ret = false;
    	String sql = "SELECT * FROM site_profile WHERE prf_email = ? LIMIT 1";
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

	public boolean nickExists(String nick)
    {
		boolean ret = false;
    	String sql = "SELECT * FROM site_profile WHERE prf_nickname = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, nick);
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

	public List<Account> getAccounts()
	{
		List<Account> ret = new ArrayList<Account>();
    	String sql = "SELECT * FROM site_profile ORDER BY prf_id DESC";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       ResultSet rs = pstmt.executeQuery();
	       while(rs.next())
	         ret.add(this.getAccount(rs));
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

	public List<Account> getAccounts(long parentID)
	{
		List<Account> ret = new ArrayList<Account>();
    	String sql = "SELECT * FROM site_profile WHERE cou_id = ? ORDER BY prf_company LIMIT 1000";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, parentID);
	       ResultSet rs = pstmt.executeQuery();
	       while(rs.next())
	         ret.add(this.getAccount(rs));
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
	
	public List<Account> getList(String filtro, String order, String plan)
	{
		List<Account> ret = new ArrayList<Account>();
    	String sql = "SELECT * FROM site_profile";
    	if(filtro!=null && !filtro.equals(""))
    		sql += " WHERE prf_firstname LIKE '" + filtro + "%' ";
    	sql += " ORDER BY " + order;
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       ResultSet rs = pstmt.executeQuery();
	       while(rs.next())
	       {	   
	         boolean showAcc = false;
	    	 if(plan!=null && !plan.equals(""))
	         {	 
	           Subscription sub = new Subscription().getSubscriptionByAccount(rs.getLong("prf_id")); 	
	           if((sub.getPrc_id() == 1 && plan.equals("FREE")) || 
                  (sub.getPrc_id() == 2 && plan.equals("BASIC")) ||
                  (sub.getPrc_id() == 3 && plan.equals("ADVANCED")) ||
	              (sub.getPrc_id() == 4 && plan.equals("PREMIUM")) ||
                  (sub.getPrc_id() == 5 && plan.equals("AGENCY")))
	        	   showAcc = true;
	         } else {
	        	 showAcc = true;
	         }
	    	 if(showAcc)
	    		 ret.add(new AccountDAO().getAccount(rs));
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

	public boolean hasSubscription(long id)
	{
			boolean ret = false;
	    	String sql = "SELECT 1 FROM site_subscription WHERE acc_id = ? AND (sub_status = 'A' OR sub_status = 'U') AND sub_enddate >= SYSDATE() ORDER BY sub_enddate DESC LIMIT 1";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, id);
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

}
