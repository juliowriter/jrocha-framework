package site.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import site.core.ConnectionPool;
import site.core.UpdateControl;


public class UpdateControlDAO extends ConnectionPool {

	public UpdateControl getUpdateControl()
    {
    	String sql = "SELECT * FROM site_updatecontrol LIMIT 1";
	    Connection con = null;
		UpdateControl ret = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	    	   ret = this.getUpdateControl(rs);
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

	public UpdateControl getUpdateControl(ResultSet rs) throws SQLException
    {
         UpdateControl ret = new UpdateControl();
	     ret.setUpd_running(rs.getInt("upd_running"));
         ret.setUpd_last(rs.getString("upd_last"));
	     return ret;
    }

    public void save(UpdateControl item)
    {
    	String sql = "UPDATE site_updatecontrol SET upd_running = ?, upd_last = SYSDATE() LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setInt(1, item.getUpd_running());
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

}
