package site.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site.core.ConnectionPool;
import site.core.Config;


public class ConfigDAO extends ConnectionPool {

	public Config getConfig(long id)
    {
    	String sql = "SELECT * FROM site_config WHERE cfg_id = ? LIMIT 1";
	    Connection con = null;
		Config ret = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, id);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	    	   ret = this.getConfig(rs);
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

	public Config getConfig(String tag)
    {
		Config ret = null;
    	String sql = "SELECT * FROM site_config WHERE cfg_tag = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, tag);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getConfig(rs);
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

	public Config getConfig(ResultSet rs) throws SQLException
    {
         Config ret = new Config();
	     ret.setCfg_id(rs.getLong("cfg_id"));
         ret.setCfg_tag(rs.getString("cfg_tag"));
         ret.setCfg_value(rs.getString("cfg_value"));
	     return ret;
    }

    public long insert(Config item)
    {
    	String sql = "INSERT INTO site_config (cfg_tag,cfg_value) VALUES (?,?)";
	    Connection con = null;
	    long ret = 0;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, item.getCfg_tag());
	       pstmt.setString(2, item.getCfg_value());
	       if(pstmt.executeUpdate() == 1)
	       {
		       sql = "SELECT cfg_id FROM site_config WHERE cfg_tag = ? ORDER BY cfg_id DESC LIMIT 1";
		       pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getCfg_tag());
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		    	   ret = rs.getLong("cfg_id");
		       cleanUp(rs);
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

    public void save(Config item)
    {
    	String sql = "UPDATE site_config SET cfg_tag = ?, cfg_value = ? WHERE cfg_id = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, item.getCfg_tag());
	       pstmt.setString(2, item.getCfg_value());
	       pstmt.setLong(3, item.getCfg_id());
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

    public boolean delete(Config item)
    {
    	String sql = "DELETE FROM site_config WHERE cfg_id = ? LIMIT 1";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, item.getCfg_id());
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

	public List<Config> getList(String order)
	{
		List<Config> ret = new ArrayList<Config>();
    	String sql = "SELECT * FROM site_config ORDER BY " + order;
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       ResultSet rs = pstmt.executeQuery();
	       while(rs.next())
	         ret.add(this.getConfig(rs));
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
