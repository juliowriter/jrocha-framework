package site.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import site.core.ConnectionPool;
import site.core.ActivityLog;

public class ActivityLogDAO extends ConnectionPool {

	public ActivityLog getActivityLog(long id)
    {
		ActivityLog ret = null;
    	String sql = "SELECT * FROM site_activitylog WHERE log_id = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, id);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getActivityLog(rs);
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

	public ActivityLog getActivityLog(ResultSet rs) throws SQLException
    {
         ActivityLog ret = new ActivityLog();
    	 ret.setLog_id(rs.getLong("log_id"));
    	 ret.setCli_id(rs.getLong("cli_id"));
         ret.setLog_datetime(rs.getString("log_datetime"));
         ret.setLog_description(rs.getString("log_description"));
         ret.setLog_function(rs.getString("log_function"));
         ret.setLog_ip(rs.getString("log_ip"));
         ret.setLog_type(rs.getString("log_type"));
         return ret;
    }

    public boolean insert(ActivityLog cadastro)
    {
    	String sql = "INSERT INTO site_activitylog (cli_id,log_description,log_function,log_ip,log_type,log_datetime) VALUES " +
    			"(?,?,?,?,?,SYSDATE())";
	    Connection con = null;
	    boolean ret = false;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, cadastro.getCli_id());
	       pstmt.setString(2, cadastro.getLog_description());
	       pstmt.setString(3, cadastro.getLog_function());
	       pstmt.setString(4, cadastro.getLog_ip());
	       pstmt.setString(5, cadastro.getLog_type());
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
