package site.drm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site.core.ConnectionPool;
import site.core.Security;
import site.drm.Link;

public class LinkDAO  extends ConnectionPool {

	public Link getLink(long id)
    {
		Link ret = null;
    	String sql = "SELECT * FROM site_drm_link WHERE lnk_id = ? LIMIT 1";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setLong(1, id);
	       ResultSet rs = pstmt.executeQuery();
	       if(rs.next())
	         ret = this.getLink(rs);
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

	public Link getLink(ResultSet rs) throws SQLException
    {
         Link ret = new Link();
         ret.setLnk_id(rs.getLong("lnk_id"));
         ret.setLnk_name(rs.getString("lnk_name"));
         ret.setLnk_file(rs.getString("lnk_file"));
         ret.setLnk_path(rs.getString("lnk_path"));
         ret.setLnk_pathplay(rs.getString("lnk_pathplay"));
         ret.setLnk_urlplay(rs.getString("lnk_urlplay"));
         ret.setLnk_status(rs.getInt("lnk_status"));
         ret.setLnk_group(rs.getInt("lnk_group"));
         return ret;
    }

    public String insert(Link item)
    {
    	String sql = "INSERT INTO site_drm_link (lnk_name,lnk_file,lnk_path,lnk_pathplay,lnk_urlplay,lnk_status,lnk_group) " +
    			"VALUES (?,?,?,?,?,?,?,?)";
	    Connection con = null;
	    String ret = new Security().getToken();
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, item.getLnk_name());
	       pstmt.setString(2, item.getLnk_file());
	       pstmt.setString(3, item.getLnk_path());
	       pstmt.setString(4, item.getLnk_pathplay());
	       pstmt.setString(5, item.getLnk_urlplay());
	       pstmt.setInt(6, item.getLnk_status());
	       pstmt.setInt(7, item.getLnk_group());
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

    public String save(Link item)
    {
    	String sql = "UPDATE site_drm_link SET lnk_name = ?,lnk_file = ?,lnk_path = ?,lnk_pathplay = ?,lnk_urlplay = ?,lnk_status = ?,lnk_group = ? WHERE lnk_id = ? LIMIT 1";
	    Connection con = null;
	    String ret = new Security().getToken();
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       pstmt.setString(1, item.getLnk_name());
	       pstmt.setString(2, item.getLnk_file());
	       pstmt.setString(3, item.getLnk_path());
	       pstmt.setString(4, item.getLnk_pathplay());
	       pstmt.setString(5, item.getLnk_urlplay());
	       pstmt.setInt(6, item.getLnk_status());
	       pstmt.setInt(7, item.getLnk_group());
	       pstmt.setLong(8, item.getLnk_id());
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

    public List<Link> getLinkList()
	{
		List<Link> ret = new ArrayList<Link>();
    	String sql = "SELECT * FROM site_drm_link ORDER BY lnk_id";
	    Connection con = null;
	    try{
	       con = getConnection();
	       PreparedStatement pstmt = con.prepareStatement(sql);
	       ResultSet rs = pstmt.executeQuery();
	       while(rs.next())
	         ret.add(this.getLink(rs));
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
