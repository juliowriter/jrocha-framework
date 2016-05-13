package site.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import site.core.ConnectionPool;
import site.data.Optin;


public class OptinDAO extends ConnectionPool {

	public Optin getOptin(long id)
    {
		Optin ret = null;
    	String sql = "SELECT * FROM site_optin WHERE opt_id = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, id);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getOptin(rs);
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

	public Optin getOptin(String email)
    {
		Optin ret = null;
    	String sql = "SELECT * FROM site_optin WHERE opt_email = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, email);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
		         ret = this.getOptin(rs);
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

	public Optin getOptinToken(String token)
    {
		Optin ret = null;
    	String sql = "SELECT * FROM site_optin WHERE opt_token = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, token);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
		         ret = this.getOptin(rs);
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

	public Optin getOptin(ResultSet rs) throws SQLException
    {
         Optin ret = new Optin();
    	 ret.setOpt_id(rs.getLong("opt_id"));
         ret.setOpt_email(rs.getString("opt_email"));
         ret.setOpt_name(rs.getString("opt_name"));
         ret.setOpt_token(rs.getString("opt_token"));
         ret.setOpt_zipcode(rs.getString("opt_zipcode"));
         ret.setOpt_optinstamp(rs.getString("opt_optinstamp"));
         ret.setOpt_lists(rs.getString("opt_lists"));
	     return ret;
    }

    public boolean insert(Optin optin)
    {
    	String sql = "INSERT INTO site_optin (opt_name,opt_zipcode,opt_email," +
    			"opt_token,opt_lists) VALUES " +
    			"(?,?,?,?,?)";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, optin.getOpt_name());
	       pstmt.setString(2, optin.getOpt_zipcode());
	       pstmt.setString(3, optin.getOpt_email());
	       pstmt.setString(4, optin.getOpt_token());
	       pstmt.setString(5, optin.getOpt_lists());
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

    public boolean save(Optin optin)
    {
    	String sql = "UPDATE site_optin SET opt_optinstamp = ? WHERE opt_id = ? LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, optin.getOpt_optinstamp());
	       pstmt.setLong(2, optin.getOpt_id());
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
