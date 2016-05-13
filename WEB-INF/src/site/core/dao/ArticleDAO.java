package site.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import site.core.ConnectionPool;
import site.core.Article;

public class ArticleDAO extends ConnectionPool {

		public Article getArticle(ResultSet rs) throws SQLException
	    {
			Article ret = new Article();
	        ret.setArt_id(rs.getLong("art_id"));
	        ret.setArt_order(rs.getInt("art_order"));
	        ret.setArt_published(rs.getInt("art_published"));
	        ret.setArt_comments(rs.getInt("art_comments"));
	        ret.setArt_tag(rs.getString("art_tag"));
	        ret.setArt_text(rs.getString("art_text"));
	        ret.setArt_resume(rs.getString("art_resume"));
	        ret.setArt_title(rs.getString("art_title"));
	        ret.setCts_id(rs.getLong("cts_id"));
	        ret.setArt_haschat(rs.getInt("art_haschat"));
	        ret.setArt_session(rs.getInt("art_session"));
	        ret.setEdt_id(rs.getLong("edt_id"));
		    return ret;
	    }

		public Article getArticle(long id)
	    {
	    	String sql = "SELECT * FROM site_articles WHERE art_id = ? LIMIT 1";
		    Connection con = null;
			Article ret = new Article();
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, id);
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		    	   ret = this.getArticle(rs);
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

		public Article getArticle(String tag)
	    {
	    	String sql = "SELECT * FROM site_articles WHERE art_tag = ? AND art_published = 1 ORDER BY art_id DESC LIMIT 1";
		    Connection con = null;
			Article ret = new Article();
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, tag);
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		    	   ret = this.getArticle(rs);
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

	    public long insert(Article item)
	    {
	    	String sql = "INSERT INTO site_articles (art_tag,art_title,art_text,art_order,art_published," +
	    			"art_comments,cts_id,art_haschat,art_session,edt_id,art_resume) VALUES " +
	    			"(?,?,?,?,?,?,?,?,?,?,?)";
		    Connection con = null;
		    long ret = 0;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getArt_tag());
		       pstmt.setString(2, item.getArt_title());
		       pstmt.setString(3, item.getArt_text());
		       pstmt.setInt(4, item.getArt_order());
		       pstmt.setInt(5, item.getArt_published());
		       pstmt.setInt(6, item.getArt_comments());
		       pstmt.setLong(7, item.getCts_id());
		       pstmt.setInt(8, item.getArt_haschat());
		       pstmt.setInt(9, item.getArt_session());
		       pstmt.setLong(10, item.getEdt_id());
		       pstmt.setString(11, item.getArt_resume());
		       pstmt.executeUpdate();
		       cleanUp(pstmt);
		       PreparedStatement pstmtS = con.prepareStatement("SELECT art_id FROM site_articles WHERE art_tag = ? ORDER BY art_id DESC LIMIT 1");
		       pstmtS.setString(1, item.getArt_tag());
		       ResultSet rs = pstmtS.executeQuery();
		       if(rs.next())
		    	   ret = rs.getLong("art_id");
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

	    public void savePrevious(Article item, String user)
	    {
	    	String sql = "INSERT INTO site_articles_history (art_tag,art_title,art_text,art_order,art_published," +
	    			"art_comments,cts_id,art_haschat,art_session,art_user,art_id,edt_id,art_resume,art_datetime) VALUES " +
	    			"(?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE())";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getArt_tag());
		       pstmt.setString(2, item.getArt_title());
		       pstmt.setString(3, item.getArt_text());
		       pstmt.setInt(4, item.getArt_order());
		       pstmt.setInt(5, item.getArt_published());
		       pstmt.setInt(6, item.getArt_comments());
		       pstmt.setLong(7, item.getCts_id());
		       pstmt.setInt(8, item.getArt_haschat());
		       pstmt.setInt(9, item.getArt_session());
		       pstmt.setString(10, user);
		       pstmt.setLong(11, item.getArt_id());
		       pstmt.setLong(12, item.getEdt_id());
		       pstmt.setString(13, item.getArt_resume());
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

	    public void restorePrevious(Article item, long hid)
	    {
	    	String sql = "SELECT * FROM site_articles_history WHERE cth_id = ? LIMIT 1";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, hid);
		       ResultSet rs = pstmt.executeQuery();
		       if(rs.next())
		       {
		    	   Article ctd = this.getArticle(rs);
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

	    public boolean save(Article item)
	    {
	    	String sql = "UPDATE site_articles SET art_tag = ?,art_title = ?,art_text = ?,art_order = ?,art_published = ?," +
	    			"art_comments = ?, cts_id = ?, art_haschat = ?, art_session = ?, edt_id = ?, art_resume = ? WHERE art_id = ? LIMIT 1";
		    Connection con = null;
		    boolean ret = false;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, item.getArt_tag());
		       pstmt.setString(2, item.getArt_title());
		       pstmt.setString(3, item.getArt_text());
		       pstmt.setInt(4, item.getArt_order());
		       pstmt.setInt(5, item.getArt_published());
		       pstmt.setInt(6, item.getArt_comments());
		       pstmt.setLong(7, item.getCts_id());
		       pstmt.setInt(8, item.getArt_haschat());
		       pstmt.setInt(9, item.getArt_session());
		       pstmt.setLong(10, item.getEdt_id());
		       pstmt.setString(11, item.getArt_resume());
		       pstmt.setLong(12, item.getArt_id());
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

	    public boolean delete(Article item)
	    {
	    	String sql = "DELETE FROM site_articles WHERE art_id = ? LIMIT 1";
		    Connection con = null;
		    boolean ret = false;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, item.getArt_id());
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

	    public List<Article> getList(String order)
		{
			List<Article> ret = new ArrayList<Article>();
	    	String sql = "SELECT * FROM site_articles ORDER BY " + order;
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(new ArticleDAO().getArticle(rs));
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

		public List<Article> getList(String filtro, String order)
		{
			List<Article> ret = new ArrayList<Article>();
	    	String sql = "SELECT * FROM site_articles ";
	    	if(filtro!=null && !filtro.equals(""))
	    		sql += "WHERE " + filtro + " ";
	    	sql += "ORDER BY " + order;
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(new ArticleDAO().getArticle(rs));
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

		public List<Article> getArticles(String tag)
		{
			List<Article> ret = new ArrayList<Article>();
	    	String sql = "SELECT * FROM site_articles WHERE art_tag = ? AND art_published = 1 AND cts_id > 0 ORDER BY art_order";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setString(1, tag);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(this.getArticle(rs));
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

		public List<Article> getArticles(long editoria)
		{
			List<Article> ret = new ArrayList<Article>();
	    	String sql = "SELECT * FROM site_articles WHERE edt_id = ? AND art_published = 1 ORDER BY art_order";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, editoria);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(this.getArticle(rs));
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

		public List<Article> getArticles(long parent, long editoria)
		{
			List<Article> ret = new ArrayList<Article>();
	    	String sql = "SELECT * FROM site_articles WHERE cts_id = ? AND edt_id = ? AND art_published = 1 ORDER BY art_order";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, parent);
		       pstmt.setLong(2, editoria);
		       ResultSet rs = pstmt.executeQuery();
		       while(rs.next())
		         ret.add(this.getArticle(rs));
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
	    	String sql = "SELECT DISTINCT art_tag FROM site_articles ORDER BY art_tag";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       ResultSet rs = pstmt.executeQuery();
		       String tags = "";
		       while(rs.next())
		         tags += rs.getString("art_tag") + ",";
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

		public String[] getVersions(Article ctt)
		{
			String[] ret = null;
	    	String sql = "SELECT cth_id, art_datetime, art_user FROM site_articles_history WHERE art_id = ? ORDER BY art_datetime DESC";
		    Connection con = null;
		    try{
		       con = getConnection();
		       PreparedStatement pstmt = con.prepareStatement(sql);
		       pstmt.setLong(1, ctt.getArt_id());
		       ResultSet rs = pstmt.executeQuery();
		       String versions = "";
		       while(rs.next())
		         versions += rs.getString("cth_id") + ";" + rs.getString("art_datetime") + ";" + rs.getString("art_user") + ",";
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
