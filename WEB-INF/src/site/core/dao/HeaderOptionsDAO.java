package site.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site.core.ConnectionPool;
import site.core.HeaderOptions;

public class HeaderOptionsDAO extends ConnectionPool {

		public HeaderOptions getHeaderOptions(ResultSet rs) throws SQLException
	    {
			HeaderOptions ret = new HeaderOptions();
	        ret.setOpt_id(rs.getLong("opt_id"));
	        ret.setOpt_order(rs.getInt("opt_order"));
	        ret.setOpt_status(rs.getInt("opt_status"));
	        ret.setOpt_target(rs.getString("opt_target"));
	        ret.setOpt_title(rs.getString("opt_title"));
	        ret.setOpt_link(rs.getString("opt_link"));
	        ret.setOpt_language(rs.getString("opt_language"));
		    return ret;
	    }

		public HeaderOptions getHeaderOptions(long id)
	    {
	    	String sql = "SELECT * FROM site_header_options WHERE opt_id = ? LIMIT 1";
		    Connection con = null;
			HeaderOptions ret = new HeaderOptions();
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, id);
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		    	   ret = this.getHeaderOptions(rs);
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

	    public long insert(HeaderOptions item)
	    {
	    	String sql = "INSERT INTO site_header_options (opt_title,opt_link,opt_target,opt_order,opt_status,opt_language) VALUES " +
	    			"(?,?,?,?,?,?)";
		    Connection con = null;
		    long ret = 0;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getOpt_title());
		       pstmt.setString(2, item.getOpt_link());
		       pstmt.setString(3, item.getOpt_target());
		       pstmt.setInt(4, item.getOpt_order());
		       pstmt.setInt(5, item.getOpt_status());
		       pstmt.setString(6, item.getOpt_language());
		       pstmt.executeUpdate();
		       cleanUp(pstmt);
		       PreparedStatement pstmtS = con.prepareStatement("SELECT opt_id FROM site_header_options WHERE opt_title = ? ORDER BY opt_id DESC LIMIT 1");
		       pstmtS.setString(1, item.getOpt_title());
		       ResultSet rs = pstmtS.executeQuery();
		       if(rs.next())
		    	   ret = rs.getLong("opt_id");
		       cleanUp(pstmtS, rs);
		    } catch (SQLException e) {
		      e.printStackTrace();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      closeConnection(con);
		    }
		    return ret;
	    }

	    public boolean save(HeaderOptions item)
	    {
	    	String sql = "UPDATE site_header_options SET opt_title = ?,opt_link = ?,opt_target = ?,opt_order = ?,opt_status = ?,opt_language = ? WHERE opt_id = ? LIMIT 1";
		    Connection con = null;
		    boolean ret = false;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getOpt_title());
		       pstmt.setString(2, item.getOpt_link());
		       pstmt.setString(3, item.getOpt_target());
		       pstmt.setInt(4, item.getOpt_order());
		       pstmt.setInt(5, item.getOpt_status());
		       pstmt.setString(6, item.getOpt_language());
		       pstmt.setLong(7, item.getOpt_id());
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

	    public boolean delete(HeaderOptions item)
	    {
	    	String sql = "DELETE FROM site_header_options WHERE opt_id = ? LIMIT 1";
		    Connection con = null;
		    boolean ret = false;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, item.getOpt_id());
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

		public List<HeaderOptions> getList()
		{
			List<HeaderOptions> ret = new ArrayList<HeaderOptions>();
	    	String sql = "SELECT * FROM site_header_options WHERE opt_status = 1 ORDER BY opt_order";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(this.getHeaderOptions(rs));
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

		public List<HeaderOptions> getList(boolean withSession, String lng)
		{
			List<HeaderOptions> ret = new ArrayList<HeaderOptions>();
	    	String sql = "SELECT * FROM site_header_options WHERE opt_status = ? AND opt_language = ? ORDER BY opt_order";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setInt(1, (withSession?2:1));
		       pstmt.setString(2, lng.toLowerCase());
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(this.getHeaderOptions(rs));
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

		public List<HeaderOptions> getList(String order)
		{
			List<HeaderOptions> ret = new ArrayList<HeaderOptions>();
	    	String sql = "SELECT * FROM site_header_options ORDER BY " + order;
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(this.getHeaderOptions(rs));
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
