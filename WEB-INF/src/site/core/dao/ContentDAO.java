package site.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site.core.ConnectionPool;
import site.core.Content;

public class ContentDAO extends ConnectionPool {

		public Content getContent(ResultSet rs) throws SQLException
	    {
			Content ret = new Content();
	        ret.setCts_id(rs.getLong("cts_id"));
	        ret.setCts_order(rs.getInt("cts_order"));
	        ret.setCts_published(rs.getInt("cts_published"));
	        ret.setCts_comments(rs.getInt("cts_comments"));
	        ret.setCts_tag(rs.getString("cts_tag"));
	        ret.setCts_text(rs.getString("cts_text"));
	        ret.setCts_title(rs.getString("cts_title"));
	        ret.setCts_parent(rs.getLong("cts_parent"));
	        ret.setCts_haschat(rs.getInt("cts_haschat"));
	        ret.setCts_session(rs.getInt("cts_session"));
		    return ret;
	    }

		public Content getContent(long id)
	    {
	    	String sql = "SELECT * FROM site_contents WHERE cts_id = ? LIMIT 1";
		    Connection con = null;
			Content ret = new Content();
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, id);
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		    	   ret = this.getContent(rs);
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

		public Content getContent(String tag)
	    {
	    	String sql = "SELECT * FROM site_contents WHERE cts_tag = ? AND cts_published = 1 AND cts_parent = 0 LIMIT 1";
		    Connection con = null;
			Content ret = new Content();
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, tag);
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		    	   ret = this.getContent(rs);
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

	    public long insert(Content item)
	    {
	    	String sql = "INSERT INTO site_contents (cts_tag,cts_title,cts_text,cts_order,cts_published," +
	    			"cts_comments,cts_parent,cts_haschat,cts_session) VALUES " +
	    			"(?,?,?,?,?,?,?,?,?)";
		    Connection con = null;
		    long ret = 0;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getCts_tag());
		       pstmt.setString(2, item.getCts_title());
		       pstmt.setString(3, item.getCts_text());
		       pstmt.setInt(4, item.getCts_order());
		       pstmt.setInt(5, item.getCts_published());
		       pstmt.setInt(6, item.getCts_comments());
		       pstmt.setLong(7, item.getCts_parent());
		       pstmt.setInt(8, item.getCts_haschat());
		       pstmt.setInt(9, item.getCts_session());
		       pstmt.executeUpdate();
		       cleanUp(pstmt);
		       PreparedStatement pstmtS = con.prepareStatement("SELECT cts_id FROM site_contents WHERE cts_tag = ? ORDER BY cts_id DESC LIMIT 1");
		       pstmtS.setString(1, item.getCts_tag());
		       ResultSet rs = pstmtS.executeQuery();
		       if(rs.next())
		    	   ret = rs.getLong("cts_id");
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

	    public void savePrevious(Content item, String user)
	    {
	    	String sql = "INSERT INTO site_contents_history (cts_tag,cts_title,cts_text,cts_order,cts_published," +
	    			"cts_comments,cts_parent,cts_haschat,cts_session,cts_user,cts_id,cts_datetime) VALUES " +
	    			"(?,?,?,?,?,?,?,?,?,?,?,SYSDATE())";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getCts_tag());
		       pstmt.setString(2, item.getCts_title());
		       pstmt.setString(3, item.getCts_text());
		       pstmt.setInt(4, item.getCts_order());
		       pstmt.setInt(5, item.getCts_published());
		       pstmt.setInt(6, item.getCts_comments());
		       pstmt.setLong(7, item.getCts_parent());
		       pstmt.setInt(8, item.getCts_haschat());
		       pstmt.setInt(9, item.getCts_session());
		       pstmt.setString(10, user);
		       pstmt.setLong(11, item.getCts_id());
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

	    public void restorePrevious(Content item, long hid)
	    {
	    	String sql = "SELECT * FROM site_contents_history WHERE cth_id = ? LIMIT 1";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, hid);
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		       {
		    	   Content ctd = this.getContent(rs);
		    	   ctd.save();
		       }
		       cleanUp(pstmt);
		    } catch (SQLException e) {
		      e.printStackTrace();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      closeConnection(con);
		    }
	    }

	    public boolean save(Content item)
	    {
	    	String sql = "UPDATE site_contents SET cts_tag = ?,cts_title = ?,cts_text = ?,cts_order = ?,cts_published = ?," +
	    			"cts_comments = ?, cts_parent = ?, cts_haschat = ?, cts_session = ? WHERE cts_id = ? LIMIT 1";
		    Connection con = null;
		    boolean ret = false;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getCts_tag());
		       pstmt.setString(2, item.getCts_title());
		       pstmt.setString(3, item.getCts_text());
		       pstmt.setInt(4, item.getCts_order());
		       pstmt.setInt(5, item.getCts_published());
		       pstmt.setInt(6, item.getCts_comments());
		       pstmt.setLong(7, item.getCts_parent());
		       pstmt.setInt(8, item.getCts_haschat());
		       pstmt.setInt(9, item.getCts_session());
		       pstmt.setLong(10, item.getCts_id());
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

	    public boolean delete(Content item)
	    {
	    	String sql = "DELETE FROM site_contents WHERE cts_id = ? LIMIT 1";
		    Connection con = null;
		    boolean ret = false;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, item.getCts_id());
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

	    public List<Content> getList(String order)
		{
			List<Content> ret = new ArrayList<Content>();
	    	String sql = "SELECT * FROM site_contents ORDER BY " + order;
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(new ContentDAO().getContent(rs));
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

		public List<Content> getList(String filtro, String order)
		{
			List<Content> ret = new ArrayList<Content>();
	    	String sql = "SELECT * FROM site_contents ";
	    	if(filtro!=null && !filtro.equals(""))
	    		sql += "WHERE cts_tag = '" + filtro + "' ";
	    	sql += "ORDER BY " + order;
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(new ContentDAO().getContent(rs));
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

		public List<Content> getContents(String tag)
		{
			List<Content> ret = new ArrayList<Content>();
	    	String sql = "SELECT * FROM site_contents WHERE cts_tag = ? AND cts_published = 1 AND cts_parent > 0 ORDER BY cts_order";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, tag);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(this.getContent(rs));
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

		public List<Content> getContents(long parent)
		{
			List<Content> ret = new ArrayList<Content>();
	    	String sql = "SELECT * FROM site_contents WHERE cts_parent = ? AND cts_published = 1 ORDER BY cts_order";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, parent);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(this.getContent(rs));
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

		public String[] getTags()
		{
			String[] ret = null;
	    	String sql = "SELECT DISTINCT cts_tag FROM site_contents ORDER BY cts_tag";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       ResultSet rs = pstmt.executeQuery();
		       String tags = "";
		       while(rs.next())
		         tags += rs.getString("cts_tag") + ",";
		       cleanUp(pstmt, rs);
		       if(tags.length() > 0)
		       {
		    	   tags = tags.substring(0,tags.length()-1);
		    	   ret = tags.split(",");
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

		public String[] getVersions(Content ctt)
		{
			String[] ret = null;
	    	String sql = "SELECT cth_id, cts_datetime, cts_user FROM site_contents_history WHERE cts_id = ? ORDER BY cts_datetime DESC";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, ctt.getCts_id());
		       ResultSet rs = pstmt.executeQuery();
		       String versions = "";
		       while(rs.next())
		         versions += rs.getString("cth_id") + ";" + rs.getString("cts_datetime") + ";" + rs.getString("cts_user") + ",";
		       cleanUp(pstmt, rs);
		       if(versions.length() > 0)
		       {
		    	   versions = versions.substring(0,versions.length()-1);
		    	   ret = versions.split(",");
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
}
