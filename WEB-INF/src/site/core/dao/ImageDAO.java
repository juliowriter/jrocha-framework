package site.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site.core.ConnectionPool;
import site.core.Image;

public class ImageDAO extends ConnectionPool {

		public Image getImage(ResultSet rs) throws SQLException
	    {
			Image ret = new Image();
	        ret.setImg_id(rs.getLong("img_id"));
	        ret.setImg_title(rs.getString("img_title"));
	        ret.setImg_file(rs.getString("img_file"));
	        ret.setImg_tag(rs.getString("img_tag"));
	        ret.setImg_language(rs.getString("img_language"));
	        ret.setImg_carousel_caption(rs.getString("img_carousel_caption"));
	        ret.setImg_carousel_description(rs.getString("img_carousel_description"));
	        ret.setImg_size(rs.getInt("img_size"));
		    return ret;
	    }

		public Image getImage(long id)
	    {
	    	String sql = "SELECT * FROM site_images WHERE img_id = ? LIMIT 1";
		    Connection con = null;
			Image ret = new Image();
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, id);
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		    	   ret = this.getImage(rs);
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

		public List<Image> getList(String order)
		{
			List<Image> ret = new ArrayList<Image>();
	    	String sql = "SELECT * FROM site_images ORDER BY " + order;
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(new ImageDAO().getImage(rs));
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

		public List<Image> getListByTag(String tag)
		{
			List<Image> ret = new ArrayList<Image>();
	    	String sql = "SELECT * FROM site_images WHERE img_tag = ? ORDER BY img_title";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, tag);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(new ImageDAO().getImage(rs));
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

		public List<Image> getListByTag(String tag, String lng)
		{
			List<Image> ret = new ArrayList<Image>();
	    	String sql = "SELECT * FROM site_images WHERE img_tag = ? AND img_language = ? ORDER BY img_title";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, tag);
		       pstmt.setString(2, lng);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(new ImageDAO().getImage(rs));
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

	    public long insert(Image item)
	    {
	    	String sql = "INSERT INTO site_images (img_title,img_file,img_size,img_tag,img_carousel_caption,img_carousel_description,img_language) VALUES " +
	    			"(?,?,?,?,?,?,?)";
		    Connection con = null;
		    long ret = 0;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getImg_title());
		       pstmt.setString(2, item.getImg_file());
		       pstmt.setInt(3, item.getImg_size());
		       pstmt.setString(4, item.getImg_tag());
		       pstmt.setString(5, item.getImg_carousel_caption());
		       pstmt.setString(6, item.getImg_carousel_description());
		       pstmt.setString(7, item.getImg_language());
		       pstmt.executeUpdate();
		       cleanUp(pstmt);
		       PreparedStatement pstmtS = con.prepareStatement("SELECT img_id FROM site_images ORDER BY img_id DESC LIMIT 1");
		       ResultSet rs = pstmtS.executeQuery();
		       if(rs.next())
		    	   ret = rs.getLong("img_id");
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

	    public boolean save(Image item)
	    {
	    	String sql = "UPDATE site_images SET img_title = ?, img_tag = ?, img_carousel_caption = ?, img_carousel_description = ?, img_language = ? WHERE img_id = ? LIMIT 1";
		    Connection con = null;
		    boolean ret = false;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getImg_title());
		       pstmt.setString(2, item.getImg_tag());
		       pstmt.setString(3, item.getImg_carousel_caption());
		       pstmt.setString(4, item.getImg_carousel_description());
		       pstmt.setString(5, item.getImg_language());
		       pstmt.setLong(6, item.getImg_id());
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

	    public boolean delete(Image item)
	    {
	    	String sql = "DELETE FROM site_images WHERE img_id = ? LIMIT 1";
		    Connection con = null;
		    boolean ret = false;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, item.getImg_id());
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
