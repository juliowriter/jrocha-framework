package site.drm.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site.core.ConnectionPool;
import site.drm.AccountSession;
import site.core.Config;
import site.core.Security;

public class AccountSessionDAO extends ConnectionPool {

	public AccountSession getAccountSession(long id)
    {
		AccountSession ret = null;
    	String sql = "SELECT * FROM site_drm_session WHERE ssn_id = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, id);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getAccountSession(rs);
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

	public AccountSession getAccountSession(String token)
    {
		AccountSession ret = null;
    	String sql = "SELECT * FROM site_drm_session WHERE ssn_token = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, token);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getAccountSession(rs);
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

	public AccountSession getAccountSession(ResultSet rs) throws SQLException
    {
         AccountSession ret = new AccountSession();
    	 ret.setSsn_id(rs.getLong("ssn_id"));
    	 ret.setCli_id(rs.getLong("cli_id"));
         ret.setSsn_datetime(rs.getString("ssn_datetime"));
         ret.setSsn_expire(rs.getString("ssn_expire"));
         ret.setSsn_token(rs.getString("ssn_token"));
         ret.setSsn_ip(rs.getString("ssn_ip"));
         ret.setShf_id(rs.getLong("shf_id"));
         ret.setSsn_logout(rs.getString("ssn_logout"));
         ret.setSsn_reason(rs.getInt("ssn_reason"));
	     return ret;
    }


	public int[] verifyAccess(long cliID, long linkID, String ip)
    {
		int[] ret = {0,0,0};
		//Numero de ips com sessoes validas para o mesmo usuario
    	String sqlIps = "SELECT COUNT(*) AS TOTAL FROM (SELECT DISTINCT ssn_ip FROM site_drm_session WHERE cli_id = ? AND ssn_expire >= SYSDATE() AND ssn_logout IS NULL) AS TEMP";

		//Numero de conexoes validas no mesmo ip para o mesmo usuario
    	String sqlCons = "SELECT COUNT(*) AS TOTAL FROM site_drm_session WHERE cli_id = ? AND ssn_ip = ? AND ssn_expire >= SYSDATE() AND ssn_logout IS NULL";

		//Numero total de views para o mesmo usuario e mesmo link no mesmo dia
    	String sqlViews = "SELECT COUNT(*) AS TOTAL FROM site_drm_session WHERE cli_id = ? AND DATE(ssn_datetime) = DATE(SYSDATE())";

    	Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sqlIps);
	       pstmt.setLong(1, cliID);
	       ResultSet rs = pstmt.executeQuery();
	       rs.next();
	       ret[0] = rs.getInt("TOTAL");

	       pstmt = con.prepareStatement(sqlCons);
	       pstmt.setLong(1, cliID);
	       pstmt.setString(2, ip);
	       rs = pstmt.executeQuery();
	       rs.next();
	       ret[1] = rs.getInt("TOTAL");

	       pstmt = con.prepareStatement(sqlViews);
	       pstmt.setLong(1, cliID);
	       rs = pstmt.executeQuery();
	       rs.next();
	       ret[2] = rs.getInt("TOTAL");

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

	public AccountSession lastValidShelf(long shelfid)
    {
		AccountSession ret = null;
    	String sql = "SELECT *, (ssn_expire < SYSDATE()) AS EXPIRA FROM site_drm_session WHERE shf_id = ? ORDER BY ssn_id DESC LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, shelfid);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	       {
	    	   if(!rs.getBoolean("EXPIRA") && rs.getString("ssn_logout") == null)
	    		   ret = this.getAccountSession(rs);
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

	public AccountSession lastValid(long cliID)
    {
		AccountSession ret = null;
    	String sql = "SELECT *, (ssn_expire < SYSDATE()) AS EXPIRA FROM site_drm_session WHERE cli_id = ? ORDER BY ssn_id DESC LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, cliID);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	       {
	    	   if(!rs.getBoolean("EXPIRA") && rs.getString("ssn_logout") == null)
	    		   ret = this.getAccountSession(rs);
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

	public AccountSession newSession(long shelfID, long cliID, int minutes)
    {
    	String sql = "INSERT INTO site_drm_session (shf_id, cli_id, ssn_datetime, ssn_expire, ssn_token) VALUES (?,?,SYSDATE(),DATE_ADD(SYSDATE(), INTERVAL " + minutes + " MINUTE),?)";
	    Connection con = null;
	    AccountSession ret = null;
	    try{
	       String token = new Security().getToken();
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, shelfID);
	       pstmt.setLong(2, cliID);
	       pstmt.setString(3, token);
	       pstmt.executeUpdate();
	       cleanUp(pstmt);
		   ret = this.lastValid(shelfID);
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      closeConnection(con);
	    }
	    return ret;
    }

	public AccountSession newSession(long cliID, long linkID, int minutes, String ip)
    {
    	String sql = "INSERT INTO site_drm_session (cli_id, shf_id, ssn_datetime, ssn_expire, ssn_token, ssn_ip) VALUES (?,?,SYSDATE(),DATE_ADD(SYSDATE(), INTERVAL " + minutes + " MINUTE),?,?)";
	    Connection con = null;
	    AccountSession ret = null;
	    try{
	       String token = new Security().getToken();
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, cliID);
	       pstmt.setLong(2, linkID);
	       pstmt.setString(3, token);
	       pstmt.setString(4, ip);
	       pstmt.executeUpdate();
	       cleanUp(pstmt);
		   ret = this.lastValid(cliID);
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      closeConnection(con);
	    }
	    return ret;
    }

	public boolean createLink(String origem, String link)
	{
    	   boolean ok = false;
	       Runtime rt=Runtime.getRuntime();
    	   String newLink = "ln -s " + origem + " " + link;
    	   System.out.println(newLink);
		   try {
			   rt.exec(newLink);
			   ok = true;
		   } catch (IOException e) {
			   e.printStackTrace();
		   }
		   System.out.println(ok);
		   return ok;
	}

	public List<AccountSession> getSessionsExpired()
	{
		List<AccountSession> ret = new ArrayList<AccountSession>();
    	String sql = "SELECT * FROM site_drm_session WHERE ssn_expire < SYSDATE() ORDER BY ssn_id";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       ResultSet rs = pstmt.executeQuery();
	       while(rs.next())
	         ret.add(this.getAccountSession(rs));
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

    public boolean isValid(long sessionid)
    {
    	String sql = "SELECT 1 FROM site_drm_session WHERE ssn_id = ? AND (ssn_expire > SYSDATE()) AND ssn_logout IS NULL LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, sessionid);
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

    public boolean keepAlive(long sessionid)
    {
    	String sql = "UPDATE site_drm_session SET ssn_expire = DATE_ADD(SYSDATE(), INTERVAL 30 MINUTE) " +
    			"WHERE ssn_id = ? LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, sessionid);
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

    public boolean endSession(AccountSession ssn)
    {
    	return this.endSession(ssn, 1);
    }

    public boolean endSession(AccountSession ssn, int reason)
    {
    	String sql = "UPDATE site_drm_session SET ssn_logout = SYSDATE(), ssn_reason = ? " +
    			"WHERE ssn_id = ? LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setInt(1, reason);
	       pstmt.setLong(2, ssn.getSsn_id());
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

    public boolean updateUser(AccountSession ssn)
    {
    	String sql = "UPDATE site_drm_session SET ssn_userid = ? " +
    			"WHERE ssn_id = ? LIMIT 1";
    	Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, ssn.getSsn_userid());
	       pstmt.setLong(2, ssn.getSsn_id());
	       ret = pstmt.executeUpdate() == 1;
	       cleanUp(pstmt);
	       this.terminateOthers(ssn);
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      closeConnection(con);
	    }
	    return ret;
    }

    public void terminateOthers(AccountSession ssn)
    {
    	Connection con = null;
	    try{
	        con = getConnection();
	        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM site_drm_session WHERE ssn_userid = ? AND " +
	        		"cli_id = ? AND ssn_reason = 0 AND ssn_id <> ? LIMIT 100");
            pstmt.setString(1, ssn.getSsn_userid());
            pstmt.setLong(2, ssn.getCli_id());
            pstmt.setLong(3, ssn.getSsn_id());
	        ResultSet rs = pstmt.executeQuery();
            String path = new Config().getConfig("VIDEOSPATH").getCfg_value();
            while(rs.next())
            {
                Runtime rt=Runtime.getRuntime();
	    		String rmLink = "unlink " + path + rs.getString("ssn_token");
                boolean ok = true;
	    		try {
	    			rt.exec(rmLink);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    			ok = false;
	    		}
	    		if(ok)
	    		{
	                PreparedStatement pstmtU = con.prepareStatement("UPDATE site_drm_session SET ssn_reason = 4 WHERE ssn_id = ? LIMIT 1");
	    			pstmtU.setLong(1, rs.getLong("ssn_id"));
	    			pstmtU.executeUpdate();
	    			pstmtU.close();
	    		}
            }
            rs.close();
            pstmt.close();
            con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
    }
}
